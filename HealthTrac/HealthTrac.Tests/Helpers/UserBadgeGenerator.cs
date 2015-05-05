using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HealthTrac.Tests.Helpers
{
    public static class UserBadgeGenerator
    {
        public static UserBadge GenerateUserBadge1()
        {
            return new UserBadge
            {
                BadgeID = 5,
                DateCompleted = DateTime.Now,
                ID = 7,
                UserID = "apples",
            };
        }

        public static UserBadge GenerateUserBadge2()
        {
            return new UserBadge
            {
                BadgeID = 55,
                DateCompleted = DateTime.Now.AddDays(-7),
                ID = 10,
                UserID = "bananas",
            };
        }

        public static UserBadge[] GenerateManyUserBadges()
        {
            return new UserBadge[] {
                GenerateUserBadge1(), GenerateUserBadge2()
            };
        }
    }
}
