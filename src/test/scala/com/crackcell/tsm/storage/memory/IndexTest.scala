package com.crackcell.tsm.storage.memory

import breeze.linalg.DenseMatrix
import org.joda.time._
import org.scalatest._

class IndexTest extends FlatSpec {

  "Time" should "work" in {
    val dt:Array[DateTime] = Array[DateTime](
      DateTime.parse("2010-06-30T01:00+02:00"),
      DateTime.parse("2010-06-30T02:00+02:00"),
      DateTime.parse("2010-06-30T03:00+02:00"),
      DateTime.parse("2010-06-30T04:00+02:00")
    )
    val mat = new DenseMatrix[Double](rows = 4, cols = 1, data = Array(0, 1, 2, 3))
  }
}
