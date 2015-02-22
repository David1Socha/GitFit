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

        [TestMethod]
        public void UsersControllerGetUser()
        {
            string id = "qwerty";
            var user = _user1;
            var acc = Mock.Of<IUserAccessor>(a => a.FindUser(id) == user);
            var con = new UsersController(acc);
            var response = con.GetUser(id);
            var result = response as OkNegotiatedContentResult<UserDto>;
            var resultUser = result.Content;
            Assert.IsTrue(resultUser.EqualValues(user));
        }

        [TestMethod]
        public void UsersControllerPutUser()
        {
            string id = "poiuy";
            var user = _user2;
            var mock = new Mock<IUserAccessor>();
            mock.Setup(a => a.UpdateUser(user))
                .Returns(user);
            var acc = mock.Object;
            var con = new UsersController(acc);
            con.PutUser(id, user);
            mock.Verify(a => a.UpdateUser(user));
        }

        [TestMethod]
        public void UsersControllerIsAvailableWhenAvailable()
        {
            var user = _user1;
            var userName = user.UserName;
            var mock = new Mock<IUserAccessor>();
            mock.Setup(a => a.GetAnyUserWithUserName(userName))
                .Returns<User>(null);
            var acc = mock.Object;
            var con = new UsersController(acc);
            var response = con.IsAvailable(user);
            var result = response as OkNegotiatedContentResult<bool>;
            bool available = result.Content;
            Assert.IsTrue(available);
        }

    }

}
