using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models.Dto
{
    public class ActivityReportDto
    {
        public DateTime Date { get; set; }
        public long ID { get; set; }
        public String UserID { get; set; }
        public long Steps { get; set; }
        public double Distance { get; set; }
        public double Duration { get; set; }

        public static ActivityReportDto FromActivityReport(ActivityReport ar)
        {
            return new ActivityReportDto()
            {
                Date = ar.Date,
                Distance = ar.Distance,
                Duration = ar.Duration,
                ID = ar.ID,
                Steps = ar.Steps,
                UserID = ar.UserID
            };
        }
    }
}