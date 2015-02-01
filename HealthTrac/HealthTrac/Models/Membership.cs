using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.ComponentModel.DataAnnotations;

namespace HealthTrac.Models
{
    public class Membership
    {
        public enum Status
        {
            BANNED, WAITING_USER, WAITING_TEAM, MEMBER, INACTIVE, ADMIN
        }
        public int ID { get; set; }
        public int TeamID { get; set; }
        public int UserID { get; set; }
        public DateTime CreationDate { get; set; }
        public Status Status { get; set; }

        public virtual User Team { get; set; }
        public virtual User User { get; set; }
    }
}