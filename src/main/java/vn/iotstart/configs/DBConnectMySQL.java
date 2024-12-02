package vn.iotstart.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectMySQL {
	private static String USERNAME = "root";
	 private static String PASSWORD = "tzxr95123#";
	 private static String DRIVER = "com.mysql.cj.jdbc.Driver";
	 private static String URL = "jdbc:mysql://localhost:3306/ltwebct2";

	 public static Connection getConnection() throws SQLException {
		    try {
		        // Tải driver JDBC cho MySQL
		        Class.forName(DRIVER);
		    } catch (ClassNotFoundException e) {
		        e.printStackTrace(); // In ra lỗi nếu không tìm thấy driver
		    }

		    // Trả về đối tượng kết nối với cơ sở dữ liệu
		    return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}

		public static void main(String[] args) {
		    try {
		        // Khởi tạo đối tượng DBConnectMySQL và kiểm tra kết nối
		        new DBConnectMySQL();
		        System.out.println(DBConnectMySQL.getConnection());
		    } catch (Exception e) {
		        e.printStackTrace(); // In ra lỗi nếu có ngoại lệ
		    }
		}

}
