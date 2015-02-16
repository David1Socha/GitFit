using HealthTrac.DataAccess;
using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.DataAccess.Entity
{
    public class EntityTeamAccessor : ITeamAccessor
    {
        public Team FindTeam(long ID)
        {
            using (var db = new ApplicationDbContext())
            {
                var team = db.Teams
                                .Where(t => t.ID == ID).FirstOrDefault();
                return team;
            }
        }
        public IEnumerable<Team> FindTeams(string userID)
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

        public IEnumerable<Membership> SaveMemberships(List<Membership> memberships)
        {
            using (var db = new ApplicationDbContext())
            {
                if (memberships[0].ID == 0)
                {
                    db.Memberships.AddRange(memberships);
                }
                else
                {
                    // **** NEEDS to be tested to see if update works for multiple memberships
                    foreach (Membership membership in memberships)
                    {
                        db.Memberships.Attach(membership);
                        db.Entry(membership).State = System.Data.Entity.EntityState.Modified;
                    }
                }
                db.SaveChanges();
            }
            return memberships;
        }
        public Membership SaveMembership(Membership membership)
        {
            using (var db = new ApplicationDbContext())
            {
                if (membership.ID == 0)
                {
                    db.Memberships.Add(membership);
                }
                else
                {
                    db.Memberships.Attach(membership);
                    db.Entry(membership).State = System.Data.Entity.EntityState.Modified;
                }
                db.SaveChanges();
            }
            return membership;
        }
        public Membership FindMembership(long membershipID)
        {
            using (var db = new ApplicationDbContext())
            {
                var membership = db.Memberships
                                .Where(m => m.ID == membershipID).FirstOrDefault();
                return membership;
            }
        }

    }
}