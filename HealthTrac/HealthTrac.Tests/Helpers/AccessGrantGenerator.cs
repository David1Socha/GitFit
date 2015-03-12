using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HealthTrac.Tests.Helpers
{
    public static class AccessGrantGenerator
    {
        public static AccessGrantDto GenFacebookGrant()
        {
            return new AccessGrantDto
            {
                AccessToken = "abc",
                Expires = DateTime.Now,
                ExpiresIn = "9001",
                ID = "qwerty",
                Issued = DateTime.Now,
                TokenType = "bearer",
                UserName = "xXxOgrelordxXx"
            };
        }

        public static AccessGrantDto GenTwitterGrant()
        {
            return new AccessGrantDto
            {
                AccessToken = "ash2",
                Expires = DateTime.Now,
                ExpiresIn = "9001",
                ID = "poiuy",
                Issued = DateTime.Now,
                TokenType = "bearer",
                UserName = "John"
            };
        }

    }
}
