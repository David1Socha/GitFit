using HealthTrac.Services;
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
        private ITeamService team;
        private IUserService user;
        private IBadgeAccessor badge;
        private IEnergyLevelAccessor energyLevel;
        private IGoalAccessor goal;
        private IMealAccessor meal;
        private IPointAccessor point;
        private IUserBadgeAccessor userBadge;
        private IUserGoalAccessor userGoal;

        public EntityUnitOfWork(ApplicationDbContext db)
        {
            this.db = db;
        }

        public IUserGoalAccessor UserGoalAccessor
        {
            get
            {
                userGoal = userGoal ?? new EntityUserGoalAccessor(db);
                return userGoal;
            }
        }

        public IUserBadgeAccessor UserBadgeAccessor
        {
            get
            {
                userBadge = userBadge ?? new EntityUserBadgeAccessor(db);
                return userBadge;
            }
        }

        public IPointAccessor PointAccessor
        {
            get
            {
                point = point ?? new EntityPointAccessor(db);
                return point;
            }
        }

        public IMealAccessor MealAccessor
        {
            get
            {
                meal = meal ?? new EntityMealAccessor(db);
                return meal;
            }
        }

        public IGoalAccessor GoalAccessor
        {
            get
            {
                goal = goal ?? new EntityGoalAccessor(db);
                return goal;
            }
        }

        public IEnergyLevelAccessor EnergyLevelAccessor
        {
            get
            {
                energyLevel = energyLevel ?? new EntityEnergyLevelAccessor(db);
                return energyLevel;
            }
        }

        public IBadgeAccessor BadgeAccessor
        {
            get
            {
                badge = badge ?? new EntityBadgeAccessor(db);
                return badge;
            }
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

        public ITeamService TeamService
        {
            get
            {
                team = team ?? new TeamService(new EntityTeamAccessor(db));
                return team;
            }
        }

        public IUserService UserService
        {
            get
            {
                user = user ?? new UserService(new EntityUserAccessor(db));
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