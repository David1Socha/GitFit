using HealthTrac.Models;
using Microsoft.AspNet.Identity.EntityFramework;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Web;

namespace HealthTrac.DataAccess.Entity
{
    public class ApplicationDbContext : IdentityDbContext<User>
    {
        public DbSet<Activity> Activities { get; set; }
        public DbSet<Membership> Memberships { get; set; }
        public DbSet<Team> Teams { get; set; }

        public ApplicationDbContext()
            : base("DefaultConnection", throwIfV1Schema: false)
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