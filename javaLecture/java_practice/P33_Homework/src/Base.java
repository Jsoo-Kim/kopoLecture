
public abstract class Base {
    String name;
    int hp;

    Base(){
        this.name = "기본 캐릭터";
        this.hp = 100;
    }

    public abstract void underAttack(int damage);

    public boolean isLive() {
        if(this.hp<=0) {
            return false;
        }
        return true;
    }

}