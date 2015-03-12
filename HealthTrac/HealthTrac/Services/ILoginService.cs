using HealthTrac.Models;
using HealthTrac.Models.Dto;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HealthTrac.Services
{
    public interface ILoginService
    {
        IProviderVerifyResult VerifyCredentials(CredentialsDto credentials);
        AccessGrantDto GenerateAccessGrant(User user, CredentialsDto credentials);
    }
}
