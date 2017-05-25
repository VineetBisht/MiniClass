/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniclass.backend;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

/**
 *
 * @author Vineet
 */
public class ChatHandler extends Thread {

  public String userid;
  protected Socket s;
  protected ObjectInputStream i;
  protected ObjectOutputStream o;
  protected static Vector handlers = new Vector();
  public Factory ob;
  private boolean professor;
  public String searchy;
  private int score;
  private boolean authenticated;
  
   public ChatHandler (Socket s) throws IOException {
    this.s = s;
    professor=false;
    i = new ObjectInputStream (s.getInputStream ());
    o = new ObjectOutputStream (s.getOutputStream ());
    ob=new Factory();
    authenticated=false;
    userid="";
    searchy="";
    score=0;
   } 
  
  public void run () {
   try {
      handlers.addElement (this);
      while (true) {
          ob = (Factory)i.readObject();
          if((ob.getType()).equalsIgnoreCase("searchyc")==true){
              ob.searchdone=searchy;
              new SerializeToDatabase(ob,userid);
              ob.setText(new SerializeToDatabase(ob.getQues()).tempText);
              ob.setAdded(" ");
              ob.setRetType("searchyc");
              broadcast(ob);
          }
          //searchyC 
          else if((ob.getType()).equalsIgnoreCase("check")==true){
             if(professor){
                 ob.professor=true;
             }
              ob.setRetType("check");
             broadcast(ob);
          }
          //check
          else if((ob.getType()).equalsIgnoreCase("search")==true){
               ob.setList((new SerializeToDatabase(ob)).list);
                 try {
             ob.setRetType("search");
             
                     broadcast(ob);
                 } catch(Exception ex) {
           ex.printStackTrace(); }
        }//search 
          
          else if(ob.getType().equalsIgnoreCase("searchdone")==true){
               ob.setRetType("searchdone");
             
              searchy=ob.searchdone;
          }//searched item
          
          else if((ob.getType()).equalsIgnoreCase("ongo")==true){
              
          }//ongo
          else if((ob.getType()).equalsIgnoreCase("newly")==true){
              if(!(ob.getText().isEmpty())){
               new SerializeToDatabase(ob.getQues(),ob.getType(),ob.getText(),ob.getCompleted(),ob.getSubject(),userid); 
              }
           }//newly
          
          else if((ob.getType()).equalsIgnoreCase("login")==true){
                  authenticated=(new LoginDb()).authenticate(ob);
        try {
            if(authenticated){
              this.professor=(ob.professor)?true:false;
              ob.authenticated=true;}
             ob.setRetType("login");
            broadcast(ob);
           } catch(Exception ex) {
           ex.printStackTrace(); }
          }//login
          
          else if((ob.getType()).equalsIgnoreCase("quiz")==true){
          ob.setUser(userid);
           ob.setRetType("quiz");
             
          broadcast(ob);
          }//quiz
           
          else if((ob.getType()).equalsIgnoreCase("score")==true){
          score=ob.score;
          }//score
          
          else if((ob.getType()).equalsIgnoreCase("menu")==true){
                ob.setUser(userid);
                ob.professor=professor;
                ob.setRetType("main");
                broadcast(ob);  
          }
          
          else if((ob.getType()).equalsIgnoreCase("quize")==true){
             ob.score=score;
              ob.setRetType("quize");
             
             broadcast(ob);
          }//material
          else if((ob.getType()).equalsIgnoreCase("leader")==true){
                ob.setUser(userid);
                ob.professor=professor;
                 ob.setRetType("leader");
                broadcast(ob);
          }//sche
          else{
              System.out.println("Wrong Entry:");
          }
      }
    } catch (Exception ex) {
   ex.printStackTrace();
    } finally {
      handlers.removeElement (this);
    }
    }
  
  protected static void broadcast(Factory ob) {
    synchronized (handlers) {
      Enumeration e = handlers.elements ();
      while (e.hasMoreElements ()) {
        ChatHandler c = (ChatHandler) e.nextElement ();
        try {
          synchronized (c.o) {
            c.o.writeObject(ob);
          }
         } catch(Exception ex) {
             ex.printStackTrace();
         }
      }
    }
  }

 
}


