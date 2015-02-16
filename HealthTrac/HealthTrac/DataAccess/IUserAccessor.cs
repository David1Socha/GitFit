using System;
namespace HealthTrac.DataAccess
{
    interface IUserAccessor
    {
        HealthTrac.Models.ApplicationUser FindUser(string ID);
        HealthTrac.Models.ApplicationUser SaveUser(HealthTrac.Models.ApplicationUser user);
    }
}
