using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HealthTrac.Tests.Helpers
{
    public static class TeamGenerator
    {
        public static Team GenerateTeam1()
        {
            return new Team
            {
                DateCreated = new DateTime(2000, 4, 20),
                DateModified = new DateTime(2005, 4, 20),
                Name = "Fake team 9000",
                Description = null,
                Visibility = Visibility.PUBLIC
            };
        }

        public static Team GenerateTeam2()
        {
            return new Team
            {
                DateCreated = new DateTime(1000, 10, 10),
                DateModified = new DateTime(1111, 11, 11),
                Description = "Enough said",
                Name = "Team 6 is the best",
                Visibility = Visibility.SECRET
            };
        }

        public static Team[] GenerateManyTeams()
        {
            return new Team[] {
                GenerateTeam1(), GenerateTeam2()
            };
        }
    }
}
