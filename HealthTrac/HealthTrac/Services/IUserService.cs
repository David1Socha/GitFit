using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HealthTrac.Services
{
    public interface IUserService
    {
        HealthTrac.Models.User FindUser(string ID);
        IEnumerable<HealthTrac.Models.User> SearchUsers(string name);
        HealthTrac.Models.User UpdateUser(HealthTrac.Models.User user);
        IEnumerable<HealthTrac.Models.User> GetUsers();
        HealthTrac.Models.User DeleteUser(HealthTrac.Models.User user);
        //Any in method name indicates that "deleted" models where Enabled field is false will be included
        HealthTrac.Models.User GetAnyUserWithUserName(String userName);
    }
}
