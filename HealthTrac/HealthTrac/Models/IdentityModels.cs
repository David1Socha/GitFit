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

    // You can add profile data for the user by adding more properties to your ApplicationUser class, please visit http://go.microsoft.com/fwlink/?LinkID=317594 to learn more.
    public class ApplicationUser : IdentityUser, IDateTrackingModel
    {
        public bool Enabled { get; set; }
        public DateTime DateCreated { get; set; }
        public DateTime DateModified { get; set; }
        public String FirstName { get; set; }
        public String LastName { get; set; }
        public String PreferredName { get; set; }
        public String Email { get; set; }
        public Sex Sex { get; set; }
        public double Height { get; set; }
        public double Width { get; set; }
        public DateTime BirthDate { get; set; }
        public virtual ICollection<Membership> Memberships { get; set; }
    }

    public class ApplicationDbContext : IdentityDbContext<ApplicationUser>
    {
        public DbSet<Activity> Activities { get; set; }
        public DbSet<ExerciseSession> ExerciseSessions { get; set; }
        public DbSet<Membership> Memberships { get; set; }
        public DbSet<Status> Statuses { get; set; }
        public DbSet<Team> Teams { get; set; }

        public ApplicationDbContext()
            : base("DefaultConnection")
        {
        }

        public override int SaveChanges()
        {
            IEnumerable<DbEntityEntry> entities = _GetChangedDateTrackingEntities();

            foreach (var entity in entities)
            {
                if (entity.State == EntityState.Added)
                {
                    ((IDateTrackingModel)entity.Entity).DateCreated = DateTime.Now;
                }
                ((IDateTrackingModel)entity.Entity).DateModified = DateTime.Now;
            }

            return base.SaveChanges();
        }

        private IEnumerable<DbEntityEntry> _GetChangedDateTrackingEntities()
        {
            return ChangeTracker.Entries().Where(x => x.Entity is IDateTrackingModel && (x.State == EntityState.Added || x.State == EntityState.Modified));
        }
    }
}