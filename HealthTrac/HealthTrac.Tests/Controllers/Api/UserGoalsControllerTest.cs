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
    public class UserGoalsControllerTest
    {
        private UserGoal _sampleUserGoal1, _sampleUserGoal2;
        private UserGoal[] _manyUserGoals;

        [TestInitialize]
        public void Initialize()
        {
            _sampleUserGoal1 = UserGoalGenerator.GenerateUserGoal1();
            _sampleUserGoal2 = UserGoalGenerator.GenerateUserGoal2();
            _manyUserGoals = UserGoalGenerator.GenerateManyUserGoals();
        }

        [TestMethod]
        public void ApiGetUserGoals()
        {
            var acc = Mock.Of<IUserGoalService>(a => a.GetUserGoals() == _manyUserGoals);
            var uow = Mock.Of<IUnitOfWork>(u => u.UserGoalService == acc);
            UserGoalsController controller = new UserGoalsController(uow);
            var userGoals = controller.GetUserGoals();
            Assert.IsTrue(userGoals.EqualValues(_manyUserGoals));
        }

        [TestMethod]
        public void ApiGetUserGoalsByUser()
        {
            var sampleUserId = "kick ass";
            var acc = Mock.Of<IUserGoalService>(a => a.GetUserGoals(sampleUserId) == _manyUserGoals);
            var uow = Mock.Of<IUnitOfWork>(u => u.UserGoalService == acc);
            UserGoalsController controller = new UserGoalsController(uow);
            var userGoals = controller.GetUserGoals(sampleUserId);
            Assert.IsTrue(userGoals.EqualValues(_manyUserGoals));
        }

        [TestMethod]
        public void ApiGetUserGoalsByGoal()
        {
            var sampleGoalId = 4;
            var acc = Mock.Of<IUserGoalService>(a => a.GetUserGoals(sampleGoalId) == _manyUserGoals);
            var uow = Mock.Of<IUnitOfWork>(u => u.UserGoalService == acc);
            UserGoalsController controller = new UserGoalsController(uow);
            var userGoals = controller.GetUserGoals(sampleGoalId);
            Assert.IsTrue(userGoals.EqualValues(_manyUserGoals));
        }

        [TestMethod]
        public void ApiGetUserGoalById()
        {
            long id = 4;
            var acc = Mock.Of<IUserGoalService>(a => a.GetUserGoal(id) == _sampleUserGoal1);
            var uow = Mock.Of<IUnitOfWork>(u => u.UserGoalService == acc);
            UserGoalsController controller = new UserGoalsController(uow);
            var response = controller.GetUserGoal(id);
            var result = response as OkNegotiatedContentResult<UserGoalDto>;
            var userGoal = result.Content;
            Assert.IsTrue(userGoal.EqualValues(_sampleUserGoal1));
        }

        [TestMethod]
        public void ApiGetUserGoalByGoalIdAndUserId()
        {
            var userId = "kick ass";
            var goalId = 4;
            var acc = Mock.Of<IUserGoalService>(a => a.GetUserGoal(goalId, userId) == _sampleUserGoal1);
            var uow = Mock.Of<IUnitOfWork>(u => u.UserGoalService == acc);
            UserGoalsController controller = new UserGoalsController(uow);
            var response = controller.GetUserGoal(userId, goalId);
            var result = response as OkNegotiatedContentResult<UserGoalDto>;
            var userGoal = result.Content;
            Assert.IsTrue(userGoal.EqualValues(_sampleUserGoal1));
        }

        [TestMethod]
        public void ApiPostUserGoal()
        {
            var userGoal = _sampleUserGoal1;
            var mock = new Mock<IUserGoalService>();
            mock.Setup(acc => acc.CreateUserGoal(userGoal))
                .Returns(userGoal);
            var uowMock = new Mock<IUnitOfWork>();
            uowMock.Setup(u => u.UserGoalService)
                .Returns(mock.Object);
            var con = new UserGoalsController(uowMock.Object);
            con.User = new ClaimsPrincipal(
  new GenericPrincipal(new GenericIdentity(""), null));
            var response = con.PostUserGoal(userGoal);
            var result = response as CreatedAtRouteNegotiatedContentResult<UserGoalDto>;
            var resultUserGoal = result.Content;
            Assert.IsTrue(resultUserGoal.EqualValues(userGoal));
            mock.Verify(acc => acc.CreateUserGoal(userGoal));
            uowMock.Verify(u => u.Save());
        }

        [TestMethod]
        public void ApiPutUserGoals()
        {
            long id = 8;
            var userGoal = _sampleUserGoal2;
            var mock = new Mock<IUserGoalService>();
            mock.Setup(a => a.UpdateUserGoal(userGoal))
                .Returns(userGoal);
            var sv = mock.Object;
            var uowMock = new Mock<IUnitOfWork>();
            uowMock.Setup(u => u.UserGoalService)
                .Returns(sv);
            var con = new UserGoalsController(uowMock.Object);
            con.PutUserGoal(id, userGoal);
            mock.Verify(a => a.UpdateUserGoal(userGoal));
        }
    }
}
