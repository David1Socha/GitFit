﻿using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.Entity.Infrastructure;

namespace HealthTrac.DataAccess.Entity
{
    public class EntitySessionAccessor : ISessionAccessor
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
            using (var db = new ApplicationDbContext())
            {
                var sessions = db.ExerciseSessions
                    .Where(s => IDs.Contains(s.ID));
                return sessions;
            }
        }
        public IEnumerable<ExerciseSession> GetSessions(string userId, DateTime from, DateTime to)
        {
            using (var db = new ApplicationDbContext())
            {
                var sessions = db.ExerciseSessions
                    .Where(s => s.UserID.Equals(userId)
                        && s.DateCreated > from
                        && s.DateCreated < to);
                return sessions;
            }
        }
        public IEnumerable<ExerciseSession> GetSessions(string userId, long activityId, DateTime from, DateTime to)
        {
            using (var db = new ApplicationDbContext())
            {
                var sessions = db.ExerciseSessions
                    .Where(s => s.UserID.Equals(userId)
                        && s.ActivityID == activityId
                        && s.DateCreated > from
                        && s.DateCreated < to);
                return sessions;
            }
        }
        public Boolean CreateSession(ExerciseSession session)
        {
            using (var db = new ApplicationDbContext())
            {
                db.ExerciseSessions.Add(session);
                int changes = db.SaveChanges();
                return changes == 1;
            }
        }
        public Boolean DeleteSession(long sessionId)
        {
            using (var db = new ApplicationDbContext())
            {
                var session = db.ExerciseSessions
                    .Where(s => s.ID == sessionId)
                    .FirstOrDefault();
                if (session == null)
                {
                    return false;
                }
                else
                {
                    db.ExerciseSessions.Remove(session);
                    try
                    {
                        int changes = db.SaveChanges();
                        return changes == 1;
                    }
                    catch (DbUpdateConcurrencyException ex)
                    {
                        throw new ConcurrentUpdateException("The Session you are trying to delete has been modified externally.", ex);
                    }

                }

            }
        }
    }
}