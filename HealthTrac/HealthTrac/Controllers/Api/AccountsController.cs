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
using System.Net;
using System.Security.Claims;
using Newtonsoft.Json.Linq;
using OAuth;
using System.Net.Http.Headers;

namespace HealthTrac.Controllers.Api
{
    //TODO remove hardcoded dependency on entity context (IOC container?)
    public class AccountsController : ApiController
    {

        private static readonly string FACEBOOK_BASE_URL = "https://graph.facebook.com/me?access_token=",
            TWITTER_BASE_URL = "https://api.twitter.com/1.1/account/verify_credentials.json?",
            TWITTER_CONSUMER_KEY = "fHG53L9zDOTltJ77JPjFGzxf8",
            TWITTER_CONSUMER_SECRET = "QbX7YXFiZb49HQP0jz0H72pKp5pBUEgJuJBswIroh29NjUrfXU";

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
        public IHttpActionResult Register(UserLoginDto userLoginDto)
        {
            var userDto = userLoginDto.User;
            var credentials = userLoginDto.Credentials;
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
            var loginInfo = GetUserLogin(credentials);
            if (loginInfo == null)
            {
                return BadRequest();
            }
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

        private UserLoginInfo GetUserLogin(CredentialsDto credentials)
        {
            String provider = credentials.Provider;
            if (provider == CredentialsDto.FACEBOOK)
            {
                string id = GetFacebookId(credentials.Token);
                var loginInfo = id == null ? null : new UserLoginInfo(id, provider);
                return loginInfo;
            }
            else if (provider == CredentialsDto.TWITTER)
            {
                string id = GetTwitterId(credentials.Token, credentials.Secret);
                var loginInfo = id == null ? null : new UserLoginInfo(id, provider);
                return loginInfo;
            }
            else
            {
                return null;
            }
        }

        private static string GetTwitterId(string token, string secret)
        {
            OAuthRequest oAuth = OAuthRequest.ForProtectedResource("GET", TWITTER_CONSUMER_KEY, TWITTER_CONSUMER_SECRET, token, secret);
            oAuth.RequestUrl = TWITTER_BASE_URL;
            var auth = oAuth.GetAuthorizationQuery();
            var uri = new Uri(oAuth.RequestUrl + auth);
            var client = new HttpClient();
            var response = client.GetAsync(uri).Result;
            if (response.IsSuccessStatusCode)
            {
                var content = response.Content.ReadAsStringAsync().Result;
                dynamic data = JObject.Parse(content);
                return data.id_str;
            }
            return null;
        }

        private static string GetFacebookId(string token)
        {

            var path = FACEBOOK_BASE_URL + token;
            var client = new HttpClient();
            var uri = new Uri(path);
            var response = client.GetAsync(uri).Result;
            if (response.IsSuccessStatusCode)
            {
                var content = response.Content.ReadAsStringAsync().Result;
                dynamic data = JObject.Parse(content);
                return data.id;
            }
            return null;
        }

    }
}