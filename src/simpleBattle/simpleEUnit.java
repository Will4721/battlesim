package simpleBattle;

public class simpleEUnit implements interfaces.Unit{
    private String type;
    private int Health;
    private int damage;
    private int range;
    public simpleEUnit(String type,int Health, int damage,int range){this.type=type; this.Health=Health;this.damage=damage;this.range=range;}

    @Override
    public String getType(){return type;}
}
