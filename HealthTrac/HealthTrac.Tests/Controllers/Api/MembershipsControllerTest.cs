﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Web.Mvc;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using HealthTrac;
using System.Web.Http.Results;
using HealthTrac.Controllers;
using HealthTrac.DataAccess;
using HealthTrac.Models;
using HealthTrac.Models.Dto;
using HealthTrac.Controllers.Api;
using Moq;
using System.Net.Http;
using Moq.Linq;
using HealthTrac.Tests.Helpers;

namespace HealthTrac.Tests.Controllers.Api
{
    [TestClass]
    public class MembershipsControllerTest
    {
        private Membership _memberMembership, _adminMembership, _bannedMembership;
        private Membership[] _activeMemberships;

        [TestInitialize]
        public void Initialize()
        {
            _memberMembership = MembershipGenerator.GenerateMemberMembership();
            _adminMembership = MembershipGenerator.GenerateAdminMembership();
            _bannedMembership = MembershipGenerator.GenerateBannedMembership();
            _activeMemberships = MembershipGenerator.GenerateActiveMemberships();
        }

        [TestMethod]
        public void MembershipsControllerGetMemberships()
        {
            var acc = Mock.Of<IMembershipAccessor>(a => a.GetMemberships() == _activeMemberships);
            var controller = new MembershipsController(acc);
            var memberships = controller.GetMemberships();
            Assert.IsTrue(memberships.EqualValues(_activeMemberships));
        }

        [TestMethod]
        public void MembershipsControllerGetMembershipsByUser()
        {
            var userId = "abc";
            var acc = Mock.Of<IMembershipAccessor>(a => a.GetMemberships(userId) == _activeMemberships);
            var con = new MembershipsController(acc);
            var memberships = con.GetMemberships(userId);
            Assert.IsTrue(memberships.EqualValues(_activeMemberships));
        }

        [TestMethod]
        public void MembershipsControllerGetMembershipsByTeam()
        {
            long teamId = 14;
            var membership = _memberMembership;
            var userMemberships = new Membership[] { membership };
            var acc = Mock.Of<IMembershipAccessor>(a => a.GetMemberships(teamId) == userMemberships);
            var con = new MembershipsController(acc);
            var memberships = con.GetMemberships(teamId);
            Assert.IsTrue(memberships.EqualValues(userMemberships));
        }

        [TestMethod]
        public void MembershipsControllerGetMembership()
        {
            long id = 1;
            var membership = _adminMembership;
            var acc = Mock.Of<IMembershipAccessor>(a => a.GetMembership(id) == membership);
            var con = new MembershipsController(acc);
            var response = con.GetMembership(id);
            var result = response as OkNegotiatedContentResult<MembershipDto>;
            var resultMembership = result.Content;
            Assert.IsTrue(resultMembership.EqualValues(membership));
        }

        [TestMethod]
        public void MembershipsControllerGetMembershipByTeamUser()
        {
            long teamId = 14;
            string userId = "abc";
            var membership = _memberMembership;
            var acc = Mock.Of<IMembershipAccessor>(a => a.GetMembership(teamId, userId) == membership);
            var con = new MembershipsController(acc);
            var response = con.GetMembership(userId, teamId);
            var result = response as OkNegotiatedContentResult<MembershipDto>;
            var resultMembership = result.Content;
            Assert.IsTrue(resultMembership.EqualValues(membership));
        }

        [TestMethod]
        public void MembershipsControllerPutMembership()
        {
            long id = 3;
            var membership = _bannedMembership;
            var mock = new Mock<IMembershipAccessor>();
            mock.Setup(acc => acc.UpdateMembership(membership))
                .Returns(membership);
            var con = new MembershipsController(mock.Object);
            con.PutMembership(id, membership);
            mock.Verify(acc => acc.UpdateMembership(membership));
        }

        [TestMethod]
        public void MembershipsControllerPostMembership()
        {
            var membership = _memberMembership;
            var mock = new Mock<IMembershipAccessor>();
            mock.Setup(acc => acc.CreateMembership(membership))
                .Returns(membership);
            var con = new MembershipsController(mock.Object);
            con.PostMembership(membership);
            mock.Verify(acc => acc.CreateMembership(membership));
        }

    }
}