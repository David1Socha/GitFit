using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HealthTrac.Tests.Helpers
{
    public static class ActivityReportGenerator
    {
        public static ActivityReport GenerateActivityReport1()
        {
            return new ActivityReport
            {
                Date = DateTime.Now,
                ID = 7, 
                UserID = "xyz",
                Steps = 60,
                Distance = 60,
                Duration = 60
            };
        }

        public static ActivityReport GenerateActivityReport2()
        {
            return new ActivityReport
            {
                Date = DateTime.Now.AddDays(-2),
                ID = 13,
                UserID = "apple",
                Steps = 120,
                Distance = 120,
                Duration = 120
            };
        }

        public static ActivityReport[] GenerateManyActivityReports()
        {
            return new ActivityReport[] {
                GenerateActivityReport1(), GenerateActivityReport2()
            };
        }
    }
}
