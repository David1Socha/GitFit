using HealthTrac.DataAccess;
using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;

namespace HealthTrac.DataAccess.Entity
{
    public class EntityUserAccessor : IUserAccessor
    {
        public User FindUser(string ID)
        {
            using (var db = new ApplicationDbContext())
            {
                var user = db.Users.Include("Memberships").Include("Memberships.Team")
                                .Where(u => u.Id == ID).FirstOrDefault();
                user = user != null && user.Enabled ? user : null;
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
                try
                {
                    db.SaveChanges();
                }
                catch (DbUpdateConcurrencyException ex)
                {
                    throw new ConcurrentUpdateException("The User you are attempting to update has been externally modified.", ex);
                }

            }
            return user;
        }

        public IEnumerable<User> GetUsers()
        {
            using (var db = new ApplicationDbContext())
            {
                return db.Users.Where(u => u.Enabled).ToList();
            }
        }

        public User DeleteUser(User user)
        {
            if (user != null)
            {
                user.Enabled = false;
            }
            return UpdateUser(user);
        }
    }
}