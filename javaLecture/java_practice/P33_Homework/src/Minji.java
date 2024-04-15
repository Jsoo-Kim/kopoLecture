import java.util.Random;

public class Minji extends Base {
    private final GameState gameState;

    Minji(GameState gameState) {
        this.gameState = gameState;
        this.name = "민지";
    }

    public int attack() {
        Random random = new Random();
        int damage = random.nextInt(61) + 20;
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