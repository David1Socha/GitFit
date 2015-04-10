﻿using System;
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
