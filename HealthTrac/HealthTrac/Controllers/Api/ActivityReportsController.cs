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
using HealthTrac.Services;

namespace HealthTrac.Controllers.Api
{
    [Authorize]
    public class ActivityReportsController : ApiController
    {

        private IUnitOfWork uow;
        private IActivityReportService arsvc;

        public ActivityReportsController(IUnitOfWork uow)
        {
            this.uow = uow;
            this.arsvc = uow.ActivityReportService;
        }

        // GET: api/ActivityReports
        public IEnumerable<ActivityReportDto> GetActivityReports()
        {
            return arsvc.GetActivityReports().Select(b => ActivityReportDto.FromActivityReport(b));
        }

        // GET: api/ActivityReports?userId=xyz
        public IEnumerable<ActivityReportDto> GetActivityReports(String userId)
        {
            return arsvc.GetActivityReports(userId).Select(b => ActivityReportDto.FromActivityReport(b));
        }

        // GET: api/ActivityReports/5
        [ResponseType(typeof(ActivityReportDto))]
        public IHttpActionResult GetActivityReport(long id)
        {
            var b = arsvc.GetActivityReport(id);
            if (b == null)
            {
                return NotFound();
            }
            return Ok(ActivityReportDto.FromActivityReport(b));
        }

        protected override void Dispose(bool disposing)
        {
            uow.Dispose();
            base.Dispose(disposing);
        }
    }
}