using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.DataAccess.Entity
{
    public class EntityMembershipAccessor : IMembershipAccessor
    {
        public Models.Membership GetMembership(long id)
        {
            throw new NotImplementedException();
        }

        public Models.Membership GetMembership(long teamId, string userId)
        {
            throw new NotImplementedException();
        }

        public IEnumerable<Models.Membership> GetMemberships()
        {
            throw new NotImplementedException();
        }

        public IEnumerable<Models.Membership> GetMemberships(long teamId)
        {
            throw new NotImplementedException();
        }

        public IEnumerable<Models.Membership> GetMemberships(string userId)
        {
            throw new NotImplementedException();
        }

        public Models.Membership UpdateMembership(Models.Membership m)
        {
            throw new NotImplementedException();
        }

        public Models.Membership CreateMembership(Models.Membership m)
        {
            throw new NotImplementedException();
        }
    }
}