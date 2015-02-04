﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models
{
    public class ExerciseSession : DateTrackingModel
    {
        public int ID { get; set; }
        public int ActivityID { get; set; }
        public int UserID { get; set; }
        public DateTime startTime { get; set; }
        public Boolean isDeleted { get; set; }
        public int Seconds { get; set; }
        public int Steps { get; set; }
        public int Distance { get; set; }
        public virtual Activity Activity { get; set; }
    }
}