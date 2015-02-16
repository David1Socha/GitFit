using System;
namespace HealthTrac.DataAccess
{
    interface ITeamAccessor
    {
        HealthTrac.Models.Membership FindMembership(long membershipID);
        HealthTrac.Models.Team FindTeam(long ID);
        System.Collections.Generic.IEnumerable<HealthTrac.Models.Team> FindTeams(string userID);
        HealthTrac.Models.Membership SaveMembership(HealthTrac.Models.Membership membership);
        System.Collections.Generic.IEnumerable<HealthTrac.Models.Membership> SaveMemberships(System.Collections.Generic.List<HealthTrac.Models.Membership> memberships);
        HealthTrac.Models.Team SaveTeam(HealthTrac.Models.Team team);
    }
}
