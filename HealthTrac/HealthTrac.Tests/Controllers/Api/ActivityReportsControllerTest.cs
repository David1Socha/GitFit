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
    public class ActivityReportsControllerTest
    {
        private ActivityReport _sampleActivityReport1, _sampleActivityReport2;
        private ActivityReport[] _manyActivityReports;

        [TestInitialize]
        public void Initialize()
        {
            _sampleActivityReport1 = ActivityReportGenerator.GenerateActivityReport1();
            _sampleActivityReport2 = ActivityReportGenerator.GenerateActivityReport2();
            _manyActivityReports = ActivityReportGenerator.GenerateManyActivityReports();
        }

        [TestMethod]
        public void ApiGetActivityReports()
        {
            var acc = Mock.Of<IActivityReportService>(a => a.GetActivityReports() == _manyActivityReports);
            var uow = Mock.Of<IUnitOfWork>(u => u.ActivityReportService == acc);
            ActivityReportsController controller = new ActivityReportsController(uow);
            var activityReports = controller.GetActivityReports();
            Assert.IsTrue(activityReports.EqualValues(_manyActivityReports));
        }

        [TestMethod]
        public void ApiGetActivityReportsByUser()
        {
            var sampleUserId = "3";
            var acc = Mock.Of<IActivityReportService>(a => a.GetActivityReports(sampleUserId) == _manyActivityReports);
            var uow = Mock.Of<IUnitOfWork>(u => u.ActivityReportService == acc);
            ActivityReportsController controller = new ActivityReportsController(uow);
            var activityReports = controller.GetActivityReports(sampleUserId);
            Assert.IsTrue(activityReports.EqualValues(_manyActivityReports));
        }

        [TestMethod]
        public void ApiGetActivityReportById()
        {
            long id = 15;
            var acc = Mock.Of<IActivityReportService>(a => a.GetActivityReport(id) == _sampleActivityReport1);
            var uow = Mock.Of<IUnitOfWork>(u => u.ActivityReportService == acc);
            ActivityReportsController controller = new ActivityReportsController(uow);
            var response = controller.GetActivityReport(id);
            var result = response as OkNegotiatedContentResult<ActivityReportDto>;
            var activityReport = result.Content;
            Assert.IsTrue(activityReport.EqualValues(_sampleActivityReport1));
        }
    }
}
