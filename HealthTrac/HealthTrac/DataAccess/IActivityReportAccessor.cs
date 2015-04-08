using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HealthTrac.DataAccess
{
    public interface IActivityReportAccessor
    {
        IEnumerable<ActivityReport> GetActivityReports();
        ActivityReport GetActivityReport(long id);
        IEnumerable<ActivityReport> GetActivityReports(String uid);
        ActivityReport AddOrUpdate(ActivityReport ar);
    }
}
