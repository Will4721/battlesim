package simpleBattle;

import interfaces.*;


public class simpleBattleFactory implements interfaces.battleFactory {
    @Override
    public Battle createBattle(){return new simpleBattle();}

}
