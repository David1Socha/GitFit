using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using HealthTrac.Models;

namespace HealthTrac.DataAccess
{
    interface IStatusAccessor
    {
        public bool CreateStatus(Status s);

        public bool UpdateStatus(Status s);

        public Status GetStatus(long id);

        public IEnumerable<Status> GetStatuses(ApplicationUser user);
    }
}
