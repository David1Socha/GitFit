using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models
{
    public class FacebookVerifyResult
    {
        [JsonProperty("id")]
        public string ID { get; set; }
    }
}