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
using Microsoft.AspNet.Identity;

namespace HealthTrac.Controllers.Api
{
    [Authorize]
    public class EnergyLevelsController : ApiController
    {

        private IUnitOfWork uow;
        private IEnergyLevelService esvc;

        public EnergyLevelsController(IUnitOfWork uow)
        {
            this.uow = uow;
            this.esvc = uow.EnergyLevelService;
        }

        // GET: api/EnergyLevels
        public IEnumerable<EnergyLevelDto> GetEnergyLevels()
        {
            return esvc.GetEnergyLevels().Select(b => EnergyLevelDto.FromEnergyLevel(b));
        }

        // GET: api/EnergyLevels?userId=xjlh2b
        public IEnumerable<EnergyLevelDto> GetEnergyLevels(String userId)
        {
            return esvc.GetEnergyLevels(userId).Select(b => EnergyLevelDto.FromEnergyLevel(b));
        }

        // GET: api/EnergyLevels/5
        [ResponseType(typeof(EnergyLevelDto))]
        public IHttpActionResult GetEnergyLevel(long id)
        {
            var b = esvc.GetEnergyLevel(id);
            if (b == null)
            {
                return NotFound();
            }
            return Ok(EnergyLevelDto.FromEnergyLevel(b));
        }

        // POST: api/Goals
        [ResponseType(typeof(EnergyLevelDto))]
        public IHttpActionResult PostEnergyLevel(EnergyLevel el)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            esvc.CreateEnergyLevel(el);
            uow.Save();

            return CreatedAtRoute("DefaultApi", new { id = el.ID }, EnergyLevelDto.FromEnergyLevel(el));
        }

        protected override void Dispose(bool disposing)
        {
            uow.Dispose();
            base.Dispose(disposing);
        }
    }
}