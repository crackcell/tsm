package com.crackcell.tsm

import breeze.linalg._
import com.crackcell.tsm.IntervalType
import org.joda.time.DateTime

import com.crackcell.tsm.stats._
import com.crackcell.tsm.io._

trait TimeSeries extends Stats {
  def loadData(dt: Array[DateTime], mat: DenseMatrix[Double])
}

class IndexedTimeSeries(val index: Index) extends TimeSeries {

  override def mean(): Double = {
    return this.index.mean()
  }

  override def variance(): Double = {
    return this.index.variance()
  }

  override def loadData(dt: Array[DateTime], mat: DenseMatrix[Double]): Unit = {
    this.index.loadData(dt, mat)
  }

}

object IntervalType extends Enumeration {
  val DAY = Value("DAY")
  val HOUR = Value("HOUR")
}

object TimeSeries {
  
  def createIndexedTimeSeries(typ: IntervalType.Value, intervalLenth: Int,
                              dt: Array[DateTime], mat: DenseMatrix[Double]): TimeSeries = {
    var partitioner = typ match {
      case IntervalType.DAY => new FixedIntervalPartitioner(new HourInterval(intervalLenth))
      case IntervalType.HOUR => new FixedIntervalPartitioner(new DayInterval(intervalLenth))
    }

    return new IndexedTimeSeries(new PartitionedIndex(partitioner, dt, mat))
  }

}
