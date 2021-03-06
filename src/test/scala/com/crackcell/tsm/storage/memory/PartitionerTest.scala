package com.crackcell.tsm.storage.memory

import com.crackcell.tsm.storage.memory._
import org.joda.time._
import org.scalatest._

class PartitionerTest extends FlatSpec {

  "Partition" should "work" in {
    val dt:Array[DateTime] = Array[DateTime](
      DateTime.parse("2010-06-30T01:00+02:00"),
      DateTime.parse("2010-06-30T02:00+02:00"),
      DateTime.parse("2010-06-30T03:00+02:00"),
      DateTime.parse("2010-06-30T04:00+02:00")
    )
    val tp = new HourPartitioner(dt(0), 1)
    println(tp.getPartitionID(dt(2)))
  }


}
