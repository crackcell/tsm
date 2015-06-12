package com.crackcell.tsm.io.storage.memory

import com.crackcell.tsm.storage.memory.{HourSlot, TimePartition}
import org.joda.time._
import org.scalatest._

class PartitionTest extends FlatSpec {

  "Partition" should "work" in {
    val dt:Array[DateTime] = Array[DateTime](
      DateTime.parse("2010-06-30T01:00+02:00"),
      DateTime.parse("2010-06-30T02:00+02:00"),
      DateTime.parse("2010-06-30T03:00+02:00"),
      DateTime.parse("2010-06-30T04:00+02:00")
    )
    val tp = new TimePartition(new HourSlot(1), dt(0))
    println(tp.getPartitionID(dt(2)))
  }


}
