﻿using System;
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
using HealthTrac.Services;
using HealthTrac.Tests.Helpers;
using Microsoft.Owin.Security;
using Microsoft.AspNet.Identity;
using HealthTrac.Services;

namespace HealthTrac.Tests.Controllers.Api
{
    [TestClass]
    public class AccountsControllerTest
    {
        private User _facebookUser, _twitterUser;
        private IProviderVerifyResult _facebookResult, _twitterResult;
        private AccessGrantDto _twitterGrant, _facebookGrant;
        private CredentialsDto _facebookCredentials, _twitterCredentials;

        [TestInitialize]
        public void Initialize()
        {
            _facebookUser = UserGenerator.GenerateFacebookUser();
            _twitterUser = UserGenerator.GenerateTwitterUser();
            _facebookResult = ProviderVerifyResultGenerator.GenFacebookVerifyResult();
            _twitterResult = ProviderVerifyResultGenerator.GenTwitterVerifyResult();
            _facebookGrant = AccessGrantGenerator.GenFacebookGrant();
            _twitterGrant = AccessGrantGenerator.GenTwitterGrant();
            _facebookCredentials = CredentialsDtoGenerator.GenFacebookCredentials();
            _twitterCredentials = CredentialsDtoGenerator.GenTwitterCredentials();
        }

        [TestMethod]
        public void ApiLoginReturnsAccessGrantWhenLegitFacebookCredentials()
        {
            var grant = _facebookGrant;
            var user = _facebookUser;
            var credentials = _facebookCredentials;
            var verifyResult = _facebookResult;
            assertApiLoginReturnsAccessGrantWhenLegitCredentials(grant, user, credentials, verifyResult);
        }

        [TestMethod]
        public void ApiLoginReturnsAccessGrantWhenLegitTwitterCredentials()
        {
            var grant = _twitterGrant;
            var user = _twitterUser;
            var credentials = _twitterCredentials;
            var verifyResult = _twitterResult;
            assertApiLoginReturnsAccessGrantWhenLegitCredentials(grant, user, credentials, verifyResult);
        }

        private void assertApiLoginReturnsAccessGrantWhenLegitCredentials(AccessGrantDto grant, User user, CredentialsDto credentials, IProviderVerifyResult verifyResult)
        {
            var loginMock = new Mock<ILoginService>();
            loginMock.Setup(svc => svc.VerifyCredentials(credentials)).Returns(verifyResult);
            loginMock.Setup(svc => svc.GenerateAccessGrant(user, credentials)).Returns(grant);
            var userManager = Mock.Of<IUserManager>(man => man.Find(It.IsAny<UserLoginInfo>()) == user);
            var con = new AccountsController(userManager, loginMock.Object);
            var response = con.Login(credentials);
            var result = response as OkNegotiatedContentResult<AccessGrantDto>;
            var resultGrant = result.Content;
            Assert.IsTrue(resultGrant.EqualValues(grant));
        }

        [TestMethod]
        public void ApiRegisterCreatesAccountWhenValidInfoPosted()
        {
            var user = UserDto.FromUser(_facebookUser);
            var credentials = _facebookCredentials;
            var verifyResult = _facebookResult;
            UserLoginDto login = new UserLoginDto
            {
                Credentials = credentials,
                User = user
            };
            var userManagerMock = new Mock<IUserManager>();
            userManagerMock.Setup(man => man.Find(It.Is<UserLoginInfo>(info => info.LoginProvider == credentials.Provider && info.ProviderKey == verifyResult.Id))).Returns<User>(null);
            var loginMock = new Mock<ILoginService>();
            loginMock.Setup(svc => svc.VerifyCredentials(credentials)).Returns(verifyResult);
            loginMock.Setup(svc => svc.CreateAccount(It.IsAny<User>(), It.IsAny<UserLoginInfo>())).Returns(true);
            var con = new AccountsController(userManagerMock.Object, loginMock.Object);
            var response = con.Register(login);
            Assert.IsInstanceOfType(response, typeof(OkResult));
        }
    }

}
