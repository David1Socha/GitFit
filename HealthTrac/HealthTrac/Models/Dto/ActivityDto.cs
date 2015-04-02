using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models.Dto
{
    public class ActivityDto
    {
        public double Duration { get; set; }
        public long Steps { get; set; }
        public double Distance { get; set; }
        public DateTime StartDate { get; set; }
        public long ID { get; set; }
        public String UserID { get; set; }
        public ActivityType Type { get; set; }

        public static ActivityDto FromActivity(Activity a)
        {
            return new ActivityDto()
            {
                Duration = a.Duration,
                Distance = a.Distance,
                ID = a.ID,
                StartDate = a.StartDate,
                Steps = a.Steps,
                Type = a.Type,
                UserID = a.UserID,
            };
        }
    }
}