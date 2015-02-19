using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models.Dto
{
    public class CredentialsDto
    {
        public static readonly string FACEBOOK = "Facebook",
            TWITTER = "Twitter";
        public string Provider { get; set; }
        public string Secret { get; set; }
        public string Token { get; set; }
    }
}