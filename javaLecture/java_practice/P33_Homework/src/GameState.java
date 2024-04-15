import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameState {
    private List<Floor> floors;
    private List<Base> players;
    Scanner s = new Scanner(System.in);


    // 정해진 만큼의 floor 객체, player 객체 생성
    public void initializeGame(int maxFloor) {
        this.floors = new ArrayList<>();
        for (int i = 0; i < maxFloor; i++) {
            this.floors.add(new Floor("floor" + (i + 1)));
        }
        this.players = new ArrayList<>();
        // TODO
        // Player 클래스를 다 작성했다면 여기에 객체를 추가해야 합니다.
        // 객체 생성하고 추가하는 방법은 아래와 같습니다.
        // Base 객체이름 = new 클래스이름(this);
        // this.players.add(객체이름);
        Base minji = new Minji(this);
        Base hanni = new Hanni(this);
        Base haerin = new Haerin(this);
        players.add(minji);
        players.add(hanni);
        players.add(haerin);
        

        //게임 루프 시작
        System.out.println("\n던전으로 들어갑니다...\n");
        for (Floor floor : floors) {
            if (!isGameOver()) {
                System.out.println("-----" + floor.floorName + "-----");
                floor.runBattle(players);
                System.out.println(floor.floorName + " closed");
            }
        }
    }


    //player는 GameState에서 생성되기 때문에, player의 삭제 역시 GameState에서 처리함
    public void removePlayer(Base player) {
        players.remove(player);
    }


    // 플레이어가 사망할때마다 플레이어 객체도 삭제되기 때문에,
    // players가 비어있다는 것은 곧 모든 플레이어의 사망을 뜻함
    public boolean isGameOver() {
        return players.isEmpty(); // players 리스트에 더이상 player 객체가 남아있지 않다면 true
    }

}