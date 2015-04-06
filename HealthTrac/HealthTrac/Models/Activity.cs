using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models
{
    public enum ActivityType
    {
        WALKING, JOGGING, RUNNING, BIKING
    }

    public class Activity
    {

        public double Duration { get; set; }
        public long Steps { get; set; }
        public double Distance { get; set; }
        public DateTime StartDate { get; set; }
        public long ID { get; set; }
        public String UserID { get; set; }
        public ActivityType Type { get; set; }
        public virtual User User { get; set; }
        public virtual ICollection<Point> Points { get; set; }
    }
}