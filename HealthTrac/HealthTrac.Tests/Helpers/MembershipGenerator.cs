using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using HealthTrac.Models;
using System.Threading.Tasks;

namespace HealthTrac.Tests.Helpers
{
    class MembershipGenerator
    {
        public static Membership GenerateAdminMembership()
        {
            return new Membership
            {
                DateCreated = new DateTime(2000, 4, 20),
                DateModified = new DateTime(2005, 4, 20),
                ID = 1,
                MembershipStatus = MembershipStatus.ADMIN,
                TeamID = 12,
                UserID = "abc"
            };
        }

        public static Membership GenerateMemberMembership()
        {
            return new Membership
            {
                DateCreated = new DateTime(1999, 2, 9),
                DateModified = new DateTime(2003, 5, 5),
                ID = 2,
                TeamID = 14,
                UserID = "abc",
                MembershipStatus = MembershipStatus.MEMBER
            };
        }

        public static Membership GenerateBannedMembership()
        {
            return new Membership
            {
                DateCreated = new DateTime(1999, 2, 9),
                DateModified = new DateTime(2003, 5, 5),
                ID = 3,
                TeamID = 14,
                UserID = "xyz",
                MembershipStatus = MembershipStatus.BANNED
            };
        }

        public static Membership[] GenerateActiveMemberships()
        {
            return new Membership[] {
                GenerateAdminMembership(),
                GenerateMemberMembership()
            };
        }
    }
}
