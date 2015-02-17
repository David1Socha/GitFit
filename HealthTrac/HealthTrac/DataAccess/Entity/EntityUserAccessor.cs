using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.DataAccess.Entity
{
    public class EntityUserAccessor : IUserAccessor
    {
        public User FindUser(string ID)
        {
            using (var db = new ApplicationDbContext())
            {
                var user = db.Users
                                .Where(u => u.Id == ID).FirstOrDefault();
                return user;
            }
        }

        public User SaveUser(User user)
        {
            using (var db = new ApplicationDbContext())
            {
                if (user.Id == "0")
                {
                    db.Users.Add(user);
                }
                else
                {
                    db.Users.Attach(user);
                    db.Entry(user).State = System.Data.Entity.EntityState.Modified;
                }
                db.SaveChanges();
            }
            return user;
        }

        public IEnumerable<User> GetUsers()
        {
            using (var db = new ApplicationDbContext())
            {
                return db.Users.ToList();
            }
        }
    }
}