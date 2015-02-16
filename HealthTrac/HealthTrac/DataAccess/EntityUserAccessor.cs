using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.DataAccess
{
    public class EntityUserAccessor : IUserAccessor
    {
        public ApplicationUser FindUser(string ID)
        {
            using (var db = new ApplicationDbContext())
            {
                var user = db.Users
                                .Where(u => u.Id == ID).FirstOrDefault();
                return user;
            }
        }

        public ApplicationUser SaveUser(ApplicationUser user)
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
    }
}