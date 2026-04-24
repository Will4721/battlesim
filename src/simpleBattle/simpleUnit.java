package simpleBattle;

public class simpleUnit implements interfaces.Unit {
    private String type;
    private int Health;
    private int damage;
    private int range;
    public simpleUnit(String type,int Health, int damage,int range){this.type=type; this.Health=Health;this.damage=damage;this.range=range;}

    @Override
    public String getType(){return type;}
   /* @Override
    public int getHealth(){return Health;}
    @Override
    public int getDamage(){return damage;}
    @Override
    public int getRange(){return range;}
    */
}
