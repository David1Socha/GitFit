using HealthTrac.DataAccess;
using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Services
{
    public class TeamService : ITeamService
    {
        private ITeamAccessor _acc;
        private IMembershipAccessor _memAcc;

        public TeamService(ITeamAccessor acc, IMembershipAccessor memAcc)
        {
            _acc = acc;
            _memAcc = memAcc;
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

        public Models.Team CreateTeam(Models.Team team, String uid)
        {
            var createdTeam = _acc.CreateTeam(team);
            var creatorsAdminMembership = new Membership
            {
                MembershipStatus = Models.MembershipStatus.ADMIN,
                TeamID = createdTeam.ID,
                UserID = uid,
            };
            _memAcc.CreateMembership(creatorsAdminMembership);
            return createdTeam;
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