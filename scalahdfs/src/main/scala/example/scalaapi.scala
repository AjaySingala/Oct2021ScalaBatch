// scalaapi.scala
package example

import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.client.ClientProtocolException
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient

object GetUrlContent extends App {
  // simpleApi()
  // var data = getRestContent(
  //   "https://reqres.in/api/users"
  // )
  // println(data)

  //data = getRestContent("https://gorest.co.in/public/v1/posts")
  println("Getting single tweet with id 1456337876564668418")
  var data2 = twitterApi("https://api.twitter.com/2/tweets/1456337876564668418")
  println(data2)

  println("\nGetting recent search...")
  data2 = twitterApi("https://api.twitter.com/2/tweets/search/recent?query=from:TwitterDev")
  println(data2)


  def simpleApi(): Unit = {
    val url = "http://api.hostip.info/get_json.php?ip=12.215.42.19"
    val result = scala.io.Source.fromURL(url).mkString
    println(result)
  }

  /** Returns the text content from a REST URL. Returns a blank String if there
    * is a problem. (Probably should use Option/Some/None; I didn't know about
    * it back then.)
    */
  def getRestContent(url: String): String = {
    val httpClient = new DefaultHttpClient()
    val httpResponse = httpClient.execute(new HttpGet(url))
    val entity = httpResponse.getEntity()
    var content = ""
    if (entity != null) {
      val inputStream = entity.getContent()
      content = scala.io.Source.fromInputStream(inputStream).getLines.mkString
      inputStream.close
    }
    httpClient.getConnectionManager().shutdown()
    return content
  }

  def twitterApi(url: String): String = {
    val httpClient = new DefaultHttpClient();
    //val  post = new HttpPost(url);
    val get = new HttpGet(url)
    get.setHeader("Content-Type", "application/json")
    get.setHeader("Authorization", "Bearer AAAAAAAAAAAAAAAAAAAAAI5yVgEAAAAAFbfoVy5EOml3AXFiozAYr6AWuKs%3Dnba4SwXfi4wb2mVwPw6FGSDbjuyWmxEtxrL3psCsbMspY5usJ1");
    val httpResponse = httpClient.execute(get)
    val entity = httpResponse.getEntity()
    var content = ""
    if (entity != null) {
      val inputStream = entity.getContent()
      content = scala.io.Source.fromInputStream(inputStream).getLines.mkString
      inputStream.close
    }
    httpClient.getConnectionManager().shutdown()
    return content

//     JSONObject json = new JSONObject();
// // json.put ...
// // Send it as request body in the post request

//     StringEntity params = new StringEntity(json.toString());
//     post.setEntity(params);

//     HttpResponse response = httpclient.execute(post);
//     httpclient.getConnectionManager().shutdown();
  }
}
