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
using System.Web.Security;

namespace HealthTrac.Controllers.Api
{
    [Authorize]
    public class AccountsController : ApiController
    {
        
        private ILoginService LoginService { get; set; }

        private IFacebookService FacebookService { get; set; }
        private ITwitterService TwitterService { get; set; }

        public AccountsController(IUserManager userManager, ILoginService loginService)
        {
            LoginService = loginService;
            UserManager = userManager;
        }

        public IUserManager UserManager { get; private set; }

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
        
        [HttpPost]
        [Route("api/Account/LogOff")]
        public IHttpActionResult LogOff()
        {
            System.Web.HttpContext.Current.Response.Cookies.Add(new HttpCookie(".AspNet.ApplicationCookie", ""));
            return Ok();
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
            User user = userDto.ToUser();
            IProviderVerifyResult res = LoginService.VerifyCredentials(credentials);
            user.ProfilePicture = res.ProfilePictureUrl;
            UserLoginInfo loginInfo = new UserLoginInfo(credentials.Provider, res.Id); //TODO add user prof picture to user once model supports that
            if (loginInfo == null)
            {
                return BadRequest("Invalid authentication data");
            }
            var extantUser = UserManager.Find(loginInfo);
            if (extantUser != null)
            {
                return BadRequest("You have already created an account!");
            }
            bool created = LoginService.CreateAccount(user, loginInfo);
            if (created)
            {
                return Ok();
            }
            else
            {
                return BadRequest();
            }
        }
    }
}