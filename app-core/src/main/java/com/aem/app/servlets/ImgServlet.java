package com.aem.app.servlets;


import com.day.cq.commons.ImageHelper;
import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.day.cq.wcm.foundation.Image;
import com.day.image.Layer;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SlingServlet(paths = {"/bin/img"}, methods = {"GET"})
public class ImgServlet extends SlingSafeMethodsServlet {
    private Logger logger = LoggerFactory.getLogger(ImgServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        logger.debug("ImgServlet called!");
        String imgpath = request.getParameter("imgpath");
        String res = request.getParameter("res");
        logger.info("imgpath:" + imgpath);
        logger.info("res:" + res);


        Image image = new Image(request.getResourceResolver().getResource(imgpath));
        Asset asset = request.getResourceResolver().getResource(imgpath).adaptTo(Asset.class);

        Rendition original = asset.getOriginal();
        Layer layer = new Layer(original.getStream());


        int w = Integer.valueOf(res.split("x")[0]);
        int h = Integer.valueOf(res.split("x")[1]);

        logger.info("image==null:" + (image == null));

        try {
            //layer = image.getLayer(true, true, true);
            if (layer != null) {
                logger.info("layer is not null");
                layer.resize(w, h);

            } else {
                logger.info("layer is null");
            }
            String mimeType = image.getMimeType();
            if (ImageHelper.getExtensionFromType(mimeType) == null) {
                mimeType = "image/png";
            }

            logger.info("response.getOutputStream():" + response.getOutputStream());
            logger.info("mimeType:" + mimeType);
            logger.info("layer:" + layer.toString());

            response.setContentType(mimeType);
            layer.write(mimeType, 1.0, response.getOutputStream());
            response.flushBuffer();
        } catch (RepositoryException e) {
            response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        }
    }
}