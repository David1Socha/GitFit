using HealthTrac.Models;
using Microsoft.AspNet.Identity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HealthTrac.Services
{
    public interface IUserManager
    {
        User Find(UserLoginInfo info);
    }
}
