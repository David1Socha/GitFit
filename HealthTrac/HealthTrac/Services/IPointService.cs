using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HealthTrac.Services
{
    public interface IPointService
    {
        Point GetPoint(long id);
        IEnumerable<Point> GetPoints(long activityId);
        IEnumerable<Point> GetPoints();
        Point CreatePoint(Point p);
        IEnumerable<Point> CreatePoints(IEnumerable<Point> pts);
    }
}
