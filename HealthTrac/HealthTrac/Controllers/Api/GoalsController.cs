using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using HealthTrac.Models;
using HealthTrac.DataAccess;
using HealthTrac.Models.Dto;
using HealthTrac.Services;
using Microsoft.AspNet.Identity;

namespace HealthTrac.Controllers.Api
{
    [Authorize]
    public class GoalsController : ApiController
    {

        private IUnitOfWork uow;
        private IGoalService gSvc;

        public GoalsController(IUnitOfWork uow)
        {
            this.uow = uow;
            this.gSvc = uow.GoalService;
        }

        // GET: api/Goals
        public IEnumerable<GoalDto> GetGoals()
        {
            return gSvc.GetGoals().Select(b => GoalDto.FromGoal(b));
        }

        // GET: api/Goals/5
        [ResponseType(typeof(GoalDto))]
        public IHttpActionResult GetGoal(long id)
        {
            var b = gSvc.GetGoal(id);
            if (b == null)
            {
                return NotFound();
            }
            return Ok(GoalDto.FromGoal(b));
        }

        // POST: api/Goals
        [ResponseType(typeof(GoalDto))]
        public IHttpActionResult PostGoal(Goal goal)
        {
            var uid = User.Identity.GetUserId();
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            gSvc.CreateGoal(goal, uid);
            uow.Save();

            return CreatedAtRoute("DefaultApi", new { id = goal.ID }, GoalDto.FromGoal(goal));
        }

        protected override void Dispose(bool disposing)
        {
            uow.Dispose();
            base.Dispose(disposing);
        }
    }
}