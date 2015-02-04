using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.DataAccess
{
    public class SessionAccessor
    {
        public ExerciseSession GetSession(int ID)
        {

        }
        public IEnumerable<ExerciseSession> GetSessions(List<int> IDs)
        {

        }
        public IEnumerable<ExerciseSession> GetSessions(int userId, DateTime from, DateTime to)
        {

        }
        public IEnumerable<ExerciseSession> GetSessions(int userId, int activityId, DateTime from, DateTime to)
        {

        }
        public Boolean SaveSession(ExerciseSession Session)
        {
            
        }
        public Boolean DeleteSession(int sessionId)
        {

        }
    }
}