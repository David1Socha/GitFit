using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HealthTrac.Tests.Helpers
{
    public static class UserGoalGenerator
    {
        public static UserGoal GenerateUserGoal1()
        {
            return new UserGoal
            {
                DateAssigned = DateTime.Now,
                DateCompleted = DateTime.Now.AddDays(3),
                GoalID = 4,
                ID = 5,
                UserID = "kick ass",
            };
        }

        public static UserGoal GenerateUserGoal2()
        {
            return new UserGoal
            {
                DateAssigned = DateTime.Now,
                DateCompleted = DateTime.Now.AddDays(2),
                GoalID = 6,
                ID = 8,
                UserID = "ace baby ass all up in your face",
            };
        }

        public static UserGoal[] GenerateManyUserGoals()
        {
            return new UserGoal[] {
                GenerateUserGoal1(), GenerateUserGoal2()
            };
        }
    }
}
