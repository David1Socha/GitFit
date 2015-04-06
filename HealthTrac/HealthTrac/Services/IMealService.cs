using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HealthTrac.Services
{
    public interface IMealService
    {
        Meal GetMeal(long id);
        IEnumerable<Meal> GetMeals();
        Meal CreateMeal(Meal m);
        IEnumerable<Meal> GetMeals(String uid);
    }
}
