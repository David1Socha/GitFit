using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using HealthTrac.Models;
using HealthTrac.Models.Dto;
using HealthTrac.DataAccess;
using HealthTrac.Services;

namespace HealthTrac.Controllers.Api
{
    [Authorize]
    public class UsersController : ApiController
    {
        private IUserService userService;
        private IUnitOfWork uow;

        public UsersController(IUnitOfWork uow)
        {
            this.uow = uow;
            this.userService = uow.UserService;
        }

        // GET: api/Users
        public IEnumerable<UserDto> GetUsers()
        {
            var users = userService.GetUsers();
            return users.Select(u => UserDto.FromUser(u));
        }

        [Route("api/Users/Search/{name}")]
        [HttpGet]
        public IEnumerable<User> SearchUsers(string name)
        {
            IEnumerable<User> users = userService.SearchUsers(name);
            return users;
        }


        [Route("api/Users/Available")]
        [ResponseType(typeof(bool))]
        [HttpGet]
        [AllowAnonymous]
        public IHttpActionResult IsAvailable(String userName)
        {
            User u = userService.GetAnyUserWithUserName(userName);
            bool available = u == null;
            return Ok(available);
        }

        // GET: api/Users/5
        [ResponseType(typeof(UserDto))]
        public IHttpActionResult GetUser(string id)
        {
            User u = userService.FindUser(id);
            if (u == null)
            {
                return NotFound();
            }
            else
            {
                return Ok(UserDto.FromUser(u));
            }
        }

        // PUT: api/Users/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutUser(string id, User user)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != user.Id)
            {
                return BadRequest();
            }
            userService.UpdateUser(user);
            try
            {
                uow.Save();
            }
            catch (ConcurrentUpdateException)
            {
                if (!UserExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }

        private bool UserExists(string id)
        {
            return userService.GetUsers().Count(e => e.Id == id) > 0;
        }

        protected override void Dispose(bool disposing)
        {
            uow.Dispose();
            base.Dispose(disposing);
        }
    }
}