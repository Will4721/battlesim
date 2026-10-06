package simpleBattle;

public class simpleUnit implements interfaces.Unit {
    private String type;
    private int health;
    private int damage;
    private int range;

    public simpleUnit(String type, int health, int damage, int range){
        this.type = type;
        this.health = health;
        this.damage = damage;
        this.range = range;
    }

    @Override
    public String getType(){ return type; }

    @Override
    public int getHealth(){ return health; }

    @Override
    public int getDamage(){ return damage; }

    @Override
    public int getRange(){ return range; }

    @Override
    public void takeDamage(int dmg){
        health -= dmg;
    }
}
