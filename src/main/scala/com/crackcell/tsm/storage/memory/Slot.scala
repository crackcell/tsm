package com.crackcell.tsm.storage.memory

import breeze.linalg.DenseMatrix

class TimeSlot {
  var partitionID: Int = -1
  var features: DenseMatrix[Double] = _
}

object TimeSlot {
  def apply(pid: Int, fea: DenseMatrix[Double]): TimeSlot = {
    val ts = new TimeSlot
    ts.partitionID = pid
    ts.features = fea
    return ts
  }
}