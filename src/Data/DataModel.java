package Data;
import java.time.*;
import java.util.*;

public class DataModel {
	List<DataModel> ls = new LinkedList<>();
	int num;
	String title;
	String writer;
	String time;
	String content;

	public DataModel(int num, String title, String writer, String time) {
		this.num = num;
		this.title = title;
		this.writer = writer;
		this.time = time;
	}

	public int Num() {
		for (int i = 1; i < ls.size(); i++) {
			this.num = i;
		}
		return this.num;
	}
}

class DataList extends DataModel { // 목록
	public DataList(int num, String title, String writer, String time) {
		super(num, title, writer, time);
	}

	public String toString() {
		return " " + this.num + "	" + this.title + "	" + this.writer + "	" + this.time + "\n";
	}
}

class DataContent extends DataModel { // 내용
	public DataContent(int num, String title, String writer, String time, String content) {
		super(num, title, writer, time);
		this.content = content;
	}

	public String toString() {
		return "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + "\n" + this.num + ". [제목: " + this.title + "]	" + this.time + "\n" + "작성자: " + this.writer;
	}			
}