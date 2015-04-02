using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Web;

namespace HealthTrac.DataAccess.Entity
{
    public class EntityPointAccessor : IPointAccessor
    {
        private ApplicationDbContext db;

        public EntityPointAccessor(ApplicationDbContext db)
        {
            this.db = db;
        }

        public Point GetPoint(long ID)
        {
            Point p = db.Points
                .Where(a => a.ID == ID)
                .FirstOrDefault();
            return p;
        }

        public IEnumerable<Point> GetPoints()
        {
            IEnumerable<Point> pts = db.Points
                .ToList();
            return pts;
        }
        public IEnumerable<Point> GetPoints(long activityId)
        {
            IEnumerable<Point> pts = db.Points
                .Where(l => l.ActivityID == activityId)
                .ToList();
            return pts;
        }

        public Point CreatePoint(Point p)
        {
            db.Points.Add(p);
            return p;
        }

        public IEnumerable<Point> CreatePoints(IEnumerable<Point> pts)
        {
            foreach (Point p in pts)
            {
                db.Points.Add(p);
            }
            return pts;
        }
    }
}