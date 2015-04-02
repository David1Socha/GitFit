using System;
namespace HealthTrac.DataAccess
{
    public interface ITeamAccessor
    {
        HealthTrac.Models.Team GetTeam(long ID);
        System.Collections.Generic.IEnumerable<HealthTrac.Models.Team> SearchTeams(string name);
        System.Collections.Generic.IEnumerable<HealthTrac.Models.Team> GetTeams(string userID);
        System.Collections.Generic.IEnumerable<HealthTrac.Models.Team> GetTeams();
        HealthTrac.Models.Team CreateTeam(HealthTrac.Models.Team team);
        HealthTrac.Models.Team UpdateTeam(HealthTrac.Models.Team team);
        HealthTrac.Models.Team DeleteTeam(HealthTrac.Models.Team team);
        HealthTrac.Models.Team DeleteTeam(long teamId);
    }
}
