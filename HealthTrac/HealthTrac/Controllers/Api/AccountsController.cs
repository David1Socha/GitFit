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

        //POST api/Account/Register
        [OverrideAuthentication]
        [HostAuthentication(DefaultAuthenticationTypes.ExternalBearer)]
        [Route("Register")]
        public IHttpActionResult Register(UserDto userDto)
        {
            UserLoginInfo loginInfo = GetExternalLoginInfo(AuthenticationManager);
            User user = new User()
            {
                BirthDate = userDto.BirthDate,
                Email = userDto.Email,
                Enabled = userDto.Enabled,
                FirstName = userDto.FirstName,
                Height = userDto.Height,
                LastName = userDto.LastName,
                PreferredName = userDto.PreferredName,
                Sex = userDto.Sex,
                Width = userDto.Width,
                UserName = userDto.UserName
            };
            Authentication.GetExternalLoginInfo();
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
            return Ok();
        }

        private static UserLoginInfo GetExternalLoginInfo(IAuthenticationManager auth)
        {
            return null; //TODO
        }

    }
}