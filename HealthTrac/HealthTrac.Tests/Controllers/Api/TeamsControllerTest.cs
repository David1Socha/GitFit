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
        private Team _sampleTeam;
        private Team[] _manyTeams;

        [TestInitialize]
        public void Initialize()
        {
            _sampleTeam = new Team
            {
                Name = "Fake team 9000",
                Visibility = Visibility.PUBLIC
            };
            _manyTeams = new Team[] {
                _sampleTeam,
                new Team {
                    Description = "Enough said",
                    Name = "Team 6 is the best"
                }
            };
        }

        [TestMethod]
        public void GetManyTeamsTest()
        {
            var acc = Mock.Of<ITeamAccessor>(a => a.GetTeams() == _manyTeams);
            TeamsController controller = new TeamsController(acc);
            Assert.IsTrue(true);
        }
    }
}
