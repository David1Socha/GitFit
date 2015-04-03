using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HealthTrac.Services
{
    public interface IEnergyLevelService
    {
        EnergyLevel GetEnergyLevel(long id);
        IEnumerable<EnergyLevel> GetEnergyLevels();
        EnergyLevel CreateEnergyLevel(EnergyLevel l);
        IEnumerable<EnergyLevel> GetEnergyLevels(String userId);
    }
}
