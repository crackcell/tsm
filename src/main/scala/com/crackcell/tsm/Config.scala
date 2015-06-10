package com.crackcell.tsm

import org.apache.commons.cli._
import org.apache.log4j.Logger

object Config {
  val logger = Logger.getLogger(this.getClass)

  var verbose: Boolean = false
  var paramP: Double = 0
  var paramD: Double = 0
  var paramQ: Double = 0
  var input: String = null
  var output: String = null
  var mode: String = null

  val opt: Options = new Options
  opt.addOption("v", "verbose", false, "print mode info")
  opt.addOption("h", "help", false, "show help info")
  opt.addOption("p", "param_p", true, "p of ARIMA(p, d, q)")
  opt.addOption("d", "param_d", true, "d of ARIMA(p, d, q)")
  opt.addOption("q", "param_q", true, "q of ARIMA(p, d, q)")
  opt.addOption("i", "input", true, "input file")
  opt.addOption("o", "output", true, "output file")

  def printHelpAndExit(msg: String, retcode: Int): Unit = {
    if (msg != null) {
      println("error: " + msg)
    }
    val usage =
      """
        |usage: arima [ options ]
        |
        |  -h,--help      show help info
        |  -v,--verbose   print more info
        |
        |  -i,--input     input file
        |  -o,--output    output file
        |
        |  -p,--param_p   p of ARIMA(p,d,q)
        |  -d,--param_d   d of ARIMA(p,d,q)
        |  -q,--param_q   q of ARIMA(p,d,q)
      """.stripMargin
    usage.split("\n").foreach(System.out.println(_))

    System.exit(retcode)
  }

  def loadArguments(args: Array[String]): Unit = {
    val parser: CommandLineParser = new PosixParser
    var cl: CommandLine = null
    try {
      cl = parser.parse(opt, args)
    } catch {
      case _: Throwable => printHelpAndExit("parse arguments failed", 1)
    }

    if (cl.hasOption("h")) {
      printHelpAndExit(null, 0)
    }

    if (cl.hasOption("v")) {
      this.verbose = true
    }

    if (!cl.hasOption("p")) {
      printHelpAndExit("no -p", 1)
    }
    this.paramP = cl.getOptionValue("p").toDouble
    logger.info("p: " + this.paramP)

    if (!cl.hasOption("d")) {
      printHelpAndExit("no -d", 1)
    }
    this.paramD = cl.getOptionValue("d").toDouble
    logger.info("d: " + this.paramD)

    if (!cl.hasOption("q")) {
      printHelpAndExit("no -q", 1)
    }
    this.paramQ = cl.getOptionValue("q").toDouble
    logger.info("q: " + this.paramQ)

    if (!cl.hasOption("i")) {
    }
    this.input = cl.getOptionValue("i")
    logger.info("input: " + this.input)

    if (!cl.hasOption("o")) {
      printHelpAndExit("no -o", 1)
    }
    this.output = cl.getOptionValue("o")
    logger.info("output: " + this.output)

  }

}