using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using HealthTrac.Models;

namespace HealthTrac.DataAccess
{
    public interface IActivityAccessor
    {
        public Activity GetActivity(long id);

        public IEnumerable<Activity> GetActivities(String userId);

        public bool CreateActivity(Activity activity);

        public bool DeleteActivity(Activity activity);

    }
}