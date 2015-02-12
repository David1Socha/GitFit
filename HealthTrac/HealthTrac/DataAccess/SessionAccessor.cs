using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.DataAccess
{
    public class SessionAccessor
    {
        public ExerciseSession GetSession(long ID)
        {

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