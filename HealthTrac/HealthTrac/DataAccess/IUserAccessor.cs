using System;
namespace HealthTrac.DataAccess
{
    public interface IUserAccessor
    {
        HealthTrac.Models.User FindUser(string ID);
        HealthTrac.Models.User SaveUser(HealthTrac.Models.User user);
    }
}
