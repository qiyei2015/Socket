package com.qiyei.client.mina;

import com.qiyei.util.LogUtil;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * @author Created by qiyei2015 on 2018/1/9.
 * @version: 1.0
 * @email: 1273482124@qq.com
 * @description:
 */
public class MinaClientHandler extends IoHandlerAdapter {

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
        LogUtil.println("sessionCreated "+ session.hashCode(),"");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
        LogUtil.println("sessionCreated "+ session.hashCode(),"");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
        LogUtil.println("sessionClosed "+ session.hashCode(),"");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        super.sessionIdle(session, status);
        LogUtil.println("sessionIdle "+ session.hashCode(),"");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
        LogUtil.println("exceptionCaught "+ session.hashCode(),"");
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        super.messageReceived(session, message);
        String s = (String) message;
        //session.write(message);
        LogUtil.println("messageReceived " + session.hashCode(),s);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
        LogUtil.println("messageSent "+ session.hashCode(),(String) message);
    }

    @Override
    public void inputClosed(IoSession session) throws Exception {
        super.inputClosed(session);
        LogUtil.println("inputClosed " + session.hashCode(), "");
    }

}
