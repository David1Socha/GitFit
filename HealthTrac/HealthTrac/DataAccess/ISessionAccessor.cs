using System;
namespace HealthTrac.DataAccess
{
    interface ISessionAccessor
    {
        bool DeleteSession(long sessionId);
        HealthTrac.Models.ExerciseSession GetSession(long ID);
        System.Collections.Generic.IEnumerable<HealthTrac.Models.ExerciseSession> GetSessions(System.Collections.Generic.List<long> IDs);
        System.Collections.Generic.IEnumerable<HealthTrac.Models.ExerciseSession> GetSessions(string userId, DateTime from, DateTime to);
        System.Collections.Generic.IEnumerable<HealthTrac.Models.ExerciseSession> GetSessions(string userId, long activityId, DateTime from, DateTime to);
        bool SaveSession(HealthTrac.Models.ExerciseSession Session);
    }
}
