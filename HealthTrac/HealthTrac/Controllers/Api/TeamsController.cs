﻿using System;
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
using HealthTrac.Models.Dto;

namespace HealthTrac.Controllers.Api
{
    [Authorize]
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
        public IEnumerable<TeamDto> GetTeams()
        {
            var teams = acc.GetTeams();
            var teamDtos = teams.Select(t => TeamDto.FromTeam(t));
            return teamDtos;
        }

        // GET: api/Teams/5
        [ResponseType(typeof(TeamDto))]
        public IHttpActionResult GetTeam(long id)
        {
            Team team = acc.GetTeam(id);
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
            var teams = acc.GetTeams(userId);
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
        [ResponseType(typeof(TeamDto))]
        public IHttpActionResult PostTeam(Team team)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            acc.CreateTeam(team);

            return CreatedAtRoute("DefaultApi", new { id = team.ID }, TeamDto.FromTeam(team));
        }


        private bool TeamExists(long id)
        {
            return acc.GetTeams().Count(e => e.ID == id) > 0;
        }
    }
}