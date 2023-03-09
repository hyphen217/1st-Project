package Run;

import java.io.*;
import java.util.Scanner;

import Data.*;

public class ConsoleBoard extends ConsoleBoardReader {
	public static void main(String[] args) {
		ConsoleBoardReader run = new ConsoleBoardReader();
		Scanner sc = new Scanner(System.in);
		boolean r = true;

		while (r) {
			System.out.print("1.���	2.���	3.����	4.����	0.���� >> "); // ����
			int input = Integer.parseInt(sc.nextLine()); // ���ڸ� ���ڷ� ��ȯ
			System.out.println("������������������������������������������������������������������������������������������"); // ����

			switch (input) {
			case 0:
				r = false; // ���� �Լ� ȣ��
				break;

			case 1:
				ListCheck(); // ��� �Լ� ȣ��
				break;

			case 2:
				Add(); // ��� �Լ� ȣ��
				break;

			case 3:
				ContentCheck(); // ���� �Լ� ȣ��
				break;

			case 4:
				DeleteCheck(); // ���� �Լ� ȣ��
				break;

			default:
				System.out.println("����");
				break;
			}
		}
	}
}
