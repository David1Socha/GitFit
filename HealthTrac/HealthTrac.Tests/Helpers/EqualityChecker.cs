using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using HealthTrac.Models.Dto;
using HealthTrac.Models;
using Microsoft.AspNet.Identity.EntityFramework;

namespace HealthTrac.Tests.Helpers
{
    public static class EqualityChecker
    {
        public static bool EqualValues(this TeamDto teamDto, Team team)
        {
            bool equal = teamDto.DateCreated == team.DateCreated
                && teamDto.DateModified == team.DateModified
                && teamDto.Description == team.Description
                && teamDto.ID == team.ID
                && teamDto.Name == team.Name;
            return equal;
        }

        public static bool EqualValues(this ActivityDto activityDto, Activity activity)
        {
            bool equal = activityDto.Distance == activity.Distance &&
                activityDto.Duration == activity.Duration &&
                activityDto.Steps == activity.Steps &&
                activityDto.StartDate == activity.StartDate &&
                activityDto.ID == activity.ID &&
                activityDto.UserID == activity.UserID &&
                activityDto.Type == activity.Type;
            return equal;
        }

        public static bool EqualValues(this ActivityReportDto activityReportDto, ActivityReport activityReport)
        {
            bool equal = activityReportDto.Date == activityReport.Date &&
                activityReportDto.ID == activityReport.ID &&
                activityReportDto.UserID == activityReport.UserID &&
                activityReportDto.Steps == activityReport.Steps &&
                activityReportDto.Distance == activityReport.Distance &&
                activityReportDto.Duration == activityReport.Duration;
            return equal;
        }

        public static bool EqualValues(this BadgeDto badgeDto, Badge badge)
        {
            bool equal = badgeDto.Field == badge.Field &&
                badgeDto.ID == badge.ID &&
                badgeDto.Name == badge.Name &&
                badgeDto.Threshold == badge.Threshold;
            return equal;
        }

        public static bool EqualValues(this EnergyLevelDto energyLevelDto, EnergyLevel energyLevel)
        {
            bool equal = energyLevelDto.ID == energyLevel.ID &&
                energyLevelDto.Mood == energyLevel.Mood &&
                energyLevelDto.UserID == energyLevel.UserID &&
                energyLevelDto.DateCreated == energyLevel.DateCreated;
            return equal;
        }

        public static bool EqualValues(this AccessGrantDto self, AccessGrantDto other)
        {
            bool equal = self.AccessToken == other.AccessToken
                && self.Expires == other.Expires
                && self.ExpiresIn == other.ExpiresIn
                && self.ID == other.ID
                && self.Issued == other.Issued
                && self.TokenType == other.TokenType
                && self.UserName == other.UserName;
            return equal;
        }

        public static bool EqualValues(this IEnumerable<TeamDto> teamDtos, IEnumerable<Team> teams)
        {
            if (teamDtos.Count() != teams.Count())
            {
                return false;
            }
            bool equal = teamDtos
                .Zip(teams, (dto, t) => new Tuple<TeamDto, Team>(dto, t))
                .All(t => t.Item1.EqualValues(t.Item2));
            return equal;
        }

        public static bool EqualValues(this IEnumerable<ActivityDto> activityDtos, IEnumerable<Activity> activities)
        {
            if (activityDtos.Count() != activities.Count())
            {
                return false;
            }
            bool equal = activityDtos
                .Zip(activities, (dto, a) => new Tuple<ActivityDto, Activity>(dto, a))
                .All(a => a.Item1.EqualValues(a.Item2));
            return equal;
        }

        public static bool EqualValues(this IEnumerable<ActivityReportDto> activityReportDtos, IEnumerable<ActivityReport> activityReports)
        {
            if (activityReportDtos.Count() != activityReports.Count())
            {
                return false;
            }
            bool equal = activityReportDtos
                .Zip(activityReports, (dto, a) => new Tuple<ActivityReportDto, ActivityReport>(dto, a))
                .All(a => a.Item1.EqualValues(a.Item2));
            return equal;
        }

        public static bool EqualValues(this IEnumerable<BadgeDto> badgeDtos, IEnumerable<Badge> badges)
        {
            if (badgeDtos.Count() != badges.Count())
            {
                return false;
            }
            bool equal = badgeDtos
                .Zip(badges, (dto, b) => new Tuple<BadgeDto, Badge>(dto, b))
                .All(b => b.Item1.EqualValues(b.Item2));
            return equal;
        }

        public static bool EqualValues(this IEnumerable<EnergyLevelDto> energyLevelDtos, IEnumerable<EnergyLevel> energyLevels)
        {
            if (energyLevelDtos.Count() != energyLevels.Count())
            {
                return false;
            }
            bool equal = energyLevelDtos
                .Zip(energyLevels, (dto, el) => new Tuple<EnergyLevelDto, EnergyLevel>(dto, el))
                .All(el => el.Item1.EqualValues(el.Item2));
            return equal;
        }

        public static bool EqualValues(this MembershipDto membershipDto, Membership membership)
        {
            bool equal = membershipDto.DateCreated == membership.DateCreated
                && membershipDto.DateModified == membership.DateModified
                && membershipDto.ID == membership.ID
                && membershipDto.MembershipStatus == membership.MembershipStatus
                && membershipDto.TeamID == membership.TeamID
                && membershipDto.UserID == membership.UserID;
            return equal;
        }

        public static bool EqualValues(this IEnumerable<MembershipDto> membershipDtos, IEnumerable<Membership> memberships)
        {
            if (membershipDtos.Count() != memberships.Count())
            {
                return false;
            }
            bool equal = membershipDtos
                .Zip(memberships, (dto, m) => new Tuple<MembershipDto, Membership>(dto, m))
                .All(t => t.Item1.EqualValues(t.Item2));
            return equal;
        }

        public static bool EqualValues(this UserDto userDto, User user)
        {
            bool equal = userDto.BirthDate == user.BirthDate
                && userDto.DateCreated == user.DateCreated
                && userDto.DateModified == user.DateModified
                && userDto.Email == user.Email
                && userDto.FirstName == user.FirstName
                && userDto.Height == user.Height
                && userDto.Id == user.Id
                && userDto.LastName == user.LastName
                && userDto.PreferredName == user.PreferredName
                && userDto.ProfilePicture == user.ProfilePicture
                && userDto.Sex == user.Sex
                && userDto.UserName == user.UserName
                && userDto.Weight == user.Weight
                && userDto.Location == user.Location;
            return equal;
        }

        public static bool EqualValues(this IEnumerable<UserDto> userDtos, IEnumerable<User> users)
        {
            if (userDtos.Count() != users.Count())
            {
                return false;
            }
            bool equal = userDtos
                .Zip(users, (dto, u) => new Tuple<UserDto, User>(dto, u))
                .All(t => t.Item1.EqualValues(t.Item2));
            return equal;
        }
    }
}
