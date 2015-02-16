using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using HealthTrac.Models;

namespace HealthTrac.DataAccess
{
    public interface IActivityAccessor
    {
        Activity GetActivity(long id);

        IEnumerable<Activity> GetActivities(String userId);

        bool CreateActivity(Activity activity);

        bool DeleteActivity(Activity activity);

    }
}