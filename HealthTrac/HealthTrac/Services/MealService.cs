using HealthTrac.DataAccess;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Services
{
    public class MealService : IMealService
    {
        private IMealAccessor _acc;

        public MealService(IMealAccessor acc)
        {
            _acc = acc;
        }

        public Models.Meal GetMeal(long id)
        {
            return _acc.GetMeal(id);
        }

        public IEnumerable<Models.Meal> GetMeals()
        {
            return _acc.GetMeals();
        }

        public Models.Meal CreateMeal(Models.Meal m)
        {
            return _acc.CreateMeal(m);
        }
    }
}