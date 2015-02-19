using System;
using HealthTrac.DataAccess;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace HealthTrac.Controllers
{
    public class UserController : Controller
    {

        private IUserAccessor userAccessor;
        private AccountController accountController = new AccountController();
        //
        // GET: /User/
        public ActionResult Index(string id)
        {
            string searchString = id;

            if (!String.IsNullOrEmpty(searchString))
            {
                return View(userAccessor.SearchUsers(id).ToList());
            }
            return null;

        }
	}
}