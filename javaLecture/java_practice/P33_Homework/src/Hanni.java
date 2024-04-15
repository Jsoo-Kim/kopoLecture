import java.util.Random;

public class Hanni extends Base {
    private final GameState gameState;

    Hanni(GameState gameState) {
        this.gameState = gameState;
        this.name = "하니";
    }

    public int attack() {
        Random random = new Random();
        int damage = random.nextInt(51) + 20;
        return damage;
    }
    
    @Override
    public void underAttack(int damage) {
        this.hp -= damage;
        if (!this.isLive()) {
            gameState.removePlayer(this);
            System.out.println(this.name + "가 사망했습니다.");
        }
    }
}
