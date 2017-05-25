/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package miniclass.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class LoginDb{
     public boolean authenticate(Factory ob){  
         try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/logindb","root","");
                Statement st=cn.createStatement();
                Boolean d=false;
                ResultSet rs=st.executeQuery("select * from login where userid=\""+ob.getUser()+"\" AND professor="+((ob.professor)?"1":"0")+" AND password=\""+ob.getPass()+"\"");
                if(rs.next()){
                    System.out.println(rs.getString("userid")+": "+rs.getString("password")+" "+rs.getString("professor"));
                    return true;
                }
                else return false;
        }catch(Exception e){
            e.printStackTrace();
     }
        return false;
   }
}
