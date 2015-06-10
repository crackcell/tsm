package com.crackcell.tsm.dataset

import org.joda.time.DateTime

trait Partitioner {

  def slice(dt1: DateTime, dt2: DateTime): Partitioner
  def getIDByTime(dt: DateTime): Int
  def getTimeByID(id: Int): DateTime

}

class FixedPartitioner(val start: DateTime, val numOfInterval: Int, val interval: Interval)
  extends Partitioner {

  override def slice(dt1: DateTime, dt2: DateTime): Partitioner =
    new FixedPartitioner(start, interval.gapBetween(dt1, dt2) + 1, interval)

  override def getIDByTime(dt: DateTime): Int = interval.gapBetween(start, dt)
  override def getTimeByID(id: Int): DateTime = interval.aheadOf(start, id)

}