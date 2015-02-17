using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using HealthTrac.DataAccess.Entity;
using HealthTrac.Models;
using HealthTrac.Models.Dto;
using HealthTrac.DataAccess;

namespace HealthTrac.Controllers
{
    public class UsersController : ApiController
    {
        private IUserAccessor accessor = new EntityUserAccessor();

        // GET: api/Users
        public IEnumerable<UserDto> GetIdentityUsers()
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
            catch (DbUpdateConcurrencyException) //TODO generalize
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


        // DELETE: api/Users/5
        [ResponseType(typeof(UserDto))]
        public IHttpActionResult DeleteUser(string id)
        {
            User user = accessor.FindUser(id);
            if (user == null)
            {
                return NotFound();
            }

            accessor.DeleteUser(user);

            return Ok(UserDto.FromUser(user));
        }

        private bool UserExists(string id)
        {
            return accessor.GetUsers().Count(e => e.Id == id) > 0;
        }
    }
}