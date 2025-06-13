package com.sky.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sky.jwt")
@Data
public class JwtProperties {

    /**
     * Admin side JWT Properties
     */
    private String adminSecretKey;
    private long adminTtl;
    private String adminTokenName;

    /**
     * User side JWT Properties
     */
    private String userSecretKey;
    private long userTtl;
    private String userTokenName;

}
