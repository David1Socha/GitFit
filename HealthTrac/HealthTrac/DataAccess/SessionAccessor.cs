using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.DataAccess
{
    public class SessionAccessor : ISessionAccessor
    {
        public ExerciseSession GetSession(long ID)
        {
            using (var db = new ApplicationDbContext())
            {
                ExerciseSession session = db.ExerciseSessions
                    .Where(s => s.ID == ID)
                    .FirstOrDefault();
                return session;
            }
        }
        public IEnumerable<ExerciseSession> GetSessions(List<long> IDs)
        {

        }
        public IEnumerable<ExerciseSession> GetSessions(string userId, DateTime from, DateTime to)
        {

        }
        public IEnumerable<ExerciseSession> GetSessions(string userId, long activityId, DateTime from, DateTime to)
        {

        }
        public Boolean SaveSession(ExerciseSession Session)
        {

        }
        public Boolean DeleteSession(long sessionId)
        {

        }
    }
}