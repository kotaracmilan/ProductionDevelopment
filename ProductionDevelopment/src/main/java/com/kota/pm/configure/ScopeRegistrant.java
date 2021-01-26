package com.kota.pm.configure;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScopeRegistrant extends CustomScopeConfigurer {
    public ScopeRegistrant() {
        
        Map<String, Object> map = new HashMap<>();
        map.put("view", new ViewScope());
        super.setScopes(map);
    }
}
