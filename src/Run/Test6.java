package Run;

public class Test6 {

	public static void main(String[] args) {
		char grade = grade(avg(90, 80, 100));
		System.out.println(grade + " 입니다."); // 수입니다.

	}

	public static double avg(double a, double b, double c) {
		double avg = (a + b + c) / 3;
		return avg;
	}

	public static char grade(double avg) {
		char grade = '가';
		if (90 >= avg) {
			grade = '수';
		} else if (80 >= avg) {
			grade = '우';
		} else if (70 >= avg) {
			grade = '미';
		} else if (60 >= avg) {
			grade = '양';
		} else {
			grade = '가';
		}
		return grade;
	}
}