using HealthTrac.DataAccess;
using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.Entity.Infrastructure;

namespace HealthTrac.DataAccess.Entity
{
    public class EntityTeamAccessor : ITeamAccessor
    {
        public Team GetTeam(long ID)
        {
            using (var db = new ApplicationDbContext())
            {
                var team = db.Teams
                                .Where(t => t.ID == ID && t.Enabled).FirstOrDefault();
                return team;
            }
        }

        public IEnumerable<Team> SearchTeams(string name)
        {
            using (var db = new ApplicationDbContext())
            {
                var teams = db.Teams
                                .Where(t => t.Name.Contains(name)).ToList();
                return teams;
            }
        }

        public IEnumerable<Team> GetTeams()
        {
            using (var db = new ApplicationDbContext())
            {
                var teams = db.Teams.Where(t => t.Enabled).ToList();
                return teams;
            }
        }
        public IEnumerable<Team> GetTeams(string userID)
        {
            using (var db = new ApplicationDbContext())
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
        }

        public Team UpdateTeam(Team team)
        {
            using (var db = new ApplicationDbContext())
            {
                db.Entry(team).State = System.Data.Entity.EntityState.Modified;
                try
                {
                    db.SaveChanges();
                }
                catch (DbUpdateConcurrencyException ex)
                {
                    throw new ConcurrentUpdateException("The team you are trying to update has been modified externally", ex);
                }
                return team;
            }
        }

        public Team CreateTeam(Team team)
        {
            using (var db = new ApplicationDbContext())
            {
                db.Teams.Add(team);
                db.SaveChanges();

            }
            return team;
        }

    }
}