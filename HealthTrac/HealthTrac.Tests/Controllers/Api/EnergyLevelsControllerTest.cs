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
    public class EnergyLevelsControllerTest
    {
        private EnergyLevel _sampleEnergyLevel1, _sampleEnergyLevel2;
        private EnergyLevel[] _manyEnergyLevels;

        [TestInitialize]
        public void Initialize()
        {
            _sampleEnergyLevel1 = EnergyLevelGenerator.GenerateEnergyLevel1();
            _sampleEnergyLevel2 = EnergyLevelGenerator.GenerateEnergyLevel2();
            _manyEnergyLevels = EnergyLevelGenerator.GenerateManyEnergyLevels();
        }

        [TestMethod]
        public void ApiGetEnergyLevels()
        {
            var acc = Mock.Of<IEnergyLevelService>(a => a.GetEnergyLevels() == _manyEnergyLevels);
            var uow = Mock.Of<IUnitOfWork>(u => u.EnergyLevelService == acc);
            EnergyLevelsController controller = new EnergyLevelsController(uow);
            var energyLevels = controller.GetEnergyLevels();
            Assert.IsTrue(energyLevels.EqualValues(_manyEnergyLevels));
        }

        [TestMethod]
        public void ApiGetEnergyLevelsByUser()
        {
            var sampleUserId = "3";
            var acc = Mock.Of<IEnergyLevelService>(a => a.GetEnergyLevels(sampleUserId) == _manyEnergyLevels);
            var uow = Mock.Of<IUnitOfWork>(u => u.EnergyLevelService == acc);
            EnergyLevelsController controller = new EnergyLevelsController(uow);
            var energyLevels = controller.GetEnergyLevels(sampleUserId);
            Assert.IsTrue(energyLevels.EqualValues(_manyEnergyLevels));
        }

        [TestMethod]
        public void ApiGetEnergyLevelById()
        {
            long id = 15;
            var acc = Mock.Of<IEnergyLevelService>(a => a.GetEnergyLevel(id) == _sampleEnergyLevel1);
            var uow = Mock.Of<IUnitOfWork>(u => u.EnergyLevelService == acc);
            EnergyLevelsController controller = new EnergyLevelsController(uow);
            var response = controller.GetEnergyLevel(id);
            var result = response as OkNegotiatedContentResult<EnergyLevelDto>;
            var energyLevel = result.Content;
            Assert.IsTrue(energyLevel.EqualValues(_sampleEnergyLevel1));
        }

        [TestMethod]
        public void ApiPostEnergyLevel()
        {
            var energyLevel = _sampleEnergyLevel1;
            var mock = new Mock<IEnergyLevelService>();
            mock.Setup(acc => acc.CreateEnergyLevel(energyLevel))
                .Returns(energyLevel);
            var uowMock = new Mock<IUnitOfWork>();
            uowMock.Setup(u => u.EnergyLevelService)
                .Returns(mock.Object);
            var con = new EnergyLevelsController(uowMock.Object);
            con.User = new ClaimsPrincipal(
  new GenericPrincipal(new GenericIdentity(""), null));
            var response = con.PostEnergyLevel(energyLevel);
            var result = response as CreatedAtRouteNegotiatedContentResult<EnergyLevelDto>;
            var resultEnergyLevel = result.Content;
            Assert.IsTrue(resultEnergyLevel.EqualValues(energyLevel));
            mock.Verify(acc => acc.CreateEnergyLevel(energyLevel));
            uowMock.Verify(u => u.Save());
        }
    }
}
