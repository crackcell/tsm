package com.crackcell.tsm.dataset

import org.joda.time._

trait Interval {

  def gapBetween(dt1: DateTime, dt2: DateTime): Int
  def aheadOf(dt: DateTime, n: Int): DateTime

}

class HourInterval(val hours: Int) extends Interval {

  override def gapBetween(dt1: DateTime, dt2: DateTime): Int = {
    return Hours.hoursBetween(dt1, dt2).getHours / Hours.hours(hours).getHours
  }

  override def aheadOf(dt: DateTime, n: Int): DateTime = dt.plusHours(n)

}

class DayInterval(val days: Int) extends Interval {

  override def gapBetween(dt1: DateTime, dt2: DateTime): Int = {
    return Days.daysBetween(dt1, dt2).getDays / Days.days(days).getDays
  }

  override def aheadOf(dt: DateTime, n: Int): DateTime = dt.plusDays(n)

}
