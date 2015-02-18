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
using HealthTrac.DataAccess.Entity;
using HealthTrac.Models.Dto;

namespace HealthTrac.Controllers.Api
{
    public class MembershipsController : ApiController
    {

        private IMembershipAccessor acc;
        private ApplicationDbContext db;

        public MembershipsController()
            : this(new EntityMembershipAccessor())
        {

        }

        public MembershipsController(IMembershipAccessor acc)
        {
            this.acc = acc;
        }
        // GET: api/Memberships
        public IEnumerable<MembershipDto> GetMemberships()
        {
            return acc.GetMemberships().Select(m => MembershipDto.FromMembership(m));
        }

        // GET: api/Memberships/5
        [ResponseType(typeof(MembershipDto))]
        public IHttpActionResult GetMembership(long id)
        {
            Membership membership = acc.GetMembership(id);
            if (membership == null)
            {
                return NotFound();
            }

            return Ok(MembershipDto.FromMembership(membership));
        }

        // PUT: api/Memberships/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutMembership(long id, Membership membership)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != membership.ID)
            {
                return BadRequest();
            }

            try
            {
                acc.UpdateMembership(membership);
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

            acc.CreateMembership(membership);

            return CreatedAtRoute("DefaultApi", new { id = membership.ID }, MembershipDto.FromMembership(membership));
        }

        // DELETE: api/Memberships/5
        [ResponseType(typeof(MembershipDto))]
        public IHttpActionResult DeleteMembership(long id)
        {
            Membership membership = db.Memberships.Find(id);
            if (membership == null)
            {
                return NotFound();
            }

            db.Memberships.Remove(membership);
            db.SaveChanges();

            return Ok(MembershipDto.FromMembership(membership));
        }

        private bool MembershipExists(long id)
        {
            return acc.GetMemberships().Count(e => e.ID == id) > 0;
        }
    }
}