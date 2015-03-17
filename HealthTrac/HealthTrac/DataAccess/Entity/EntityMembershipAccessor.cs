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

        private ApplicationDbContext db;

        public EntityMembershipAccessor(ApplicationDbContext db)
        {
            this.db = db;
        }

        public Models.Membership GetMembership(long id)
        {
            Membership membership = db.Memberships
            .Where(m => m.ID == id)
            .FirstOrDefault();
            return membership;
        }

        public Models.Membership GetMembership(long teamId, string userId)
        {
            Membership membership = db.Memberships
                .Where(m => m.TeamID == teamId
                && m.UserID == userId)
                .FirstOrDefault();
            return membership;
        }

        public IEnumerable<Models.Membership> GetMemberships()
        {
            var memberships = db.Memberships.ToList();
            return memberships;
        }

        public IEnumerable<Models.Membership> GetMemberships(long teamId)
        {
            var memberships = db.Memberships.
                Where(m => m.TeamID == teamId).
                ToList();
            return memberships;
        }

        public IEnumerable<Models.Membership> GetMemberships(string userId)
        {
            var memberships = db.Memberships.
                Where(m => m.UserID == userId).
                ToList();
            return memberships;
        }

        public Models.Membership UpdateMembership(Models.Membership m)
        {
            db.Entry(m).State = System.Data.Entity.EntityState.Modified;
            return m;
        }

        public Models.Membership CreateMembership(Models.Membership m)
        {
            db.Memberships.Add(m); //TODO check this to ensure we get ID back at some point, perhaps after Save() is called?
            return m;
        }

    }
}