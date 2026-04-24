package app;

import UI.GameUi;
import controller.BattleController;
import interfaces.Battle;
import interfaces.Unit;
import simpleBattle.simpleBattleFactory;

public class Main {
    public static void main(String[] args) {
        simpleBattleFactory factory = new simpleBattleFactory();

        Battle battle = factory.createBattle();

        BattleController controller = new BattleController(battle);

        GameUi game = new GameUi(controller);

        game.start();
    }
    }
