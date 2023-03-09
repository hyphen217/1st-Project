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
			System.out.print("1.목록	2.등록	3.내용	4.삭제	0.종료 >> "); // 선택
			int input = Integer.parseInt(sc.nextLine()); // 문자를 숫자로 변환
			System.out.println("─────────────────────────────────────────────"); // 선택

			switch (input) {
			case 0:
				r = false; // 종료 함수 호출
				break;

			case 1:
				ListCheck(); // 목록 함수 호출
				break;

			case 2:
				Add(); // 등록 함수 호출
				break;

			case 3:
				ContentCheck(); // 내용 함수 호출
				break;

			case 4:
				DeleteCheck(); // 삭제 함수 호출
				break;

			default:
				System.out.println("에러");
				break;
			}
		}
	}
}
