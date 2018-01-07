package com.qiyei.server.mina;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * @author Created by qiyei2015 on 2018/1/7.
 * @version: 1.0
 * @email: 1273482124@qq.com
 * @description:
 */
public class MyTextLineCodecFactory implements ProtocolCodecFactory {

    private ProtocolEncoder mEncoder;
    private ProtocolDecoder mDecoder;

    public MyTextLineCodecFactory() {
        mEncoder = new MyTextLineEncoder();
        mDecoder = new MyTextLineDecoder();
    }

    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return mEncoder;
    }

    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        return mDecoder;
    }

}
