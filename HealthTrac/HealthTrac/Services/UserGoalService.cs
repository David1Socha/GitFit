using HealthTrac.DataAccess;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Services
{
    public class UserGoalService : IUserGoalService
    {

        private IUserGoalAccessor _acc;

        public UserGoalService(IUserGoalAccessor acc)
        {
            _acc = acc;
        }
        public Models.UserGoal GetUserGoal(long id)
        {
            return _acc.GetUserGoal(id);
        }

        public IEnumerable<Models.UserGoal> GetUserGoals()
        {
            return _acc.GetUserGoals();
        }

        public IEnumerable<Models.UserGoal> GetUserGoals(long goalId)
        {
            return _acc.GetUserGoals(goalId);
        }

        public IEnumerable<Models.UserGoal> GetUserGoals(string userId)
        {
            return _acc.GetUserGoals(userId);
        }

        public Models.UserGoal GetUserGoal(long goalId, string userId)
        {
            return _acc.GetUserGoal(goalId, userId);
        }

        public Models.UserGoal CreateUserGoal(Models.UserGoal ub)
        {
            return _acc.CreateUserGoal(ub);
        }

        public Models.UserGoal UpdateUserGoal(Models.UserGoal ub)
        {
            return _acc.UpdateUserGoal(ub);
        }
    }
}