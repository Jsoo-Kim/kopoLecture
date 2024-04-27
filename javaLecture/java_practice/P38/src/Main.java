import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

// 파일 복사 프로그램
public class Main {
	public static void main(String[] args) {
		// 읽어들일 수 있는 스트림과 출력 가능한 스트림을 준비
		InputStream in = null;
		OutputStream out = null;
		
		try {
			// 역슬래시(\)는 이스케이프 문자로 사용되므로, 역슬래시 다음에 오는 문자는 특수 문자로 해석 ( \n은 줄 바꿈 문자, \t는 탭 문자 )
			// EX) "\a"는 잘못된 이스케이프 시퀀스 => 컴파일 오류 발생
			// 따라서 자바에서 역슬래시를 일반 문자로 사용하려면 \\ 이렇게 두 번 써 줘야 함
			in = new FileInputStream("C:\\Users\\SMT18\\Desktop\\하니.jpg");  
			out = new FileOutputStream("C:\\Users\\SMT18\\Desktop\\copy.jpg");
			
			while (true) {
				int data = in.read();
				if (data == -1) {
					break;
				}
				out.write(data);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try { in.close(); } catch (Exception e) {}
			}
			if (out != null) {
				try { out.close(); } catch (Exception e) {}
			}
		}
	}
}
