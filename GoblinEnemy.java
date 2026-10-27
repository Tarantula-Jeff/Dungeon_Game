package dungeontester;

import java.util.Random;

/** A combat encounter with randomised statistics. */
public class GoblinEnemy extends Encounter {
    private int health;
    private final int attack;
    private final int defence;
    private final int experienceReward;

    public GoblinEnemy(String name, int level, Random random) {
        super(name, 'G');
        health = 20 + (level * 7) + random.nextInt(9);
        attack = 5 + (level * 2) + random.nextInt(5);
        defence = 1 + level + random.nextInt(3);
        experienceReward = 8 + (level * 4);
    }

    public int getHealth() { return health; }
    public int getAttack() { return attack; }
    public int getDefence() { return defence; }
    public int getExperienceReward() { return experienceReward; }
    public void takeDamage(int damage) { health -= damage; }
    public boolean isDefeated() { return health <= 0; }
    @Override public String describe() { return getName() + " blocks your path. It watches you with " + health + " health remaining."; }
}
