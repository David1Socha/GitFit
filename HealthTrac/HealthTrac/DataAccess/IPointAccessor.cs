using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using HealthTrac.Models;

namespace HealthTrac.DataAccess
{
    public interface IPointAccessor
    {
        Point GetPoint(long id);
        IEnumerable<Point> GetPoints(long activityId);
        IEnumerable<Point> GetPoints();
        Point CreatePoint(Point p);
        IEnumerable<Point> CreatePoints(IEnumerable<Point> pts);
    }
}
