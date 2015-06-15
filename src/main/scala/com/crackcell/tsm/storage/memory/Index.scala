package com.crackcell.tsm.storage.memory

import org.joda.time.DateTime

import scala.collection.SortedMap

trait Index[I, S] {
  def iterator: Iterator[(I, S)]
}

class TimeIndex[S](val partitioner: TimePartitioner) extends Index[Int, S] {
  var start: DateTime = null
  var sortedData: SortedMap[Int, S] = null

  def iterator = sortedData.iterator
}

