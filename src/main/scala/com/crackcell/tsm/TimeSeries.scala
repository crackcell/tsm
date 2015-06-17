package com.crackcell.tsm

import scala.math._
import org.joda.time.DateTime

import com.crackcell.tsm.storage.memory._
import com.crackcell.tsm.statistics._

import scala.collection.mutable.HashMap
import scala.collection.SortedMap

object TimeSeries extends Enumeration {
  val DayIntervalTimeSeries = Value("DayIntervalTimeSeries")
  val HourIntervalTimeSeries = Value("HourIntervalTimeSeries")
}

class TimeSeries(val index: Index[Int, Double]) extends Stats {

  private var m: Double = _
  private var vp: Double = _
  private var vs: Double = _

  calcStats()

  private def calcStats(): Unit = {
    var sum1: Double = 0
    var sum2: Double = 0
    index.iterator.foreach{case (_, v) => {
      sum1 += v
      sum2 += v * v
    }}

    m = sum1 / index.size
    vp = sum2 / index.size - m * m

    sum1 = 0
    index.iterator.foreach{case (_, v) => {
      val diff = v - m
      sum1 += diff * diff
    }}
    vs = sum1 / (index.size - 1)
  }

  /**
   * {@inheritdoc}
   */
  override def mean(): Double = m

  /**
   * {@inheritdoc}
   */
  override def varp(): Double = vp

  /**
   * {@inheritdoc}
   */
  override def vars(): Double = vs

}

class TimeSeriesBuilder {
  var intervalLen: Int = -1
  var typ = TimeSeries.DayIntervalTimeSeries
  var dt: Array[DateTime] = null
  var fea: Array[Double] = null

  def setType(typ: TimeSeries.Value, l: Int): TimeSeriesBuilder = {
    this.typ = typ
    this.intervalLen = l
    return this
  }

  def setData(dt: Array[DateTime], fea: Array[Double]): TimeSeriesBuilder = {
    this.dt = dt
    this.fea = fea
    return this
  }

  def build(): TimeSeries = {
    if (this.intervalLen == -1 || dt == null || fea == null ) {
      throw new RuntimeException("interval length or data is required")
    }
    val tp: TimePartitioner = this.typ match {
      case TimeSeries.HourIntervalTimeSeries => new HourPartitioner(this.dt(0), this.intervalLen)
      case TimeSeries.DayIntervalTimeSeries => new DayPartitioner(this.dt(0), this.intervalLen)
    }
    val index = loadData(new TimeIndex[Double](tp), dt, fea)
    return new TimeSeries(index)
  }

  protected def loadData(ts: TimeIndex[Double],
                         dt: Array[DateTime], fea: Array[Double]): TimeIndex[Double] = {
    ts.start = dt(0)
    if (dt.length != fea.length) {
      throw new RuntimeException("mismatched dt and data")
    }
    val tmpIndex = new HashMap[Int, Double]
    for (i <- 0 to dt.length - 1) {
      tmpIndex(ts.partitioner.getPartitionID(dt(i))) = fea(i)
    }
    ts.sortedData = SortedMap(tmpIndex.toSeq:_*)
    return ts
  }
}
