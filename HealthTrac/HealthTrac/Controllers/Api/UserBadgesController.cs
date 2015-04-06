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
    public class UserBadgesController : ApiController
    {
        private IUserBadgeService ubsvc;
        private IUnitOfWork uow;

        public UserBadgesController(IUnitOfWork uow)
        {
            this.uow = uow;
            this.ubsvc = uow.UserBadgeService;
        }

        // GET: api/UserBadges
        public IEnumerable<UserBadgeDto> GetUserBadges()
        {
            var ubs = ubsvc.GetUserBadges();
            var ubDtos = ubs.Select(t => UserBadgeDto.FromUserBadge(t));
            return ubDtos;
        }

        // GET: api/UserBadges?userId=xyz
        public IEnumerable<UserBadgeDto> GetUserBadges(String userId)
        {
            var ubs = ubsvc.GetUserBadges(userId);
            var ubDtos = ubs.Select(t => UserBadgeDto.FromUserBadge(t));
            return ubDtos;
        }

        // GET: api/UserBadges?badgeId=2
        public IEnumerable<UserBadgeDto> GetUserBadges(long badgeId)
        {
            var ubs = ubsvc.GetUserBadges(badgeId);
            var ubDtos = ubs.Select(t => UserBadgeDto.FromUserBadge(t));
            return ubDtos;
        }

        // GET: api/UserBadges/5
        [ResponseType(typeof(UserBadgeDto))]
        public IHttpActionResult GetUserBadge(long id)
        {
            var ub = ubsvc.GetUserBadge(id);
            if (ub == null)
            {
                return NotFound();
            }
            var ubDto = UserBadgeDto.FromUserBadge(ub);
            return Ok(ubDto);
        }

        // GET: api/UserBadges?userId=xyz&badgeId=2
        [ResponseType(typeof(UserBadgeDto))]
        public IHttpActionResult GetUserBadge(String userId, long badgeId)
        {
            var ub = ubsvc.GetUserBadge(badgeId, userId);
            if (ub == null)
            {
                return NotFound();
            }
            var ubDto = UserBadgeDto.FromUserBadge(ub);
            return Ok(ubDto);
        }

        // POST: api/UserBadges
        [ResponseType(typeof(UserBadgeDto))]
        public IHttpActionResult PostUserBadge(UserBadge ub)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            ubsvc.CreateUserBadge(ub);
            uow.Save();

            return CreatedAtRoute("DefaultApi", new { id = ub.ID }, UserBadgeDto.FromUserBadge(ub));
        }

        protected override void Dispose(bool disposing)
        {
            uow.Dispose();
            base.Dispose(disposing);
        }
    }
}