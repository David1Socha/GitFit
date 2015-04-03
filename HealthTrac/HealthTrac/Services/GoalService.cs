using HealthTrac.DataAccess;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Services
{
    public class GoalService : IGoalService
    {

        private IGoalAccessor _acc;

        public GoalService(IGoalAccessor acc)
        {
            _acc = acc;
        }
        public Models.Goal GetGoal(long id)
        {
            return _acc.GetGoal(id);
        }

        public IEnumerable<Models.Goal> GetGoals()
        {
            return _acc.GetGoals();
        }

        public Models.Goal CreateGoal(Models.Goal g)
        {
            return _acc.CreateGoal(g);
        }
    }
}