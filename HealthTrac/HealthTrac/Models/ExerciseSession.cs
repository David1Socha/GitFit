using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models
{
    public class ExerciseSession : DateTrackingModel
    {
        public long ID { get; set; }
        public DateTime startTime { get; set; }
        public Boolean Enabled { get; set; }
        public long ActivityID { get; set; }
        public string UserID { get; set; }
        public int Seconds { get; set; }
        public int Steps { get; set; }
        public int Distance { get; set; }
        public virtual Activity Activity { get; set; }
    }
}