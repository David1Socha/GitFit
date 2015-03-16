using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HealthTrac.Tests.Helpers
{
    public static class ProviderVerifyResultGenerator
    {
        public static IProviderVerifyResult GenFacebookVerifyResult()
        {
            return new FacebookVerifyResult
            {
                Id = "abc700",
                ProfilePictureUrl = "http://www.facebook.com/sample.jpg"
            };
        }

        public static IProviderVerifyResult GenTwitterVerifyResult()
        {
            return new TwitterVerifyResult
            {
                Id = "abc700",
                ProfilePictureUrl = "http://www.twitter.com/hi.jpg"
            };
        }
    }
}
