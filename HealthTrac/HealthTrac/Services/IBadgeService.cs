using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HealthTrac.Services
{
    public interface IBadgeService
    {
        Badge GetBadge(long id);
        IEnumerable<Badge> GetBadges();
    }
}
