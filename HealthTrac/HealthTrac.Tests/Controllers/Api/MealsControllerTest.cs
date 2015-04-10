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
    public class MealsControllerTest
    {
        private Meal _sampleMeal1, _sampleMeal2;
        private Meal[] _manyMeals;

        [TestInitialize]
        public void Initialize()
        {
            _sampleMeal1 = MealGenerator.GenerateMeal1();
            _sampleMeal2 = MealGenerator.GenerateMeal2();
            _manyMeals = MealGenerator.GenerateManyMeals();
        }

        [TestMethod]
        public void ApiGetMeals()
        {
            var acc = Mock.Of<IMealService>(a => a.GetMeals() == _manyMeals);
            var uow = Mock.Of<IUnitOfWork>(u => u.MealService == acc);
            MealsController controller = new MealsController(uow);
            var meals = controller.GetMeals();
            Assert.IsTrue(meals.EqualValues(_manyMeals));
        }

        [TestMethod]
        public void ApiGetMealsByUser()
        {
            var sampleUserId = "bananas";
            var acc = Mock.Of<IMealService>(a => a.GetMeals(sampleUserId) == _manyMeals);
            var uow = Mock.Of<IUnitOfWork>(u => u.MealService == acc);
            MealsController controller = new MealsController(uow);
            var meals = controller.GetMeals(sampleUserId);
            Assert.IsTrue(meals.EqualValues(_manyMeals));
        }

        [TestMethod]
        public void ApiGetMealById()
        {
            long id = 7;
            var acc = Mock.Of<IMealService>(a => a.GetMeal(id) == _sampleMeal1);
            var uow = Mock.Of<IUnitOfWork>(u => u.MealService == acc);
            MealsController controller = new MealsController(uow);
            var response = controller.GetMeal(id);
            var result = response as OkNegotiatedContentResult<MealDto>;
            var meal = result.Content;
            Assert.IsTrue(meal.EqualValues(_sampleMeal1));
        }

        [TestMethod]
        public void ApiPostMeal()
        {
            var meal = _sampleMeal1;
            var mock = new Mock<IMealService>();
            mock.Setup(acc => acc.CreateMeal(meal))
                .Returns(meal);
            var uowMock = new Mock<IUnitOfWork>();
            uowMock.Setup(u => u.MealService)
                .Returns(mock.Object);
            var con = new MealsController(uowMock.Object);
            con.User = new ClaimsPrincipal(
  new GenericPrincipal(new GenericIdentity(""), null));
            var response = con.PostMeal(meal);
            var result = response as CreatedAtRouteNegotiatedContentResult<MealDto>;
            var resultMeal = result.Content;
            Assert.IsTrue(resultMeal.EqualValues(meal));
            mock.Verify(acc => acc.CreateMeal(meal));
            uowMock.Verify(u => u.Save());
        }
    }
}
