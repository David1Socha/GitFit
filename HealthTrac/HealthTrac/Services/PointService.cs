using HealthTrac.DataAccess;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Services
{
    public class PointService : IPointService
    {

        private IPointAccessor _acc;

        public PointService(IPointAccessor acc)
        {
            _acc = acc;
        }

        public Models.Point GetPoint(long id)
        {
            return _acc.GetPoint(id);
        }

        public IEnumerable<Models.Point> GetPoints(long activityId)
        {
            return _acc.GetPoints(activityId);
        }

        public IEnumerable<Models.Point> GetPoints()
        {
            return _acc.GetPoints();
        }

        public Models.Point CreatePoint(Models.Point p)
        {
            return _acc.CreatePoint(p);
        }

        public IEnumerable<Models.Point> CreatePoints(IEnumerable<Models.Point> pts)
        {
            return _acc.CreatePoints(pts);
        }
    }
}