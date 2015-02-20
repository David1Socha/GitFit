using HealthTrac.Models;
using HealthTrac.DataAccess;
using HealthTrac.DataAccess.Entity;
using Microsoft.AspNet.Identity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace HealthTrac.Controllers
{
    public class TeamController : Controller
    {
        //Changing the accessor interface broke this temporarily. Some of these methods are going to be in MembershipAccessor now
        private ITeamAccessor teamAccessor;
        private IMembershipAccessor membershipAccessor;
        private AccountController accountController;

        public TeamController(ITeamAccessor teamAcc, IMembershipAccessor membershipAcc, UserManager<User> userManager)
        {
            accountController = new AccountController(userManager);
            this.teamAccessor = teamAcc;
            this.membershipAccessor = membershipAcc;
        }

        // GET: /Team/
        public ActionResult Index(string id)
        {
            string searchString = id;

            if (!String.IsNullOrEmpty(searchString))
            {
                var teams = teamAccessor.SearchTeams(id).ToList();
                if (teams.Count == 0)
                {
                    return View(new List<Team>());
                }
                else
                {
                    return View(teams);
                }             
            }
            else
            {
                return View();
            }
        }

        public ActionResult Profile(long id)
        {
            Team team = teamAccessor.GetTeam(id);
            return View(team);
        }

        public ActionResult YourTeams()
        {
            string userId = User.Identity.GetUserId();
            return View(teamAccessor.GetTeams(userId).ToList());
        }
        public Team CreateTeam(Team team)
        {
            return teamAccessor.CreateTeam(team);
        }
        public Team UpdateTeam(Team team)
        {
            return teamAccessor.UpdateTeam(team);
        }
        public List<Membership> InviteUsers(string[] userIds, long teamId)
        { /*
            List<Membership> memberships = new List<Membership>();
            foreach (string userId in userIds)
            {
                Membership membership = new Membership
                {
                    DateCreated = DateTime.Now,
                    DateModified = DateTime.Now,
                    ID = 0,
                    UserId = userId,
                    TeamID = teamId,
                    MembershipStatus = MembershipStatus.WAITING_USER
                };
                memberships.Add(membership);
            } */

            throw new NotImplementedException();
        }

        public Membership ConfirmUser(long membershipId)
        {
            // Don't know for sure if you need to pass down an object with all previous properties for update. TEST THIS
            /*
            EntityTeamAccessor teamAccessor = new EntityTeamAccessor();
            Membership membership = teamAccessor.FindMembership(membershipId);
            membership.DateModified = DateTime.Now;
            membership.MembershipStatus = MembershipStatus.MEMBER;
            return teamAccessor.SaveMembership(membership);
             */
            throw new NotImplementedException();
        }

        public Membership BanUser(long membershipId)
        {
            // Don't know for sure if you need to pass down an object with all previous properties for update. TEST THIS
            /*
            EntityTeamAccessor teamAccessor = new EntityTeamAccessor();
            Membership membership = teamAccessor.FindMembership(membershipId);
            membership.DateModified = DateTime.Now;
            membership.MembershipStatus = MembershipStatus.BANNED;
            return teamAccessor.SaveMembership(membership);
             */
            throw new NotImplementedException();
        }

        public Membership RemoveUser(long membershipId)
        {
            // Don't know for sure if you need to pass down an object with all previous properties for update. TEST THIS
            /*
            EntityTeamAccessor teamAccessor = new EntityTeamAccessor();
            Membership membership = teamAccessor.FindMembership(membershipId);
            membership.DateModified = DateTime.Now;
            membership.MembershipStatus = MembershipStatus.INACTIVE;
            return teamAccessor.SaveMembership(membership);
             */
            throw new NotImplementedException();
        }
    }
}