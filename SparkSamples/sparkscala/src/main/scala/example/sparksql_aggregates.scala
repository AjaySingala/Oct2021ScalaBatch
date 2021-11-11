// sparksql_aggregates.scala
// spark-submit sparkscala_2.11-0.1.0-SNAPSHOT.jar  --class example.SparkSqlAggregates
//package example

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._

// object SparkSqlAggregates {
//   def main(args: Array[String]): Unit = {
//     val spark: SparkSession = SparkSession
//       .builder()
//       .master("local[3]")
//       .appName("AjaySingala - SparkSqlAggregates")
//       .getOrCreate()
//     spark.sparkContext.setLogLevel("ERROR")
//     val sc = spark.sparkContext

    println("Defining the data...")
    val simpleData = Seq(
      ("James", "Sales", 3000),
      ("Michael", "Sales", 4600),
      ("Robert", "Sales", 4100),
      ("Maria", "Finance", 3000),
      ("James", "Sales", 3000),
      ("Scott", "Finance", 3300),
      ("Jen", "Finance", 3900),
      ("Jeff", "Marketing", 3000),
      ("Kumar", "Marketing", 2000),
      ("Saif", "Sales", 4100)
    )

    println("Creating the DF...")
    val df = simpleData.toDF("employee_name", "department", "salary")
    df.show()

    //approx_count_distinct(): returns the count of distinct items in a group.
    println("approx_count_distinct: "+
    df.select(approx_count_distinct("salary")).collect()(0)(0))

    //avg
    println("avg: " + df.select(avg("salary")).collect()(0)(0))

    //collect_list
    df.select(collect_list("salary")).show(false)

    //collect_set
    df.select(collect_set("salary")).show(false)

    //countDistinct
    val df2 = df.select(countDistinct("department", "salary"))
    df2.show(false)
    println("Distinct Count of Department & Salary: "+df2.collect()(0)(0))

    println("count: "+
    df.select(count("salary")).collect()(0))

    //first
    // first("col_name", ignoreNulls)
    // if ignoreNulls is set to true, it will return the first non-null element. false by default.
    df.select(first("salary")).show(false)

    //last
    // last("col_name", ignoreNulls)
    // if ignoreNulls is set to true, it will return the last non-null element. false by default.
    df.select(last("salary")).show(false)

    // max.
    df.select(max("salary")).show(false)

    // min.
    df.select(min("salary")).show(false)

    // mean.
    df.select(mean("salary")).show(false)

    // sum.
    df.select(sum("salary")).show(false)

    // sumDistinct.
    df.select(sumDistinct("salary")).show(false)

//   }
// }
