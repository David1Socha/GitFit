﻿using System;
using System.Collections.Generic;
namespace HealthTrac.DataAccess
{
    public interface IUserAccessor
    {
        HealthTrac.Models.User FindUser(string ID);
        IEnumerable<HealthTrac.Models.User> SearchUsers(string name);
        IEnumerable<HealthTrac.Models.User> GetUsers(long teamID);
        HealthTrac.Models.User UpdateUser(HealthTrac.Models.User user);
        IEnumerable<HealthTrac.Models.User> GetUsers();
        HealthTrac.Models.User DeleteUser(HealthTrac.Models.User user);
        //Any in method name indicates that "deleted" models where Enabled field is false will be included
        HealthTrac.Models.User GetAnyUserWithUserName(String userName);
    }
}
