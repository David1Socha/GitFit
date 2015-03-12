using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models
{
    public class TwitterVerifyResult
    {
        [JsonProperty("id_str")]
        public string IdString { get; set; }
        [JsonProperty("profile_image_url")]
        public string ProfilePictureUrl { get; set; }
    }
}