using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HealthTrac.Tests.Helpers
{
    public static class ActivityGenerator
    {
        public static Activity GenerateActivity1()
        {
            return new Activity
            {
                Duration = 60,
                Steps = 60,
                Distance = 60,
                StartDate = DateTime.Now,
                ID = 15,
                UserID = "3",
                Type = ActivityType.BIKING
            };
        }

        public static Activity GenerateActivity2()
        {
            return new Activity
            {
                Duration = 120,
                Steps = 120,
                Distance = 120,
                StartDate = DateTime.Now.AddDays(-1),
                ID = 17,
                UserID = "13",
                Type = ActivityType.JOGGING
            };
        }

        public static Activity[] GenerateManyActivities()
        {
            return new Activity[] {
                GenerateActivity1(), GenerateActivity2()
            };
        }
    }
}
