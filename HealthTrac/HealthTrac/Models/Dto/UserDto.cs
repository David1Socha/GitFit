using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models.Dto
{
    public class UserDto
    {
        public DateTime DateCreated { get; set; }
        public DateTime DateModified { get; set; }
        public String FirstName { get; set; }
        public String LastName { get; set; }
        public String PreferredName { get; set; }
        public String Location { get; set; }
        public String Email { get; set; }
        public Sex Sex { get; set; }
        public double Height { get; set; }
        public double Width { get; set; }
        public DateTime? BirthDate { get; set; }
        public string Id { get; set; }
        public string UserName { get; set; }

        public static UserDto FromUser(User u)
        {
            return new UserDto
            {
                DateCreated = u.DateCreated,
                DateModified = u.DateModified,
                FirstName = u.FirstName,
                LastName = u.LastName,
                PreferredName = u.PreferredName,
                Email = u.Email,
                Sex = u.Sex,
                Height = u.Height,
                Width = u.Width,
                BirthDate = u.BirthDate,
                UserName = u.UserName,
                Id = u.Id,
                Location = u.Location,
            };
        }
    }
}