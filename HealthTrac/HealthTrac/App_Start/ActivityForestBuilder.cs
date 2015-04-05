using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using HealthTrac.Models;
using Microsoft.FSharp.Core;
using Microsoft.FSharp.Collections;
using HealthTrac.Services;

namespace HealthTrac.App_Start
{
    public static class ActivityForestBuilder
    {
        public static IActivityForest BuildForest(IEnumerable<Activity> activities)
        {
            var data = activities.Select(a => new Tuple<Activity, Activity>(a, a));
            Converter<Activity, FSharpOption<ActivityType>> labelConverter = a => new FSharpOption<ActivityType>(a.Type);
            var label = new Tuple<String, Charon.Featurization.Feature<Activity>>("Type", Charon.Featurization.Categorical<Activity, ActivityType>((FSharpFunc<Activity, FSharpOption<ActivityType>>.FromConverter(labelConverter))));
            Converter<Activity, FSharpOption<double>> durConv = a => new FSharpOption<double>(a.Duration);
            var dur = new Tuple<String, Charon.Featurization.Feature<Activity>>("Duration", Charon.Featurization.Numerical<Activity, double>((FSharpFunc<Activity, FSharpOption<double>>.FromConverter(durConv))));
            Converter<Activity, FSharpOption<double>> disConv = a => new FSharpOption<double>(a.Distance);
            var dis = new Tuple<String, Charon.Featurization.Feature<Activity>>("Distance", Charon.Featurization.Numerical<Activity, double>((FSharpFunc<Activity, FSharpOption<double>>.FromConverter(disConv))));
            Converter<Activity, FSharpOption<long>> stepConv = a => new FSharpOption<long>(a.Steps);
            var step = new Tuple<String, Charon.Featurization.Feature<Activity>>("Steps", Charon.Featurization.Numerical<Activity, long>((FSharpFunc<Activity, FSharpOption<long>>.FromConverter(stepConv))));
            var features = FSharpList<Tuple<String, Charon.Featurization.Feature<Activity>>>.Cons(step,
                FSharpList<Tuple<String, Charon.Featurization.Feature<Activity>>>.Cons(dis,
                    FSharpList<Tuple<String, Charon.Featurization.Feature<Activity>>>.Cons(dur,
                        FSharpList<Tuple<String, Charon.Featurization.Feature<Activity>>>.Empty))); // equivalent to let features = [step; dis; dur] :(
            var forest = Charon.Learning.forest<Activity, Activity>(data, label, features, Charon.Learning.DefaultSettings);
            return new CharonActivityForestAdapter(forest);
        }
    }
}