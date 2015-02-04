using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models
{
    public interface IDateTrackingModel
    {
        DateTime DateCreated { get; set; }
        DateTime DateModified { get; set; }
    }
}