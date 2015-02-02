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

    public class Membership
    {
        public int ID { get; set; }
        public int TeamID { get; set; }
        public int ApplicationUserID { get; set; }
        public DateTime CreationDate { get; set; }
        public MembershipStatus MembershipStatus { get; set; }

        public virtual Team Team { get; set; }
        public virtual ApplicationUser ApplicationUser { get; set; }
    }
}