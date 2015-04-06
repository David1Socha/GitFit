using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using HealthTrac.Models;

namespace HealthTrac.Tests.Helpers
{
    public static class UserGenerator
    {
        public static User GenerateFacebookUser()
        {
            return new User
            {
                BirthDate = new DateTime(1995, 9, 7),
                DateCreated = new DateTime(2000, 2, 2),
                DateModified = new DateTime(2001, 1, 1),
                FirstName = "Shrek",
                Height = 77,
                Id = "qwerty",
                LastName = "TheOgre",
                Location = "Omaha",
                PreferredName = "xXxOgrelordxXx",
                ProfilePicture = "http://www.facebook.com/sample.jpg",
                Sex = Sex.MALE,
                UserName = "xXxOgrelordxXx",
                Weight = 999
            };
        }

        public static User GenerateTwitterUser()
        {
            return new User
            {
                BirthDate = new DateTime(1996, 9, 7),
                DateCreated = new DateTime(2002, 2, 2),
                DateModified = new DateTime(2003, 1, 1),
                FirstName = "John",
                Height = 50,
                Id = "poiuy",
                LastName = "John",
                Location = "South Dakota",
                PreferredName = "John",
                ProfilePicture = "http://www.twitter.com/hi.jpg",
                Sex = Sex.MALE,
                UserName = "John",
                Weight = 200
            };
        }

        public static User[] GenerateUsers()
        {
            return new User[] {
                GenerateFacebookUser(),
                GenerateTwitterUser()
            };
        }
    }
}
