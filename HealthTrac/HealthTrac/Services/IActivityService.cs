using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HealthTrac.Services
{
    public interface IActivityService
    {
        Activity GetActivity(long id);
        IEnumerable<Activity> GetActivities();
        IEnumerable<Activity> GetActivities(String userId);
        Activity CreateActivity(Activity activity, String userId);
        Activity UpdateActivity(Activity activity);

        void DeleteActivity(long ID);
    }
}
