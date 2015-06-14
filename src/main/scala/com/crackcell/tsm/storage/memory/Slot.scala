package com.crackcell.tsm.storage.memory

import breeze.linalg.DenseMatrix

class TimeSlot {
  var partitionID: Int = -1
  var features: DenseMatrix[Double] = _
}
