using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models.Dto
{
    public class GoalDto
    {
        public long ID { get; set; }
        public String Name { get; set; }
        public Field Field { get; set; }
        public double Threshold { get; set; }

        public static GoalDto FromGoal(Goal g)
        {
            return new GoalDto()
            {
                Field = g.Field,
                ID = g.ID,
                Name = g.Name,
                Threshold = g.Threshold,
            };
        }
    }
}