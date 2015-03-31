using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HealthTrac.Models
{
    public interface IDateTrackingModel
    {
        DateTime DateCreated { get; set; }
        DateTime DateModified { get; set; }
    }
}
