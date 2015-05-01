using HealthTrac.Models;
using MathNet.Numerics;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Services
{
    public class LinearModelBuilder
    {
        private double[] _coeffs;

        public LinearModelBuilder()
        {
            ;
        }

        public void BuildLinearModel(IEnumerable<User> users)
        {
            var inputs = users.Select(u => new[] { u.Weight }).ToArray();
            var outputs = users.Select(u => u.Height).ToArray();
            try
            {
                _coeffs = Fit.MultiDim(inputs, outputs, intercept: true);
            }
            catch (ArgumentException e)
            {
                //if no weights entered model won't build correctly
            }
        }

        public double[] GetCoeffs()
        {
            return _coeffs;
        }
    }
}