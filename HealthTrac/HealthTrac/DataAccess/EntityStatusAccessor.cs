using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using HealthTrac.Models;
using System.Data.Entity;

namespace HealthTrac.DataAccess
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
            throw new NotImplementedException();
        }

        public IEnumerable<Models.Status> GetStatuses(ApplicationUser user)
        {
            throw new NotImplementedException();
        }
    }
}