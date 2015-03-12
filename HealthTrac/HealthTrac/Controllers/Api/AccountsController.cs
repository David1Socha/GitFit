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

        private IFacebookService FacebookService { get; set; }
        private ITwitterService TwitterService { get; set; }

        public AccountsController(UserManager<User> userManager, IAuthenticationManager auth)
        {
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
            var tokenExpirationTimeSpan = TimeSpan.FromDays(14);
            var loginInfo = GetUserLogin(credentials);
            if (loginInfo == null)
            {
                return BadRequest("Invalid authentication information");
            }
            User user = UserManager.Find(loginInfo);
            if (user == null)
            {
                return BadRequest();
            }
            var identity = new ClaimsIdentity(Startup.OAuthBearerOptions.AuthenticationType);
            identity.AddClaim(new Claim(ClaimTypes.Name, user.Id, null, credentials.Provider));
            identity.AddClaim(new Claim(ClaimTypes.NameIdentifier, user.Id, null, "LOCAL_AUTHORITY"));
            AuthenticationTicket ticket = new AuthenticationTicket(identity, new AuthenticationProperties());
            var currentUtc = new Microsoft.Owin.Infrastructure.SystemClock().UtcNow;
            ticket.Properties.IssuedUtc = currentUtc;
            ticket.Properties.ExpiresUtc = currentUtc.Add(tokenExpirationTimeSpan);
            var accesstoken = Startup.OAuthBearerOptions.AccessTokenFormat.Protect(ticket);
            Authentication.SignIn(identity);
            AccessGrantDto grant = new AccessGrantDto
            {
                UserName = user.UserName,
                AccessToken = accesstoken,
                TokenType = "bearer",
                ExpiresIn = tokenExpirationTimeSpan.Seconds.ToString(),
                Issued = ticket.Properties.IssuedUtc.Value.DateTime,
                Expires = ticket.Properties.ExpiresUtc.Value.DateTime,
                ID = user.Id,
            };
            return Ok(grant);
        }

        [Route("api/Account/Logout")]
        [HttpPost]
        public IHttpActionResult Logout()
        {
            Authentication.SignOut(CookieAuthenticationDefaults.AuthenticationType);
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

        private UserLoginInfo GetUserLogin(CredentialsDto credentials)
        {
            String provider = credentials.Provider;
            if (provider == CredentialsDto.FACEBOOK)
            {
                FacebookVerifyResult res = FacebookService.VerifyCredentials(credentials.Token);
                string id = res.ID;
                var loginInfo = id == null ? null : new UserLoginInfo(provider, id);
                return loginInfo;
            }
            else if (provider == CredentialsDto.TWITTER)
            {
                TwitterVerifyResult res = TwitterService.VerifyCredentials(credentials.Token, credentials.Secret);
                string id = res.IdString;
                var loginInfo = id == null ? null : new UserLoginInfo(provider, id);
                return loginInfo;
            }
            else
            {
                return null;
            }
        }

    }
}