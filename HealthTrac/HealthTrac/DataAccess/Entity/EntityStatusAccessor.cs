using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using HealthTrac.Models;
using System.Data.Entity;

namespace HealthTrac.DataAccess.Entity
{
    public class EntityStatusAccessor : IStatusAccessor
    {
        public bool CreateStatus(Status s)
        {
            using (var db = new ApplicationDbContext())
            {
                db.Statuses.Add(s);
                int changes = db.SaveChanges();
                return changes == 1;
            }
        }

        public bool UpdateStatus(Status s)
        {
            using (var db = new ApplicationDbContext())
            {
                db.Entry(s).State = EntityState.Modified;
                int changes = db.SaveChanges();
                return changes == 1;
            }
        }

        public Models.Status GetStatus(long id)
        {
            using (var db = new ApplicationDbContext())
            {
                var status = db.Statuses
                    .Where(s => s.ID == id)
                    .FirstOrDefault();
                return status;
            }
        }

        public IEnumerable<Models.Status> GetStatuses(User user)
        {
            using (var db = new ApplicationDbContext())
            {
                var statuses = db.Statuses
                    .Where(s => s.ApplicationUserID.Equals(user.Id));
                return statuses;
            }
        }
    }
}