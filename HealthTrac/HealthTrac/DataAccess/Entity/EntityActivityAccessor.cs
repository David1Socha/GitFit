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
        public bool CreateActivity(Activity activity)
        {
            using (var db = new ApplicationDbContext())
            {
                db.Activities.Add(activity);
                int objectsWritten = db.SaveChanges();
                return objectsWritten == 1;
            }
        }
        public bool DeleteActivity(long ID)
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
                    try
                    {
                        int changes = db.SaveChanges();
                        return changes == 1;
                    }
                    catch (DbUpdateConcurrencyException ex)
                    {
                        throw new ConcurrentUpdateException("The Activity you are trying to delete has been modified externally", ex);
                    }

                }
            }
        }


        public bool DeleteActivity(Activity activity)
        {
            using (var db = new ApplicationDbContext())
            {
                if (activity == null)
                {
                    return false;
                }
                db.Activities.Remove(activity);
                try
                {
                    int changes = db.SaveChanges();
                    return changes == 1;
                }
                catch (DbUpdateConcurrencyException ex)
                {
                    throw new ConcurrentUpdateException("The Activity you are trying to delete has been modified externally", ex);
                }

            }
        }
    }
}