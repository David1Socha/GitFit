using System;
using System.Collections.Generic;
namespace HealthTrac.DataAccess
{
    public interface IUserAccessor
    {
        HealthTrac.Models.User FindUser(string ID);
        HealthTrac.Models.User SaveUser(HealthTrac.Models.User user);
        IEnumerable<HealthTrac.Models.User> GetUsers();
    }
}
