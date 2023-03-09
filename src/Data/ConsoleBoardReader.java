package Data;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class ConsoleBoardReader {
	static LocalDateTime now = LocalDateTime.now();
	static String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	static List<DataModel> ls = new LinkedList<>();
	static List<DataModel> lt = new LinkedList<>();
	static String div = "嵐はは";

//목록==================================================================================
	public static void ListCheck() {
		int i = 0;
		System.out.println("번호	제목	작성자	작성일");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

		ls.clear();
		String s = null;
		try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("list.txt")));) {
			while ((s = in.readLine()) != null) {
				String[] sp = s.split(div); // split으로 나뉜 데이터를 배열에 대입
				ls.add(new DataList(i + 1, sp[0], sp[1], sp[3]));
				System.out.print(ls.get(i));
				i++;
			}
			System.out.println();
		} catch (IOException e) {
			System.out.println("에러");
		}
	}

//등록==================================================================================
	public static void Add() {
		Scanner sc = new Scanner(System.in);
		System.out.print("제목 입력: "); // 제목
		String title = sc.nextLine();

		System.out.print("내용 입력: "); // 내용
		String content = sc.nextLine();

		System.out.print("작성자 입력: "); // 작성자
		String writer = sc.nextLine();

		try (BufferedWriter out = new BufferedWriter(new FileWriter("list.txt", true))) {
			out.write(title + div);
			out.write(writer + div);
			out.write(content + div);
			out.write(formatedNow);
			out.newLine();
			System.out.println();

		} catch (IOException e) {
			System.out.println("에러");
		}
	}

//내용==================================================================================
	public static void ContentCheck() {
		int i = 0;

		ListCheck();
		lt.clear();
		String s = null;
		try (BufferedReader in = new BufferedReader(new FileReader("list.txt"));) {
			while ((s = in.readLine()) != null) {
				String[] sp = s.split(div); // split으로 나뉜 데이터를 배열에 대입

				lt.add(new DataContent(i + 1, sp[0], sp[1], sp[3], sp[2]));
				i++;
			}
			Scanner sc = new Scanner(System.in);
			System.out.print("위의 게시판 번호 중 하나를 넣어 주세요. >> ");
			int n = Integer.parseInt(sc.nextLine());
//			System.out.println("인덱스 " + n);
//			System.out.println("data내부값" + lt.get(n - 1).num);
			System.out.println(lt.get(n - 1));

			if (n == lt.get(n - 1).num) {
				System.out.println("<	상	세	내	용	>");
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.print(lt.get(n - 1).content + "\n");
			} else {
				System.out.println("에러");
			}

			System.out.println();
		} catch (IOException e) {
			System.out.println("에러");
		}
	}

//삭제==================================================================================
	public static void DeleteCheck() {
		Scanner sc = new Scanner(System.in);
		ListCheck();
		System.out.print("삭제할 글 번호를 입력해주세요. >> ");
		String delData = sc.nextLine();

		String dummy = "";
		Path path = Paths.get("list.txt");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("list.txt")));) {
			// 파일 라인수 세기
			long lineCount = Files.lines(path, Charset.defaultCharset()).count();
			// 삭제할 열을 숫자로 변환
			int int_val = Integer.parseInt(delData);
			// 라인수를 문자열로 변환
			String lC = String.valueOf(lineCount);

			// 삭제할 열 이전까지 dummy에 저장
			for (int i = 1; i < int_val; i++) {
				lC = br.readLine(); // 읽으며 이동
				dummy += (lC + "\n");
			}
			// 삭제할 열 뛰어넘기
			delData = br.readLine();
			System.out.println('\n' + "─────────────────────────────────────────────" + '\n' + "[삭제할 글]" + '\n'
					+ delData + '\n' + "─────────────────────────────────────────────");
			// 삭제할 열 이후부터 dummy에 저장
			while ((lC = br.readLine()) != null) {
				dummy += (lC + "\r\n");
			}
			// dummy에 저장된 데이터를 다시 덮어쓰기
			System.out.print("삭제하시겠습니까? 1:YES | 2:NO >> ");
			int yOrN = sc.nextInt();
			sc.nextLine();
			if ((yOrN == 1) || (String.valueOf(yOrN).equals("YES"))) {
				FileWriter fw = new FileWriter("list.txt");
				fw.write(dummy);
				fw.close();
				br.close();
				System.out.println("삭제되었습니다.");
			} else if ((yOrN == 2) || (String.valueOf(yOrN).equals("NO"))) {
				System.out.println("취소하였습니다.");
			}
			br.close();
		} catch (Exception e) {
			System.out.println("에러");
		}
	}
}