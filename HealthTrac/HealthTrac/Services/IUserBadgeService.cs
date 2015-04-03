using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HealthTrac.Services
{
    public interface IUserBadgeService
    {
        UserBadge GetUserBadge(long id);
        IEnumerable<UserBadge> GetUserBadges();
        IEnumerable<UserBadge> GetUserBadges(long badgeId);
        IEnumerable<UserBadge> GetUserBadges(String userId);
        UserBadge GetUserBadge(long badgeId, String userId);
        UserBadge CreateUserBadge(UserBadge ub);
    }
}
