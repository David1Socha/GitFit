using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using HealthTrac.Models;

namespace HealthTrac.DataAccess
{
    public interface IMealAccessor
    {
        Meal GetMeal(long id);
        IEnumerable<Meal> GetMeals();
        IEnumerable<Meal> GetMeals(String uid);
        Meal CreateMeal(Meal m);

    }
}