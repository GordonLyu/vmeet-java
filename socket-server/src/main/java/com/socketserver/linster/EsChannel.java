package com.socketserver.linster;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author 像风如你
 * @since 2023/6/19
 */

//测试kafka
public interface EsChannel {
    /**
     * 缺省发送消息通道名称
     */
    String WS_DEFAULT_OUTPUT = "ws_default_output";

    /**
     * 缺省接收消息通道名称
     */
    String WS_DEFAULT_INPUT = "ws_default_input";

    /**
     * 告警发送消息通道名称
     */
    String WS_ALARM_OUTPUT = "ws_alarm_output";

    /**
     * 告警接收消息通道名称
     */
    String WS_ALARM_INPUT = "ws_alarm_input";

    /**
     * 缺省发送消息通道
     * @return channel 返回缺省信息发送通道
     */
    @Output(WS_DEFAULT_OUTPUT)
    MessageChannel sendWsDefaultMessage();

    /**
     * 告警发送消息通道
     * @return channel 返回告警信息发送通道
     */
    @Output(WS_ALARM_OUTPUT)
    MessageChannel sendWsAlarmMessage();

    /**
     * 缺省接收消息通道
     * @return channel 返回缺省信息接收通道
     */
    @Input(WS_DEFAULT_INPUT)
    MessageChannel recieveWsDefaultMessage();

    /**
     * 告警接收消息通道
     * @return channel 返回告警信息接收通道
     */
    @Input(WS_ALARM_INPUT)
    MessageChannel recieveWsAlarmMessage();
}
