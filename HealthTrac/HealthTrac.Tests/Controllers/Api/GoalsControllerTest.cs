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
    public class GoalsControllerTest
    {
        private Goal _sampleGoal1, _sampleGoal2;
        private Goal[] _manyGoals;

        [TestInitialize]
        public void Initialize()
        {
            _sampleGoal1 = GoalGenerator.GenerateGoal1();
            _sampleGoal2 = GoalGenerator.GenerateGoal2();
            _manyGoals = GoalGenerator.GenerateManyGoals();
        }

        [TestMethod]
        public void ApiGetGoals()
        {
            var acc = Mock.Of<IGoalService>(a => a.GetGoals() == _manyGoals);
            var uow = Mock.Of<IUnitOfWork>(u => u.GoalService == acc);
            GoalsController controller = new GoalsController(uow);
            var goals = controller.GetGoals();
            Assert.IsTrue(goals.EqualValues(_manyGoals));
        }

        [TestMethod]
        public void ApiGetGoalById()
        {
            long id = 7;
            var acc = Mock.Of<IGoalService>(a => a.GetGoal(id) == _sampleGoal1);
            var uow = Mock.Of<IUnitOfWork>(u => u.GoalService == acc);
            GoalsController controller = new GoalsController(uow);
            var response = controller.GetGoal(id);
            var result = response as OkNegotiatedContentResult<GoalDto>;
            var goal = result.Content;
            Assert.IsTrue(goal.EqualValues(_sampleGoal1));
        }

        [TestMethod]
        public void ApiPostGoal()
        {
            var goal = _sampleGoal1;
            var mock = new Mock<IGoalService>();
            mock.Setup(acc => acc.CreateGoal(goal, null))
                .Returns(goal);
            var uowMock = new Mock<IUnitOfWork>();
            uowMock.Setup(u => u.GoalService)
                .Returns(mock.Object);
            var con = new GoalsController(uowMock.Object);
            con.User = new ClaimsPrincipal(
  new GenericPrincipal(new GenericIdentity(""), null));
            var response = con.PostGoal(goal);
            var result = response as CreatedAtRouteNegotiatedContentResult<GoalDto>;
            var resultGoal = result.Content;
            Assert.IsTrue(resultGoal.EqualValues(goal));
            mock.Verify(acc => acc.CreateGoal(goal, null));
            uowMock.Verify(u => u.Save());
        }
    }
}
