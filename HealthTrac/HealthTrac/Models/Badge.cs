using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models
{
    public class Badge
    {
        public long ID { get; set; }
        public String Name { get; set; }
        public String Description { get; set; }
        public virtual ICollection<UserBadge> UserBadges { get; set }
    }
}