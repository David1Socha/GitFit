using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models
{
    public class Activity
    {
        public enum Status
        {
            HAPPY, SAD, TIRED, EXCITED, PROUD, LAZY, GREAT, COLD, HOT, FABULOUS, MOTIVATED, YOLO
        }
        public long ID { get; set; }
        public String name { get; set; }
        public int UserID { get; set; }
    }
}