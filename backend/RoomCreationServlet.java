import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RoomCreationServlet")
public class RoomCreationServlet extends HttpServlet{
	private static final long serialVersionUID = 100L;
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //token creation
		 String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		 int TOKEN_LENGTH = 6;
		 Random random = new Random();
		 StringBuilder token = new StringBuilder(TOKEN_LENGTH);
		 for (int i = 0; i < TOKEN_LENGTH; i++) {
			 int index = random.nextInt(CHARACTERS.length());
			 token.append(CHARACTERS.charAt(index));
		 }
		 String code = token.toString();
		 
		 String user_id = request.getParameter("user_id");
		 String roomName = request.getParameter("roomName");
		 String video_link = request.getParameter("roomName");
		 String active_users = user_id;
		 
		 Connection conn = null;
	     PreparedStatement ps = null;
	     ResultSet rs = null;
	     try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BingeBaddies?user=root&password=Rayquaza10!");
			ps = conn.prepareStatement("INSERT INTO Room (user_id, roomName, video_link, code, active_users) "
					+ "VALUES (?, ?, ?, ?, ?, ?)");
			ps.setString(1, user_id);
			ps.setString(2, roomName);
			ps.setString(3, video_link);
			ps.setString(4, code);
			ps.setString(5, active_users);
			rs = ps.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(ps != null) {
					ps.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	 }
}
