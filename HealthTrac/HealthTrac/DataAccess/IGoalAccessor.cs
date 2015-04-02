using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using HealthTrac.Models;

namespace HealthTrac.DataAccess
{
    public interface IGoalAccessor
    {
        Goal GetGoal(long id);
        IEnumerable<Goal> GetGoals();
        Goal CreateGoal(Goal g);

    }
}