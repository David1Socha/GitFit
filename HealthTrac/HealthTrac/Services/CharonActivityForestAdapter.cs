using HealthTrac.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HealthTrac.Services
{
    public class CharonActivityForestAdapter : IActivityForest
    {

        private Charon.Learning.ForestResults<Object, Activity> _forest;

        public CharonActivityForestAdapter(Charon.Learning.ForestResults<Object, Activity> forest)
        {
            _forest = forest;
        }

        public Models.ActivityType PredictType(Models.Activity a)
        {
            return (ActivityType)Enum.Parse(typeof(ActivityType), _forest.Classifier.Invoke(a));
        }
    }
}