using Microsoft.AspNet.Identity.EntityFramework;
using System;

namespace HealthTrac.Models
{
    // You can add profile data for the user by adding more properties to your ApplicationUser class, please visit http://go.microsoft.com/fwlink/?LinkID=317594 to learn more.
    public class ApplicationUser : IdentityUser
    {
        public enum Sex
        {
            MALE, FEMALE
        }
        public bool Enabled { get; set; }
        public DateTime DateCreated { get; set; }
        public String FirstName { get; set; }
        public String LastName { get; set; }
        public String PreferredName { get; set; }
        public String Email { get; set; }
        public Sex Sex { get; set; }
        public double Height { get; set; }
        public double Width { get; set; }
        public DateTime BirthDate { get; set; }
        public virtual Membership Membership { get; set; }
    }

    public class ApplicationDbContext : IdentityDbContext<ApplicationUser>
    {
        public ApplicationDbContext()
            : base("DefaultConnection")
        {
        }
    }
}