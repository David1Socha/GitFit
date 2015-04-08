using HealthTrac.DataAccess;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Services
{
    public class ActivityReportService : IActivityReportService
    {

        private IActivityReportAccessor _acc;

        public ActivityReportService(IActivityReportAccessor acc)
        {
            _acc = acc;
        }

        public IEnumerable<Models.ActivityReport> GetActivityReports()
        {
            return _acc.GetActivityReports();
        }

        public IEnumerable<Models.ActivityReport> GetActivityReports(string uid)
        {
            return _acc.GetActivityReports(uid);
        }

        public Models.ActivityReport GetActivityReport(long id)
        {
            return _acc.GetActivityReport(id);
        }
    }
}