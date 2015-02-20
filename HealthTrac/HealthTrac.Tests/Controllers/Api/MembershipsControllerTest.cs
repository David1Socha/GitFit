using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Web.Mvc;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using HealthTrac;
using System.Web.Http.Results;
using HealthTrac.Controllers;
using HealthTrac.DataAccess;
using HealthTrac.Models;
using HealthTrac.Models.Dto;
using HealthTrac.Controllers.Api;
using Moq;
using System.Net.Http;
using Moq.Linq;
using HealthTrac.Tests.Helpers;

namespace HealthTrac.Tests.Controllers.Api
{
    [TestClass]
    public class MembershipsControllerTest
    {
        private Membership _memberMembership, _adminMembership, _bannedMembership;
        private Membership[] _activeMemberships;

        [TestInitialize]
        public void Initialize()
        {
            _memberMembership = MembershipGenerator.GenerateMemberMembership();
            _adminMembership = MembershipGenerator.GenerateAdminMembership();
            _bannedMembership = MembershipGenerator.GenerateBannedMembership();
            _activeMemberships = MembershipGenerator.GenerateActiveMemberships();
        }

    }
}
