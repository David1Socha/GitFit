using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.ComponentModel.DataAnnotations;

namespace HealthTrac.Models
{
    public class Team : DateTrackingModel
    {

        public Team()
        {
            Enabled = true;
        }
        public long ID { get; set; }
        public string Name { get; set; }
        public String Description { get; set; }
        public bool Enabled { get; set; }
        public virtual ICollection<Membership> Memberships { get; set; }
    }
}