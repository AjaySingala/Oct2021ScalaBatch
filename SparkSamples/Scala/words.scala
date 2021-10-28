// words.scala.
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object Words {
  def main(args: Array[String]) {

    // set up Spark Context
    val sparkSession = SparkSession.builder
      .appName("Word Application")
      .config("spark.master", "local[*]")
      .getOrCreate()
    val sparkContext = sparkSession.sparkContext
    sparkContext.setLogLevel("ERROR")

    println("Initialized sparSession...")

    // step 0: establish source data sets
    val stringsToAnalyse: List[String] = List(
      "Can you tell the difference between Scala & Spark?",
      "You will have to look really closely!"
    )
    val stringsToAnalyseRdd: RDD[String] =
      sparkContext.parallelize(stringsToAnalyse)

    // step 1: split sentences into words
    val wordsList: List[String] = stringsToAnalyse.flatMap(_.split(" "))
    val wordsListRdd: RDD[String] = stringsToAnalyseRdd.flatMap(_.split(" "))

    // step 2: convert words to lists of chars, create (key,value) pairs.
    val lettersList: List[(Char, Int)] = wordsList.flatMap(_.toList).map((_, 1))
    val lettersListRdd: RDD[(Char, Int)] =
      wordsListRdd.flatMap(_.toList).map((_, 1))

    // step 3: count letters
    val lettersCount: List[(Char, Int)] =
      lettersList.groupBy(_._1).mapValues(_.size).toList
    val lettersCountRdd: RDD[(Char, Int)] = lettersListRdd.reduceByKey(_ + _)

    // step 4: get Top 5 letters in our sentences.
    val lettersCountTop5: List[(Char, Int)] = lettersCount.sortBy(-_._2).take(5)
    val lettersCountTop5FromRdd: List[(Char, Int)] =
      lettersCountRdd.sortBy(_._2, ascending = false).take(5).toList

    // the results
    println(s"Top 5 letters by Scala native: ${lettersCountTop5}")
    println(s"Top 5 letters by Spark: ${lettersCountTop5FromRdd}")

    // we are done
    sparkSession.stop()
  }
}
