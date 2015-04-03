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
    public class MealsController : ApiController
    {

        private IUnitOfWork uow;
        private IMealService msvc;

        public MealsController(IUnitOfWork uow)
        {
            this.uow = uow;
            this.msvc = uow.MealService;
        }

        // GET: api/Meals
        public IEnumerable<MealDto> GetMeals()
        {
            return msvc.GetMeals().Select(b => MealDto.FromMeal(b));
        }

        // GET: api/Meals?userId=xyz
        public IEnumerable<MealDto> GetMeals(String userId)
        {
            return msvc.GetMeals(userId).Select(b => MealDto.FromMeal(b));
        }

        // GET: api/Meals/5
        [ResponseType(typeof(MealDto))]
        public IHttpActionResult GetMeal(long id)
        {
            var b = msvc.GetMeal(id);
            if (b == null)
            {
                return NotFound();
            }
            return Ok(MealDto.FromMeal(b));
        }

        // POST: api/Meals
        [ResponseType(typeof(MealDto))]
        public IHttpActionResult PostMeal(Meal m)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            msvc.CreateMeal(m);
            uow.Save();

            return CreatedAtRoute("DefaultApi", new { id = m.ID }, MealDto.FromMeal(m));
        }

        protected override void Dispose(bool disposing)
        {
            uow.Dispose();
            base.Dispose(disposing);
        }
    }
}