using HealthTrac.Models;
using Microsoft.AspNet.Identity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Services
{
    public class UserManagerAdapter : IUserManager
    {
        private UserManager<User> Man;
        public UserManagerAdapter(UserManager<User> man)
        {
            Man = man;
        }


        public User Find(UserLoginInfo info)
        {
            return Man.Find(info);
        }
    }
}