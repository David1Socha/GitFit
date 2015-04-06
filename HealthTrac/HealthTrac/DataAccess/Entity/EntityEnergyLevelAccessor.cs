using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Web;

namespace HealthTrac.DataAccess.Entity
{
    public class EntityEnergyLevelAccessor : IEnergyLevelAccessor
    {
        private ApplicationDbContext db;

        public EntityEnergyLevelAccessor(ApplicationDbContext db)
        {
            this.db = db;
        }

        public EnergyLevel GetEnergyLevel(long ID)
        {
            EnergyLevel l = db.EnergyLevels
                .Where(a => a.ID == ID)
                .FirstOrDefault();
            return l;
        }

        public IEnumerable<EnergyLevel> GetEnergyLevels()
        {
            IEnumerable<EnergyLevel> lvls = db.EnergyLevels
                .ToList();
            return lvls;
        }
        public IEnumerable<EnergyLevel> GetEnergyLevels(String userId)
        {
            IEnumerable<EnergyLevel> lvls = db.EnergyLevels
                .Where(l => l.UserID == userId)
                .ToList();
            return lvls;
        }



        public EnergyLevel CreateEnergyLevel(EnergyLevel l)
        {
            db.EnergyLevels.Add(l);
            return l;
        }
    }
}