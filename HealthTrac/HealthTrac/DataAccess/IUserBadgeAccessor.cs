using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using HealthTrac.Models;

namespace HealthTrac.DataAccess
{
    public interface IUserBadgeAccessor
    {
        UserBadge GetUserBadge(long id);
        IEnumerable<UserBadge> GetUserBadges();
        IEnumerable<UserBadge> GetUserBadges(long badgeId);
        IEnumerable<UserBadge> GetUserBadges(String userId);
        UserBadge GetUserBadge(long badgeId, String userId);
        UserBadge CreateUserBadge(UserBadge ub);

    }
}