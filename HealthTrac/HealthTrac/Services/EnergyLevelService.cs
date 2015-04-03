using HealthTrac.DataAccess;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Services
{
    public class EnergyLevelService : IEnergyLevelService
    {

        private IEnergyLevelAccessor _acc;

        public EnergyLevelService(IEnergyLevelAccessor acc)
        {
            this._acc = acc;
        }

        public Models.EnergyLevel GetEnergyLevel(long id)
        {
            return _acc.GetEnergyLevel(id);
        }

        public IEnumerable<Models.EnergyLevel> GetEnergyLevels()
        {
            return _acc.GetEnergyLevels();
        }

        public Models.EnergyLevel CreateEnergyLevel(Models.EnergyLevel l)
        {
            return _acc.CreateEnergyLevel(l);
        }

        public IEnumerable<Models.EnergyLevel> GetEnergyLevels(string userId)
        {
            return _acc.GetEnergyLevels(userId);
        }
    }
}