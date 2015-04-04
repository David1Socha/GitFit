using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models
{
    public class SampleActivity
    {
        public int RID { get; set; }
        public string ID { get; set; }
        public string Sex { get; set; }
        public string BirthDate { get; set; }
        public double Height { get; set; }
        public double Weight { get; set; }
        public double Duration { get; set; }
        public double Distance { get; set; }
        public long Steps { get; set; }
        public String Type { get; set; }

        public Activity ToActivity(long id)
        {
            return new Activity()
            {
                Distance = Distance,
                Duration = Duration,
                ID = id,
                Steps = Steps,
                Type = Type == "W" ? ActivityType.WALKING :
                       Type == "B" ? ActivityType.BIKING :
                       Type == "J" ? ActivityType.JOGGING :
                                     ActivityType.RUNNING,
                StartDate = DateTime.Now,
            };
        }
    }
}