using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace HealthTrac.Models
{
    public class UserBadge
    {
        public long ID { get; set; }
        public String UserID { get; set; }
        public long BadgeID { get; set; }
        public DateTime DateCompleted { get; set; }
        public virtual User User { get; set; }
        public virtual Badge Badge { get; set; }

    }
}
