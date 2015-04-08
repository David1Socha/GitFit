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
        private IActivityService activity;
        private IMembershipService membership;
        private ITeamService team;
        private IUserService user;
        private IBadgeService badge;
        private IEnergyLevelService energyLevel;
        private IGoalService goal;
        private IMealService meal;
        private IPointService point;
        private IUserBadgeService userBadge;
        private IUserGoalService userGoal;
        private IActivityForest _forest;
        private IActivityReportService activityReport;

        public EntityUnitOfWork(ApplicationDbContext db, IActivityForest forest)
        {
            _forest = forest;
            this.db = db;
        }

        public IUserGoalService UserGoalService
        {
            get
            {
                userGoal = userGoal ?? new UserGoalService(new EntityUserGoalAccessor(db));
                return userGoal;
            }
        }

        public IUserBadgeService UserBadgeService
        {
            get
            {
                userBadge = userBadge ?? new UserBadgeService(new EntityUserBadgeAccessor(db));
                return userBadge;
            }
        }

        public IPointService PointService
        {
            get
            {
                point = point ?? new PointService(new EntityPointAccessor(db));
                return point;
            }
        }

        public IMealService MealService
        {
            get
            {
                meal = meal ?? new MealService(new EntityMealAccessor(db));
                return meal;
            }
        }

        public IGoalService GoalService
        {
            get
            {
                goal = goal ?? new GoalService(new EntityGoalAccessor(db), new EntityUserGoalAccessor(db));
                return goal;
            }
        }

        public IEnergyLevelService EnergyLevelService
        {
            get
            {
                energyLevel = energyLevel ?? new EnergyLevelService(new EntityEnergyLevelAccessor(db));
                return energyLevel;
            }
        }

        public IBadgeService BadgeService
        {
            get
            {
                badge = badge ?? new BadgeService(new EntityBadgeAccessor(db));
                return badge;
            }
        }

        public IActivityService ActivityService
        {
            get
            {
                activity = activity ?? new ActivityService(new EntityActivityAccessor(db), new EntityUserAccessor(db), new EntityUserBadgeAccessor(db), new EntityUserGoalAccessor(db), new EntityBadgeAccessor(db), new EntityGoalAccessor(db), _forest, new EntityActivityReportAccessor(db));
                return activity;
            }
        }

        public IMembershipService MembershipService
        {
            get
            {
                membership = membership ?? new MembershipService(new EntityMembershipAccessor(db), new EntityTeamAccessor(db));
                return membership;
            }
        }

        public ITeamService TeamService
        {
            get
            {
                team = team ?? new TeamService(new EntityTeamAccessor(db), new EntityMembershipAccessor(db));
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


        public IActivityReportService ActivityReportService
        {
            get
            {
                activityReport = activityReport ?? new ActivityReportService(new EntityActivityReportAccessor(db));
                return activityReport;
            }
        }
    }
}