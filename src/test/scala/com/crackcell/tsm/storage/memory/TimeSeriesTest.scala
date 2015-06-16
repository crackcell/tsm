package com.crackcell.tsm.storage.memory

import org.joda.time._
import org.scalatest._

import com.crackcell.tsm._

class TimeSeriesTest extends FlatSpec {

  "Time" should "work" in {
    val dt:Array[DateTime] = Array[DateTime](
      DateTime.parse("2010-06-30T01:00+02:00"),
      DateTime.parse("2010-06-30T02:00+02:00"),
      DateTime.parse("2010-06-30T03:00+02:00"),
      DateTime.parse("2010-06-30T04:00+02:00")
    )
    val fea:Array[Double] = Array[Double](0, 1, 2, 3)
    val ts = (new TimeSeriesBuilder)
      .setType(TimeSeries.HourIntervalTimeSeries, 1)
      .setData(dt, fea)
      .build()
    printf("mean = %f\n", ts.mean())
    printf("var.s = %f\n", ts.vars())
    printf("var.p = %f\n", ts.varp())
  }
}
