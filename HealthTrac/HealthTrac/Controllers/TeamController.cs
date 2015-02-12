using HealthTrac.Models;
using HealthTrac.Service_References;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace HealthTrac.Controllers
{
    public class TeamController : Controller
    {
        
        // GET: /Team/
        public ActionResult Index()
        {
            return View();
        }

        public List<Team> GetTeams(string userId)
        {
            return new TeamAccessor().FindTeams(userId).ToList();
        }
        public Team CreateTeam(Team team)
        {
            return new TeamAccessor().SaveTeam(team);
        }
        public Team UpdateTeam(Team team)
        {
            return new TeamAccessor().SaveTeam(team);
        }
        public List<Membership> InviteUsers(string[] userIds, long teamId)
        {
            List<Membership> memberships = new List<Membership>();
            foreach (string userId in userIds)
            {
                Membership membership = new Membership
                {
                    DateCreated = DateTime.Now,
                    DateModified = DateTime.Now,
                    ID = 0,
                    ApplicationUserID = userId,
                    TeamID = teamId,
                    MembershipStatus = MembershipStatus.WAITING_USER
                };
                memberships.Add(membership);
            }
            return new TeamAccessor().SaveMemberships(memberships).ToList();
        }

        public Membership ConfirmUser(long membershipId)
        {
            // Don't know for sure if you need to pass down an object with all previous properties for update. TEST THIS
            TeamAccessor teamAccessor = new TeamAccessor();
            Membership membership = teamAccessor.FindMembership(membershipId);
            membership.DateModified = DateTime.Now;
            membership.MembershipStatus = MembershipStatus.MEMBER;
            return teamAccessor.SaveMembership(membership);
        }

        public Membership BanUser(long membershipId)
        {
            // Don't know for sure if you need to pass down an object with all previous properties for update. TEST THIS
            TeamAccessor teamAccessor = new TeamAccessor();
            Membership membership = teamAccessor.FindMembership(membershipId);
            membership.DateModified = DateTime.Now;
            membership.MembershipStatus = MembershipStatus.BANNED;
            return teamAccessor.SaveMembership(membership);
        }

        public Membership RemoveUser(long membershipId)
        {
            // Don't know for sure if you need to pass down an object with all previous properties for update. TEST THIS
            TeamAccessor teamAccessor = new TeamAccessor();
            Membership membership = teamAccessor.FindMembership(membershipId);
            membership.DateModified = DateTime.Now;
            membership.MembershipStatus = MembershipStatus.INACTIVE;
            return teamAccessor.SaveMembership(membership);
        }
	}
}