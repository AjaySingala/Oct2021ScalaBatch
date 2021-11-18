// networkwordcount.scala

import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext._ // not necessary since Spark 1.3

/*
 * To run this on your local machine, you need to first run a Netcat server
 *    `$ nc -lk 9999`
 * and then run the example
 *    ON HDP: spark-shell -i networkwordcount.scala
 * OR (the master requires 2 cores)
 *    spark-shell --packages org.apache.spark:spark-sql-kafka-0-10_2.12:3.1.2 --master local[2]
*/

// object NetworkWordCount {
//   def main(args: Array[String]) {
//     if (args.length < 2) {
//       System.err.println("Usage: NetworkWordCount <hostname> <port>")
//       System.exit(1)
//     }

    // Create a local StreamingContext with two working thread and batch interval of 1 second.
    // The master requires 2 cores to prevent from a starvation scenario.

    val conf =
      new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")

    //val ssc = new StreamingContext(conf, Seconds(1))
    val ssc = new StreamingContext(sc, Seconds(10))

    // Create a DStream that will connect to hostname:port, like localhost:9999
    val lines = ssc.socketTextStream("localhost", 9999)

    // Split each line into words
    val words = lines.flatMap(_.split(" "))

    // Count each word in each batch
    val pairs = words.map(word => (word, 1))
    val wordCounts = pairs.reduceByKey(_ + _)

    // Print the first ten elements of each RDD generated in this DStream to the console
    wordCounts.print()

    ssc.start() // Start the computation
    ssc.awaitTermination() // Wait for the computation to terminate
    ssc.stop() // Stop the computation.
//   }
// }
