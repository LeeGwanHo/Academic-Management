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
			System.out.println("1.������ ��ȸ 2.������ �Է� 3.������ ���� 4.������ �˻� 5.����");
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
				System.out.println("�ٽ� �Է����ּ���(1~6)");
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
				System.out.println("ã�� ���� �̸� �˻�");
				String name = sc.next();

				studentInfo = DatabaseController.searchControllerStudentInfoWhereByName(name);

				if (studentInfo != null) {
					System.out.println(studentInfo);
					break;
				} else {
					System.out.println("���������ʽ��ϴ�");
				}

			}

		} catch (Exception e) {
			System.out.println("ã�� ����" + e.toString());
		}
	}

	private static void deleteStudentInfo() {
		try {
			System.out.println("�������� �̸� �Է�");
			String name = sc.next();
			boolean returnValue = DatabaseController.deleteStudentInfo(name);
			if (returnValue == true) {
				System.out.println("�����Ϸ�");
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
				System.out.println("�̸� �Է�");
				name = sc.next();
				studentInfo = DatabaseController.searchControllerStudentInfoWhereByName(name);
				if (studentInfo != null && studentInfo.getName().equals(name)) {
					System.out.println("�����ϴ� �̸��Դϴ�.");
					continue;
				}
				System.out.println("��ȣ�Է�");
				int num = sc.nextInt();
				System.out.println("�й��Է�");
				String studentNum = sc.next();
				System.out.println("�̸��Է�");
				String name1 = sc.next();
				System.out.println("�����Է�");
				String subjectName = sc.next();
				System.out.println("������ �����Է�");
				int multiple = sc.nextInt();
				System.out.println("�ְ��� �����Է�");
				int essay = sc.nextInt();
				System.out.println("�����Է�");
				int total = sc.nextInt();
				System.out.println("����Է�");
				double avg = sc.nextDouble();
				System.out.println("����Է�");
				String grade = sc.next();

				inputStudentInfo = new StudentInfo(num, studentNum, name1, subjectName, multiple, essay, total, avg,
						grade);
				flag = true;
			}
		} catch (Exception e) {
			System.out.println("��Ȯ�� �̸��Է¿��");
		}
		boolean returnValue = DatabaseController.insertStudentInfo(inputStudentInfo);
		if (returnValue == true) {
			System.out.println("���ԿϷ�");
		} else {
			System.out.println("���Խ���");
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
			System.out.println("��¿���");
		}
	}

}
