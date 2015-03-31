using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models
{
    public class Point
    {
        public long ID { get; set; }
        public double Lng { get; set; }
        public double Lat { get; set; }
        public long ActivityID { get; set; }
        public virtual Activity Activity { get; set; }

    }
}