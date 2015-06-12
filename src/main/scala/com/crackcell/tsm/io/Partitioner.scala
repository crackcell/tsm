package com.crackcell.tsm.io

import org.joda.time.DateTime

trait TimePartitioner {

  def getIDByTime(start: DateTime, dt: DateTime): Int
  def getTimeByID(start: DateTime, id: Int): DateTime

}

class FixedIntervalPartitioner(val interval: Interval)
  extends TimePartitioner {

  override def getIDByTime(start: DateTime, dt: DateTime): Int = interval.gapBetween(start, dt)
  override def getTimeByID(start: DateTime, id: Int): DateTime = interval.aheadOf(start, id)

}