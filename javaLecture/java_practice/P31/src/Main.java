import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// 3 vs 2
		Base[] team1Arr = {new Character1(), new Character2(), new Character3()};
//		Base[] team2 = {new Character4(), new Character5()};
		
//		ArrayList<Base> team1 = new ArrayList<Base>();
//		team1.add(new Character1());
//		team1.add(new Character2());
//		team1.add(new Character3());
		ArrayList<Base> team1 = (ArrayList<Base>) Arrays.asList(team1Arr); // 배열을 ArrayList로
		Base[] a2 = (Base[]) team1.toArray(); // ArrayList를 배열로
		
		ArrayList<Base> team2 = new ArrayList<Base>();
		team2.add(new Character4());
		team2.add(new Character5());
		
		
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < 999999; i++) {
			System.out.println("턴 진행을 원하시면 엔터");
			scanner.nextLine();
			
			// team1 공격
			for (int j = 0; j < team2.size(); j++) {
				if (team2.get(j).isLive()) {
					team2.get(j).underAttack();
				}
			}
			
			// team2 공격
			for (int j = 0; j < team2.size(); j++) {
				if (team2.get(j).isLive()) {
					team2.get(j).underAttack();
				}
			}
			
			boolean isTeam1Live = false;
			for (int j = 0; j < team1.size(); j++) {
				isTeam1Live = isTeam1Live || team1.get(j).isLive();
			}
			
			boolean isTeam2Live = false;
			for (int j = 0; j < team1.size(); j++) {
				isTeam2Live = isTeam2Live || team2.get(j).isLive();
			}
			
			if (isTeam1Live && !isTeam2Live) {
				System.out.println("팀1 승리");
			} else if (!isTeam1Live && isTeam2Live) {
				System.out.println("팀2 승리");
			} else if (!isTeam1Live && !isTeam2Live) {
				System.out.println("공멸했습니다.");
				break;
			} 
		}
	}
}
