using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models.Dto
{
    public class BadgeDto
    {
        public long ID { get; set; }
        public String Name { get; set; }
        public double Threshold { get; set; }
        public Field Field { get; set; }

        public static BadgeDto FromBadge(Badge b)
        {
            return new BadgeDto()
            {
                Field = b.Field,
                ID = b.ID,
                Name = b.Name,
                Threshold = b.Threshold,
            };
        }
    }
}