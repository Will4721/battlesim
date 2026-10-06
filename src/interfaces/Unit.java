package interfaces;

public interface Unit {
    String getType();
    int getHealth();
    int getDamage();
    void takeDamage(int dmg);
    int getRange();
}
