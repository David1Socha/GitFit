using Microsoft.AspNet.Identity.EntityFramework;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;

namespace HealthTrac.Models
{
    public enum Sex
    {
        MALE, FEMALE
    }

    public class User : IdentityUser, IDateTrackingModel
    {
        public User()
        {
            Enabled = true;
            DateCreated = DateTime.Now;
            DateModified = DateTime.Now;
        }

        public bool Enabled { get; set; }
        public DateTime DateCreated { get; set; }
        public DateTime DateModified { get; set; }
        public String FirstName { get; set; }
        public String LastName { get; set; }
        public String PreferredName { get; set; }
        public String Email { get; set; }
        public Sex Sex { get; set; }
        public String Location { get; set; }
        public double Height { get; set; }
        public double Weight { get; set; }
        public long LifetimeSteps { get; set; }
        public double LifetimeDuration { get; set; }
        public double LifetimeDistance { get; set; }
        public DateTime? BirthDate { get; set; }
        public virtual ICollection<Membership> Memberships { get; set; }
    }

}