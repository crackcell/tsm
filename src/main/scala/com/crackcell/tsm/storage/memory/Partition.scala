package com.crackcell.tsm.storage.memory

import scala.reflect.BeanProperty
import org.joda.time._

trait Partition[Key] {
  def getPartitionID(k: Key): Int
}

class TimePartition(val slot: Slot[DateTime]) extends Partition[DateTime] {

  @BeanProperty var start: DateTime = _

  def this(slot: Slot[DateTime], start: DateTime) {
    this(slot)
    this.start = start
  }

  override def getPartitionID(here: DateTime): Int = {
    return this.slot.getOffset(this.start, here)
  }

}
