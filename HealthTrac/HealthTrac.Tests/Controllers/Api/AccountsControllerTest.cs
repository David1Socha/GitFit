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
using HealthTrac.Tests.Helpers;
using Microsoft.Owin.Security;
using Microsoft.AspNet.Identity;

namespace HealthTrac.Tests.Controllers.Api
{
    [TestClass]
    public class AccountsControllerTest
    {

        [TestInitialize]
        public void Initialize()
        {

        }

        [TestMethod]
        public void AccountsControllerLogout()
        {
            var authMock = new Mock<IAuthenticationManager>();
            authMock.Setup(a => a.SignOut(It.IsAny<String[]>()));
            var uManMock = new Mock<UserManager<User>>(Mock.Of<IUserStore<User>>());
            var controller = new AccountsController(uManMock.Object, authMock.Object);
            var response = controller.Logout();
            Assert.IsInstanceOfType(response, typeof(OkResult));
        }

    }

}