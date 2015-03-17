using System;
using System.Collections.Generic;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Web;

namespace HealthTrac.DataAccess.Entity
{
    public class EntityUnitOfWork : IUnitOfWork
    {

        private ApplicationDbContext db;
        private IActivityAccessor activity;
        private IMembershipAccessor membership;
        private ITeamAccessor team;
        private IUserAccessor user;

        public EntityUnitOfWork(ApplicationDbContext db)
        {
            this.db = db;
        }

        public IActivityAccessor ActivityAccessor
        {
            get
            {
                activity = activity ?? new EntityActivityAccessor(db);
                return activity;
            }
        }

        public IMembershipAccessor MembershipAccessor
        {
            get
            {
                membership = membership ?? new EntityMembershipAccessor(db);
                return membership;
            }
        }

        public ITeamAccessor TeamAccessor
        {
            get
            {
                team = team ?? new EntityTeamAccessor(db);
                return team;
            }
        }

        public IUserAccessor UserAccessor
        {
            get
            {
                user = user ?? new EntityUserAccessor(db);
                return user;
            }
        }

        public void Save()
        {
            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                throw new ConcurrentUpdateException();
            }
        }

        public void Dispose()
        {
            db.Dispose();
        }
    }
}