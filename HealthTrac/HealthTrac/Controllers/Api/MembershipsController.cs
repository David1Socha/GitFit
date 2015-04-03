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
    public class MembershipsController : ApiController
    {

        private IUnitOfWork uow;
        private IMembershipService memSvc;

        public MembershipsController(IUnitOfWork uow)
        {
            this.uow = uow;
            this.memSvc = uow.MembershipService;
        }
        // GET: api/Memberships
        public IEnumerable<MembershipDto> GetMemberships()
        {
            return memSvc.GetMemberships().Select(m => MembershipDto.FromMembership(m));
        }

        // GET: api/Memberships?userId=xyz&teamId=5
        [ResponseType(typeof(MembershipDto))]
        public IHttpActionResult GetMembership(string userId, long teamId)
        {
            var membership = memSvc.GetMembership(teamId, userId);
            if (membership == null)
            {
                return NotFound();
            }
            return Ok(MembershipDto.FromMembership(membership));
        }

        // GET: api/Memberships/5
        [ResponseType(typeof(MembershipDto))]
        public IHttpActionResult GetMembership(long id)
        {
            Membership membership = memSvc.GetMembership(id);
            if (membership == null)
            {
                return NotFound();
            }

            return Ok(MembershipDto.FromMembership(membership));
        }

        // GET: api/Memberships?userId=xyz
        public IEnumerable<MembershipDto> GetMemberships(string userId)
        {
            return memSvc.GetMemberships(userId).
                Select(m => MembershipDto.FromMembership(m));
        }

        // GET: api/Memberships?teamId=5
        public IEnumerable<MembershipDto> GetMemberships(long teamId)
        {
            return memSvc.GetMemberships(teamId).
                Select(m => MembershipDto.FromMembership(m));
        }

        // PUT: api/Memberships/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutMembership(long id, Membership membership)
        {
            //TODO validate current user admin status on team
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != membership.ID)
            {
                return BadRequest();
            }
            memSvc.UpdateMembership(membership);
            try
            {
                uow.Save();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!MembershipExists(id))
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

        // POST: api/Memberships
        [ResponseType(typeof(MembershipDto))]
        public IHttpActionResult PostMembership(Membership membership)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            memSvc.CreateMembership(membership);
            uow.Save();

            return CreatedAtRoute("DefaultApi", new { id = membership.ID }, MembershipDto.FromMembership(membership));
        }

        private bool MembershipExists(long id)
        {
            return memSvc.GetMemberships().Count(e => e.ID == id) > 0;
        }

        protected override void Dispose(bool disposing)
        {
            uow.Dispose();
            base.Dispose(disposing);
        }
    }
}