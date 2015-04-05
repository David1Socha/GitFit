using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.UI;
using System.Web.Http.Description;
using HealthTrac.Models;
using HealthTrac.DataAccess;
using HealthTrac.Models.Dto;
using HealthTrac.Services;

namespace HealthTrac.Controllers.Api
{
    [Authorize]
    public class PointsController : ApiController
    {

        private IUnitOfWork uow;
        private IPointService psvc;

        public PointsController(IUnitOfWork uow)
        {
            this.uow = uow;
            this.psvc = uow.PointService;
        }

        // GET: api/Points
        public IEnumerable<PointDto> GetPoints()
        {
            return psvc.GetPoints().Select(b => PointDto.FromPoint(b));
        }

        // GET: api/Points?activityId=5
        public IEnumerable<PointDto> GetPoints(long activityId)
        {
            return psvc.GetPoints(activityId).Select(b => PointDto.FromPoint(b));
        }

        // GET: api/Points/5
        [ResponseType(typeof(PointDto))]
        public IHttpActionResult GetPoint(long id)
        {
            var b = psvc.GetPoint(id);
            if (b == null)
            {
                return NotFound();
            }
            return Ok(PointDto.FromPoint(b));
        }

        // POST: api/Points
        [ResponseType(typeof(PointDto))]
        public IHttpActionResult PostPoint(Point m)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            psvc.CreatePoint(m);
            uow.Save();
            return CreatedAtRoute("DefaultApi", new { id = m.ID }, PointDto.FromPoint(m));
        }

        protected override void Dispose(bool disposing)
        {
            uow.Dispose();
            base.Dispose(disposing);
        }
    }
}