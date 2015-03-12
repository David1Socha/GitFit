using HealthTrac.Models;
using HealthTrac.Models.Dto;
using Microsoft.Owin.Security;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Claims;
using System.Web;

namespace HealthTrac.Services
{
    public class LoginService : ILoginService
    {
        private static readonly string TOKEN_TYPE = "bearer";

        private ITwitterService TwitterService { get; set; }
        private IFacebookService FacebookService { get; set; }
        private IAuthenticationManager Authentication { get; set; }

        public LoginService(IFacebookService facebookService, ITwitterService twitterService, IAuthenticationManager authentication)
        {
            Authentication = authentication;
            TwitterService = twitterService;
            FacebookService = facebookService;
        }

        public Models.IProviderVerifyResult VerifyCredentials(CredentialsDto credentials)
        {
            IProviderVerifyResult res = null;
            String provider = credentials.Provider;
            if (provider == CredentialsDto.FACEBOOK)
            {
                FacebookService.Token = credentials.Token;
                res = FacebookService.VerifyCredentials();
            }
            else if (provider == CredentialsDto.TWITTER)
            {
                TwitterService.Secret = credentials.Secret;
                TwitterService.Token = credentials.Token;
                res = TwitterService.VerifyCredentials();
            }
            return res;
        }

        public Models.AccessGrantDto GenerateAccessGrant(User user, CredentialsDto credentials)
        {
            var tokenExpirationTimeSpan = TimeSpan.FromDays(14);
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
                AccessToken = accesstoken,
                Expires = ticket.Properties.ExpiresUtc.Value.DateTime,
                ExpiresIn = tokenExpirationTimeSpan.Seconds.ToString(),
                Issued = ticket.Properties.IssuedUtc.Value.DateTime,
                ID = user.Id,
                UserName = user.UserName,
                TokenType = TOKEN_TYPE
            };
            return grant;
        }
    }
}