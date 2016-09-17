package com.aem.app.services.impl;

import com.aem.app.services.BundleService;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by awd on 17/9/16.
 */
@Service
@Component
public class BundleServiceImpl implements BundleService {
    public Map<String, String> getBundleInfo() {
        Bundle bundle = FrameworkUtil.getBundle(this.getClass());
        Map<String, String> bundleInfoMap = new HashMap<>();
        bundleInfoMap.put("SymbolicName", bundle.getSymbolicName());
        bundleInfoMap.put("Version", bundle.getVersion().toString());
        bundleInfoMap.put("LastModified", new Date(bundle.getLastModified()).toString());
        return bundleInfoMap;
    }
}
