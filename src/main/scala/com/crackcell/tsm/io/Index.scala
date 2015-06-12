package com.crackcell.tsm.io

import scala.collection.SortedMap
import scala.collection.mutable.HashMap
import breeze.linalg._
import breeze.stats._
import org.joda.time.DateTime
import com.crackcell.tsm.stats.Stats

trait Index extends Stats {
  def loadData(dt: Array[DateTime], data: DenseMatrix[Double])
}

class PartitionedIndex(val partitioner: TimePartitioner) extends Index {

  var start: DateTime = null
  var partIndex: SortedMap[Int, DenseVector[Double]] = null
  var compactCol: DenseVector[Double] = null

  def this(partitioner: TimePartitioner, dt: Array[DateTime], data: DenseMatrix[Double]) {
    this(partitioner)
    this.loadData(dt, data)
  }

  def loadData(dt: Array[DateTime], mat: DenseMatrix[Double]): Unit = {
    this.start = dt(0)
    if (dt.length != mat.rows) {
      throw new RuntimeException("mismatched dt and data")
    }
    val tmpIndex = new HashMap[Int, DenseVector[Double]]
    for (i <- 0 to dt.length - 1) {
      tmpIndex(partitioner.getIDByTime(this.start, dt(i))) = mat(i, ::).t
    }
    this.partIndex = SortedMap(tmpIndex.toSeq:_*)
    this.compactCol = mat(::, 0)
  }

  override def mean(): Double = {
    return breeze.stats.mean(this.compactCol)
  }

  override def variance(): Double = {
    return breeze.stats.variance(this.compactCol)
  }


}
