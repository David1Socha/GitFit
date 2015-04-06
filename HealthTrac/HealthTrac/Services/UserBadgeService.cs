using HealthTrac.DataAccess;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Services
{
    public class UserBadgeService : IUserBadgeService
    {

        private IUserBadgeAccessor _acc;

        public UserBadgeService(IUserBadgeAccessor acc)
        {
            _acc = acc;
        }
        public Models.UserBadge GetUserBadge(long id)
        {
            return _acc.GetUserBadge(id);
        }

        public IEnumerable<Models.UserBadge> GetUserBadges()
        {
            return _acc.GetUserBadges();
        }

        public IEnumerable<Models.UserBadge> GetUserBadges(long badgeId)
        {
            return _acc.GetUserBadges(badgeId);
        }

        public IEnumerable<Models.UserBadge> GetUserBadges(string userId)
        {
            return _acc.GetUserBadges(userId);
        }

        public Models.UserBadge GetUserBadge(long badgeId, string userId)
        {
            return _acc.GetUserBadge(badgeId, userId);
        }

        public Models.UserBadge CreateUserBadge(Models.UserBadge ub)
        {
            return _acc.CreateUserBadge(ub);
        }
    }
}