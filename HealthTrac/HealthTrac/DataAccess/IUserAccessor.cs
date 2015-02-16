using System;
namespace HealthTrac.DataAccess
{
    public interface IApplicationUserAccessor
    {
        HealthTrac.Models.ApplicationUser FindUser(string ID);
        HealthTrac.Models.ApplicationUser SaveUser(HealthTrac.Models.ApplicationUser user);
    }
}
