package com.crackcell.tsm.statistics

import scala.math._

trait Stats {
  /**
   * 均值
   */
  def mean(): Double

  /**
   * 总体方差
   */
  def varp(): Double

  /**
   * 样本方差
   */
  def vars(): Double

  /**
   * 标准差
   */
  def sd(): Double = sqrt(vars())
}
