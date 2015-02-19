using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity.Infrastructure;
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

        public IEnumerable<User> SearchUsers(string name)
        {
            using (var db = new ApplicationDbContext())
            {
                String[] names = name.Split(' ');
                List<User> users = new List<User>();
                foreach (string tempName in names)
                {
                    var tempUsers = db.Users
                                    .Where(U => U.FirstName.Contains(tempName) || U.LastName.Contains(tempName) || U.UserName.Contains(tempName)).ToList();
                    users.AddRange(tempUsers);
                }
                return users;
            }
        }

        public User UpdateUser(User user)
        {
            using (var db = new ApplicationDbContext())
            {
                db.Entry(user).State = System.Data.Entity.EntityState.Modified;
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

        public User DeleteUser(User user)
        {
            using (var db = new ApplicationDbContext())
            {
                db.Users.Remove(user);
                try
                {
                    db.SaveChanges();
                }
                catch (DbUpdateConcurrencyException ex)
                {
                    throw new ConcurrentUpdateException("The User you are attempting to delete has been externally modified", ex);
                }
                return user;
            }
        }
    }
}