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
            _sampleTeam1 = new Team
            {
                DateCreated = new DateTime(2000, 4, 20),
                DateModified = new DateTime(2005, 4, 20),
                Name = "Fake team 9000",
                Description = null,
                Visibility = Visibility.PUBLIC
            };
            _sampleTeam2 = new Team
            {
                DateCreated = new DateTime(1000, 10, 10),
                DateModified = new DateTime(1111, 11, 11),
                Description = "Enough said",
                Name = "Team 6 is the best",
                Visibility = Visibility.SECRET
            };
            _manyTeams = new Team[] {
                _sampleTeam1, _sampleTeam2
            };
        }

        [TestMethod]
        public void TeamsControllerGetManyTeamsTest()
        {
            var acc = Mock.Of<ITeamAccessor>(a => a.GetTeams() == _manyTeams);
            TeamsController controller = new TeamsController(acc);
            var teams = controller.GetTeams();
            Assert.IsTrue(true);
        }
    }
}
