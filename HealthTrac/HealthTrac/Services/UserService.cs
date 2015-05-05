using HealthTrac.DataAccess;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Services
{
    public class UserService : IUserService
    {

        private IUserAccessor _acc;

        public UserService(IUserAccessor acc)
        {
            _acc = acc;
        }
        public Models.User FindUser(string ID)
        {
            return _acc.FindUser(ID);
        }

        public IEnumerable<Models.User> SearchUsers(string name)
        {
            return _acc.SearchUsers(name);
        }

        public Models.User UpdateUser(Models.User user)
        {
            return _acc.UpdateUser(user);
        }

        public IEnumerable<Models.User> GetUsers()
        {
            return _acc.GetUsers();
        }

        public Models.User DeleteUser(Models.User user)
        {
            return _acc.DeleteUser(user);
        }

        public Models.User GetAnyUserWithUserName(string userName)
        {
            return _acc.GetAnyUserWithUserName(userName);
        }
    }
}