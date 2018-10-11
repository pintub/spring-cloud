package com.p2.microservices.limitsservice;

import com.p2.microservices.limitsservice.bean.LimitsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits-hardcoded")
    public LimitsConfiguration getLimitsHardCoded(){
        return new LimitsConfiguration(configuration.getMax(), configuration.getMin());
    }

    @GetMapping("/limits")
    public Configuration getLimitsFromConfigurations(){
        return configuration;
    }
}
