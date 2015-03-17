using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Web;

namespace HealthTrac.DataAccess.Entity
{
    public class EntityActivityAccessor : IActivityAccessor
    {
        private ApplicationDbContext db;

        public EntityActivityAccessor(ApplicationDbContext db)
        {
            this.db = db;
        }

        public Activity GetActivity(long ID)
        {
            Activity activity = db.Activities
                .Where(a => a.ID == ID)
                .FirstOrDefault();
            return activity;
        }
        public IEnumerable<Activity> GetActivities(string userId)
        {
            IEnumerable<Activity> activities = db.Activities
                .Where(a => a.UserID.Equals(userId));
            return activities;
        }
        public void CreateActivity(Activity activity)
        {
            db.Activities.Add(activity);
        }
        public void DeleteActivity(long ID)
        {
            Activity activity = db.Activities
                .Where(a => a.ID == ID)
                .FirstOrDefault();
            if (activity != null)
            {
                db.Activities.Remove(activity);
            }
        }

    }
}