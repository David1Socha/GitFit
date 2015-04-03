using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HealthTrac.Services
{
    public interface IMembershipService
    {
        Membership GetMembership(long id);
        Membership GetMembership(long teamId, string userId);
        IEnumerable<Membership> GetMemberships();
        IEnumerable<Membership> GetMemberships(long teamId);
        IEnumerable<Membership> GetMemberships(string userId);
        Membership UpdateMembership(Membership m);
        Membership CreateMembership(Membership m);
    }
}
