package Run;

public class Test6 {

	public static void main(String[] args) {
		char grade = grade(avg(90, 80, 100));
		System.out.println(grade + " �Դϴ�."); // ���Դϴ�.

	}

	public static double avg(double a, double b, double c) {
		double avg = (a + b + c) / 3;
		return avg;
	}

	public static char grade(double avg) {
		char grade = '��';
		if (90 >= avg) {
			grade = '��';
		} else if (80 >= avg) {
			grade = '��';
		} else if (70 >= avg) {
			grade = '��';
		} else if (60 >= avg) {
			grade = '��';
		} else {
			grade = '��';
		}
		return grade;
	}
}