package com.crackcell.tsm

import breeze.linalg._
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

