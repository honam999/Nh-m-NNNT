/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examplechat4;

import java.util.StringTokenizer;

/**
 * Cac phan cach nhau boi dau |
 * Muc dich de don gian
 * @author Dell
 */
public class MyMessage {
    //De gian, tat ca la public
    public String sender;
    public String receiver;
    public String type;
    public String content;
    
    public MyMessage(){
        this.sender = "null";
        this.receiver = "null";
        this.type = "null";
        this.content = "null";
    }
    public MyMessage(String sender, String receiver, String type, String content){
        this.sender = sender;
        this.receiver = receiver;
        this.type = type;
        this.content = content;
    }
    public MyMessage(String s){
        StringTokenizer st = new StringTokenizer(s, "|");
        while (st.hasMoreElements()){
            this.sender = st.nextElement().toString();
            this.receiver = st.nextElement().toString();
            this.type = st.nextElement().toString();
            this.content = st.nextElement().toString();
        }
    }
    
    public String toMyString(){
        String s = this.sender + "|"
               + this.receiver + "|"
               + this.type + "|"
               + this.content;
        return s;
    }
    public static void main(String[] argv){
        //vi du user1 muon login
        MyMessage mm = new MyMessage("user1", "server", "login", "user123456");
        String s = mm.toMyString();
        System.out.println(s);
        
        //vi du user1 muon gui toi user2 doan chat "hi. Mi khoe ko?"
        MyMessage mm1 = new MyMessage("user1", "user2", "chat", "hi. Mi khoe ko?");
        System.out.println(mm1.toMyString());
        
        //vi du server nhan duoc 1 message user3|user4|chat|hom nay nghi hoc ko?
        s = "user3|user4|chat|hom nay nghi hoc ko?";
        System.out.println("server received message: " + s);
        MyMessage mm2 = new MyMessage(s);
        System.out.println("sender: " + mm2.sender);
        System.out.println("receiver: " + mm2.receiver);
        System.out.println("type: " + mm2.type);
        System.out.println("content: " + mm2.content);
        
        //vi du client yeu cau server, gui cho ta nhung gi nguoi khac gui
        MyMessage mm3 = new MyMessage("user1", "server", "get", "messages");
        System.out.println(mm3.toMyString());
    }
}
