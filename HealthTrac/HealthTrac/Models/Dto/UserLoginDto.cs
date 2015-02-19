using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models.Dto
{
    public class UserLoginDto
    {
        public UserDto User { get; set; }
        public CredentialsDto Credentials { get; set; }
    }
}