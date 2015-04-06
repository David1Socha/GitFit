using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models.Dto
{
    public class EnergyLevelDto
    {
        public DateTime DateCreated { get; set; }
        public long ID { get; set; }
        public String UserID { get; set; }
        public Mood Mood { get; set; }

        public static EnergyLevelDto FromEnergyLevel(EnergyLevel e)
        {
            return new EnergyLevelDto()
            {
                DateCreated = e.DateCreated,
                ID = e.ID,
                Mood = e.Mood,
                UserID = e.UserID,
            };
        }
    }
}