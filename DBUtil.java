package studentScore;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	public static Connection getConnection() throws Exception {

		String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
		String URL = "jdbc:mysql://localhost/studentdb";

		Class.forName(MYSQL_DRIVER);

		// 2. 데이타 베이스 연결
		Connection connection = DriverManager.getConnection(URL, "root", "123456");
		if (connection != null) {
			System.out.println("DB 연결 성공");
		} else {
			System.out.println("DB 연결 실패");
		}

		return connection;
	}

}
