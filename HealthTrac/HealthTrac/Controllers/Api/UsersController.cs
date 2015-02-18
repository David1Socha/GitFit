using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using HealthTrac.DataAccess.Entity;
using HealthTrac.Models;
using HealthTrac.Models.Dto;
using HealthTrac.DataAccess;

namespace HealthTrac.Controllers.Api
{
    public class UsersController : ApiController
    {
        //TODO add login in this or another API controller
        private IUserAccessor accessor;

        public UsersController()
            : this(new EntityUserAccessor())
        {

        }

        public UsersController(IUserAccessor acc)
        {
            this.accessor = acc;
        }

        // GET: api/Users
        public IEnumerable<UserDto> GetUsers()
        {
            var users = accessor.GetUsers();
            return users.Select(u => UserDto.FromUser(u));
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
            try
            {
                accessor.UpdateUser(user);
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
    }
}