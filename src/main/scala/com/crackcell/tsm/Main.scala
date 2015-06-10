package com.crackcell.tsm

import org.apache.log4j.Logger

object Main {

  val logger = Logger.getLogger(this.getClass)

  def printLogo(): Unit = {
    val logo =
      """
        | _______ _______ _______
        ||_     _|     __|   |   |
        |  |   | |__     |       |
        |  |___| |_______|__|_|__|
        |
      """.stripMargin
    logo.split("\n").foreach(logger.info(_))
  }

  def main(args: Array[String]) {
    printLogo()
    Config.loadArguments(args)
  }
}
