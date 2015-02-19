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
                                .Where(t => t.ID == ID).FirstOrDefault();
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
                var teams = db.Teams.ToList();
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
                var teams = db.Teams.Where(t => teamIdsWhereUserMember.Contains(t.ID));

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
                try
                {
                    db.SaveChanges();
                }
                catch (DbUpdateConcurrencyException ex)
                {
                    throw new ConcurrentUpdateException("One or more of the teams you are trying to save have been modified externally.", ex);
                }

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
                try
                {
                    db.SaveChanges();
                }
                catch (DbUpdateConcurrencyException ex)
                {
                    throw new ConcurrentUpdateException("The membership you are trying to save has been modified externally.", ex);
                }

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