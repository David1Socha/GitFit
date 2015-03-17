using System;
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
        public void ApiGetMemberships()
        {
            var acc = Mock.Of<IMembershipAccessor>(a => a.GetMemberships() == _activeMemberships);
            var uow = Mock.Of<IUnitOfWork>(u => u.MembershipAccessor == acc);
            var controller = new MembershipsController(uow);
            var memberships = controller.GetMemberships();
            Assert.IsTrue(memberships.EqualValues(_activeMemberships));
        }

        [TestMethod]
        public void ApiGetMembershipsByUser()
        {
            var userId = "abc";
            var acc = Mock.Of<IMembershipAccessor>(a => a.GetMemberships(userId) == _activeMemberships);
            var uow = Mock.Of<IUnitOfWork>(u => u.MembershipAccessor == acc);
            var controller = new MembershipsController(uow);
            var memberships = controller.GetMemberships(userId);
            Assert.IsTrue(memberships.EqualValues(_activeMemberships));
        }

        [TestMethod]
        public void ApiGetMembershipsByTeam()
        {
            long teamId = 14;
            var membership = _memberMembership;
            var userMemberships = new Membership[] { membership };
            var acc = Mock.Of<IMembershipAccessor>(a => a.GetMemberships(teamId) == userMemberships);
            var uow = Mock.Of<IUnitOfWork>(u => u.MembershipAccessor == acc);
            var controller = new MembershipsController(uow);
            var memberships = controller.GetMemberships(teamId);
            Assert.IsTrue(memberships.EqualValues(userMemberships));
        }

        [TestMethod]
        public void ApiGetMembership()
        {
            long id = 1;
            var membership = _adminMembership;
            var acc = Mock.Of<IMembershipAccessor>(a => a.GetMembership(id) == membership);
            var uow = Mock.Of<IUnitOfWork>(u => u.MembershipAccessor == acc);
            var controller = new MembershipsController(uow);
            var response = controller.GetMembership(id);
            var result = response as OkNegotiatedContentResult<MembershipDto>;
            var resultMembership = result.Content;
            Assert.IsTrue(resultMembership.EqualValues(membership));
        }

        [TestMethod]
        public void ApiGetMembershipByTeamUser()
        {
            long teamId = 14;
            string userId = "abc";
            var membership = _memberMembership;
            var acc = Mock.Of<IMembershipAccessor>(a => a.GetMembership(teamId, userId) == membership);
            var uow = Mock.Of<IUnitOfWork>(u => u.MembershipAccessor == acc);
            var controller = new MembershipsController(uow);
            var response = controller.GetMembership(userId, teamId);
            var result = response as OkNegotiatedContentResult<MembershipDto>;
            var resultMembership = result.Content;
            Assert.IsTrue(resultMembership.EqualValues(membership));
        }

        [TestMethod]
        public void ApiPutMembership()
        {
            long id = 3;
            var membership = _bannedMembership;
            var mock = new Mock<IMembershipAccessor>();
            mock.Setup(acc => acc.UpdateMembership(membership))
                .Returns(membership);
            var uowMock = new Mock<IUnitOfWork>();
            uowMock.Setup(u => u.MembershipAccessor)
                .Returns(mock.Object);
            var con = new MembershipsController(uowMock.Object);
            con.PutMembership(id, membership);
            mock.Verify(acc => acc.UpdateMembership(membership));
            uowMock.Verify(u => u.Save());
        }

        [TestMethod]
        public void ApiPostMembership()
        {
            var membership = _memberMembership;
            var mock = new Mock<IMembershipAccessor>();
            mock.Setup(acc => acc.CreateMembership(membership))
                .Returns(membership);
            var uowMock = new Mock<IUnitOfWork>();
            uowMock.Setup(u => u.MembershipAccessor)
                .Returns(mock.Object);
            var con = new MembershipsController(uowMock.Object);
            con.PostMembership(membership);
            mock.Verify(acc => acc.CreateMembership(membership));
            uowMock.Verify(u => u.Save());
        }

    }
}
