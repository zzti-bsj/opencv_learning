package inte.test.scala

import org.apache.spark.sql.SparkSession
import java.util.Properties
import java.util.Date

object SqlExample{
  def main(args: Array[String]){
    val properties = new Properties()
    val spark = SparkSession.builder.appName("scala sqlite_example App").getOrCreate()

    // spark_inited
    val spark_inited = new Date()
    println("PERF spark_inited: " + spark_inited.getTime)

    val sqlite_db = "jdbc:sqlite:/ppml/trusted-big-data-ml/work/data/sqlite_example/test_5000w.db"

    val alice = spark.read.jdbc(sqlite_db, "alice", "a1169", 0L, 1000L, 128, properties)
    val bob = spark.read.jdbc(sqlite_db, "bob", properties)
    // db_read
    val db_read = new Date()
    println("PERF db_read: " + db_read.getTime)

    alice.createOrReplaceTempView("alice")
    bob.createOrReplaceTempView("bob")

    // viewed
    val viewed = new Date()
    println("PERF viewed: " + viewed.getTime)

    val result1 = bob.filter(bob("c_id") === "ab").join(alice, alice("id") === bob("id")).filter("c1028 is not null").groupBy("c1028").count().sort("c1028")
    result1.show(2000, false)
    // resulted1
    val resulted1 = new Date()
    println("PERF resulted1: " + resulted1.getTime)

    val result2 = alice.filter("a1169 in (151, 152)").union(alice.filter("b1209 in (220, 330)")).union(alice.filter("c1034 in (422, 63)")).distinct().count()
    // resulted2
    val resulted2 = new Date()
    println("PERF resulted2: " + resulted2.getTime)

    spark.stop()
  }
}
