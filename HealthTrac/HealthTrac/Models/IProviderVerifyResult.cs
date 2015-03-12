using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace HealthTrac.Models
{
    public interface IProviderVerifyResult
    {
        string Id { get; set; }
        string ProfilePictureUrl { get; }
    }
}
