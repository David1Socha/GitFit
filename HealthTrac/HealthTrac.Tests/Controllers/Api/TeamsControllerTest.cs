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
using Moq.Linq;
using HealthTrac.Tests.Helpers;

namespace HealthTrac.Tests.Controllers.Api
{
    [TestClass]
    public class TeamsControllerTest
    {
        private Team _sampleTeam1, _sampleTeam2;
        private Team[] _manyTeams;

        [TestInitialize]
        public void Initialize()
        {
            _sampleTeam1 = TeamGenerator.GenerateTeam1();
            _sampleTeam2 = TeamGenerator.GenerateTeam2();
            _manyTeams = TeamGenerator.GenerateManyTeams();
        }

        [TestMethod]
        public void TeamsControllerGetManyTeamsTest()
        {
            var acc = Mock.Of<ITeamAccessor>(a => a.GetTeams() == _manyTeams);
            TeamsController controller = new TeamsController(acc);
            var teams = controller.GetTeams();
            Assert.IsTrue(teams.EqualValues(_manyTeams));
        }
    }
}
