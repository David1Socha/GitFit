using HealthTrac.DataAccess;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Services
{
    public class TeamService : ITeamService
    {
        private ITeamAccessor _acc;

        public TeamService(ITeamAccessor acc)
        {
            _acc = acc;
        }

        public Models.Team GetTeam(long ID)
        {
            return _acc.GetTeam(ID);
        }

        public IEnumerable<Models.Team> SearchTeams(string name)
        {
            return _acc.SearchTeams(name);
        }

        public IEnumerable<Models.Team> GetTeams(string userID)
        {
            return _acc.GetTeams(userID);
        }

        public IEnumerable<Models.Team> GetTeams()
        {
            return _acc.GetTeams();
        }

        public Models.Team CreateTeam(Models.Team team)
        {
            return _acc.CreateTeam(team);
        }

        public Models.Team UpdateTeam(Models.Team team)
        {
            return _acc.UpdateTeam(team);
        }

        public Models.Team DeleteTeam(Models.Team team)
        {
            return _acc.DeleteTeam(team);
        }

        public Models.Team DeleteTeam(long teamId)
        {
            return _acc.DeleteTeam(teamId);
        }

    }
}