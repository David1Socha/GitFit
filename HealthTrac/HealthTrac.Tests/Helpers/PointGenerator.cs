using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HealthTrac.Tests.Helpers
{
    public static class PointGenerator
    {
        public static Point GeneratePoint1()
        {
            return new Point
            {
                ActivityID = 7,
                ID = 7,
                Lat = 37.456,
                Lng = 33.467,
            };
        }

        public static Point GeneratePoint2()
        {
            return new Point
            {
                ActivityID = 9,
                ID = 17,
                Lat = 57.456,
                Lng = 54.764,
            };
        }

        public static Point[] GenerateManyPoints()
        {
            return new Point[] {
                GeneratePoint1(), GeneratePoint2()
            };
        }
    }
}
