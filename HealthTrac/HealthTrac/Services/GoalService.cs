using HealthTrac.DataAccess;
using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Services
{
    public class GoalService : IGoalService
    {

        private IGoalAccessor _acc;
        private IUserGoalAccessor _ugAcc;

        public GoalService(IGoalAccessor acc, IUserGoalAccessor ugAcc)
        {
            _ugAcc = ugAcc;
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

        public Models.Goal CreateGoal(Models.Goal g, String uid)
        {
            var createdGoal = _acc.CreateGoal(g);
            var ug = new UserGoal
            {
                GoalID = createdGoal.ID,
                DateAssigned = DateTime.Now,
                UserID = uid,
            };
            _ugAcc.CreateUserGoal(ug);
            return createdGoal;
        }
    }
}