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

        Activity CreateActivity(Activity activity);
        Activity UpdateActivity(Activity activity);

        void DeleteActivity(long ID);

        IEnumerable<Activity> GetActivities();
    }
}