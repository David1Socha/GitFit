using HealthTrac.DataAccess;
using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.Entity.Infrastructure;
using System.Data.Entity;

namespace HealthTrac.DataAccess.Entity
{
    public class EntityTeamAccessor : ITeamAccessor
    {

        private ApplicationDbContext db;

        public EntityTeamAccessor(ApplicationDbContext db)
        {
            this.db = db;
        }

        public Team GetTeam(long ID)
        {
            var team = db.Teams.Include("Memberships").Include("Memberships.User")
                            .Where(t => t.ID == ID && t.Enabled).FirstOrDefault();
            return team;
        }

        public IEnumerable<Team> SearchTeams(string name)
        {
            var teams = db.Teams
                            .Where(t => t.Name.Contains(name)).ToList();
            return teams;
        }

        public IEnumerable<Team> GetTeams()
        {
            var teams = db.Teams.Where(t => t.Enabled).ToList();
            return teams;
        }
        public IEnumerable<Team> GetTeams(string userID)
        {
            var memberships = db.Memberships.Where(m => m.UserID == userID &&
                (m.MembershipStatus == MembershipStatus.ADMIN
                || m.MembershipStatus == MembershipStatus.MEMBER)).Select(m => new
            {
                m.TeamID,
            });
            IEnumerable<long> teamIdsWhereUserMember = memberships.Select(m => m.TeamID);
            var teams = db.Teams.Where(t => teamIdsWhereUserMember.Contains(t.ID) && t.Enabled);

            return teams.ToList();
        }

        public Team UpdateTeam(Team team)
        {
            db.Entry(team).State = System.Data.Entity.EntityState.Modified;
            return team;
        }

        public Team CreateTeam(Team team)
        {
            db.Teams.Add(team);
            return team;
        }

        public Team DeleteTeam(Team team)
        {
            team.Enabled = false;
            return UpdateTeam(team);
        }


        public Team DeleteTeam(long teamId)
        {
            var team = db.Teams.Include(t => t.Memberships).Single(t => t.ID == teamId);
            return DeleteTeam(team);
        }

        public Team UpdateTeam(long teamId)
        {
            var team = db.Teams.Find(teamId);
            return UpdateTeam(team);
        }
    }
}