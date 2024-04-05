
//콜백 인터페이스
interface Callback {
 void onCallback();
}


//실행자 클래스
class Executor {
 // 콜백을 받아 실행하는 메서드
 void executeCallback(Callback callback) {
     // 콜백 함수 호출
	 System.out.println("콜백 호출 전 자기 일 하는 중");
     callback.onCallback(); // 콜백 호출
 }
}

//호출자 클래스
class Caller implements Callback {
 // 콜백 함수 구현
 @Override
 public void onCallback() {
     System.out.println("콜백 함수가 호출되었습니다!");
 }
}

public class CallBackEx {

	public static void main(String[] args) {
		Executor executor = new Executor(); // 실행자 객체 생성
        Caller caller = new Caller(); // 호출자 객체 생성

        // 실행자에게 호출자의 콜백을 등록
        executor.executeCallback(caller);
        
        // 실행자가 자기 일 다 하면 콜해줌! 전화번호(알려줄 곳)가 caller 

	}

}

