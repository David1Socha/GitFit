using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models.Dto
{
    public class MembershipDto
    {
        public long ID { get; set; }
        public long TeamID { get; set; }
        public string UserID { get; set; }
        public MembershipStatus MembershipStatus { get; set; }
    }
}