using System;
namespace HealthTrac.DataAccess
{
    public interface ISessionAccessor : IDisposable
    {
        bool DeleteSession(long sessionId);
        HealthTrac.Models.ExerciseSession GetSession(long ID);
        System.Collections.Generic.IEnumerable<HealthTrac.Models.ExerciseSession> GetSessions(System.Collections.Generic.List<long> IDs);
        System.Collections.Generic.IEnumerable<HealthTrac.Models.ExerciseSession> GetSessions(string userId, DateTime from, DateTime to);
        System.Collections.Generic.IEnumerable<HealthTrac.Models.ExerciseSession> GetSessions(string userId, long activityId, DateTime from, DateTime to);
        bool CreateSession(HealthTrac.Models.ExerciseSession session);
    }
}
