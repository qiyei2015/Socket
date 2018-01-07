package com.qiyei.server.mina;


import com.qiyei.util.LogUtil;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Created by qiyei2015 on 2018/1/7.
 * @version: 1.0
 * @email: 1273482124@qq.com
 * @description:
 */
public class MyTextLineDecoder implements ProtocolDecoder {



    public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
//        int startPos = in.position();
//        //循环读取数据
//        while (in.hasRemaining()){
//            byte b = in.get();
//            //读到换行符
//            if (b == '\n'){
//                int currentPos = in.position();
//                int limit = in.limit();
//                in.position(startPos);
//                in.limit(currentPos);
//                IoBuffer buffer = in.slice();
//                byte[] dest = new byte[buffer.limit()];
//                buffer.get(dest);
//                String s = new String(dest);
//                out.write(s);
//                //恢复到截取之前的状态
//                in.position(currentPos);
//                in.limit(limit);
//            }
//        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in.asInputStream()));
        String s = null;
        while ((s = bufferedReader.readLine()) != null){
            LogUtil.println("MyTextLineDecoder","decode:" + s);
            out.write(s + "\n");
        }
    }

    public void finishDecode(IoSession session, ProtocolDecoderOutput out) throws Exception {

    }

    public void dispose(IoSession session) throws Exception {

    }
}
