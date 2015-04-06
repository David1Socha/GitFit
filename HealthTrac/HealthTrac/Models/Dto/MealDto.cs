using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models.Dto
{
    public class MealDto
    {
        public long ID { get; set; }
        public String UserID { get; set; }
        public int Calories { get; set; }
        public DateTime DateCreated { get; set; }

        public static MealDto FromMeal(Meal m)
        {
            return new MealDto()
            {
                Calories = m.Calories,
                DateCreated = m.DateCreated,
                ID = m.ID,
                UserID = m.UserID,
            };
        }
    }
}