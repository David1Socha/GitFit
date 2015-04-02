using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Web;

namespace HealthTrac.DataAccess.Entity
{
    public class EntityUserBadgeAccessor : IUserBadgeAccessor
    {
        private ApplicationDbContext db;

        public EntityUserBadgeAccessor(ApplicationDbContext db)
        {
            this.db = db;
        }

        public UserBadge GetUserBadge(long ID)
        {
            UserBadge b = db.UserBadges
                .Where(a => a.ID == ID)
                .FirstOrDefault();
            return b;
        }

        public IEnumerable<UserBadge> GetUserBadges()
        {
            IEnumerable<UserBadge> badges = db.UserBadges
                .ToList();
            return badges;
        }

        public IEnumerable<UserBadge> GetUserBadges(long badgeId)
        {
            IEnumerable<UserBadge> ubs = db.UserBadges
                .Where(u => u.BadgeID == badgeId)
                .ToList();
            return ubs;
        }

        public IEnumerable<UserBadge> GetUserBadges(string userId)
        {
            IEnumerable<UserBadge> ubs = db.UserBadges
                .Where(u => u.UserID == userId)
                .ToList();
            return ubs;
        }

        public UserBadge GetUserBadge(long badgeId, string userId)
        {
            UserBadge ub = db.UserBadges
                .Where(u => u.BadgeID == badgeId && u.UserID == userId)
                .FirstOrDefault();
            return ub;
        }

        public UserBadge CreateUserBadge(UserBadge ub)
        {
            db.UserBadges.Add(ub);
            return ub;
        }
    }
}