using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.ComponentModel.DataAnnotations;

namespace HealthTrac.Models
{
    public class Status
    {
        public enum Mood
        {
            HAPPY, SAD, TIRED, EXCITED, PROUD, LAZY, GREAT, DETERMINED, SICK, ANGRY, COLD, HOT, FABULOUS, MOTIVATED, YOLO, DRAINED, FAT
        }
        public int ID { get; set; }
        public int UserID { get; set; }
        public int? ExerciseSessionID { get; set; }
        public DateTime PostDate { get; set; }
        public DateTime EditDate { get; set; }
        public String message { get; set; }
        public virtual ApplicationUser User { get; set; }
        public virtual ExerciseSession ExerciseSession { get; set; }
    }
}