package com.crackcell.tsm.storage.memory

import org.joda.time._

trait Partitioner[Key] {
  def getPartitionID(k: Key): Int
}

abstract class TimePartitioner(start: DateTime, n: Int) extends Partitioner[DateTime]

class HourPartitioner(start: DateTime, n: Int) extends TimePartitioner(start, n) {
  override def getPartitionID(here: DateTime): Int =
    Hours.hoursBetween(start, here).getHours / Hours.hours(n).getHours
}

class DayPartitioner(start: DateTime, n: Int) extends TimePartitioner(start, n) {
  override def getPartitionID(here: DateTime): Int =
    Days.daysBetween(start, here).getDays / Days.days(n).getDays
}

object TimePartitioner extends Enumeration {
  val DAY = Value("DAY")
  val HOUR = Value("HOUR")

  def apply(typ: Value, n: Int, start: DateTime) = typ match {
    case DAY => new DayPartitioner(start, n)
    case HOUR => new HourPartitioner(start, n)
  }
}