using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models
{
    public class FacebookVerifyResult : IProviderVerifyResult
    {
        private static string PROFILE_BASE_URL = "https://graph.facebook.com/{0}/picture?type=square";
        [JsonProperty("id")]
        public string Id { get; set; }
        private string profilePictureUrl;
        public string ProfilePictureUrl { get { return profilePictureUrl ?? String.Format(PROFILE_BASE_URL, Id); } set { profilePictureUrl = value; } }
    }
}