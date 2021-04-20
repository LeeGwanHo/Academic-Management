package studentScore;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class MainScoreTable {
	public static final Scanner sc = new Scanner(System.in);
	public static final int PRINT = 1;
	public static final int INSERT = 2;
	public static final int DELETE = 3;
	public static final int SEARCH = 4;
	public static final int EXIT = 5;

	public static void main(String[] args) throws Exception {
		boolean flag = false;

		while (!flag) {
			System.out.println("1.데이터 조회 2.데이터 입력 3.데이터 삭제 4.데이터 검색 5.종료");
			int num = sc.nextInt();

			switch (num) {
			case PRINT:
				printStudentInfo();
				break;
			case INSERT:
				insertStudentInfo();
				break;
			case DELETE:
				deleteStudentInfo();
				break;
			case SEARCH:
				searchStudentInfo();
				break;
			case EXIT:
				flag = true;
				break;

			default:
				System.out.println("다시 입력해주세요(1~6)");
				break;
			}
		}
		System.out.println("byebye");

	}

	private static void searchStudentInfo() throws Exception {
		boolean flag = false;
		StudentInfo studentInfo = null;

		try {
			while (!flag) {
				System.out.println("찾고 싶은 이름 검색");
				String name = sc.next();

				studentInfo = DatabaseController.searchControllerStudentInfoWhereByName(name);

				if (studentInfo != null) {
					System.out.println(studentInfo);
					break;
				} else {
					System.out.println("존재하지않습니다");
				}

			}

		} catch (Exception e) {
			System.out.println("찾기 오류" + e.toString());
		}
	}

	private static void deleteStudentInfo() {
		try {
			System.out.println("지우고싶은 이름 입력");
			String name = sc.next();
			boolean returnValue = DatabaseController.deleteStudentInfo(name);
			if (returnValue == true) {
				System.out.println("삭제완료");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void insertStudentInfo() throws Exception {
		boolean flag = false;
		StudentInfo studentInfo = null;
		StudentInfo inputStudentInfo = null;
		String name = null;

		try {
			while (!flag) {
				System.out.println("이름 입력");
				name = sc.next();
				studentInfo = DatabaseController.searchControllerStudentInfoWhereByName(name);
				if (studentInfo != null && studentInfo.getName().equals(name)) {
					System.out.println("존재하는 이름입니다.");
					continue;
				}
				System.out.println("번호입력");
				int num = sc.nextInt();
				System.out.println("학번입력");
				String studentNum = sc.next();
				System.out.println("이름입력");
				String name1 = sc.next();
				System.out.println("과목입력");
				String subjectName = sc.next();
				System.out.println("객관식 점수입력");
				int multiple = sc.nextInt();
				System.out.println("주관식 점수입력");
				int essay = sc.nextInt();
				System.out.println("총점입력");
				int total = sc.nextInt();
				System.out.println("평균입력");
				double avg = sc.nextDouble();
				System.out.println("등급입력");
				String grade = sc.next();

				inputStudentInfo = new StudentInfo(num, studentNum, name1, subjectName, multiple, essay, total, avg,
						grade);
				flag = true;
			}
		} catch (Exception e) {
			System.out.println("정확한 이름입력요망");
		}
		boolean returnValue = DatabaseController.insertStudentInfo(inputStudentInfo);
		if (returnValue == true) {
			System.out.println("삽입완료");
		} else {
			System.out.println("삽입실패");
		}
	}

	private static void printStudentInfo() throws Exception {
		HashSet<StudentInfo> hashSet = new HashSet<StudentInfo>();
		hashSet = DatabaseController.selectControllerStudentInfo();

		if (hashSet != null) {
			Iterator<StudentInfo> iterator = hashSet.iterator();
			while (iterator.hasNext()) {
				StudentInfo studentInfo = iterator.next();
				System.out.println(studentInfo);
			}
		} else {
			System.out.println("출력오류");
		}
	}

}
