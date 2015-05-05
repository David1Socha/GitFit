using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models
{
    public class ActivityReport
    {
        public DateTime Date { get; set; }
        public long ID { get; set; }
        public String UserID { get; set; }
        public long Steps { get; set; }
        public double Distance { get; set; }
        public double Duration { get; set; }
        public virtual User User { get; set; }
    }
}