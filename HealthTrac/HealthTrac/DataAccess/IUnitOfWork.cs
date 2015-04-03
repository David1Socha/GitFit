using HealthTrac.Services;
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
        IMembershipService MembershipService { get; }
        ITeamService TeamService { get; }
        IUserService UserService { get; }
        IBadgeAccessor BadgeAccessor { get; }
        IEnergyLevelAccessor EnergyLevelAccessor { get; }
        IGoalAccessor GoalAccessor { get; }
        IMealAccessor MealAccessor { get; }
        IPointAccessor PointAccessor { get; }
        IUserBadgeAccessor UserBadgeAccessor { get; }
        IUserGoalAccessor UserGoalAccessor { get; }
        void Save();

    }
}
