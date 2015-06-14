package com.crackcell.tsm.storage.memory

trait Index[S] {
  def iterator: Iterator[S]
}

class TimePartitionedIndex extends Index[TimeSlot] {
  def iterator = new Iterator[TimeSlot] {
    val self = this
    def next: TimeSlot = null
    def hasNext: Boolean = false
  }
}

