package javaLecture.java_practice.P33_Homework;

public class Player extends Base {
    private GameState gameState;


    //TODO
    //클래스 명은 Player 대신 자유롭게 변경해도 괜찮습니다. 자기 이름으로 하신다던가
    //캐릭터의 이름을 새롭게 설정하시고, 공격시 이름이 출력되게 해 주세요.
    //enemy에게 공격할수있는 attack 메소드를 하나 이상, 공격을 받았을때 실행되는 underAttack 메소드를 하나 작성해야 합니다.
    //underAttack 메소드 안에는 캐릭터의 hp가 0이 되었을 때 객체 자기자신을 삭제하도록 하는 메소드가 포함되어야 합니다.
    public Player(GameState gameState) {
        this.gameState = gameState;
    }


    @Override
    public void underAttack(int damage) {
        this.hp -= damage;
        if (!super.isLive()) {
            this.gameState.removePlayer(this);
            System.out.println(this.name + "이(가) 사망했습니다.");
        }
    }

}