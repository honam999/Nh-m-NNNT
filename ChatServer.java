/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examplechat4;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author Dell
 */
public class ChatServer {
    public static void main(String[] args) {
        // TODO code application logic here
        int port = 1509;
        System.out.println("Hello. This is server!");
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Start server! Waiting ...");
            while (true){
                Socket currentSocket = serverSocket.accept();
                System.out.println("Connect");
                
                InputStream is = currentSocket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                String s = (String)ois.readObject();
                System.out.println(s);
                
                //phan tich message
                MyMessage messageIn = new MyMessage(s);
                MyMessage messageOut = new MyMessage();
                if (messageIn.type.equalsIgnoreCase("login")){
                    if (messageIn.sender.equalsIgnoreCase("user1") 
                            && messageIn.content.equalsIgnoreCase("user123456")){
                        //login thanh cong
                        /*
                        lam nhieu thu o day
                        */
                        
                        //ta ve cho client, login thanh cong 
                        messageOut.sender = "server";
                        messageOut.receiver = messageIn.sender;
                        messageOut.type = messageIn.type;
                        messageOut.content = "OK";
                    }
                    else{
                        //sai password
                        messageOut.sender = "server";
                        messageOut.receiver = messageIn.sender;
                        messageOut.type = messageIn.type;
                        messageOut.content = "Fail";
                    }
                }
                
                OutputStream os = currentSocket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(messageOut.toMyString());
                oos.flush();
                
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }    
}
