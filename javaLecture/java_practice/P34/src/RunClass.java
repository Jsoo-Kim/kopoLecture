public class RunClass {
	public static void main(String[] args) {
		
		Test t1 = new Test();
//		t1.doAction();
		try {
			t1.doAction();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("오류났음");
		} finally {
			System.out.println();
		}
		
	}
}
