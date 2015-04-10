using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HealthTrac.Tests.Helpers
{
    public static class EnergyLevelGenerator
    {
        public static EnergyLevel GenerateEnergyLevel1()
        {
            return new EnergyLevel
            {
                DateCreated = DateTime.Now,
                ID = 7,
                Mood = Mood.EXCITED,
                UserID = "koalas",
            };
        }

        public static EnergyLevel GenerateEnergyLevel2()
        {
            return new EnergyLevel
            {
                DateCreated = DateTime.Now.AddDays(-5),
                ID = 9,
                Mood = Mood.PROUD,
                UserID = "id",
            };
        }

        public static EnergyLevel[] GenerateManyEnergyLevels()
        {
            return new EnergyLevel[] {
                GenerateEnergyLevel1(), GenerateEnergyLevel2()
            };
        }
    }
}
