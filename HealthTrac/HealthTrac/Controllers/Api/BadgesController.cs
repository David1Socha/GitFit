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

namespace HealthTrac.Controllers.Api
{
    [Authorize]
    public class BadgesController : ApiController
    {

        private IUnitOfWork uow;
        private IBadgeService badgeSvc;

        public BadgesController(IUnitOfWork uow)
        {
            this.uow = uow;
            this.badgeSvc = uow.BadgeService;
        }

        // GET: api/Badges
        public IEnumerable<BadgeDto> GetBadges()
        {
            return badgeSvc.GetBadges().Select(b => BadgeDto.FromBadge(b));
        }

        // GET: api/Badges/5
        [ResponseType(typeof(BadgeDto))]
        public IHttpActionResult GetBadge(long id)
        {
            var b = badgeSvc.GetBadge(id);
            if (b == null)
            {
                return NotFound();
            }
            return Ok(BadgeDto.FromBadge(b));
        }

        protected override void Dispose(bool disposing)
        {
            uow.Dispose();
            base.Dispose(disposing);
        }
    }
}