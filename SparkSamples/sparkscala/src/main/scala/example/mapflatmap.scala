package example

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{IntegerType, StringType, StructType, ArrayType}

object MapFlatMap extends App {

  val spark: SparkSession = SparkSession
    .builder()
    .master("local[1]")
    .appName("AjaySingala.com")
    .getOrCreate()
  
  spark.sparkContext.setLogLevel("ERROR")

  println("Defining the data...")
  val data = Seq(
    "Project Gutenberg’s",
    "Alice’s Adventures in Wonderland",
    "Project Gutenberg’s",
    "Adventures in Wonderland",
    "Project Gutenberg’s"
  )

  import spark.sqlContext.implicits._
  println("Creating the DF...")
  val df = data.toDF("data")
  df.show()

  //Map Transformation
  println("Map transformation...")
  val mapDF = df.map(fun => {
    fun.getString(0).split(" ")
  })
  mapDF.show()

  //Flat Map Transformation
  println("Flatmap transformation...")
  val flatMapDF = df.flatMap(fun => {
    fun.getString(0).split(" ")
  })
  flatMapDF.show()

  // Example #2:
  println("Defing new data...")
  val arrayStructureData = Seq(
    Row("James,,Smith", List("Java", "Scala", "C++"), "CA"),
    Row("Michael,Rose,", List("Spark", "Java", "C++"), "NJ"),
    Row("Robert,,Williams", List("CSharp", "VB", "R"), "NV")
  )

  println("Defining new schema...")
  val arrayStructureSchema = new StructType()
    .add("name", StringType)
    .add("languagesAtSchool", ArrayType(StringType))
    .add("currentState", StringType)

  println("Creating new DF...")
  val dfa = spark.createDataFrame(
    spark.sparkContext.parallelize(arrayStructureData),
    arrayStructureSchema
  )
  import spark.implicits._

  //flatMap() Usage.
  // Gives a compile error, but runs properly.
  println("Flatmap transformation...")
  val df2 = dfa.flatMap(f => {
    val lang = f.getSeq[String](1)
    lang.map((f.getString(0), _, f.getString(2)))
  })
  df2.show(false)
  
  println("New DF with new Schema...")
  val df3 = df2.toDF("Name", "language", "State")
  df3.show(false)
}
