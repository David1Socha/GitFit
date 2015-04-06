using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Web;

namespace HealthTrac.DataAccess.Entity
{
    public class EntityGoalAccessor : IGoalAccessor
    {
        private ApplicationDbContext db;

        public EntityGoalAccessor(ApplicationDbContext db)
        {
            this.db = db;
        }

        public Goal GetGoal(long ID)
        {
            Goal g = db.Goals
                .Where(a => a.ID == ID)
                .FirstOrDefault();
            return g;
        }

        public IEnumerable<Goal> GetGoals()
        {
            IEnumerable<Goal> gls = db.Goals
                .ToList();
            return gls;
        }

        public Goal CreateGoal(Goal g)
        {
            db.Goals.Add(g);
            return g;
        }
    }
}