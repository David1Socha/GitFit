using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;
using HealthTrac.Models;
using System.Data.Entity.Infrastructure;

namespace HealthTrac.DataAccess.Entity
{
    public class EntityMembershipAccessor : IMembershipAccessor
    {

        public Models.Membership GetMembership(long id)
        {
            using (var db = new ApplicationDbContext) {
                Membership membership = db.Memberships
                .Where(m => m.ID == id)
                .FirstOrDefault();
                return membership;
            }
        }

        public Models.Membership GetMembership(long teamId, string userId)
        {
            using (var db = new ApplicationDbContext())
            {
                Membership membership = db.Memberships
                    .Where(m => m.TeamID == teamId
                    && m.UserId == userId)
                    .FirstOrDefault();
                return membership;
            }
        }

        public IEnumerable<Models.Membership> GetMemberships()
        {
            using (var db = new ApplicationDbContext())
            {
                var memberships = db.Memberships.ToList();
                return memberships;
            }
        }

        public IEnumerable<Models.Membership> GetMemberships(long teamId)
        {
            using (var db = new ApplicationDbContext())
            {
                var memberships = db.Memberships.
                    Where(m => m.TeamID == teamId).
                    ToList();
                return memberships;
            }
        }

        public IEnumerable<Models.Membership> GetMemberships(string userId)
        {
            using (var db = new ApplicationDbContext())
            {
                var memberships = db.Memberships.
                    Where(m => m.UserId == userId).
                    ToList();
                return memberships;
            }
        }

        public Models.Membership UpdateMembership(Models.Membership m)
        {
            using (var db = new ApplicationDbContext())
            {
                db.Entry(m).State = System.Data.Entity.EntityState.Modified;
                try
                {
                    db.SaveChanges();
                }
                catch (DbUpdateConcurrencyException ex)
                {
                    throw new ConcurrentUpdateException("The membership you are trying to update has been modified externally,", ex);
                }
                return m;
            }
        }

        public Models.Membership CreateMembership(Models.Membership m)
        {
            using (var db = new ApplicationDbContext())
            {
                db.Memberships.Add(m);
                db.SaveChanges();

            }
            return m;
        }
    }
}