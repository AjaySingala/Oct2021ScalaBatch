// mongodb.scala
package example;

// import scala.collection.JavaConverters._
// import org.mongodb.scala.connection.ClusterSettings

import org.mongodb.scala._

import example.Helpers._

object MongoDemo {
  def main(args: Array[String]) {
    println("Starting MongoDB - Scala Demo...")

    /** ** differet ways to connec to MongoDB: // To directly connect to the
      * default server localhost on port 27017 val mongoClient: MongoClient =
      * MongoClient()
      *
      * // Use a Connection String val mongoClient: MongoClient =
      * MongoClient("mongodb://localhost")
      *
      * // or provide custom MongoClientSettings val settings:
      * MongoClientSettings = MongoClientSettings.builder()
      * .applyToClusterSettings(b => b.hosts(List(new
      * ServerAddress("localhost")).asJava)) .build() val mongoClient:
      * MongoClient = MongoClient(settings)
      *
      * val database: MongoDatabase = mongoClient.getDatabase("mydb")
      *
      * val collection: MongoCollection[Document] =
      * database.getCollection("test");
      */

    //
    val client: MongoClient = MongoClient()
    val database: MongoDatabase = client.getDatabase("test")
    // Get a Collection.
    val collection: MongoCollection[Document] = database.getCollection("test")

    // // insert a document
    // val document: Document = Document("_id" -> 1, "x" -> 1)
    // val insertObservable: Observable[Completed] = collection.insertOne(document)

    // insertObservable.subscribe(new Observer[Completed] {
    //   override def onNext(result: Completed): Unit = println(s"onNext: $result")
    //   override def onError(e: Throwable): Unit = println(s"onError: $e")
    //   override def onComplete(): Unit = println("onComplete")
    // })

    // Insert a document.
    // Sample:
    // {
    //   "name" : "MongoDB",
    //   "type" : "database",
    //   "count" : 1,
    //   "info" : {
    //               x : 203,
    //               y : 102
    //             }
    // }
    val doc: Document = Document(
      "_id" -> 0,
      "name" -> "MongoDB",
      "type" -> "database",
      "count" -> 1,
      "info" -> Document("x" -> 203, "y" -> 102)
    )
    //println(doc)
    println("Printing results...")
    collection.insertOne(doc).results()

    // In the API all methods returning a Observables are “cold” streams meaning
    // that nothing happens until they are Subscribed to.
    val observable: Observable[Completed] = collection.insertOne(doc)

    // Only when an Observable is subscribed to and data requested will the operation happen.
    // Explictly subscribe:
    observable.subscribe(new Observer[Completed] {
      override def onNext(result: Completed): Unit = println("Inserted")
      override def onError(e: Throwable): Unit = println("Failed")
      override def onComplete(): Unit = println("Completed")
    })

    // Once the document has been inserted the onNext method will be called
    // and it will print “Inserted!” followed by the onCompleted method which will print “Completed”.
    // If there was an error for any reason the onError method would print “Failed”.

    // Add multiple documents.
    // The following example will add multiple documents of the form: { "i" : value }
    val documents = (1 to 100) map { i: Int => Document("i" -> i) }
    val insertObservable = collection.insertMany(documents)
    // As we haven’t subscribed yet no documents have been inserted, lets chain together two operations, inserting and counting.
    // Count Documents in A Collection.
    // Lets chain the two operations together using a for comprehension.
    // The following code should insert the documents then count the number of documents and print the results.
    val insertAndCount = for {
      insertResult <- insertObservable
      countResult <- collection.count()
    } yield countResult

    // println("total using head()...")
    // println(s"(1) total # of documents after inserting 100 small ones (should be 101):  ${insertAndCount.head()}")
    println("total using headResult()...")
    println(
      s"(2) total # of documents after inserting 100 small ones (should be 101):  ${insertAndCount.headResult()}"
    )

    // Find the 1st doc in the collection.
    println("First doc in collection with head()...")
    collection.find().first().head()
    println("First doc in collection with printHeadResult()...")
    collection.find().first().printHeadResult()

    // Find All Documents in a Collection.
    collection.find().printResults()

    // Get A Single Document with a Query Filter.
    import org.mongodb.scala.model.Filters._

    println("Get A Single Document with a Query Filter...")
    collection.find(equal("i", 71)).first().printHeadResult()

    // Get a Set of Documents with a Query.
    println("Get a Set of Documents with a Query...")
    collection.find(gt("i", 50)).printResults()

    // We could also get a range, say 50 < i <= 100:
    println("Get a Set of Documents with a Range Query...")
    collection.find(and(gt("i", 50), lte("i", 100))).printResults()

    // Sorting documents.
    import org.mongodb.scala.model.Sorts._

    println("Sorting in DESC order...")
    collection.find(exists("i")).sort(descending("i")).printResults()

    // Projecting fields.
    import org.mongodb.scala.model.Projections._

    println("Projecting...All fields....")
    collection.find().first().printHeadResult()
    println("Projecting...Exclude Id....")
    collection.find().projection(excludeId()).first().printHeadResult()

    // Aggregations.
    import org.mongodb.scala.model.Aggregates._

    println("Aggregations...multiply i by 10")
    collection
      .aggregate(
        Seq(
          filter(gt("i", 0)),
          project(Document("""{ITimes10: {$multiply: ["$i", 10]}}"""))
        )
      )
      .printResults()

    // Updating documents.
    import org.mongodb.scala.model.Updates._

    println("Updating doc...BEFORE...")
    collection.find(equal("i", 1)).printResults()
    println("Updating doc...")
    collection
      .updateOne(equal("i", 10), set("i", 110))
      .printHeadResult("Update Result: ")
    println("Updating doc...AFTER...")
    collection.find(equal("i", 10)).printResults()
    collection.find(equal("i", 110)).printResults()

    // Update ALL docs matching a filter.
    println("Update many docs...BEFORE...")
    collection.find(lt("i", 100)).printResults()
    // Here we increment the value of i by 100 where i is less than 100.
    collection
      .updateMany(lt("i", 100), inc("i", 100))
      .printHeadResult("Update Result: ")
    println("Update many docs...AFTER...")
    collection.find().printResults()

    // Deleting docs.
    println("Deleting docs...BEFORE...")
    collection.find(and(gt("i", 100), lte("i", 115))).printResults()
    collection.deleteOne(equal("i", 110)).printHeadResult("Delete Result: ")
    println("Deleting docs...AFTER...")
    collection.find(and(gt("i", 100), lte("i", 115))).printResults()

    // Delete multiple docs.
    println("Deleting multiple docs BEFORE...")
    collection.find(gt("i", 95)).printResults()
    collection.deleteMany(gte("i", 101)).printHeadResult("Delete Result: ")
    println("Deleting multiple docs AFTER...")
    collection.find(gt("i", 95)).printResults()

    // Bulk operations.
    import org.mongodb.scala.model._

    val writes: List[WriteModel[_ <: Document]] = List(
      InsertOneModel(Document("_id" -> 4)),
      InsertOneModel(Document("_id" -> 5)),
      InsertOneModel(Document("_id" -> 6)),
      UpdateOneModel(Document("_id" -> 1), set("x", 2)),
      DeleteOneModel(Document("_id" -> 2)),
      ReplaceOneModel(Document("_id" -> 3), Document("_id" -> 3, "x" -> 4))
    )

    // // 1. Ordered bulk operation - order is guaranteed
    // collection.bulkWrite(writes).printHeadResult("Bulk write results: ")

    // 2. Unordered bulk operation - no guarantee of order of operation
    collection
      .bulkWrite(writes, BulkWriteOptions().ordered(false))
      .printHeadResult("Bulk write results (unordered): ")
    println("After bulk Write...")
    collection.find().printResults()

  }
}
