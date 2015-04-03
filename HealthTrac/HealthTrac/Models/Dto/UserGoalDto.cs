using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models.Dto
{
    public class UserGoalDto
    {
        public long ID { get; set; }
        public long GoalID { get; set; }
        public String UserID { get; set; }
        public DateTime DateAssigned { get; set; }
        public DateTime? DateCompleted { get; set; }

        public static UserGoalDto FromUserGoal(UserGoal ug)
        {
            return new UserGoalDto()
            {
                DateAssigned = ug.DateAssigned,
                DateCompleted = ug.DateCompleted,
                GoalID = ug.GoalID,
                ID = ug.ID,
                UserID = ug.UserID,
            };
        }
    }
}