package com.crackcell.tsm.storage.memory

import org.joda.time._

trait Slot[Key] {
  def getOffset(k1: Key, k2: Key): Int
}

class HourSlot(val hours: Int) extends Slot[DateTime] {
  override def getOffset(dt1: DateTime, dt2: DateTime): Int =
    return Hours.hoursBetween(dt1, dt2).getHours / Hours.hours(hours).getHours
}

class DaySlot(val days: Int) extends Slot[DateTime] {
  override def getOffset(dt1: DateTime, dt2: DateTime): Int =
    return Days.daysBetween(dt1, dt2).getDays / Days.days(days).getDays
}

