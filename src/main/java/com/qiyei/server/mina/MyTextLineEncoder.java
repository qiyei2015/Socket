package com.qiyei.server.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/**
 * @author Created by qiyei2015 on 2018/1/7.
 * @version: 1.0
 * @email: 1273482124@qq.com
 * @description:
 */
public class MyTextLineEncoder implements ProtocolEncoder{

    private CharsetEncoder mEncode;

    public MyTextLineEncoder() {
        mEncode = Charset.forName("UTF-8").newEncoder();
    }

    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        String msg = null;

        if (message instanceof String){
            msg = (String) message;
        }
        if (msg != null){
            IoBuffer ioBuffer = IoBuffer.allocate(msg.length());
            ioBuffer.setAutoExpand(true);
            ioBuffer.putString(msg,mEncode);
            ioBuffer.flip();
            out.write(ioBuffer);
        }

    }

    public void dispose(IoSession session) throws Exception {

    }
}
