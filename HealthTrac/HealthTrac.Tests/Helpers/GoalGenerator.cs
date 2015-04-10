using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HealthTrac.Tests.Helpers
{
    public static class GoalGenerator
    {
        public static Goal GenerateGoal1()
        {
            return new Goal
            {
                Field = Field.DISTANCE,
                ID = 7,
                Name = "James",
                Threshold = 0.75,
            };
        }

        public static Goal GenerateGoal2()
        {
            return new Goal
            {
                Field = Field.STEPS,
                ID = 9,
                Name = "Panda",
                Threshold = 0.6,
            };
        }

        public static Goal[] GenerateManyGoals()
        {
            return new Goal[] {
                GenerateGoal1(), GenerateGoal2()
            };
        }
    }
}
