package com.qiyei.server.mina;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author Created by qiyei2015 on 2018/1/6.
 * @version: 1.0
 * @email: 1273482124@qq.com
 * @description:
 */
public class MinaServer {

    public static void main(String[] args){
        new MinaServer().start();
    }

    public void start(){
        try {
            //1 创建NioSocketAcceptor
            NioSocketAcceptor acceptor = new NioSocketAcceptor();
            //2 设置Handler
            acceptor.setHandler(new MinaServerHandler());
            //3 添加拦截器Filter
            //acceptor.getFilterChain().addLast("text",new ProtocolCodecFilter(new TextLineCodecFactory()));
            acceptor.getFilterChain().addLast("text",new ProtocolCodecFilter(new MyTextLineCodecFactory()));
            acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,5);
            //4 NioSocketAcceptor绑定
            acceptor.bind(new InetSocketAddress(8888));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
