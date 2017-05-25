package miniclass.menu.qui;

 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import miniclass.backend.Refresh;
import miniclass.backend.Refresh;
 
class Quizzing{
    public static void test(String user,Refresh quiz,String answer) throws IOException{
            try{String insert=" UPDATE scores \n" +
"   SET score = ? " +
" WHERE userid=?";
                Class.forName("com.mysql.jdbc.Driver");
                Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/logindb","root","");
                PreparedStatement pstmt1 = cn
				.prepareStatement("SELECT * FROM scores WHERE userid = ?");
		pstmt1.setString(1, user);
		ResultSet rs = pstmt1.executeQuery();
		if(rs.next()){
                 PreparedStatement pstmt = cn
				.prepareStatement(insert);

		// just setting the class name
		pstmt.setInt(1,(rs.getInt("score")+((answer.equals(quiz.answer))?1:0)));
                pstmt.setString(2, user);
                pstmt.executeUpdate();}
                else{
               PreparedStatement pstmt = cn
				.prepareStatement("INSERT INTO scores" +
" VALUES (?, 0, 0, ?);");

		// just setting the class name
		pstmt.setString(1,user);
                pstmt.setInt(2,(answer.equals(quiz.answer))?1:0);
                pstmt.executeUpdate();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            }
    }


            