using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using HealthTrac.DataAccess;
using HealthTrac.Models;
using HealthTrac.Models.Dto;
using HealthTrac.Services;

namespace HealthTrac.Controllers.Api
{
    [Authorize]
    public class TeamsController : ApiController
    {
        private ITeamService teamService;
        private IUnitOfWork uow;

        public TeamsController(IUnitOfWork uow)
        {
            this.uow = uow;
            this.teamService = uow.TeamService;
        }
        // GET: api/Teams
        public IEnumerable<TeamDto> GetTeams()
        {
            var teams = teamService.GetTeams();
            var teamDtos = teams.Select(t => TeamDto.FromTeam(t));
            return teamDtos;
        }

        // GET: api/Teams/5
        [ResponseType(typeof(TeamDto))]
        public IHttpActionResult GetTeam(long id)
        {
            Team team = teamService.GetTeam(id);
            if (team == null)
            {
                return NotFound();
            }
            var teamDto = TeamDto.FromTeam(team);
            return Ok(teamDto);
        }

        //GET: api/Teams?userId=xxx
        public IEnumerable<TeamDto> GetTeams(string userId)
        {
            var teams = teamService.GetTeams(userId);
            var teamDtos = teams.Select(t => TeamDto.FromTeam(t));
            return teamDtos;
        }

        // PUT: api/Teams/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutTeam(long id, Team team)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != team.ID)
            {
                return BadRequest();
            }

            teamService.UpdateTeam(team);
            try
            {
                uow.Save();
            }
            catch (ConcurrentUpdateException)
            {
                if (!TeamExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }

        // POST: api/Teams
        [ResponseType(typeof(TeamDto))]
        public IHttpActionResult PostTeam(Team team)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            teamService.CreateTeam(team);
            uow.Save();

            return CreatedAtRoute("DefaultApi", new { id = team.ID }, TeamDto.FromTeam(team));
        }

        // DELETE: api/Teams/5
        [ResponseType(typeof(void))]
        public IHttpActionResult DeleteTeam(long id)
        {
            Team teamToDelete = teamService.GetTeam(id);
            teamService.DeleteTeam(teamToDelete);
            uow.Save();
            return Ok();
        }

        private bool TeamExists(long id)
        {
            return teamService.GetTeams().Count(e => e.ID == id) > 0;
        }

        protected override void Dispose(bool disposing)
        {
            uow.Dispose();
            base.Dispose(disposing);
        }
    }
}