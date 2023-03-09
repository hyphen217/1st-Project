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
	static String div = "չ�Ϫ�";

//���==================================================================================
	public static void ListCheck() {
		int i = 0;
		System.out.println("��ȣ	����	�ۼ���	�ۼ���");
		System.out.println("������������������������������������������������������������������������������������������");

		ls.clear();
		String s = null;
		try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("list.txt")));) {
			while ((s = in.readLine()) != null) {
				String[] sp = s.split(div); // split���� ���� �����͸� �迭�� ����
				ls.add(new DataList(i + 1, sp[0], sp[1], sp[3]));
				System.out.print(ls.get(i));
				i++;
			}
			System.out.println();
		} catch (IOException e) {
			System.out.println("����");
		}
	}

//���==================================================================================
	public static void Add() {
		Scanner sc = new Scanner(System.in);
		System.out.print("���� �Է�: "); // ����
		String title = sc.nextLine();

		System.out.print("���� �Է�: "); // ����
		String content = sc.nextLine();

		System.out.print("�ۼ��� �Է�: "); // �ۼ���
		String writer = sc.nextLine();

		try (BufferedWriter out = new BufferedWriter(new FileWriter("list.txt", true))) {
			out.write(title + div);
			out.write(writer + div);
			out.write(content + div);
			out.write(formatedNow);
			out.newLine();
			System.out.println();

		} catch (IOException e) {
			System.out.println("����");
		}
	}

//����==================================================================================
	public static void ContentCheck() {
		int i = 0;

		ListCheck();
		lt.clear();
		String s = null;
		try (BufferedReader in = new BufferedReader(new FileReader("list.txt"));) {
			while ((s = in.readLine()) != null) {
				String[] sp = s.split(div); // split���� ���� �����͸� �迭�� ����

				lt.add(new DataContent(i + 1, sp[0], sp[1], sp[3], sp[2]));
				i++;
			}
			Scanner sc = new Scanner(System.in);
			System.out.print("���� �Խ��� ��ȣ �� �ϳ��� �־� �ּ���. >> ");
			int n = Integer.parseInt(sc.nextLine());
//			System.out.println("�ε��� " + n);
//			System.out.println("data���ΰ�" + lt.get(n - 1).num);
			System.out.println(lt.get(n - 1));

			if (n == lt.get(n - 1).num) {
				System.out.println("<	��	��	��	��	>");
				System.out.println("������������������������������������������������������������������������������������������");
				System.out.print(lt.get(n - 1).content + "\n");
			} else {
				System.out.println("����");
			}

			System.out.println();
		} catch (IOException e) {
			System.out.println("����");
		}
	}

//����==================================================================================
	public static void DeleteCheck() {
		Scanner sc = new Scanner(System.in);
		ListCheck();
		System.out.print("������ �� ��ȣ�� �Է����ּ���. >> ");
		String delData = sc.nextLine();

		String dummy = "";
		Path path = Paths.get("list.txt");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("list.txt")));) {
			// ���� ���μ� ����
			long lineCount = Files.lines(path, Charset.defaultCharset()).count();
			// ������ ���� ���ڷ� ��ȯ
			int int_val = Integer.parseInt(delData);
			// ���μ��� ���ڿ��� ��ȯ
			String lC = String.valueOf(lineCount);

			// ������ �� �������� dummy�� ����
			for (int i = 1; i < int_val; i++) {
				lC = br.readLine(); // ������ �̵�
				dummy += (lC + "\n");
			}
			// ������ �� �پ�ѱ�
			delData = br.readLine();
			System.out.println('\n' + "������������������������������������������������������������������������������������������" + '\n' + "[������ ��]" + '\n'
					+ delData + '\n' + "������������������������������������������������������������������������������������������");
			// ������ �� ���ĺ��� dummy�� ����
			while ((lC = br.readLine()) != null) {
				dummy += (lC + "\r\n");
			}
			// dummy�� ����� �����͸� �ٽ� �����
			System.out.print("�����Ͻðڽ��ϱ�? 1:YES | 2:NO >> ");
			int yOrN = sc.nextInt();
			sc.nextLine();
			if ((yOrN == 1) || (String.valueOf(yOrN).equals("YES"))) {
				FileWriter fw = new FileWriter("list.txt");
				fw.write(dummy);
				fw.close();
				br.close();
				System.out.println("�����Ǿ����ϴ�.");
			} else if ((yOrN == 2) || (String.valueOf(yOrN).equals("NO"))) {
				System.out.println("����Ͽ����ϴ�.");
			}
			br.close();
		} catch (Exception e) {
			System.out.println("����");
		}
	}
}