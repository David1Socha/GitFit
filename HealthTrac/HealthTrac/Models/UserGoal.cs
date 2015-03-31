using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace HealthTrac.Models
{
    public class UserGoal
    {
        public long ID { get; set; }
        public long GoalID { get; set; }
        public long UserID { get; set; }
        public virtual Goal Goal { get; set; }
        public virtual User User { get; set; }
        public DateTime DateAssigned { get; set; }
        public DateTime? DateCompleted { get; set; }
    }
}
