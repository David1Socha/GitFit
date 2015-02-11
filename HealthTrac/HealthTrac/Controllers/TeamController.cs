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

        public IEnumerable<Team> getTeams(string userId)
        {
            return new TeamAccessor().FindTeams(userId);
        }
        public Team createTeam(Team team)
        {
            return new TeamAccessor().SaveTeam(team);
        }
        public Team updateTeam(Team team)
        {
            return new TeamAccessor().SaveTeam(team);
        }
        public Membership InviteUsers(string[] userIds)
        {
            return new TeamAccessor().SaveMemberships(IEnumerable<Membership> memberships);
        }
	}
}