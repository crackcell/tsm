package com.crackcell.tsm.dataset

import breeze.linalg.{DenseMatrix, DenseVector}
import org.joda.time.DateTime

import scala.collection.SortedMap
import scala.collection.mutable.HashMap

trait Index {

}

class PartitionedIndex(val partitioner: Partitioner, val dt: Array[DateTime], val data: DenseMatrix[Double])
  extends Index{

  val index: SortedMap[Int, DenseVector[Double]] = this.loadIndex(partitioner, dt, data)
  println(this.index)

  def loadIndex(partitioner: Partitioner, dt: Array[DateTime],
                data: DenseMatrix[Double]): SortedMap[Int, DenseVector[Double]] = {
    if (dt.length != data.rows) {
      throw new RuntimeException("mismatched dt and data")
    }
    val tmpIndex = new HashMap[Int, DenseVector[Double]]
    for (i <- 0 to dt.length - 1) {
      tmpIndex(partitioner.getIDByTime(dt(i))) = data(::, i)
    }
    return SortedMap(tmpIndex.toSeq:_*)
  }

}
