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
    public class ActivitiesControllerTest
    {
        private Activity _sampleActivity1, _sampleActivity2;
        private Activity[] _manyActivities;

        [TestInitialize]
        public void Initialize()
        {
            _sampleActivity1 = ActivityGenerator.GenerateActivity1();
            _sampleActivity2 = ActivityGenerator.GenerateActivity2();
            _manyActivities = ActivityGenerator.GenerateManyActivities();
        }

        [TestMethod]
        public void ApiGetActivities()
        {
            var acc = Mock.Of<IActivityService>(a => a.GetActivities() == _manyActivities);
            var uow = Mock.Of<IUnitOfWork>(u => u.ActivityService == acc);
            ActivitiesController controller = new ActivitiesController(uow);
            var activities = controller.GetActivities();
            Assert.IsTrue(activities.EqualValues(_manyActivities));
        }

        [TestMethod]
        public void ApiGetActivitiesByUser()
        {
            var sampleUserId = "3";
            var acc = Mock.Of<IActivityService>(a => a.GetActivities(sampleUserId) == _manyActivities);
            var uow = Mock.Of<IUnitOfWork>(u => u.ActivityService == acc);
            ActivitiesController controller = new ActivitiesController(uow);
            var activities = controller.GetActivities(sampleUserId);
            Assert.IsTrue(activities.EqualValues(_manyActivities));
        }

        [TestMethod]
        public void ApiGetActivityById()
        {
            long id = 15;
            var acc = Mock.Of<IActivityService>(a => a.GetActivity(id) == _sampleActivity1);
            var uow = Mock.Of<IUnitOfWork>(u => u.ActivityService == acc);
            ActivitiesController controller = new ActivitiesController(uow);
            var response = controller.GetActivity(id);
            var result = response as OkNegotiatedContentResult<ActivityDto>;
            var activity = result.Content;
            Assert.IsTrue(activity.EqualValues(_sampleActivity1));
        }

        [TestMethod]
        public void ApiPutActivity()
        {
            long id = 17;
            var activity = _sampleActivity2;
            var mock = new Mock<IActivityService>();
            mock.Setup(acc => acc.UpdateActivity(activity))
                .Returns(activity);
            var uowMock = new Mock<IUnitOfWork>();
            uowMock.Setup(u => u.ActivityService)
                .Returns(mock.Object);
            var con = new ActivitiesController(uowMock.Object);
            con.PutActivity(id, activity);
            mock.Verify(acc => acc.UpdateActivity(activity));
            uowMock.Verify(u => u.Save());
        }

        [TestMethod]
        public void ApiPostActivity()
        {
            var activity = _sampleActivity1;
            var mock = new Mock<IActivityService>();
            mock.Setup(acc => acc.CreateActivity(activity, null))
                .Returns(activity);
            var uowMock = new Mock<IUnitOfWork>();
            uowMock.Setup(u => u.ActivityService)
                .Returns(mock.Object);
            var con = new ActivitiesController(uowMock.Object);
            con.User = new ClaimsPrincipal(
  new GenericPrincipal(new GenericIdentity(""), null));
            var response = con.PostActivity(activity);
            var result = response as CreatedAtRouteNegotiatedContentResult<ActivityDto>;
            var resultActivity = result.Content;
            Assert.IsTrue(resultActivity.EqualValues(activity));
            mock.Verify(acc => acc.CreateActivity(activity, "3"));
            uowMock.Verify(u => u.Save());
        }

        [TestMethod]
        public void ApiDeleteActivity()
        {
            long id = 17;
            var activity = _sampleActivity2;
            var mock = new Mock<IActivityService>();
            var uowMock = new Mock<IUnitOfWork>();
            uowMock.Setup(u => u.ActivityService)
                .Returns(mock.Object);
            var con = new ActivitiesController(uowMock.Object);
            var response = con.DeleteActivity(id);
            Assert.IsInstanceOfType(response, typeof(OkResult));
            mock.Verify(s => s.DeleteActivity(activity.ID));
            uowMock.Verify(u => u.Save());
        }
    }
}
