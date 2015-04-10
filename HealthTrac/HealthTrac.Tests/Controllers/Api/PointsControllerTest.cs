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
    public class PointsControllerTest
    {
        private Point _samplePoint1, _samplePoint2;
        private Point[] _manyPoints;

        [TestInitialize]
        public void Initialize()
        {
            _samplePoint1 = PointGenerator.GeneratePoint1();
            _samplePoint2 = PointGenerator.GeneratePoint2();
            _manyPoints = PointGenerator.GenerateManyPoints();
        }

        [TestMethod]
        public void ApiGetPoints()
        {
            var acc = Mock.Of<IPointService>(a => a.GetPoints() == _manyPoints);
            var uow = Mock.Of<IUnitOfWork>(u => u.PointService == acc);
            PointsController controller = new PointsController(uow);
            var points = controller.GetPoints();
            Assert.IsTrue(points.EqualValues(_manyPoints));
        }

        [TestMethod]
        public void ApiGetPointsByActivity()
        {
            var sampleActivityId = 7;
            var acc = Mock.Of<IPointService>(a => a.GetPoints(sampleActivityId) == _manyPoints);
            var uow = Mock.Of<IUnitOfWork>(u => u.PointService == acc);
            PointsController controller = new PointsController(uow);
            var points = controller.GetPoints(sampleActivityId);
            Assert.IsTrue(points.EqualValues(_manyPoints));
        }

        [TestMethod]
        public void ApiGetPointById()
        {
            long id = 7;
            var acc = Mock.Of<IPointService>(a => a.GetPoint(id) == _samplePoint1);
            var uow = Mock.Of<IUnitOfWork>(u => u.PointService == acc);
            PointsController controller = new PointsController(uow);
            var response = controller.GetPoint(id);
            var result = response as OkNegotiatedContentResult<PointDto>;
            var point = result.Content;
            Assert.IsTrue(point.EqualValues(_samplePoint1));
        }

        [TestMethod]
        public void ApiPostPoint()
        {
            var point = _samplePoint1;
            var mock = new Mock<IPointService>();
            mock.Setup(acc => acc.CreatePoint(point))
                .Returns(point);
            var uowMock = new Mock<IUnitOfWork>();
            uowMock.Setup(u => u.PointService)
                .Returns(mock.Object);
            var con = new PointsController(uowMock.Object);
            con.User = new ClaimsPrincipal(
  new GenericPrincipal(new GenericIdentity(""), null));
            var response = con.PostPoint(point);
            var result = response as CreatedAtRouteNegotiatedContentResult<PointDto>;
            var resultPoint = result.Content;
            Assert.IsTrue(resultPoint.EqualValues(point));
            mock.Verify(acc => acc.CreatePoint(point));
            uowMock.Verify(u => u.Save());
        }

        [TestMethod]
        public void ApiCreatePoints()
        {
            var acc = Mock.Of<IPointService>(a => a.CreatePoints(_manyPoints) == _manyPoints);
            var uow = Mock.Of<IUnitOfWork>(u => u.PointService == acc);
            PointsController controller = new PointsController(uow);
            var points = controller.CreatePoints(_manyPoints);
            Assert.IsTrue(points.EqualValues(_manyPoints));
        }
    }
}
