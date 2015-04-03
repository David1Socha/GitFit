using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HealthTrac.Services
{
    public interface IUserGoalService
    {
        UserGoal GetUserGoal(long id);
        IEnumerable<UserGoal> GetUserGoals();
        IEnumerable<UserGoal> GetUserGoals(long goalId);
        IEnumerable<UserGoal> GetUserGoals(String userId);
        UserGoal GetUserGoal(long goalId, String userId);
        UserGoal CreateUserGoal(UserGoal ub);
        UserGoal UpdateUserGoal(UserGoal ub);
    }
}
