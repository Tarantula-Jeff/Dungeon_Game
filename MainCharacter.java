package dungeontester;

/** Player state and progression. */
public class MainCharacter extends Encounter {
    private int level = 1;
    private int experience = 0;
    private int maxHealth = 60;
    private int health = maxHealth;
    private int attack = 13;
    private int defence = 4;
    private int potions = 1;

    public MainCharacter(String name) { super(name, 'P'); }
    public int getLevel() { return level; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getAttack() { return attack; }
    public int getDefence() { return defence; }
    public int getExperience() { return experience; }
    public int getPotions() { return potions; }
    public boolean isAlive() { return health > 0; }
    public void takeDamage(int damage) { health = Math.max(0, health - damage); }
    public void heal(int amount) { health = Math.min(maxHealth, health + amount); }
    public void addPotion() { potions++; }
    public boolean usePotion() { if (potions == 0) return false; potions--; heal(25); return true; }
    public boolean gainExperience(int amount) {
        experience += amount;
        int required = level * 20;
        if (experience < required) return false;
        experience -= required;
        level++;
        maxHealth += 12;
        attack += 3;
        defence += 1;
        health = maxHealth;
        return true;
    }
    public String statistics() {
        return String.format("%s — Level %d | Health %d/%d | Attack %d | Defence %d | XP %d/%d | Potions %d", getName(), level, health, maxHealth, attack, defence, experience, level * 20, potions);
    }
    @Override public String describe() { return "The adventurer " + getName() + "."; }
}
