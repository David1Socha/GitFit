using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using HealthTrac.Models;

namespace HealthTrac.DataAccess
{
    public class EntityStatusAccessor : IStatusAccessor
    {
        public bool CreateStatus(Status s)
        {
            using(var db = new ApplicationDbContext) {
                db.Statuses.Add(s);
                int changes = db.SaveChanges();
                return changes == 1;
            }
        }

        public bool UpdateStatus(Status s)
        {
            throw new NotImplementedException();
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