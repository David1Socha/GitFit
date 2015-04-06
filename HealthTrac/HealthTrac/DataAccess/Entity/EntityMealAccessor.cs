using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Web;

namespace HealthTrac.DataAccess.Entity
{
    public class EntityMealAccessor : IMealAccessor
    {
        private ApplicationDbContext db;

        public EntityMealAccessor(ApplicationDbContext db)
        {
            this.db = db;
        }

        public Meal GetMeal(long ID)
        {
            Meal m = db.Meals
                .Where(a => a.ID == ID)
                .FirstOrDefault();
            return m;
        }

        public IEnumerable<Meal> GetMeals()
        {
            IEnumerable<Meal> meals = db.Meals
                .ToList();
            return meals;
        }

        public Meal CreateMeal(Meal m)
        {
            db.Meals.Add(m);
            return m;
        }


        public IEnumerable<Meal> GetMeals(string uid)
        {
            var meals = db.Meals
                .Where(m => m.UserID == uid)
                .ToList();
            return meals;
        }
    }
}