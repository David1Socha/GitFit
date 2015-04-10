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
using Microsoft.FSharp;
using Microsoft.FSharp.Collections;
using Microsoft.FSharp.Core;
using Hangfire;
using Hangfire.SqlServer;
using HealthTrac.Services;

namespace HealthTrac
{
    public class MvcApplication : System.Web.HttpApplication
    {

        private BackgroundJobServer _server;

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
            GlobalConfiguration.Configuration.DependencyResolver = Bootstrapper.GetApiResolver();
            JobStorage.Current =
                new SqlServerStorage("DefaultConnection");
            _server = new BackgroundJobServer();
            _server.Start();
            //BuildForest();
            //RecurringJob.AddOrUpdate(() => BuildForest(), Cron.Minutely);
        }
        public void BuildForest()
        {
            var activities = ((IActivityService)GlobalConfiguration.Configuration.DependencyResolver.GetService(typeof(IActivityService))).GetActivities();
            ((ActivityForestBuilder)GlobalConfiguration.Configuration.DependencyResolver.GetService(typeof(ActivityForestBuilder))).BuildForest(activities);
        }

        protected void Application_End()
        {
            _server.Stop();
        }
    }
}
