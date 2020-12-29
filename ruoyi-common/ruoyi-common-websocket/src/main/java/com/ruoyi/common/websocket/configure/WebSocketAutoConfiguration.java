package com.ruoyi.common.websocket.configure;

import com.ruoyi.common.websocket.constants.WebSocketConstant;
import com.ruoyi.common.websocket.properties.WebSocketProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author suahe
 * @description websocket自动配置
 * @date 2020/12/9 14:33
 */
@Configuration
@EnableWebSocketMessageBroker
@EnableConfigurationProperties({WebSocketProperties.class})
public class WebSocketAutoConfiguration implements WebSocketMessageBrokerConfigurer {

    public static final long HEARTBEAT = 20000;
    @Autowired
    private WebSocketProperties webSocketProperties;

    //添加服务端点,接受客户端连接
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        String endpoint = webSocketProperties.getEndpoint();
        //webSocket添加跨域和SocketJs支持
        //registry.addEndpoint(WebSocketConstant.SLASH + endpoint).setAllowedOrigins("*").withSockJS();
        registry.addEndpoint(WebSocketConstant.SLASH + endpoint).setAllowedOrigins("*");
    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //客户端接受服务端消息的地址的前缀信息(前台被动接受消息 也就是前台的前缀要和他对应上)
        registry.enableSimpleBroker(webSocketProperties.getTopicsWithSlash())
                .setHeartbeatValue(new long[]{HEARTBEAT,HEARTBEAT})
                .setTaskScheduler(new DefaultManagedTaskScheduler());
        //客户端发给服务端消息的地址前缀信息(前台发送消息 也就是前台的前缀要和他对应上)
        registry.setApplicationDestinationPrefixes(WebSocketConstant.SLASH + webSocketProperties.getApplicationDestinationPrefixes());
        //点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
        registry.setUserDestinationPrefix(WebSocketConstant.SLASH + webSocketProperties.getUserDestinationPrefix());
    }
}
