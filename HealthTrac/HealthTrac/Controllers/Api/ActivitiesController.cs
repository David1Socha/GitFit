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
using HealthTrac.Models;
using HealthTrac.DataAccess;
using HealthTrac.Models.Dto;

namespace HealthTrac.Controllers.Api
{
    [Authorize]
    public class ActivitiesController : ApiController
    {

        private IUnitOfWork uow;
        private IActivityAccessor actAcc;

        public ActivitiesController(IUnitOfWork uow)
        {
            this.uow = uow;
            this.actAcc = uow.ActivityAccessor;
        }
        // GET: api/Activities?userId=xyz
        public IEnumerable<ActivityDto> GetActivities(String userId)
        {
            return actAcc.GetActivities(userId).Select(a => ActivityDto.FromActivity(a));
        }

        // GET: api/Activities/5
        [ResponseType(typeof(ActivityDto))]
        public IHttpActionResult GetActivity(long id)
        {
            var a = actAcc.GetActivity(id);
            if (a == null)
            {
                return NotFound();
            }
            return Ok(ActivityDto.FromActivity(a));
        }

        // PUT: api/Activities/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutActivity(long id, Activity activity)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != activity.ID)
            {
                return BadRequest();
            }
            actAcc.UpdateActivity(activity);
            try
            {
                uow.Save();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ActivityExists(id))
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

        // POST: api/Activities
        [ResponseType(typeof(ActivityDto))]
        public IHttpActionResult PostActivity(Activity activity)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            actAcc.CreateActivity(activity);
            uow.Save();

            return CreatedAtRoute("DefaultApi", new { id = activity.ID }, ActivityDto.FromActivity(activity));
        }

        // DELETE: api/Activities/5
        [ResponseType(typeof(void))]
        public IHttpActionResult DeleteActivity(long id)
        {
            actAcc.DeleteActivity(id);
            uow.Save();
            return Ok();
        }

        private bool ActivityExists(long id)
        {
            return actAcc.GetActivity(id) != null;
        }

        protected override void Dispose(bool disposing)
        {
            uow.Dispose();
            base.Dispose(disposing);
        }
    }
}