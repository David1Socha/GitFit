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
    public class BadgesControllerTest
    {
        private Badge _sampleBadge1, _sampleBadge2;
        private Badge[] _manyBadges;

        [TestInitialize]
        public void Initialize()
        {
            _sampleBadge1 = BadgeGenerator.GenerateBadge1();
            _sampleBadge2 = BadgeGenerator.GenerateBadge2();
            _manyBadges = BadgeGenerator.GenerateManyBadges();
        }

        [TestMethod]
        public void ApiGetBadges()
        {
            var acc = Mock.Of<IBadgeService>(a => a.GetBadges() == _manyBadges);
            var uow = Mock.Of<IUnitOfWork>(u => u.BadgeService == acc);
            BadgesController controller = new BadgesController(uow);
            var badges = controller.GetBadges();
            Assert.IsTrue(badges.EqualValues(_manyBadges));
        }

        [TestMethod]
        public void ApiGetBadgeById()
        {
            long id = 7;
            var acc = Mock.Of<IBadgeService>(a => a.GetBadge(id) == _sampleBadge1);
            var uow = Mock.Of<IUnitOfWork>(u => u.BadgeService == acc);
            BadgesController controller = new BadgesController(uow);
            var response = controller.GetBadge(id);
            var result = response as OkNegotiatedContentResult<BadgeDto>;
            var badge = result.Content;
            Assert.IsTrue(badge.EqualValues(_sampleBadge1));
        }
    }
}
