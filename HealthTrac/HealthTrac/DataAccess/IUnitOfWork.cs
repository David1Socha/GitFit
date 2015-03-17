using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HealthTrac.DataAccess
{
    public interface IUnitOfWork : IDisposable
    {
        IActivityAccessor ActivityAccessor { get; }
        IMembershipAccessor MembershipAccessor { get; }
        ITeamAccessor TeamAccessor { get; }
        IUserAccessor UserAccessor { get; }
        void Save();

    }
}
