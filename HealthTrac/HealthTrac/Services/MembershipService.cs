using HealthTrac.DataAccess;
using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Services
{
    public class MembershipService : IMembershipService
    {
        private IMembershipAccessor _acc;
        private ITeamAccessor _teamAcc;

        public MembershipService(IMembershipAccessor acc, ITeamAccessor teamAcc)
        {
            _acc = acc;
            _teamAcc = teamAcc;
        }

        public Models.Membership GetMembership(long id)
        {
            return _acc.GetMembership(id);
        }

        public Models.Membership GetMembership(long teamId, string userId)
        {
            return _acc.GetMembership(teamId, userId);
        }

        public IEnumerable<Models.Membership> GetMemberships()
        {
            return _acc.GetMemberships();
        }

        public IEnumerable<Models.Membership> GetMemberships(long teamId)
        {
            return _acc.GetMemberships(teamId);
        }

        public IEnumerable<Models.Membership> GetMemberships(string userId)
        {
            return _acc.GetMemberships(userId);
        }

        public Models.Membership UpdateMembership(Models.Membership m)
        {
            var membership = _acc.UpdateMembership(m);
            if (membership.MembershipStatus != MembershipStatus.MEMBER && membership.MembershipStatus != MembershipStatus.ADMIN) //if this member is active then we know the team is active without querying all memberships
            {
                int membersLeft = _acc.GetActiveMemberships(membership.TeamID).Where(m8 => m8.ID != membership.ID).Count();
                if (membersLeft == 0)
                {
                    _teamAcc.DeleteTeam(membership.TeamID);
                }
            }
            return membership;
        }

        public Models.Membership CreateMembership(Models.Membership m)
        {
            return _acc.CreateMembership(m);
        }
    }
}