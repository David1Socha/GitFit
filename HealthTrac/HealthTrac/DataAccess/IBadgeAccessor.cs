using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using HealthTrac.Models;

namespace HealthTrac.DataAccess
{
    public interface IBadgeAccessor
    {
        Badge GetBadge(long id);

        IEnumerable<Badge> GetBadges();

    }
}