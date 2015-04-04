using HealthTrac.DataAccess;
using HealthTrac.DataAccess.Entity;
using HealthTrac.Migrations;
using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Net.Http.Formatting;
using System.Web;
using System.Web.Http;
using System.Web.Mvc;
using System.Web.Optimization;
using System.Web.Routing;

namespace HealthTrac
{
    public class MvcApplication : System.Web.HttpApplication
    {
        protected void Application_Start()
        {
            Database.SetInitializer(new MigrateDatabaseToLatestVersion<ApplicationDbContext, Configuration>());
            AreaRegistration.RegisterAllAreas();
            ControllerBuilder.Current.DefaultNamespaces.Add("HealthTrac.Controllers.Api");
            GlobalConfiguration.Configure(WebApiConfig.Register);
            FilterConfig.RegisterGlobalFilters(GlobalFilters.Filters);
            RouteConfig.RegisterRoutes(RouteTable.Routes);
            BundleConfig.RegisterBundles(BundleTable.Bundles);
            GlobalConfiguration.Configuration.Formatters.Clear();
            var json = new JsonMediaTypeFormatter();
            json.SerializerSettings.ReferenceLoopHandling = Newtonsoft.Json.ReferenceLoopHandling.Ignore;
            json.SerializerSettings.PreserveReferencesHandling =
                Newtonsoft.Json.PreserveReferencesHandling.None;
            GlobalConfiguration.Configuration.Formatters.Add(json);
            DependencyResolver.SetResolver(Bootstrapper.GetMvcResolver());
            var activities = DependencyResolver.Current.GetService<IActivityAccessor>().GetActivities();
            //Charon.Learning.forest<Activity,Activity>(Charon.Learning.DefaultSettings)
            GlobalConfiguration.Configuration.DependencyResolver = Bootstrapper.GetApiResolver();
        }
    }
}
