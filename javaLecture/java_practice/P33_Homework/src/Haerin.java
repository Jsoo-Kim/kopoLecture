import java.util.Random;

public class Haerin extends Base {
    private final GameState gameState;

    Haerin(GameState gameState) {
        this.gameState = gameState;
        this.name = "해린";
    }

    public int attack() {
        Random random = new Random();
        int damage = random.nextInt(41) + 20;
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
