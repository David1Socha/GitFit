using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Web;

namespace HealthTrac.DataAccess.Entity
{
    public class EntityBadgeAccessor : IBadgeAccessor
    {
        private ApplicationDbContext db;

        public EntityBadgeAccessor(ApplicationDbContext db)
        {
            this.db = db;
        }

        public Badge GetBadge(long ID)
        {
            Badge b = db.Badges
                .Where(a => a.ID == ID)
                .FirstOrDefault();
            return b;
        }

        public IEnumerable<Badge> GetBadges()
        {
            IEnumerable<Badge> badges = db.Badges
                .ToList();
            return badges;
        }

    }
}