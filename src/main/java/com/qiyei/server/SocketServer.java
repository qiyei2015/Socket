package com.qiyei.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Created by qiyei2015 on 2018/1/5.
 * @version: 1.0
 * @email: 1273482124@qq.com
 * @description:
 */
public class SocketServer {

    public static void main(String[] args){
        new SocketServer().start();
    }

    public void start(){
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(8888);
            while (true){
                socket = serverSocket.accept();
                newSocketRun(socket);
                //startTask(socket,5);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 开启一个新socket来运行
     * @param socket
     */
    public void newSocketRun(final Socket socket){
        new Thread(new Runnable() {
            public void run() {
                BufferedReader inputReader = null;
                BufferedWriter outputWirter = null;
                System.out.println("client:" + socket.hashCode() + " connected ! ");
                try {
                    inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    outputWirter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    String s = null;
                    while (!(s = inputReader.readLine()).equals("bye")){
                        System.out.println("receive:" + s);
                        outputWirter.write("server reply :" + s + "\n");
                        outputWirter.flush();
                        System.out.println("send:" + s);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        inputReader.close();
                        outputWirter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 开启定时任务
     * @param socket
     * @param time
     */
    private void startTask(final Socket socket,int time){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                BufferedWriter outputWirter = null;
                try {
                    outputWirter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    outputWirter.write("server heartbeat:" + socket.hashCode() + "\n");
                    outputWirter.flush();
                    System.out.println("server heartbeat:" + socket.hashCode());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        },time * 1000,time * 1000);
    }
}
