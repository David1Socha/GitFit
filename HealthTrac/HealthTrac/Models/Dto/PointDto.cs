using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models.Dto
{
    public class PointDto
    {
        public long ID { get; set; }
        public double Lng { get; set; }
        public double Lat { get; set; }
        public long ActivityID { get; set; }

        public static PointDto FromPoint(Point p)
        {
            return new PointDto()
            {
                ActivityID = p.ActivityID,
                ID = p.ID,
                Lat = p.Lat,
                Lng = p.Lng,
            };
        }
    }
}