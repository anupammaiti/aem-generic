package com.aem.app;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.scripting.SlingScriptHelper;

import javax.servlet.http.HttpServletRequest;

/**
 * Base class for all backing beans.
 * access to commonly used Adobe CQ APIs.
 */
public abstract class AbstractComponent {

    private final SlingHttpServletRequest request;

    private final SlingScriptHelper slingHelper;

    private final PageManager pageManager;

    /**
     * Constructor for AbstractBackingBean.
     *
     * @param request     SlingHttpServletRequest
     * @param slingHelper SlingScriptHelper
     */
    protected AbstractComponent(SlingHttpServletRequest request, SlingScriptHelper slingHelper) {
        this.request = request;
        this.slingHelper = slingHelper;
        this.pageManager = request.getResourceResolver().adaptTo(PageManager.class);
    }

    /**
     * @return Current {@link HttpServletRequest}
     */
    protected SlingHttpServletRequest getRequest() {
        return request;
    }

    /**
     * @return Current {@link ResourceResolver}.
     */
    protected ResourceResolver getResourceResolver() {
        return request.getResourceResolver();
    }

    /**
     * @return currently accessed {@link Resource}
     */
    private Resource getResource() {
        return request.getResource();
    }

    /**
     * @return properties map for current JCR Node
     */
    protected ValueMap getProperties() {
        ValueMap properties = getResource().adaptTo(ValueMap.class);
        return (properties != null) ? properties : ValueMap.EMPTY;
    }

    /**
     * @return helper, providing programatic access to Sling & OSGi services
     * @see SlingScriptHelper#getService(Class)
     */
    protected SlingScriptHelper getSlingHelper() {
        return slingHelper;
    }

    /**
     * @return {@link PageManager} implementation
     */
    protected PageManager getPageManager() {
        return pageManager;
    }

    /**
     * @return current content {@link Page}
     */
    protected Page getCurrentPage() {
        Resource resource = getResource();
        return (pageManager != null) ? pageManager.getContainingPage(resource) : null;
    }
}
