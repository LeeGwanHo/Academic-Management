package studentScore;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class DatabaseController {

	public static HashSet<StudentInfo> selectControllerStudentInfo() throws Exception {
		HashSet<StudentInfo> hashSet = new HashSet<StudentInfo>();

		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String selectQuery = "select * from scoreTable";

		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int no = resultSet.getInt(1);
				String studentNum = resultSet.getString(2);
				String name = resultSet.getString(3);
				String subjectName = resultSet.getString(4);
				int multiple = resultSet.getInt(5);
				int essay = resultSet.getInt(6);
				int total = resultSet.getInt(7);
				double avg = resultSet.getDouble(8);
				String grade = resultSet.getString(9);

				StudentInfo studentInfo = new StudentInfo(no, studentNum, name, subjectName, multiple, essay, total,
						avg, grade);
				hashSet.add(studentInfo);
			}
		} catch (Exception e) {
			System.out.println("select query 오류" + e.toString());
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

		return hashSet;
	}

	public static boolean insertStudentInfo(StudentInfo inputStudentInfo) throws Exception {
		boolean flag = false;
		Connection connection = DBUtil.getConnection();
		String insertQuery = "insert into scoreTable values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(insertQuery);

			preparedStatement.setInt(1, inputStudentInfo.getNo());
			preparedStatement.setString(2, inputStudentInfo.getStudentNum());
			preparedStatement.setString(3, inputStudentInfo.getName());
			preparedStatement.setString(4, inputStudentInfo.getSubjectName());
			preparedStatement.setInt(5, inputStudentInfo.getMultiple());
			preparedStatement.setInt(6, inputStudentInfo.getEssay());
			preparedStatement.setInt(7, inputStudentInfo.getTotal());
			preparedStatement.setDouble(8, inputStudentInfo.getAvg());
			preparedStatement.setString(9, inputStudentInfo.getGrade());

			int count = preparedStatement.executeUpdate();
			if (count == 1) {
				System.out.println("삽입성공");
				return true;
			} else {
				System.out.println("삽입실패");
			}
		} catch (Exception e) {
			System.out.println("삽입오류");
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

		return false;
	}

	public static boolean deleteStudentInfo(String name) throws Exception {
		boolean flag = false;
		Connection connection = DBUtil.getConnection();

		String deleteQuery = "delete from scoreTable where name=?";
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(deleteQuery);
			preparedStatement.setString(1, name);
			int count = preparedStatement.executeUpdate();
			if (count > 0) {
				System.out.println("삭제성공");
				flag = true;
			} else {
				System.out.println("삭제 실패");
			}
		} catch (Exception e) {
			System.out.println("삭제부분에서 문제발생");
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return flag;
	}

	public static StudentInfo searchControllerStudentInfoWhereByName(String name) throws Exception {
		StudentInfo studentInfo = null;
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String selectQuery = "select * from scoreTable where name=?";
		try {
			preparedStatement = connection.prepareStatement(selectQuery);

			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int no1 = resultSet.getInt(1);
				String studentNum = resultSet.getString(2);
				String name1 = resultSet.getString(3);
				String subjectName = resultSet.getString(4);
				int multiple = resultSet.getInt(5);
				int essay = resultSet.getInt(6);
				int total = resultSet.getInt(7);
				double avg = resultSet.getDouble(8);
				String grade = resultSet.getString(9);

				studentInfo = new StudentInfo(no1, studentNum, name1, subjectName, multiple, essay, total, avg, grade);

			}
		} catch (Exception e) {
			System.out.println("select * from scoreTable where name=? 오류" + e.toString());
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return studentInfo;
	}

}
