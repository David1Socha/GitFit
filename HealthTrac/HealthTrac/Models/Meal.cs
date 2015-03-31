using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models
{
    public class Meal : DateTrackingModel
    {
        public long ID { get; set; }
        public String UserID { get; set; }
        public int Calories { get; set; }
        public virtual User User { get; set; }
    }
}