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

    public class Status
    {
        public int ID { get; set; }
        public int ApplicationUserID { get; set; }
        public int? ExerciseSessionID { get; set; }
        public DateTime PostDate { get; set; }
        public DateTime EditDate { get; set; }
        public String Message { get; set; }
        public Mood Mood { get; set; }
        public virtual ApplicationUser ApplicationUser { get; set; }
        public virtual ExerciseSession ExerciseSession { get; set; }
    }
}