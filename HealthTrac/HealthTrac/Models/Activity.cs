using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models
{
    public class Activity
    {
        public int ID { get; set; }
        public String name { get; set; }
        public virtual ICollection<ExerciseSession> ExerciseSessions { get; set; }
    }
}