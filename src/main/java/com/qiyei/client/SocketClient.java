package com.qiyei.client;

import java.io.*;
import java.net.Socket;

/**
 * @author Created by qiyei2015 on 2018/1/5.
 * @version: 1.0
 * @email: 1273482124@qq.com
 * @description:
 */
public class SocketClient {

    public static void main(String[] args){
        new SocketClient().start();
    }

    public void start(){
        BufferedReader inputReader = null;
        BufferedReader bufferedReader = null;
        BufferedWriter outputWriter = null;

        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1",8888);
            outputWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            inputReader = new BufferedReader(new InputStreamReader(System.in));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String s = null;
            while (!(s = inputReader.readLine()).equals("bye")){
                //需要加结束符 \n
                outputWriter.write(s + "\n");
                outputWriter.flush();
                System.out.println("receive server:" + bufferedReader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputReader.close();
                bufferedReader.close();
                outputWriter.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
