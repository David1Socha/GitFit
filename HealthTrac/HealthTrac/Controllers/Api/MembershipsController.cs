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
using HealthTrac.DataAccess.Entity;
using HealthTrac.Models;

namespace HealthTrac.Controllers.Api
{
    public class MembershipsController : ApiController
    {
        private ApplicationDbContext db = new ApplicationDbContext();

        // GET: api/Memberships
        public IQueryable<Membership> GetMemberships()
        {
            return db.Memberships;
        }

        // GET: api/Memberships/5
        [ResponseType(typeof(Membership))]
        public IHttpActionResult GetMembership(long id)
        {
            Membership membership = db.Memberships.Find(id);
            if (membership == null)
            {
                return NotFound();
            }

            return Ok(membership);
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

            db.Entry(membership).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
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
        [ResponseType(typeof(Membership))]
        public IHttpActionResult PostMembership(Membership membership)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Memberships.Add(membership);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = membership.ID }, membership);
        }

        // DELETE: api/Memberships/5
        [ResponseType(typeof(Membership))]
        public IHttpActionResult DeleteMembership(long id)
        {
            Membership membership = db.Memberships.Find(id);
            if (membership == null)
            {
                return NotFound();
            }

            db.Memberships.Remove(membership);
            db.SaveChanges();

            return Ok(membership);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool MembershipExists(long id)
        {
            return db.Memberships.Count(e => e.ID == id) > 0;
        }
    }
}