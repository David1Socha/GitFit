using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models
{
    public class Goal
    {
        public enum Field
        {
            STEPS, DISTANCE, DURATION
        }
        public long ID { get; set; }
        public String Name { get; set; }
        public Field Field { get; set; }
        public double Threshold { get; set; }
        public virtual ICollection<UserGoal> UserGoals { get; set; }
    }
}