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
        AccessGrantDto genBasicGrant()
        {
            return new AccessGrantDto
            {
                AccessToken = "abc",
                Expires = DateTime.Now,
                ExpiresIn = "9001",
                ID = "a2b4m6",
                Issued = DateTime.Now,
                TokenType = "bearer",
                UserName = "shrek"
            };
        }
    }
}
