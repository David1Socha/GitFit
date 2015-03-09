using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Models.Dto
{
    public class TeamDto
    {
        public DateTime DateCreated { get; set; }
        public DateTime DateModified { get; set; }
        public long ID { get; set; }
        public string Name { get; set; }
        public String Description { get; set; }

        public static TeamDto FromTeam(Team t)
        {
            return new TeamDto()
            {
                DateCreated = t.DateCreated,
                DateModified = t.DateModified,
                Description = t.Description,
                ID = t.ID,
                Name = t.Name,
            };
        }
    }
}