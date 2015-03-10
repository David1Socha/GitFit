using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.ComponentModel.DataAnnotations;

namespace HealthTrac.Models
{
    public enum Mood
    {
        HAPPY, SAD, TIRED, EXCITED, PROUD, LAZY, GREAT, DETERMINED, SICK, ANGRY, COLD, HOT, FABULOUS, MOTIVATED, YOLO, DRAINED, FAT
    }

    public class Status : DateTrackingModel
    {
        public long ID { get; set; }
        public string ApplicationUserID { get; set; }
        public long? ExerciseSessionID { get; set; }
        public String Message { get; set; }
        public Mood Mood { get; set; }
        public virtual User User { get; set; }
    }
}