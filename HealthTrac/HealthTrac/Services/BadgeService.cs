using HealthTrac.DataAccess;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Services
{
    public class BadgeService : IBadgeService
    {

        private IBadgeAccessor _acc;

        public BadgeService(IBadgeAccessor acc)
        {
            _acc = acc;
        }

        public Models.Badge GetBadge(long id)
        {
            return _acc.GetBadge(id);
        }

        public IEnumerable<Models.Badge> GetBadges()
        {
            return _acc.GetBadges();
        }
    }
}