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
using System.Threading.Tasks;
using Microsoft.Owin.Security;
using System;
using System.Collections.Generic;
using System.Net.Http;

namespace HealthTrac.Controllers.Api
{
    //TODO remove hardcoded dependency on entity context (IOC container?)
    public class AccountsController : ApiController
    {

        private IAuthenticationManager Authentication
        {
            get { return Request.GetOwinContext().Authentication; }
        }
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
        public IHttpActionResult PostAccount(UserLoginDto userLogin)
        {
            var userDto = userLogin.User;
            var credentials = userLogin.Credentials;
            UserLoginInfo loginInfo = GetLoginInfo(credentials);
            User user = new User()
            {
                UserName = userDto.UserName
            };
            var result = UserManager.Create(user);
            if (result.Succeeded)
            {
                result = UserManager.AddLogin(user.Id, loginInfo);
                if (result.Succeeded)
                {
                    var identity = UserManager.CreateIdentity(user, DefaultAuthenticationTypes.ApplicationCookie);
                }
                else
                {
                    return BadRequest();
                }
            }
            else
            {
                return BadRequest();
            }
            return null;
        }

        private static UserLoginInfo GetLoginInfo(CredentialsDto credentials)
        {
            return null;
        }

        private static UserLoginInfo GetLoginInfoFacebook(CredentialsDto credentials)
        {
            return null;
        }

        private static UserLoginInfo GetLoginInfoTwitter(CredentialsDto credentials)
        {
            return null;
        }

    }
}