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
import java.net.Socket;

/**
 *
 * @author Dell
 */
public class ChatClient {
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Hi. This is client");
        String address = "localhost";
        int port = 1509;
        String user = "user1";
        String password = "123";
        try{
            Socket clientSocket = new Socket(address, port);
            
            OutputStream os = clientSocket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            MyMessage messageLogin = new MyMessage(user, "server", "login", password);
            oos.writeObject(messageLogin.toMyString());
            oos.flush();
            
            InputStream is = clientSocket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            String s = (String) ois.readObject();
            MyMessage messageConfirmLogin = new MyMessage(s);
            
            if (messageConfirmLogin.type.equalsIgnoreCase(messageLogin.type)){
                System.out.println(messageConfirmLogin.content);
                if (messageConfirmLogin.content.equalsIgnoreCase("ok")){
                    System.out.println("Login thanh cong");
                }
                else{
                    System.out.println("Sai password");
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
