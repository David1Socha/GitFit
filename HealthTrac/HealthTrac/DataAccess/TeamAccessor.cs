using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Service_References
{
    public class TeamAccessor
    {
        public Team FindTeam(int ID)
        {
            using (var db = new ApplicationDbContext())
            {
                var team = db.Teams
                                .Where(t => t.ID == ID).FirstOrDefault();
                return team;
            }
        }
        public IEnumerable<Team> FindTeams(int userID)
        {
            using (var db = new ApplicationDbContext())
            {
                var teams = from team in db.Teams
                            join membership in db.Memberships
                                on team.ID equals membership.TeamID
                            where membership.ApplicationUserID == userID
                                && (membership.MembershipStatus.Equals(MembershipStatus.ADMIN)
                                || membership.MembershipStatus.Equals(MembershipStatus.MEMBER))
                            select team;
                return teams;
            }
        }
        public Team SaveTeam(Team team)
        {
            using (var db = new ApplicationDbContext())
            {
                if (team.ID == 0)
                {
                    db.Teams.Add(team);
                }
                else
                {
                    db.Teams.Attach(team);
                    db.Entry(team).State = System.Data.Entity.EntityState.Modified;
                }
                db.SaveChanges();
            }
            return team;
        }

    }
}