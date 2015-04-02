using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Web;

namespace HealthTrac.DataAccess.Entity
{
    public class EntityUserGoalAccessor : IUserGoalAccessor
    {
        private ApplicationDbContext db;

        public EntityUserGoalAccessor(ApplicationDbContext db)
        {
            this.db = db;
        }

        public UserGoal GetUserGoal(long ID)
        {
            UserGoal g = db.UserGoals
                .Where(a => a.ID == ID)
                .FirstOrDefault();
            return g;
        }

        public IEnumerable<UserGoal> GetUserGoals()
        {
            IEnumerable<UserGoal> gs = db.UserGoals
                .ToList();
            return gs;
        }

        public IEnumerable<UserGoal> GetUserGoals(long goalId)
        {
            IEnumerable<UserGoal> ubs = db.UserGoals
                .Where(u => u.GoalID == goalId)
                .ToList();
            return ubs;
        }

        public IEnumerable<UserGoal> GetUserGoals(string userId)
        {
            IEnumerable<UserGoal> ugs = db.UserGoals
                .Where(u => u.UserID == userId)
                .ToList();
            return ugs;
        }

        public UserGoal GetUserGoal(long goalId, string userId)
        {
            UserGoal ug = db.UserGoals
                .Where(u => u.GoalID == goalId && u.UserID == userId)
                .FirstOrDefault();
            return ug;
        }

        public UserGoal CreateUserGoal(UserGoal ub)
        {
            db.UserGoals.Add(ub);
            return ub;
        }

        public UserGoal UpdateUserGoal(UserGoal ug)
        {
            db.Entry(ug).State = System.Data.Entity.EntityState.Modified;
            return ug;
        }
    }
}