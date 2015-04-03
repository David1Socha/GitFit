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
        IActivityService ActivityService { get; }
        IMembershipService MembershipService { get; }
        ITeamService TeamService { get; }
        IUserService UserService { get; }
        IBadgeService BadgeService { get; }
        IEnergyLevelService EnergyLevelService { get; }
        IGoalService GoalService { get; }
        IMealService MealService { get; }
        IPointService PointService { get; }
        IUserBadgeService UserBadgeService { get; }
        IUserGoalService UserGoalService { get; }
        void Save();

    }
}
