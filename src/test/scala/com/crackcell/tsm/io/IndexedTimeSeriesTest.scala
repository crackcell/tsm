package com.crackcell.tsm.io

import breeze.linalg._
import org.joda.time._
import org.scalatest._
import com.crackcell.tsm._

class IndexedTimeSeriesTest extends FlatSpec {

  "IndexedTimeSeries" should "work" in {
    val mat = new DenseMatrix[Double](rows = 4, cols = 1, data = Array(1, 2, 3, 4))
    val dt:Array[DateTime] = Array[DateTime](
      DateTime.parse("2010-06-30T01:00+02:00"),
      DateTime.parse("2010-06-30T02:00+02:00"),
      DateTime.parse("2010-06-30T03:00+02:00"),
      DateTime.parse("2010-06-30T04:00+02:00")
    )

    val ts = TimeSeries.createIndexedTimeSeries(IntervalType.HOUR, 1, dt, mat)
    ts.loadData(dt, mat)

    println(ts.mean())
    println(ts.variance())
  }

}
