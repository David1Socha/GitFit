using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Microsoft.Practices.Unity;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.EntityFramework;
using HealthTrac.Controllers;
using HealthTrac.DataAccess;
using HealthTrac.DataAccess.Entity;
using HealthTrac.Models;
using System.Data.Entity;
using System.Web.Mvc;
using System.Web.Http.Dependencies;
using Microsoft.Owin.Security;

namespace HealthTrac
{
    public class Bootstrapper
    {
        public static System.Web.Mvc.IDependencyResolver GetMvcResolver()
        {
            var container = BuildUnityContainer();
            var resolver = new MvcResolver(container);
            return resolver;
        }

        public static System.Web.Http.Dependencies.IDependencyResolver GetApiResolver()
        {
            var container = BuildUnityContainer();
            System.Web.Http.Dependencies.IDependencyResolver resolver = new ApiResolver(container);
            return resolver;
        }

        private static IUnityContainer BuildUnityContainer()
        {
            var container = new UnityContainer();
            container.RegisterType<IUserAccessor, EntityUserAccessor>(new HierarchicalLifetimeManager());
            container.RegisterType<ITeamAccessor, EntityTeamAccessor>(new HierarchicalLifetimeManager());
            container.RegisterType<IMembershipAccessor, EntityMembershipAccessor>(new HierarchicalLifetimeManager());
            container.RegisterType<IActivityAccessor, EntityActivityAccessor>(new HierarchicalLifetimeManager());
            container.RegisterType<IStatusAccessor, EntityStatusAccessor>(new HierarchicalLifetimeManager());
            container.RegisterType<IUserStore<User>, UserStore<User>>(new InjectionConstructor(new ApplicationDbContext()));
            container.RegisterType<IAuthenticationManager>(new InjectionFactory(c => HttpContext.Current.GetOwinContext().Authentication));
            return container;
        }
    }

    public class ApiResolver : System.Web.Http.Dependencies.IDependencyResolver
    {
        protected IUnityContainer container;

        public ApiResolver(IUnityContainer container)
        {
            if (container == null)
            {
                throw new ArgumentNullException("container");
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

        public IDependencyScope BeginScope()
        {
            var child = container.CreateChildContainer();
            return new ApiResolver(child);
        }

        public void Dispose()
        {
            container.Dispose();
        }
    }

    public class MvcResolver : System.Web.Mvc.IDependencyResolver
    {

        private IUnityContainer container;
        public MvcResolver(IUnityContainer container)
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