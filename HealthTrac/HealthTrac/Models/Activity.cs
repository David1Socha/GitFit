using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models
{
    public class Activity
    {
        public enum Type
        {
            WALKING, JOGGING, RUNNING, BIKING
        }
        public String Location { get; set; }
        public long Duration { get; set; }
        public long ID { get; set; }
        public String name { get; set; }
        public int UserID { get; set; }
    }
}