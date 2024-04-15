
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Floor {
    String floorName;
    private List<Enemy> enemies;
    Scanner s = new Scanner(System.in);

    Floor(String name) {
        this.floorName = name;
    }

    // GameState에서 실행되는 메서드, enemy 객체들을 생성하고 engageInBattle 메서드를 실행
    public void runBattle(List<Base> players) {
        this.enemies = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            this.enemies.add(new Enemy(this, i));
        }
        System.out.println("전투를 시작합니다.");
        engageInBattle(players, enemies);
    }


    // 플레이어 혹은 몬스터가 전멸할때까지 번갈아가면서 공격을 주고받음
    private void engageInBattle(List<Base> players, List<Enemy> enemies) {
        int cnt = 0;
//        int damage = 0;
        //플레이어 혹은 적의 리스트가 모두 사라질때까지 while문
        while (!(players.isEmpty() || enemies.isEmpty())) {
            System.out.println("-----" + (cnt + 1) + "턴 시작-----");
            // enemy 객체들의 리스트 출력하는 for문이고, 필요에 따라 copy paste 하시거나 옮기거나 삭제하셔도 됩니다.
            for (Enemy enemy : enemies) {
                System.out.println(enemies.indexOf(enemy) + ". " + enemy.name);
            }
            //TODO
            //플레이어가 enemy를 공격하는 로직을 작성해주세요.
            //enemy 객체는 복수 존재합니다. 어떤 객체를 공격할지 결정하는 단계도 작성하셔야 합니다.
            //while문이 한번 돌때마다 한번 attack 메소드를 실행하도록 하면 됩니다.
            //서로다른 attack 메소드를 여러개(atk01, atk02...) 작성하셨다면, 다양한 방식으로 공격해도 괜찮습니다.
            for (Base player : players) {
                if (!enemies.isEmpty()) {
                    int damage = 0;
                    Enemy targetEnemy = chooseEnemy(enemies);

                    if (player instanceof Minji) {
                        damage = ((Minji) player).attack();
                        targetEnemy.underAttack(damage);
                        System.out.println(player.name + "가 " + targetEnemy.name + "에게 " + damage + "데미지를 주었습니다.");
                        System.out.println(targetEnemy.name + "의 남은 hp: " + targetEnemy.hp);
                    } else if (player instanceof Hanni) {
                    	damage = ((Hanni) player).attack();
                    	targetEnemy.underAttack(damage);
                    	System.out.println(player.name + "가 " + targetEnemy.name + "에게 " + damage + "데미지를 주었습니다.");
                    	System.out.println(targetEnemy.name + "의 남은 hp: " + targetEnemy.hp);                    	
                    } else if (player instanceof Haerin) {
	                	damage = ((Haerin) player).attack();
	                	targetEnemy.underAttack(damage);
	                	System.out.println(player.name + "이 " + targetEnemy.name + "에게 " + damage + "데미지를 주었습니다.");
	                	System.out.println(targetEnemy.name + "의 남은 hp: " + targetEnemy.hp);                    	
	                }

                }
            }


            // 모든 enemy 객체들이 각자 1번씩 플레이어를 공격하는 for문
            for (Enemy enemy : enemies) {
                if (!players.isEmpty()) {
                    int damage = 0;
                    Base targetPlayer = chooseTargetPlayer(players);
                    damage = enemy.attack();
                    System.out.println(enemy.name + "가 " + targetPlayer.name + "에게 " + damage + "데미지를 주었습니다.");
                    System.out.println(targetPlayer.name + "의 hp " + (targetPlayer.hp - damage));
                    targetPlayer.underAttack(damage);
                }
            }
            cnt++;
        }

    }


    // enemies는 다음과 같은 조건에 따라 목표 플레이어를 결정함
    // 1. 현재 hp가 가장 낮은 플레이어 우선 2. hp가 동일하다면, 인덱스 번호가 높은 순서를 우선
    private Base chooseTargetPlayer(List<Base> players) {
        Base targetPlayer = players.get(0);
        for (Base player : players) {
            if (player.hp < targetPlayer.hp) {
                targetPlayer = player;
            } else if (player.hp == targetPlayer.hp && players.indexOf(player) > players.indexOf(targetPlayer)) {
                targetPlayer = player;
            }
        }
        return targetPlayer;
    }

    private Enemy chooseEnemy(List<Enemy> enemies) {
        Enemy targetEnemy = enemies.get(0);
        for (Enemy enemy : enemies) {
            if (enemy.hp < targetEnemy.hp) {
                targetEnemy = enemy;
            } else if (enemy.hp == targetEnemy.hp && enemies.indexOf(enemy) < enemies.indexOf(targetEnemy)) {
                targetEnemy = enemy;
            }
        }
        return targetEnemy;
    }

    // enemy는 floor에서 생성되기 때문에, enemy 객체의 삭제 역시 floor에서 처리함
    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

}