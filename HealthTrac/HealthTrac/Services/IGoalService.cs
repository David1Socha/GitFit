using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HealthTrac.Services
{
    public interface IGoalService
    {
        Goal GetGoal(long id);
        IEnumerable<Goal> GetGoals();
        Goal CreateGoal(Goal g);
    }
}
