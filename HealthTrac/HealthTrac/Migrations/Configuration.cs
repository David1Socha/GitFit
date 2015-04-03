namespace HealthTrac.Migrations
{
    using HealthTrac.Models;
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Migrations;
    using System.Linq;

    internal sealed class Configuration : DbMigrationsConfiguration<HealthTrac.DataAccess.Entity.ApplicationDbContext>
    {
        public Configuration()
        {
            AutomaticMigrationsEnabled = true;
            ContextKey = "HealthTrac.DataAccess.Entity.ApplicationDbContext";
            AutomaticMigrationDataLossAllowed = true;
        }

        protected override void Seed(HealthTrac.DataAccess.Entity.ApplicationDbContext context)
        {
            context.Badges.AddOrUpdate(new Badge
            {
                ID = 1,
                Field = Field.DISTANCE,
                Name = "10000 Feet",
                Threshold = 10000,
            },
            new Badge
            {
                ID = 2,
                Field = Field.DURATION,
                Name = "Hour of exercise",
                Threshold = 3600,
            },
            new Badge
            {
                ID = 3,
                Field = Field.STEPS,
                Name = "One thousand steps ahead",
                Threshold = 1000,
            }
            );
            //  This method will be called after migrating to the latest version.

            //  You can use the DbSet<T>.AddOrUpdate() helper extension method 
            //  to avoid creating duplicate seed data. E.g.
            //
            //    context.People.AddOrUpdate(
            //      p => p.FullName,
            //      new Person { FullName = "Andrew Peters" },
            //      new Person { FullName = "Brice Lambson" },
            //      new Person { FullName = "Rowan Miller" }
            //    );
            //
        }
    }
}
