using HealthTrac.Models;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using Newtonsoft.Json.Serialization;
using OAuth;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace HealthTrac.Services
{
    public class OAuthTwitterService : ITwitterService
    {

        public string Token { get; set; }
        public string Secret { get; set; }

        private static readonly string
        TWITTER_BASE_URL = "https://api.twitter.com/1.1/account/verify_credentials.json?",
            TWITTER_CONSUMER_KEY = "fHG53L9zDOTltJ77JPjFGzxf8",
            TWITTER_CONSUMER_SECRET = "QbX7YXFiZb49HQP0jz0H72pKp5pBUEgJuJBswIroh29NjUrfXU";

        public TwitterVerifyResult VerifyCredentials()
        {
            OAuthRequest oAuth = OAuthRequest.ForProtectedResource("GET", TWITTER_CONSUMER_KEY, TWITTER_CONSUMER_SECRET, Token, Secret);
            oAuth.RequestUrl = TWITTER_BASE_URL;
            var auth = oAuth.GetAuthorizationQuery();
            var uri = new Uri(oAuth.RequestUrl + auth);
            var client = new HttpClient();
            var response = client.GetAsync(uri).Result;
            if (response.IsSuccessStatusCode)
            {
                var content = response.Content.ReadAsStringAsync().Result;
                return JsonConvert.DeserializeObject<TwitterVerifyResult>(content);
            }
            return null;
        }
    }
}
