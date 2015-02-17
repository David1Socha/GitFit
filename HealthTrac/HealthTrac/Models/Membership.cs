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
        public long ID { get; set; }
        public long TeamID { get; set; }
        public string UserId { get; set; }
        public MembershipStatus MembershipStatus { get; set; }
        public virtual Team Team { get; set; }
        public virtual User User { get; set; }
    }
}