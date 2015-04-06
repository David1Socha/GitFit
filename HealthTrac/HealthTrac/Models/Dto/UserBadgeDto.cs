using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models.Dto
{
    public class UserBadgeDto
    {
        public long ID { get; set; }
        public String UserID { get; set; }
        public long BadgeID { get; set; }
        public DateTime DateCompleted { get; set; }

        public static UserBadgeDto FromUserBadge(UserBadge ub)
        {
            return new UserBadgeDto()
            {
                BadgeID = ub.BadgeID,
                DateCompleted = ub.DateCompleted,
                ID = ub.ID,
                UserID = ub.UserID,
            };
        }
    }
}