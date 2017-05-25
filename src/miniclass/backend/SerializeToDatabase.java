/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniclass.backend;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Vineet
 */

public class SerializeToDatabase{
        private static String temp=" ";
        private static String text;
        private static String type;
	private static final String SQL_SERIALIZE_OBJECT = "INSERT INTO serialized_java_objects(ques, text , frm, subject, completed) VALUES (?, ?, ?, ?, ?)";
	private static final String SQL_DESERIALIZE_OBJECT = "SELECT ques FROM serialized_java_objects WHERE serialized_id = ?";
        private static Connection connection;
        private static long serialized_id;
        private static String ques;
        private static final HashMap<String,Long> store=new HashMap<>();
        private String driver = "com.mysql.cj.jdbc.Driver";
	private	String url = "jdbc:mysql://localhost:3306/mydb";
	private	String username = "root";
	private	String password = "";
	private static boolean completed=false;
        private static int professor=0;
        private static int online=1;
        public  static String tempText;
        public ArrayList<String> list;
        private static String subject="";
        private static String from="";
        
        public SerializeToDatabase(Factory fac){
            init();
            String search=fac.getSearch();
            list=new ArrayList<>();
            Iterator it=store.entrySet().iterator();
            while(it.hasNext()){
            Map.Entry pair=(Map.Entry)it.next();
            if(((String)pair.getKey()).startsWith(fac.getSearch())){
            list.add((String)pair.getKey());
            System.out.println("Found!");
              }
             }
           }
        
        public SerializeToDatabase(Factory ob1,String user){
          	init();
                this.completed=completed;
                this.ques=ob1.getQues();
                this.type=ob1.getType();
                this.text=ob1.getText()+"\n"+user+": "+ob1.getAdded();
                connection = null;
		try{Class.forName(driver);
		connection = DriverManager.getConnection(url, username, password);
		save();
                }catch(ClassNotFoundException|SQLException e){
                    System.out.println("SerializeToDatabase: "+e);
                }finally{
                try{connection.close();}
                catch(SQLException e){
                    System.out.println("SerializeToDatabase2:"+e );
                }
                }
        }
        
        public SerializeToDatabase(String ques,String type,String text, boolean completed,String subject,String user) {
		init();
                this.subject=subject;
                this.from=user;
                this.completed=completed;
                this.ques=ques;
                this.type=type;
                this.text=text;
                connection = null;
		try{Class.forName(driver);
		connection = DriverManager.getConnection(url, username, password);
		save();
                }catch(ClassNotFoundException|SQLException e){
                    System.out.println("SerializeToDatabase: "+e);
                }finally{
                try{connection.close();}
                catch(SQLException e){
                    System.out.println("SerializeToDatabase2:"+e );
                }
                }
	}
        
       public SerializeToDatabase(String ques) {
	        init();
                this.ques=ques;
                connection = null;
                try{Class.forName(driver);
		connection = DriverManager.getConnection(url, username, password);
		load();}
                catch(ClassNotFoundException|SQLException e){
                    System.out.println("SerializeToDatabase: "+e);
                }finally{
                try{connection.close();}
                catch(SQLException e){
                    System.out.println("SerializeToDatabase2:"+e );
                }
        }
       }
       
       public void init(){
           
        try{Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
	    PreparedStatement pst = connection.prepareStatement(SQL_DESERIALIZE_OBJECT);
           
            Statement first=connection.createStatement();
            ResultSet rst = first.executeQuery("select * from serialized_java_objects");
            rst.last();
            for(int i=1;i<=rst.getRow();i++){
            long serial=(long)i;
            pst.setLong(1, serial);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                String temp1=rs.getString("ques");
                System.out.println(" Objects: "+temp1+" "+serial);
                store.put(temp1,serial);
            }
            else
                System.out.println("No Values found");
        } 
    } catch (Exception e) { 
    e.printStackTrace();
         }
      }
       
	public static long serializeJavaObjectToDB(Object objectToSerialize) throws SQLException {
		PreparedStatement pstmt = connection
				.prepareStatement(SQL_SERIALIZE_OBJECT,Statement.RETURN_GENERATED_KEYS);

		// just setting the class name
		pstmt.setString(1, ques);
		pstmt.setString(2, text);
                pstmt.setString(3, from);
                pstmt.setString(4, subject);
                pstmt.setInt(5, ((completed==false)?0:1));
                pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		int serialized_id = -1;
		if (rs.next()) {
			serialized_id = rs.getInt(1);
		}
		rs.close();
		pstmt.close();
		System.out.println("Java object serialized to database. Object: "
				+ objectToSerialize);
		return serialized_id;
	}

		public static Object deSerializeJavaObjectFromDB()throws SQLException, IOException,
			ClassNotFoundException {
		PreparedStatement pstmt = connection
				.prepareStatement(SQL_DESERIALIZE_OBJECT);
		pstmt.setLong(1, serialized_id);
		ResultSet rs = pstmt.executeQuery();
		rs.next();

		// Object object = rs.getObject(1);

		String temp1=rs.getString("ques");
                tempText=rs.getString("text");
                
		rs.close();
		pstmt.close();
        	System.out.println("Java object de-serialized from database. Object: "
				+ ques);
		return temp1;
	}

	
        public static void save(){
            		// serializing java object to mysql database
              try{serialized_id = serializeJavaObjectToDB(temp);
                }catch(SQLException e){
                    System.out.println("Serialize: "+e);
                }
                store.put(ques,serialized_id);
        }
        
        public static void load(){
        	// de-serializing java object from mysql database
		try{
                    serialized_id=store.get(ques);
                   temp =(String) deSerializeJavaObjectFromDB();
                }catch(IOException|ClassNotFoundException|SQLException e){
                    System.out.println("Desearialize: "+e);
                }
        }
        
}

   
