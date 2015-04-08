using HealthTrac.DataAccess;
using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Services
{
    public class ActivityService : IActivityService
    {

        private IActivityAccessor _acc;
        private IUserAccessor _uAcc;
        private IUserBadgeAccessor _ubAcc;
        private IUserGoalAccessor _ugAcc;
        private IBadgeAccessor _bAcc;
        private IGoalAccessor _gAcc;
        private IActivityReportAccessor _arAcc;
        private IActivityForest _forest;

        public ActivityService(IActivityAccessor acc, IUserAccessor uAcc, IUserBadgeAccessor ubAcc, IUserGoalAccessor ugAcc, IBadgeAccessor bAcc, IGoalAccessor gAcc, IActivityForest forest, IActivityReportAccessor arAcc)
        {
            _forest = forest;
            _acc = acc;
            _arAcc = arAcc;
            _uAcc = uAcc;
            _ubAcc = ubAcc;
            _ugAcc = ugAcc;
            _bAcc = bAcc;
            _gAcc = gAcc;
        }

        public IEnumerable<Activity> GetActivities()
        {
            return _acc.GetActivities();
        }

        public Models.Activity GetActivity(long id)
        {
            return _acc.GetActivity(id);
        }

        public IEnumerable<Models.Activity> GetActivities(string userId)
        {
            return _acc.GetActivities(userId);
        }

        public Models.Activity CreateActivity(Models.Activity activity, String uid)
        {
            _PredictType(activity);
            var createdActivity = _acc.CreateActivity(activity);
            _UpdateUserAchievements(activity, uid);
            _UpdateActivityReport(activity, uid);
            return createdActivity;
        }

        private void _UpdateActivityReport(Activity activity, String uid)
        {
            ActivityReport r = new ActivityReport()
            {
                Date = activity.StartDate,
                Distance = activity.Distance,
                Duration = activity.Duration,
                Steps = activity.Steps,
                UserID = uid,
            };
            _arAcc.AddOrUpdate(r);
        }

        private void _PredictType(Activity activity)
        {
            var type = _forest.PredictType(activity);
            activity.Type = type;
        }

        private void _UpdateUserAchievements(Activity activity, String uid)
        {
            var user = _uAcc.FindUser(uid);
            var oldDistance = user.LifetimeDistance;
            var oldDuration = user.LifetimeDuration;
            var oldSteps = user.LifetimeSteps;
            var newDistance = oldDistance + activity.Distance;
            var newDuration = oldDuration + activity.Duration;
            var newSteps = oldSteps + activity.Steps;
            user.LifetimeDistance = newDistance;
            user.LifetimeDuration = newDuration;
            user.LifetimeSteps = newSteps;
            _uAcc.UpdateUser(user);

            foreach (Badge b in _bAcc.GetBadges())
            {
                bool justEarned = false;
                switch (b.Field)
                {
                    case Field.DISTANCE:
                        justEarned = (oldDistance < b.Threshold && newDistance >= b.Threshold);
                        break;
                    case Field.DURATION:
                        justEarned = oldDuration < b.Threshold && newDuration >= b.Threshold;
                        break;
                    case Field.STEPS:
                        justEarned = oldSteps < b.Threshold && newSteps >= b.Threshold;
                        break;
                }
                if (justEarned)
                {
                    _ubAcc.CreateUserBadge(new UserBadge()
                    {
                        BadgeID = b.ID,
                        DateCompleted = DateTime.Now,
                        UserID = user.Id,
                    });
                }
            }

            foreach (Goal b in _gAcc.GetGoals()) // TODO The duplication here feels bad; is there some clean way to give goals and badges the same interface/prevent this?
            {
                bool justEarned = false;
                switch (b.Field)
                {
                    case Field.DISTANCE:
                        justEarned = (oldDistance < b.Threshold && newDistance >= b.Threshold);
                        break;
                    case Field.DURATION:
                        justEarned = oldDuration < b.Threshold && newDuration >= b.Threshold;
                        break;
                    case Field.STEPS:
                        justEarned = oldSteps < b.Threshold && newSteps >= b.Threshold;
                        break;
                }
                if (justEarned)
                {
                    var ug = _ugAcc.GetUserGoal(b.ID, uid);
                    if (ug != null)
                    {
                        ug.DateCompleted = DateTime.Now;
                        _ugAcc.UpdateUserGoal(ug);
                    }
                }
            }
        }

        public Models.Activity UpdateActivity(Models.Activity activity)
        {
            return _acc.UpdateActivity(activity);
        }

        public void DeleteActivity(long ID)
        {
            _acc.DeleteActivity(ID);
        }
    }
}