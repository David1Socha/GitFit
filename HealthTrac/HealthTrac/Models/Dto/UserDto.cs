using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models.Dto
{
    public class UserDto
    {
        public String ProfilePicture { get; set; }
        public DateTime DateCreated { get; set; }
        public DateTime DateModified { get; set; }
        public String FirstName { get; set; }
        public String LastName { get; set; }
        public String PreferredName { get; set; }
        public String Location { get; set; }
        public String Email { get; set; }
        public Sex Sex { get; set; }
        public double Height { get; set; }
        public double Weight { get; set; }
        public DateTime? BirthDate { get; set; }
        public string Id { get; set; }
        public string UserName { get; set; }

        public User ToUser()
        {
            return new User()
            {
                BirthDate = BirthDate,
                Email = Email,
                FirstName = FirstName,
                Height = Height,
                Location = Location,
                LastName = LastName,
                PreferredName = PreferredName,
                ProfilePicture = ProfilePicture,
                Sex = Sex,
                Weight = Weight,
                UserName = UserName
            };
        }

        public static UserDto FromUser(User u)
        {
            return new UserDto
            {
                DateCreated = u.DateCreated,
                DateModified = u.DateModified,
                FirstName = u.FirstName,
                LastName = u.LastName,
                PreferredName = u.PreferredName,
                ProfilePicture = u.ProfilePicture,
                Email = u.Email,
                Sex = u.Sex,
                Height = u.Height,
                Weight = u.Weight,
                BirthDate = u.BirthDate,
                UserName = u.UserName,
                Id = u.Id,
                Location = u.Location,
            };
        }
    }
}