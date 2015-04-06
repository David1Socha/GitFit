namespace HealthTrac.Migrations
{
    using CsvHelper;
    using HealthTrac.Models;
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Migrations;
    using System.IO;
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
            var baseDir = AppDomain.CurrentDomain
                               .BaseDirectory
                               .Replace("\\bin", string.Empty);
            context.Badges.AddOrUpdate(b => b.Name, new Badge
            {
                Field = Field.DISTANCE,
                Name = "10000 Feet",
                Threshold = 10000,
            },
            new Badge
            {
                Field = Field.DURATION,
                Name = "Hour of Exercise",
                Threshold = 3600,
            },
            new Badge
            {
                Field = Field.STEPS,
                Name = "One thousand steps ahead",
                Threshold = 1000,
            }
            );

            using (var textReader = new StreamReader(baseDir + "\\Data\\1000.csv"))
            {
                using (var csv = new CsvReader(textReader))
                {
                    var records = csv.GetRecords<SampleActivity>().ToList();
                    var activities = records.Select((v, i) => v.ToActivity(i + 1)); //SQL indices are 1-indexed...
                    context.Activities.AddOrUpdate(activities.ToArray());
                }
            }
        }
    }
}
