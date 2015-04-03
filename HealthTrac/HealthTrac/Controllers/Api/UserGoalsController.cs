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
using Microsoft.AspNet.Identity;

namespace HealthTrac.Controllers.Api
{
    [Authorize]
    public class UserGoalsController : ApiController
    {
        private IUserGoalService ugsvc;
        private IUnitOfWork uow;

        public UserGoalsController(IUnitOfWork uow)
        {
            this.uow = uow;
            this.ugsvc = uow.UserGoalService;
        }

        // GET: api/UserGoals
        public IEnumerable<UserGoalDto> GetUserGoals()
        {
            var ubs = ugsvc.GetUserGoals();
            var ubDtos = ubs.Select(t => UserGoalDto.FromUserGoal(t));
            return ubDtos;
        }

        // GET: api/UserGoals?userId=xyz
        public IEnumerable<UserGoalDto> GetUserGoals(String userId)
        {
            var ubs = ugsvc.GetUserGoals(userId);
            var ubDtos = ubs.Select(t => UserGoalDto.FromUserGoal(t));
            return ubDtos;
        }

        // GET: api/UserGoals?goalId=2
        public IEnumerable<UserGoalDto> GetUserGoals(long goalId)
        {
            var ubs = ugsvc.GetUserGoals(goalId);
            var ubDtos = ubs.Select(t => UserGoalDto.FromUserGoal(t));
            return ubDtos;
        }

        // GET: api/UserGoals/5
        [ResponseType(typeof(UserGoalDto))]
        public IHttpActionResult GetUserGoal(long id)
        {
            var ub = ugsvc.GetUserGoal(id);
            if (ub == null)
            {
                return NotFound();
            }
            var ubDto = UserGoalDto.FromUserGoal(ub);
            return Ok(ubDto);
        }

        // GET: api/UserGoals?userId=xyz&badgeId=2
        [ResponseType(typeof(UserGoalDto))]
        public IHttpActionResult GetUserGoal(String userId, long goalId)
        {
            var ub = ugsvc.GetUserGoal(goalId, userId);
            if (ub == null)
            {
                return NotFound();
            }
            var ubDto = UserGoalDto.FromUserGoal(ub);
            return Ok(ubDto);
        }

        // POST: api/UserGoals
        [ResponseType(typeof(UserGoalDto))]
        public IHttpActionResult PostUserGoal(UserGoal ub)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            ugsvc.CreateUserGoal(ub);
            uow.Save();

            return CreatedAtRoute("DefaultApi", new { id = ub.ID }, UserGoalDto.FromUserGoal(ub));
        }

        // PUT: api/UserGoals/2
        [ResponseType(typeof(void))]
        public IHttpActionResult PutUserGoal(long id, UserGoal ug)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != ug.ID)
            {
                return BadRequest();
            }
            ugsvc.UpdateUserGoal(ug);
            return StatusCode(HttpStatusCode.NoContent);
        }

        protected override void Dispose(bool disposing)
        {
            uow.Dispose();
            base.Dispose(disposing);
        }
    }
}