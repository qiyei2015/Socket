package com.qiyei.client.mina;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

/**
 * @author Created by qiyei2015 on 2018/1/9.
 * @version: 1.0
 * @email: 1273482124@qq.com
 * @description:
 */
public class MinaClient {

    public static void main(String[] args){
        new MinaClient().start();
    }

    public void start(){
        //1 创建NioSocketConnector
        NioSocketConnector connector = new NioSocketConnector();
        //2 设置Handler
        connector.setHandler(new MinaClientHandler());
        //3 添加拦截器Filter
        connector.getFilterChain().addLast("text",new ProtocolCodecFilter(new TextLineCodecFactory()));
        connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,5);
        //4 建立连接
        ConnectFuture future = connector.connect(new InetSocketAddress("127.0.0.1",8888));
        future.awaitUninterruptibly();

        //5 建立连接成功
        IoSession session = future.getSession();
        BufferedReader inputReader = null;
        inputReader = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        try {
            while (!(s = inputReader.readLine()).equals("bye")){
                //需要加结束符 \n
                session.write( s + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
