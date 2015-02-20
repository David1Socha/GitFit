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
        public void TeamsControllerGetManyTeams()
        {
            var acc = Mock.Of<ITeamAccessor>(a => a.GetTeams() == _manyTeams);
            TeamsController controller = new TeamsController(acc);
            var teams = controller.GetTeams();
            Assert.IsTrue(teams.EqualValues(_manyTeams));
        }

        [TestMethod]
        public void TeamsControllerGetManyTeamsByUser()
        {
            var sampleUserId = "xyz";
            var acc = Mock.Of<ITeamAccessor>(a => a.GetTeams(sampleUserId) == _manyTeams);
            TeamsController con = new TeamsController(acc);
            var teams = con.GetTeams(sampleUserId);
            Assert.IsTrue(teams.EqualValues(_manyTeams));
        }

        [TestMethod]
        public void TeamsControllerGetTeamById()
        {
            long id = 12;
            var acc = Mock.Of<ITeamAccessor>(a => a.GetTeam(id) == _sampleTeam1);
            TeamsController con = new TeamsController(acc);
            var response = con.GetTeam(id);
            var result = response as OkNegotiatedContentResult<TeamDto>;
            var team = result.Content;
            Assert.IsTrue(team.EqualValues(_sampleTeam1));
        }

        [TestMethod]
        public void TeamsControllerPutTeam()
        {
            long id = 14;
            var team = _sampleTeam2;
            var mock = new Mock<ITeamAccessor>();
            mock.Setup(acc => acc.UpdateTeam(team))
                .Returns(team);
            var con = new TeamsController(mock.Object);
            con.PutTeam(id, team);
            mock.Verify(acc => acc.UpdateTeam(team));
        }
    }
}
