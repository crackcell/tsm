package com.crackcell.tsm.io

import breeze.linalg._
import org.joda.time._
import org.scalatest._

class PartitionedIndexTest extends FlatSpec {

  "PartitionedIndex" should "work" in {
    val mat = new DenseMatrix[Double](rows = 3, cols = 1, data = Array(1, 2, 3))
    val dt:Array[DateTime] = Array[DateTime](
      DateTime.parse("2010-06-30T01:00+02:00"),
      DateTime.parse("2010-06-30T02:00+02:00"),
      DateTime.parse("2010-06-30T03:00+02:00")
    )
    val partitioner = new FixedIntervalPartitioner(new HourInterval(1))
    val index = new PartitionedIndex(partitioner, dt, mat)
    println(mat)
  }

}
