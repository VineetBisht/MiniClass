/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package miniclass.backend;

import java.io.*;
import java.sql.*;
public class Refresh
{
    public String question;
    public String option1;
    public String option2;
    public String option3;
    public String option4;
    public String answer;
      
   public void getValues(int id)throws IOException
   {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    
        try {
                System.out.println("type the correct option no.");      
                Class.forName("com.mysql.jdbc.Driver");
                Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","");
                Statement st=cn.createStatement();
                Boolean d=false;
                ResultSet rs=st.executeQuery("select * from questionbank where sno='"+id+"'");
                if(rs.next())  {
                        question=rs.getString("ques");
                        option1=rs.getString("ans1");
                        option2=rs.getString("ans2");
                        option3=rs.getString("ans3");
                        option4=rs.getString("ans4");
                        answer=rs.getString("cans");
                        d=rs.getBoolean("done");
                }
                if(d) 
                {    
                    id=1 + (int)(Math.random() * ((30- 1) + 1));
                    getValues(id);
                }
         } catch(Exception e)
            {
                e.printStackTrace();
            }
    }
}
