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
            var data = activities.Select(a => new Tuple<Activity, Activity>(a, a));
            Converter<Activity, FSharpOption<ActivityType>> labelConverter = a => new FSharpOption<ActivityType>(a.Type);
            var label = new Tuple<String, Charon.Featurization.Feature<Activity>>("Type", Charon.Featurization.Categorical<Activity, ActivityType>((FSharpFunc<Activity, FSharpOption<ActivityType>>.FromConverter(labelConverter))));
            Converter<Activity, FSharpOption<double>> durConv = a => new FSharpOption<double>(a.Duration);
            var dur = new Tuple<String, Charon.Featurization.Feature<Activity>>("Duration", Charon.Featurization.Numerical<Activity, double>((FSharpFunc<Activity, FSharpOption<double>>.FromConverter(durConv))));
            Converter<Activity, FSharpOption<double>> disConv = a => new FSharpOption<double>(a.Distance);
            var dis = new Tuple<String, Charon.Featurization.Feature<Activity>>("Distance", Charon.Featurization.Numerical<Activity, double>((FSharpFunc<Activity, FSharpOption<double>>.FromConverter(disConv))));
            Converter<Activity, FSharpOption<long>> stepConv = a => new FSharpOption<long>(a.Steps);
            var step = new Tuple<String, Charon.Featurization.Feature<Activity>>("Steps", Charon.Featurization.Numerical<Activity, long>((FSharpFunc<Activity, FSharpOption<long>>.FromConverter(stepConv))));
            var features = FSharpList<Tuple<String, Charon.Featurization.Feature<Activity>>>.Cons(step,
                FSharpList<Tuple<String, Charon.Featurization.Feature<Activity>>>.Cons(dis,
                    FSharpList<Tuple<String, Charon.Featurization.Feature<Activity>>>.Cons(dur,
                        FSharpList<Tuple<String, Charon.Featurization.Feature<Activity>>>.Empty))); // equivalent to let features = [step; dis; dur] :(
            var forest = Charon.Learning.forest<Activity, Activity>(data, label, features, Charon.Learning.DefaultSettings);
            GlobalConfiguration.Configuration.DependencyResolver = Bootstrapper.GetApiResolver();
        }
    }
}
