using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models
{
    public enum Mood
    {
        EXCITED, TIRED, PROUD
    }
    public class EnergyLevel : DateTrackingModel
    {
        public long ID { get; set; }
        public String UserID { get; set; }
        public virtual User User { get; set; }
        public Mood Mood { get; set; }
    }
}