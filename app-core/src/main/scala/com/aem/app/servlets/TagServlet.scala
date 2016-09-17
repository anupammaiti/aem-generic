package com.aem.app.servlets

import java.io.IOException
import javax.servlet.ServletException

import org.apache.felix.scr.annotations.sling.SlingServlet
import org.apache.sling.api.servlets.SlingSafeMethodsServlet
import org.apache.sling.api.{SlingHttpServletRequest, SlingHttpServletResponse}
import org.apache.sling.commons.json.JSONObject
import org.slf4j.LoggerFactory

@SlingServlet(resourceTypes = Array("generic-app/components/ctag"), methods = Array("GET"), selectors = Array("tags"), extensions = Array("json"))
class TagServlet extends SlingSafeMethodsServlet {
  val logger = LoggerFactory.getLogger(classOf[TagServlet])

  @throws[ServletException]
  @throws[IOException]
  override def doGet(request: SlingHttpServletRequest, response: SlingHttpServletResponse): Unit = {
    logger.info("TagServlet GET called!")
    //response.getWriter.write("{\"response from tagservlet1\", \"OK\"}")
    response.getWriter.write(new JSONObject().put("response from tagservlet2", "OK").toString())
  }
}