using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using HealthTrac.Models;

namespace HealthTrac.DataAccess
{
    public interface IStatusAccessor
    {
        bool CreateStatus(Status s);

        bool UpdateStatus(Status s);

        Status GetStatus(long id);

        IEnumerable<Status> GetStatuses(User user);
    }
}
