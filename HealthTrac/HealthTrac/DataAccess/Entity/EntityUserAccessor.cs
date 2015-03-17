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

        private ApplicationDbContext db;

        public EntityUserAccessor(ApplicationDbContext db)
        {
            this.db = db;
        }

        public User FindUser(string ID)
        {
            var user = db.Users.Include("Memberships").Include("Memberships.Team")
                            .Where(u => u.Id == ID && u.Enabled).FirstOrDefault();
            return user;
        }

        public IEnumerable<User> SearchUsers(string name)
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

        public User UpdateUser(User user)
        {
            db.Entry(user).State = System.Data.Entity.EntityState.Modified;
            return user;
        }

        public IEnumerable<User> GetUsers()
        {
            return db.Users.Where(u => u.Enabled).ToList();
        }

        public User DeleteUser(User user)
        {
            if (user != null)
            {
                user.Enabled = false;
            }
            return UpdateUser(user);
        }

        public User GetAnyUserWithUserName(String userName)
        {
            return db.Users.Where(u => u.UserName == userName).FirstOrDefault();
        }

    }
}