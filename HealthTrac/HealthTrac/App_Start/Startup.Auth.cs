using HealthTrac.DataAccess.Entity;
using HealthTrac.Models;
using HealthTrac.Providers;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.EntityFramework;
using Microsoft.Owin;
using Microsoft.Owin.Security.Cookies;
using Microsoft.Owin.Security.OAuth;
using Owin;
using System;
using System.Linq;
using System.Threading.Tasks;

namespace HealthTrac
{
    public partial class Startup
    {
        static Startup()
        {
            PublicClientId = "self";

            //UserManagerFactory = () => new UserManager<ApplicationUser>(new UserStore<ApplicationUser>(new ApplicationDbContext()));
            UserManagerFactory = () =>
            {
                var userManager = new UserManager<IdentityUser>(new UserStore<IdentityUser>(new ApplicationDbContext()));
                userManager.UserValidator = new UserValidator<IdentityUser>(userManager) { AllowOnlyAlphanumericUserNames = false };
                return userManager;
            };

            OAuthOptions = new OAuthAuthorizationServerOptions
            {
                TokenEndpointPath = new PathString("/Token"),
                Provider = new ApplicationOAuthProvider(PublicClientId, UserManagerFactory),
                AuthorizeEndpointPath = new PathString("/api/Account/ExternalLogin"),
                AccessTokenExpireTimeSpan = TimeSpan.FromDays(14),
                AllowInsecureHttp = true
            };

            OAuthBearerOptions = new OAuthBearerAuthenticationOptions();
            OAuthBearerOptions.AccessTokenFormat = OAuthOptions.AccessTokenFormat;
            OAuthBearerOptions.AccessTokenProvider = OAuthOptions.AccessTokenProvider;
            OAuthBearerOptions.AuthenticationMode = OAuthOptions.AuthenticationMode;
            OAuthBearerOptions.AuthenticationType = OAuthOptions.AuthenticationType;
            OAuthBearerOptions.Description = OAuthOptions.Description;
            OAuthBearerOptions.Provider = new CustomBearerAuthenticationProvider();
            OAuthBearerOptions.SystemClock = OAuthOptions.SystemClock;
        }

        public static OAuthAuthorizationServerOptions OAuthOptions { get; private set; }

        public static string PublicClientId { get; private set; }
        public static Func<UserManager<IdentityUser>> UserManagerFactory { get; set; }
        public static OAuthBearerAuthenticationOptions OAuthBearerOptions { get; private set; }

        public void ConfigureAuth(IAppBuilder app)
        {
            // Enable the application to use a cookie to store information for the signed in user
            app.UseCookieAuthentication(new CookieAuthenticationOptions
            {
                AuthenticationType = DefaultAuthenticationTypes.ApplicationCookie,
                LoginPath = new PathString("/Account/Login"),
                Provider = new CookieAuthenticationProvider()
                {
                    OnApplyRedirect = ctx =>
                        {
                            if (!ctx.Request.Path.StartsWithSegments(new PathString("/api"))) //Prevent login redirect when using api
                            {
                                ctx.Response.Redirect(ctx.RedirectUri);
                            }
                        }
                }
            });
            // Use a cookie to temporarily store information about a user logging in with a third party login provider
            app.UseExternalSignInCookie(DefaultAuthenticationTypes.ExternalCookie);
            OAuthBearerAuthenticationExtensions.UseOAuthBearerAuthentication(app, OAuthBearerOptions);


            app.UseTwitterAuthentication(
               consumerKey: "REDACTED",
               consumerSecret: "REDACTED");

            app.UseFacebookAuthentication(
               appId: "REDACTED",
               appSecret: "REDACTED");

        }

        public class CustomBearerAuthenticationProvider : OAuthBearerAuthenticationProvider
        {
            public override Task ValidateIdentity(OAuthValidateIdentityContext context)
            {
                var claims = context.Ticket.Identity.Claims;
                if (claims.Count() == 0 || claims.Any(claim => claim.Issuer != "Facebook" && claim.Issuer != "Twitter" && claim.Issuer != "LOCAL_AUTHORITY"))
                    context.Rejected();
                return Task.FromResult<object>(null);
            }
        }
    }
}