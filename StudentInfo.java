package studentScore;

import java.util.Objects;

public class StudentInfo {
	private int no;
	private String studentNum;
	private String name;
	private String subjectName;
	private int multiple;
	private int essay;
	private int total;
	private double avg;
	private String grade;
	private static final int SUBJECT = 2;

	public StudentInfo(int no, String studentNum, String name, String subjectName, int multiple, int essay, int total,
			double avg, String grade) {
		super();
		this.no = no;
		this.studentNum = studentNum;
		this.name = name;
		this.subjectName = subjectName;
		this.multiple = multiple;
		this.essay = essay;
		this.total = total;
		this.avg = avg;
		this.grade = grade;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getMultiple() {
		return multiple;
	}

	public void setMultiple(int multiple) {
		this.multiple = multiple;
	}

	public int getEssay() {
		return essay;
	}

	public void setEssay(int essay) {
		this.essay = essay;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void calculateTotal() {
		this.total = this.multiple + this.essay;
	}

	public void calculateAvg() {
		this.avg = this.total / SUBJECT;
	}

	public void calculateGrade() {
		switch ((int) this.avg / 10) {
		case 10:

		case 9:
			this.grade = "A";
			break;
		case 8:
			this.grade = "B";
			break;
		case 7:
			this.grade = "C";
			break;
		case 6:
			this.grade = "D";
			break;
		default:
			this.grade = "F";
			break;
		}
	}

	@Override
	public String toString() {
		return no + "\t" + studentNum + "\t" + name + "\t" + subjectName + "\t" + multiple + "\t" + essay + "\t" + total
				+ "\t" + avg + "\t" + grade + "\t";
	}

	@Override
	public int hashCode() {
		return Objects.hash(no);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof StudentInfo) {
			StudentInfo studentInfo = (StudentInfo) obj;
			if (this.no == studentInfo.no) {
				return true;
			}
		}
		return false;
	}

}
