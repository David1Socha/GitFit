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
using HealthTrac.Services;

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
            _user1 = UserGenerator.GenerateFacebookUser();
            _user2 = UserGenerator.GenerateTwitterUser();
            _users = UserGenerator.GenerateUsers();
        }

        [TestMethod]
        public void ApiGetUsers()
        {
            var users = _users;
            var serv = Mock.Of<IUserService>(acc => acc.GetUsers() == _users);
            var uow = Mock.Of<IUnitOfWork>(u => u.UserService == serv);
            var con = new UsersController(uow);
            var resultUsers = con.GetUsers();
            Assert.IsTrue(resultUsers.EqualValues(users));
        }

        [TestMethod]
        public void ApiGetUser()
        {
            string id = "qwerty";
            var user = _user1;
            var sv = Mock.Of<IUserService>(a => a.FindUser(id) == user);
            var uow = Mock.Of<IUnitOfWork>(u => u.UserService == sv);
            var con = new UsersController(uow);
            var response = con.GetUser(id);
            var result = response as OkNegotiatedContentResult<UserDto>;
            var resultUser = result.Content;
            Assert.IsTrue(resultUser.EqualValues(user));
        }

        [TestMethod]
        public void ApiPutUser()
        {
            string id = "poiuy";
            var user = _user2;
            var mock = new Mock<IUserService>();
            mock.Setup(a => a.UpdateUser(user))
                .Returns(user);
            var sv = mock.Object;
            var uowMock = new Mock<IUnitOfWork>();
            uowMock.Setup(u => u.UserService)
                .Returns(sv);
            var con = new UsersController(uowMock.Object);
            con.PutUser(id, user);
            mock.Verify(a => a.UpdateUser(user));
            uowMock.Verify(u => u.Save());
        }

        [TestMethod]
        public void ApiUserIsAvailableWhenAvailable()
        {
            var userName = "Sarah";
            var mock = new Mock<IUserService>();
            mock.Setup(a => a.GetAnyUserWithUserName(userName))
                .Returns<User>(null);
            var sv = mock.Object;
            var uow = Mock.Of<IUnitOfWork>(u => u.UserService == sv);
            var con = new UsersController(uow);
            var response = con.IsAvailable(userName);
            var result = response as OkNegotiatedContentResult<bool>;
            bool available = result.Content;
            Assert.IsTrue(available);
        }

        [TestMethod]
        public void ApiUserIsAvailableWhenUnavailable()
        {
            var user = _user1;
            var userName = "Susan";
            var mock = new Mock<IUserService>();
            mock.Setup(a => a.GetAnyUserWithUserName(userName))
                .Returns(user);
            var sv = mock.Object;
            var uow = Mock.Of<IUnitOfWork>(u => u.UserService == sv);
            var con = new UsersController(uow);
            var response = con.IsAvailable(userName);
            var result = response as OkNegotiatedContentResult<bool>;
            bool available = result.Content;
            Assert.IsFalse(available);
        }

    }

}
