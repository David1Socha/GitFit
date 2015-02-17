using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using HealthTrac.DataAccess.Entity;
using HealthTrac.DataAccess;
using HealthTrac.Models;

namespace HealthTrac.Controllers
{
    public class TeamsController : ApiController
    {
        private ITeamAccessor acc;

        public TeamsController()
            : this(new EntityTeamAccessor())
        {

        }

        public TeamsController(ITeamAccessor acc)
        {
            this.acc = acc;
        }
        // GET: api/Teams
        public IEnumerable<Team> GetTeams()
        {
            var teams = acc.GetTeams();
            return teams;
        }

        // GET: api/Teams/5
        [ResponseType(typeof(Team))]
        public IHttpActionResult GetTeam(long id)
        {
            Team team = acc.GetTeam(id);
            if (team == null)
            {
                return NotFound();
            }

            return Ok(team);
        }

        //GET: api/Teams?userId=xxx
        public IEnumerable<Team> GetTeams(string userId)
        {
            var teams = acc.GetTeams(userId);
            return teams;
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

            try
            {
                acc.UpdateTeam(team);
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
        [ResponseType(typeof(Team))]
        public IHttpActionResult PostTeam(Team team)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            acc.CreateTeam(team);

            return CreatedAtRoute("DefaultApi", new { id = team.ID }, team);
        }


        private bool TeamExists(long id)
        {
            return acc.GetTeams().Count(e => e.ID == id) > 0;
        }
    }
}