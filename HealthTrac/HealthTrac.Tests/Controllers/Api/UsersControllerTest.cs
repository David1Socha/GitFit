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
    public class UsersControllerTest
    {
        private User _user1, _user2;
        private User[] _users;

        [TestInitialize]
        public void Initialize()
        {
            _user1 = UserGenerator.GenerateUser1();
            _user2 = UserGenerator.GenerateUser2();
            _users = UserGenerator.GenerateUsers();
        }

        [TestMethod]
        public void UsersControllerGetUsers()
        {
            var users = _users;
            var accessor = Mock.Of<IUserAccessor>(acc => acc.GetUsers() == _users);
            var con = new UsersController(accessor);
            var resultUsers = con.GetUsers();
            Assert.IsTrue(resultUsers.EqualValues(users));
        }

    }

}
