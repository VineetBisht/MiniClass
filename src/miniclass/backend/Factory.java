/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniclass.backend;

import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Vineet
 */
public class Factory implements Serializable{
   private String type,text,ques,search;
   private List<String> list;
   static final long serialVersionUID = -7190475245887099667L;
   private boolean completed;
   private String user;
   private String pass;
   public boolean professor;
   public boolean authenticated;
   public String searchdone;
   private String added;
   private String subject;
   public String answer;
   public boolean oppo;
   public boolean oppodone;
   public String option1;
   public String option2;
   public String option3;
   public String option4;
   public int score;
   public String retType;
  
   public Factory() {
        this.text = " ";
        this.type = " ";
        this.ques = " ";
        this.search=" ";
        completed=false;
        professor=false;
        authenticated=false;
        searchdone="";
        added="";
        subject="";
        answer="";
        oppo=false;
        retType="";
        oppodone=false;
   }
    public  void writeObject(ObjectOutputStream out)throws IOException{
        out.defaultWriteObject();
    }
    
    public void readObject(ObjectInputStream in)throws IOException, ClassNotFoundException{
        in.defaultReadObject();
    }
    
    public void setCompleted(boolean completed){
        this.completed=completed;
    }
    
    public boolean getCompleted(){
        return completed;
    }
   
    public void setAdded(String added){
        this.added=added;
    }
    
    public String getAdded(){
        return added;
    }
   
    public void setSubject(String subject){
        this.subject=subject;
    }
    
    public String getSubject(){
        return subject;
    }
    
    public void setQues(String ques){
        this.ques=ques;
    }
    
    public String getQues(){
        return ques;
    }
    
    public void setUser(String user){
        this.user=user;
    }
    
    public String getUser(){
        return user;
    }
    
    public void setPass(String pass){
        this.pass=pass;
    }
    
    public String getPass(){
        return pass;
    }
    
     public void setSearch(String search){
        this.search=search;
    }
    
    public String getSearch(){
        return search;
    }
    
    public void setType(String type){
        this.type=type;
    }
    
    public String getType(){
        return type;
    }
    
     public void setRetType(String retType){
        this.retType=retType;
    }
    
    public String getRetType(){
        return retType;
    }
    
    public void setText(String text){
        this.text=text;
    }
    
    public String getText(){
        return text;
    }
    
      public void setList(List<String> list){
        this.list=list;
    }
    
    public List<String> getList(){
        return list;
    }
}
