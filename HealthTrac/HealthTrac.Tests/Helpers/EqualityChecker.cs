﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using HealthTrac.Models.Dto;
using HealthTrac.Models;

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
                && teamDto.Name == team.Name
                && teamDto.Visibility == team.Visibility;
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
    }
}
