package com.ruoyi.common.websocket.service;

import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.websocket.constants.WebSocketConstant;
import com.ruoyi.common.websocket.domain.Message;
import com.ruoyi.common.websocket.properties.WebSocketProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * @author suahe
 * @description websocket服务
 * @date 2020/12/9 15:07
 */
@Service
public class WebSocketService {

    private static final Logger log = LoggerFactory.getLogger(WebSocketService.class);
    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private WebSocketProperties webSocketProperties;

    /**
     * 发送socket消息
     */
    public void sendMessage(Message message){
        String topic = StringUtils.isEmpty(message.getTopic()) ? webSocketProperties.getDefaultTopic() : message.getTopic();
        String dest = "";
        if(!message.isToAll()){
            if(StringUtils.isEmpty(message.getToUserId())){
                log.error("发送非广播消息时必须指定接收人的userid!");
                return;
            }
            else dest = WebSocketConstant.SLASH + topic + WebSocketConstant.SLASH + message.getToUserId();
        }
        else dest = WebSocketConstant.SLASH + topic;
        template.convertAndSend(dest, message);
    }
}
