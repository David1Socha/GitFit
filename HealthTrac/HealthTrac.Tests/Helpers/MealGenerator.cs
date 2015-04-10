using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HealthTrac.Tests.Helpers
{
    public static class MealGenerator
    {
        public static Meal GenerateMeal1()
        {
            return new Meal
            {
                Calories = 543,
                DateCreated = DateTime.Now,
                ID = 7,
                UserID = "apples",
            };
        }

        public static Meal GenerateMeal2()
        {
            return new Meal
            {
                Calories = 657,
                DateCreated = DateTime.Now.AddDays(-5),
                ID = 10,
                UserID = "bananas",
            };
        }

        public static Meal[] GenerateManyMeals()
        {
            return new Meal[] {
                GenerateMeal1(), GenerateMeal2()
            };
        }
    }
}
