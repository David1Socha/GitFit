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
using Microsoft.Owin.Security;
using Microsoft.AspNet.Identity;
using HealthTrac.Services;

namespace HealthTrac.Tests.Controllers.Api
{
    [TestClass]
    public class AccountsControllerTest
    {
        private User _basicUser;
        private IProviderVerifyResult _facebookResult;

        [TestInitialize]
        public void Initialize()
        {
            _basicUser = UserGenerator.GenerateUser1();
            _facebookResult = ProviderVerifyResultGenerator.GenFacebookVerifyResult();
            _grant = AccessGrantGenerator.GenBasicGrant();
        }

        [TestMethod]
        public void ApiLoginReturnsAccessGrantWhenLegitFacebookCredentials()
        {
            var user = _basicUser;
            var loginMock = new Mock<LoginService>();
            loginMock.Setup(svc => svc.VerifyCredentials(It.IsAny<CredentialsDto>())).Returns(_facebookResult);
            loginMock.Setup(svc => svc.GenerateAccessGrant(user, It.IsAny<CredentialsDto>())).Returns(_grant);
            UserManager<User> userManager = Mock.Of<UserManager<User>>(man => man.Find(It.IsAny<UserLoginInfo>()) == user);
        }

    }

}
