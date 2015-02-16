using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.DataAccess.Entity
{
    public class EntityActivityAccessor : IActivityAccessor
    {
        public Activity GetActivity(long ID)
        {
            using (var db = new ApplicationDbContext())
            {
                Activity activity = db.Activities
                    .Where(a => a.ID == ID)
                    .FirstOrDefault();
                return activity;
            }
        }
        public IEnumerable<Activity> GetActivities(string userId)
        {
            using (var db = new ApplicationDbContext())
            {
                IEnumerable<Activity> activities = db.Activities
                    .Where(a => a.UserID.Equals(userId));
                return activities;
            }
        }
        public Boolean CreateActivity(Activity activity)
        {
            using (var db = new ApplicationDbContext())
            {
                db.Activities.Add(activity);
                int objectsWritten = db.SaveChanges();
                return objectsWritten == 1;
            }
        }
        public Boolean DeleteActivity(long ID)
        {
            using (var db = new ApplicationDbContext())
            {
                Activity activity = db.Activities
                    .Where(a => a.ID == ID)
                    .FirstOrDefault();
                if (activity == null)
                {
                    return false;
                }
                else
                {
                    db.Activities.Remove(activity);
                    int changes = db.SaveChanges();
                    return changes == 1;
                }
            }
        }
    }
}