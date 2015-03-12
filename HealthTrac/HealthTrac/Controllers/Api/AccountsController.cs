using HealthTrac.DataAccess.Entity;
using HealthTrac.Models;
using Microsoft.AspNet.Identity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Http;
using HealthTrac.Models.Dto;
using System.Web.Http.Description;
using System.Threading.Tasks;
using Microsoft.Owin.Security;
using System.Net.Http.Formatting;
using System.Net.Http;
using System.Net;
using System.Security.Claims;
using Newtonsoft.Json.Linq;
using OAuth;
using System.Net.Http.Headers;
using Microsoft.Owin.Security.Cookies;
using HealthTrac.Services;

namespace HealthTrac.Controllers.Api
{
    [Authorize]
    public class AccountsController : ApiController
    {

        private IAuthenticationManager Authentication
        {
            get;
            set;
        }

        private ILoginService LoginService { get; set; }

        private IFacebookService FacebookService { get; set; }
        private ITwitterService TwitterService { get; set; }

        public AccountsController(UserManager<User> userManager, IAuthenticationManager auth, ILoginService loginService)
        {
            LoginService = loginService;
            UserManager = userManager;
            Authentication = auth;
        }

        public UserManager<User> UserManager { get; private set; }

        //POST api/Account/Login
        [HttpPost]
        [AllowAnonymous]
        [Route("api/Account/Login")]
        public IHttpActionResult Login(CredentialsDto credentials)
        {
            var verifyResult = LoginService.VerifyCredentials(credentials);
            if (verifyResult.Id == null)
            {
                return BadRequest("Invalid authentication information");
            }
            UserLoginInfo loginInfo = new UserLoginInfo(credentials.Provider, verifyResult.Id);
            User user = UserManager.Find(loginInfo);
            if (user == null)
            {
                return BadRequest("No user found with those credentials");
            }
            var grant = LoginService.GenerateAccessGrant(user, credentials);
            return Ok(grant);
        }

        //POST api/Account/Register
        [OverrideAuthentication]
        [AllowAnonymous]
        [HostAuthentication(DefaultAuthenticationTypes.ExternalBearer)]
        [Route("api/Account/Register")]
        public IHttpActionResult Register(UserLoginDto userLoginDto)
        {
            var userDto = userLoginDto.User;
            var credentials = userLoginDto.Credentials;
            User user = new User()
            {
                BirthDate = userDto.BirthDate,
                Email = userDto.Email,
                FirstName = userDto.FirstName,
                Height = userDto.Height,
                Location = userDto.Location,
                LastName = userDto.LastName,
                PreferredName = userDto.PreferredName,
                Sex = userDto.Sex,
                Weight = userDto.Weight,
                UserName = userDto.UserName
            };
            var loginInfo = GetUserLogin(credentials);
            if (loginInfo == null)
            {
                return BadRequest("Invalid authentication data");
            }
            var extantUser = UserManager.Find(loginInfo);
            if (extantUser != null)
            {
                return BadRequest("You have already created an account!");
            }
            var result = UserManager.Create(user);
            if (result.Succeeded)
            {
                result = UserManager.AddLogin(user.Id, loginInfo);
                if (result.Succeeded)
                {
                    var identity = UserManager.CreateIdentity(user, DefaultAuthenticationTypes.ApplicationCookie);
                    return Ok();
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
        }
    }
}