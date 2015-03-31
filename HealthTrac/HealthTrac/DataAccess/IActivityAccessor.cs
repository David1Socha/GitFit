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

        void CreateActivity(Activity activity);

        void DeleteActivity(long ID);

    }
}