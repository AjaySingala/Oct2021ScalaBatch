// unstructured.scala
package example

import org.apache.spark.sql.SparkSession

object UnstructuredExample {

  def main(args: Array[String]) {
    val spark = SparkSession
      .builder()
      .appName("AjaySingala")
      .getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    val sc = spark.sparkContext

    println("Read the file twinkle.txt...")
    val a = sc.textFile(
      "file:///home/maria_dev/SparkSamples/sparkscala/src/main/scala/example/twinkle.txt"
    )
    println("count: " + a.count())
    println("Fetch lines that have the word 'twinkle' using filter(_.contains())...")
    val a_twinkle = a.filter(_.contains("twinkle"))
    println(a_twinkle)
    println(a_twinkle.count)
    a_twinkle.collect.foreach(println)

    println("Read the file twinkle.txt again and show it's schema and data...")
    val b = spark.read.textFile(
      "file:///home/maria_dev/SparkSamples/sparkscala/src/main/scala/example/twinkle.txt"
    )
    b.printSchema
    b.show(false)

    println("Read the file twinkle.txt again...")
    val c = spark.read.text(
      "file:///home/maria_dev/SparkSamples/sparkscala/src/main/scala/example/twinkle.txt"
    )
    println("Fetch all lines that have the word 'twinkle' using filter(value like)...")
    val c_twinkle = c.filter("value like '%twinkle%'")
    c_twinkle.show(false)

  }
}
