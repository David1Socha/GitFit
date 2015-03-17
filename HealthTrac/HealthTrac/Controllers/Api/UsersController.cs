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

namespace HealthTrac.Controllers.Api
{
    [Authorize]
    public class UsersController : ApiController
    {
        private IUserAccessor accessor;
        private IUnitOfWork uow;

        public UsersController(IUnitOfWork uow)
        {
            this.uow = uow;
            this.accessor = uow.UserAccessor;
        }

        // GET: api/Users
        public IEnumerable<UserDto> GetUsers()
        {
            var users = accessor.GetUsers();
            return users.Select(u => UserDto.FromUser(u));
        }

        [Route("api/Users/Available")]
        [ResponseType(typeof(bool))]
        [HttpGet]
        [AllowAnonymous]
        public IHttpActionResult IsAvailable(String userName)
        {
            User u = accessor.GetAnyUserWithUserName(userName);
            bool available = u == null;
            return Ok(available);
        }

        // GET: api/Users/5
        [ResponseType(typeof(UserDto))]
        public IHttpActionResult GetUser(string id)
        {
            User u = accessor.FindUser(id);
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
            accessor.UpdateUser(user);
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
            return accessor.GetUsers().Count(e => e.Id == id) > 0;
        }

        protected override void Dispose(bool disposing)
        {
            uow.Dispose();
            base.Dispose(disposing);
        }
    }
}