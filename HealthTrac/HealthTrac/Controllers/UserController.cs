using System;
using HealthTrac.DataAccess;
using HealthTrac.DataAccess.Entity;
using HealthTrac.Models;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Microsoft.AspNet.Identity;
using Microsoft.Owin.Security;

namespace HealthTrac.Controllers
{
    public class UserController : Controller
    {

        private IUserAccessor userAccessor = new EntityUserAccessor();
        private AccountController accountController;


        public UserController(UserManager<User> userManager, IAuthenticationManager auth)
        {
            accountController = new AccountController(userManager, auth);
        }
        //
        // GET: /User/
        public ActionResult Index(string id)
        {
            string searchString = id;

            if (!String.IsNullOrEmpty(searchString))
            {
                var users = userAccessor.SearchUsers(id);
                if (users.Count() == 0)
                {
                    return View(new List<User>());
                }
                else
                {
                    return View(users);
                }
            }
            return View();

        }
        public ActionResult Profile(string id)
        {
            return ViewProfile(id);
        }
        public ActionResult ViewProfile(string id)
        {
            return View(userAccessor.FindUser(id));
        }
    }
}