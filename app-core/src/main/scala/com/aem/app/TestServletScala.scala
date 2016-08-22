package com.aem.app

import java.io.IOException
import javax.servlet.ServletException

import org.apache.felix.scr.annotations.sling.SlingServlet
import org.apache.sling.api.servlets.SlingSafeMethodsServlet
import org.apache.sling.api.{SlingHttpServletRequest, SlingHttpServletResponse}
import org.slf4j.{Logger, LoggerFactory}

@SlingServlet(paths = Array("/bin/testscala"), methods = Array("GET"))
class TestServletScala extends SlingSafeMethodsServlet {
  val logger = LoggerFactory.getLogger(classOf[TestServletScala])

  @throws[ServletException]
  @throws[IOException]
  override def doGet(request: SlingHttpServletRequest, response: SlingHttpServletResponse) : Unit = {
    logger.info("TestServletScala GET called!")
    response.getWriter.write("Hello from TestServletScala")
  }
}