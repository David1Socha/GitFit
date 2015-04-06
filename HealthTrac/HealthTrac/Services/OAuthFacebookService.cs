using HealthTrac.Models;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Web;

namespace HealthTrac.Services
{
    public class OAuthFacebookService : IFacebookService
    {

        private static readonly string FACEBOOK_BASE_URL = "https://graph.facebook.com/me?access_token=";

        public String Token { get; set; }

        public FacebookVerifyResult VerifyCredentials()
        {
            var path = FACEBOOK_BASE_URL + Token;
            var client = new HttpClient();
            var uri = new Uri(path);
            var response = client.GetAsync(uri).Result;
            if (response.IsSuccessStatusCode)
            {
                var content = response.Content.ReadAsStringAsync().Result;
                return JsonConvert.DeserializeObject<FacebookVerifyResult>(content);
            }
            return null;
        }
    }
}