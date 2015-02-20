using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Microsoft.Practices.Unity;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.EntityFramework;
using HealthTrac.Controllers;
using HealthTrac.DataAccess;
using HealthTrac.DataAccess.Entity;

namespace HealthTrac
{
    public class Bootstrapper
    {
        public static void Initialize()
        {
            var container = BuildUnityContainer();
            DependencyResolver.SetResolver(new UnityDependencyResolver(container));
        }

        private static IUnityContainer BuildUnityContainer()
        {
            var container = new UnityContainer();
            container.RegisterType<IUserAccessor, EntityUserAccessor>(new HierarchicalLifetimeManager());
            container.RegisterType<ITeamAccessor, EntityTeamAccessor>(new HierarchicalLifetimeManager());
            container.RegisterType<IMembershipAccessor, EntityMembershipAccessor>(new HierarchicalLifetimeManager());
            container.RegisterType<IActivityAccessor, EntityActivityAccessor>(new HierarchicalLifetimeManager());
            container.RegisterType<ISessionAccessor, EntitySessionAccessor>(new HierarchicalLifetimeManager());
            container.RegisterType<IStatusAccessor, EntityStatusAccessor>(new HierarchicalLifetimeManager());
            container.RegisterType<UserManager, ApplicationUserManager>(new HierarchicalLifetimeManager());
            return container;
        }
    }

    public class UnityDependencyResolver : IDependencyResolver
    {

        private IUnityContainer container;
        public UnityDependencyResolver(IUnityContainer container)
        {
            if (container == null)
            {
                throw new ArgumentNullException("Container");
            }
            this.container = container;
        }

        public object GetService(Type serviceType)
        {
            try
            {
                return container.Resolve(serviceType);
            }
            catch (ResolutionFailedException)
            {
                return null;
            }
        }

        public IEnumerable<object> GetServices(Type serviceType)
        {
            try
            {
                return container.ResolveAll(serviceType);
            }
            catch (ResolutionFailedException)
            {
                return new List<object>();
            }
        }
    }
}