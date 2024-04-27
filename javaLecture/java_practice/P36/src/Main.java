import java.util.HashSet;

public class Main {
	public static void main(String[] args) {
		HashSet<SavingBox> savingBoxSet = new HashSet<SavingBox>();
		SavingBox pigBox = new SavingBox("돼지저금통", 10000);
		SavingBox rabbitBox = new SavingBox("토끼저금통", 20000);
		SavingBox pigBox2 = new SavingBox("돼지저금통", 20000);
		
		savingBoxSet.add(pigBox);
		savingBoxSet.add(rabbitBox);
		savingBoxSet.add(pigBox2);

		System.out.println(savingBoxSet.toString());
	}
}
