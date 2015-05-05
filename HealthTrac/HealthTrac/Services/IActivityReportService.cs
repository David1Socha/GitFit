using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HealthTrac.Services
{
    public interface IActivityReportService
    {
        IEnumerable<ActivityReport> GetActivityReports();

        IEnumerable<ActivityReport> GetActivityReports(String uid);
        ActivityReport GetActivityReport(long id);
    }
}
