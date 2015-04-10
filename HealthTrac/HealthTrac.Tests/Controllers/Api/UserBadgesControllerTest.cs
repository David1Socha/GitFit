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
using HealthTrac.Services;
using System.Security.Claims;
using System.Security.Principal;

namespace HealthTrac.Tests.Controllers.Api
{
    [TestClass]
    public class UserBadgesControllerTest
    {
        private UserBadge _sampleUserBadge1, _sampleUserBadge2;
        private UserBadge[] _manyUserBadges;

        [TestInitialize]
        public void Initialize()
        {
            _sampleUserBadge1 = UserBadgeGenerator.GenerateUserBadge1();
            _sampleUserBadge2 = UserBadgeGenerator.GenerateUserBadge2();
            _manyUserBadges = UserBadgeGenerator.GenerateManyUserBadges();
        }

        [TestMethod]
        public void ApiGetUserBadges()
        {
            var acc = Mock.Of<IUserBadgeService>(a => a.GetUserBadges() == _manyUserBadges);
            var uow = Mock.Of<IUnitOfWork>(u => u.UserBadgeService == acc);
            UserBadgesController controller = new UserBadgesController(uow);
            var userBadges = controller.GetUserBadges();
            Assert.IsTrue(userBadges.EqualValues(_manyUserBadges));
        }

        [TestMethod]
        public void ApiGetUserBadgesByUser()
        {
            var sampleUserId = "xyz";
            var acc = Mock.Of<IUserBadgeService>(a => a.GetUserBadges(sampleUserId) == _manyUserBadges);
            var uow = Mock.Of<IUnitOfWork>(u => u.UserBadgeService == acc);
            UserBadgesController controller = new UserBadgesController(uow);
            var userBadges = controller.GetUserBadges(sampleUserId);
            Assert.IsTrue(userBadges.EqualValues(_manyUserBadges));
        }

        [TestMethod]
        public void ApiGetUserBadgesByBadge()
        {
            var sampleBadgeId = 10;
            var acc = Mock.Of<IUserBadgeService>(a => a.GetUserBadges(sampleBadgeId) == _manyUserBadges);
            var uow = Mock.Of<IUnitOfWork>(u => u.UserBadgeService == acc);
            UserBadgesController controller = new UserBadgesController(uow);
            var userBadges = controller.GetUserBadges(sampleBadgeId);
            Assert.IsTrue(userBadges.EqualValues(_manyUserBadges));
        }

        [TestMethod]
        public void ApiGetUserBadgeById()
        {
            long id = 7;
            var acc = Mock.Of<IUserBadgeService>(a => a.GetUserBadge(id) == _sampleUserBadge1);
            var uow = Mock.Of<IUnitOfWork>(u => u.UserBadgeService == acc);
            UserBadgesController controller = new UserBadgesController(uow);
            var response = controller.GetUserBadge(id);
            var result = response as OkNegotiatedContentResult<UserBadgeDto>;
            var userBadge = result.Content;
            Assert.IsTrue(userBadge.EqualValues(_sampleUserBadge1));
        }

        [TestMethod]
        public void ApiGetUserBadgeByBadgeIdAndUserId()
        {
            var userId = "apples";
            var badgeId = 7;
            var acc = Mock.Of<IUserBadgeService>(a => a.GetUserBadge(badgeId, userId) == _sampleUserBadge1);
            var uow = Mock.Of<IUnitOfWork>(u => u.UserBadgeService == acc);
            UserBadgesController controller = new UserBadgesController(uow);
            var response = controller.GetUserBadge(userId, badgeId);
            var result = response as OkNegotiatedContentResult<UserBadgeDto>;
            var userBadge = result.Content;
            Assert.IsTrue(userBadge.EqualValues(_sampleUserBadge1));
        }

        [TestMethod]
        public void ApiPostUserBadge()
        {
            var userBadge = _sampleUserBadge1;
            var mock = new Mock<IUserBadgeService>();
            mock.Setup(acc => acc.CreateUserBadge(userBadge))
                .Returns(userBadge);
            var uowMock = new Mock<IUnitOfWork>();
            uowMock.Setup(u => u.UserBadgeService)
                .Returns(mock.Object);
            var con = new UserBadgesController(uowMock.Object);
            con.User = new ClaimsPrincipal(
  new GenericPrincipal(new GenericIdentity(""), null));
            var response = con.PostUserBadge(userBadge);
            var result = response as CreatedAtRouteNegotiatedContentResult<UserBadgeDto>;
            var resultUserBadge = result.Content;
            Assert.IsTrue(resultUserBadge.EqualValues(userBadge));
            mock.Verify(acc => acc.CreateUserBadge(userBadge));
            uowMock.Verify(u => u.Save());
        }
    }
}
