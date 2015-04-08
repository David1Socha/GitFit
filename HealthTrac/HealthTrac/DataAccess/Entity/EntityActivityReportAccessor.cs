using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace HealthTrac.DataAccess.Entity
{
    public class EntityActivityReportAccessor : IActivityReportAccessor
    {

        private ApplicationDbContext db;

        public EntityActivityReportAccessor(ApplicationDbContext db)
        {
            this.db = db;
        }

        public IEnumerable<Models.ActivityReport> GetActivityReports(string uid)
        {
            var ars = db.ActivityReports
                .Where(ar => ar.UserID == uid)
                .ToList();
            return ars;
        }

        public ActivityReport AddOrUpdate(Models.ActivityReport ar)
        {
            var existing = db.ActivityReports
                .Where(a => a.Date.Day == ar.Date.Day && a.Date.Year == ar.Date.Year && a.Date.Month == ar.Date.Month && ar.UserID == a.UserID)
                .FirstOrDefault();
            if (existing != null)
            {
                existing.Distance += ar.Distance;
                existing.Duration += ar.Duration;
                existing.Steps += ar.Steps;
                db.Entry(existing).State = EntityState.Modified;
                return existing;
            }
            else
            {
                db.ActivityReports.Add(ar);
                return ar;
            }
        }

        public IEnumerable<Models.ActivityReport> GetActivityReports()
        {
            var ars = db.ActivityReports
                .ToList();
            return ars;
        }

        public Models.ActivityReport GetActivityReport(long id)
        {
            var ar = db.ActivityReports
                .Where(a => id == a.ID)
                .FirstOrDefault();
            return ar;
        }
    }
}