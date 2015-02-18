using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models.Dto
{
    public class MembershipDto
    {
        public DateTime DateCreated { get; set; }
        public DateTime DateModified { get; set; }
        public long ID { get; set; }
        public long TeamID { get; set; }
        public string UserID { get; set; }
        public MembershipStatus MembershipStatus { get; set; }

        public static MembershipDto FromMembership(Membership m)
        {
            return new MembershipDto()
            {
                DateCreated = m.DateCreated,
                DateModified = m.DateModified,
                ID = m.ID,
                TeamID = m.TeamID,
                UserID = m.UserID,
                MembershipStatus = m.MembershipStatus
            };
        }
    }
}