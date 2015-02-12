using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.ComponentModel.DataAnnotations;

namespace HealthTrac.Models
{
    public enum MembershipStatus
    {
        BANNED, WAITING_USER, WAITING_TEAM, MEMBER, INACTIVE, ADMIN
    }

    public class Membership : DateTrackingModel
    {
<<<<<<< HEAD
        public int ID { get; set; }
        public int TeamID { get; set; }
=======
        public long ID { get; set; }
        public long TeamID { get; set; }
>>>>>>> origin/master
        public string ApplicationUserID { get; set; }
        public MembershipStatus MembershipStatus { get; set; }
        public virtual Team Team { get; set; }
        public virtual ApplicationUser ApplicationUser { get; set; }
    }
}