package com.aem.app.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;

import com.aem.app.services.BundleService;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Created by awd on 17/9/16.
 */

@SlingServlet(paths = {"/bin/service/bundleinfo"}, methods = {"GET"})
public class BundleServlet extends SlingSafeMethodsServlet {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference
    private BundleService bundleService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        logger.info("BundleServlet GET called!");
        Map retValMap = new HashMap<String, String>();
        try {
            retValMap = bundleService.getBundleInfo();
        } catch (Exception e) {
            logger.error(e.getMessage());
            retValMap.put("Error", e.getMessage());
        }
        response.getWriter().write(new JSONObject(retValMap).toString());
    }
}