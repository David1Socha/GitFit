using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HealthTrac.Tests.Helpers
{
    public static class BadgeGenerator
    {
        public static Badge GenerateBadge1()
        {
            return new Badge
            {
                Field = Field.DISTANCE,
                ID = 7,
                Name = "James",
                Threshold = 0.75,
            };
        }

        public static Badge GenerateBadge2()
        {
            return new Badge
            {
                Field = Field.STEPS,
                ID = 9,
                Name = "Panda",
                Threshold = 0.6,
            };
        }

        public static Badge[] GenerateManyBadges()
        {
            return new Badge[] {
                GenerateBadge1(), GenerateBadge2()
            };
        }
    }
}
