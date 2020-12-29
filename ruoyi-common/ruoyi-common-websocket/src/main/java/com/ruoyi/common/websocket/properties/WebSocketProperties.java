package com.ruoyi.common.websocket.properties;

import com.ruoyi.common.websocket.constants.WebSocketConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author suahe
 * @description websocket配置
 * @date 2020/12/9 14:36
 */
@ConfigurationProperties(prefix = "websocket")
public class WebSocketProperties {
    private String endpoint = "default";
    private String[] topics = {"default"};
    private String applicationDestinationPrefixes = "app";
    private String userDestinationPrefix = "user";

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String[] getTopics() {
        return topics;
    }

    public String[] getTopicsWithSlash(){
        String[] newTopics = new String[topics.length];
        for (int i = 0; i < topics.length; i++) {
            newTopics[i] = WebSocketConstant.SLASH + topics[i];
        }
        return newTopics;
    }

    public String getDefaultTopic(){
        return topics[0];
    }

    public void setTopics(String[] topics) {
        this.topics = topics;
    }

    public String getApplicationDestinationPrefixes() {
        return applicationDestinationPrefixes;
    }

    public void setApplicationDestinationPrefixes(String applicationDestinationPrefixes) {
        this.applicationDestinationPrefixes = applicationDestinationPrefixes;
    }

    public String getUserDestinationPrefix() {
        return userDestinationPrefix;
    }

    public void setUserDestinationPrefix(String userDestinationPrefix) {
        this.userDestinationPrefix = userDestinationPrefix;
    }
}
