using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using HealthTrac.Models;

namespace HealthTrac.DataAccess
{
    public interface IEnergyLevelAccessor
    {
        EnergyLevel GetEnergyLevel(long id);
        IEnumerable<EnergyLevel> GetEnergyLevels();
        EnergyLevel CreateEnergyLevel(EnergyLevel l);
        IEnumerable<EnergyLevel> GetEnergyLevels(String userId);

    }
}