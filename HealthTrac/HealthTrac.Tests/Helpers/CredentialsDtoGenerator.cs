using HealthTrac.Models.Dto;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HealthTrac.Tests.Helpers
{
    public static class CredentialsDtoGenerator
    {
        public static CredentialsDto GenFacebookCredentials()
        {
            return new CredentialsDto
            {
                Provider = "Facebook",
                Token = "2349"
            };
        }

        public static CredentialsDto GenTwitterCredentials()
        {
            return new CredentialsDto
            {
                Provider = "Twitter",
                Secret = "290935",
                Token = "23gg4990n"
            };
        }
    }
}
