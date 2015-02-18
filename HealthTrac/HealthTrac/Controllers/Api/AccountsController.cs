using HealthTrac.DataAccess.Entity;
using HealthTrac.Models;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.EntityFramework;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Http;
using HealthTrac.Models.Dto;
using System.Web.Http.Description;

namespace HealthTrac.Controllers.Api
{
    //TODO remove hardcoded dependency on entity context (IOC container?)
    [Authorize]
    public class AccountsController : ApiController
    {
        public AccountsController()
            : this(new UserManager<User>(new UserStore<User>(new ApplicationDbContext())))
        {
        }

        public AccountsController(UserManager<User> userManager)
        {
            UserManager = userManager;
        }

        public UserManager<User> UserManager { get; private set; }

        [ResponseType(typeof(UserDto))]
        public IHttpActionResult PostAccount()
        {
            return null;
        }
    }
}