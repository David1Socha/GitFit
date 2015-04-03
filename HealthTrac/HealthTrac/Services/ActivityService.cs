using HealthTrac.DataAccess;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Services
{
    public class ActivityService : IActivityService
    {

        private IActivityAccessor _acc;

        public ActivityService(IActivityAccessor acc)
        {
            _acc = acc;
        }

        public Models.Activity GetActivity(long id)
        {
            return _acc.GetActivity(id);
        }

        public IEnumerable<Models.Activity> GetActivities(string userId)
        {
            return _acc.GetActivities(userId);
        }

        public Models.Activity CreateActivity(Models.Activity activity)
        {
            return _acc.CreateActivity(activity);
        }

        public Models.Activity UpdateActivity(Models.Activity activity)
        {
            return _acc.UpdateActivity(activity);
        }

        public void DeleteActivity(long ID)
        {
            _acc.DeleteActivity(ID);
        }
    }
}