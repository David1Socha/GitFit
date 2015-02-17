using System;
namespace HealthTrac.DataAccess
{
    public interface IApplicationUserAccessor
    {
        HealthTrac.Models.User FindUser(string ID);
        HealthTrac.Models.User SaveUser(HealthTrac.Models.User user);
    }
}
