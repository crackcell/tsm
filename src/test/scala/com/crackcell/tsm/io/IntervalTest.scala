package com.crackcell.tsm.io

import org.joda.time.DateTime
import org.scalatest._

class IntervalTest extends FlatSpec {

  "HourPartition" should "work" in {
    val dt1 = DateTime.parse("2010-06-30T01:00+02:00")
    val dt2 = DateTime.parse("2010-06-30T05:00+02:00")

  }

}
