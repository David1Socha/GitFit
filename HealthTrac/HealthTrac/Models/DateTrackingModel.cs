using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models
{
    public abstract class DateTrackingModel : IDateTrackingModel
    {
        public DateTrackingModel()
        {
            DateCreated = DateTime.Now;
            DateModified = DateTime.Now;
        }
        public DateTime DateCreated { get; set; }
        public DateTime DateModified { get; set; }
    }
}