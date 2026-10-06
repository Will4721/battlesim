package controller;

import interfaces.Battle;
import interfaces.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleController {
    private Battle battle;
    private List<Unit> unitList = new ArrayList<>();
    private List<Unit> unitEList = new ArrayList<>();
    public BattleController(Battle battle){this.battle = battle;}

    public void addUnit(Unit unit){
        unitList.add(unit);


    }
    public void addEUnit(Unit unit){
        unitEList.add(unit);


    }

    public void listPrint(){
        System.out.println(unitList.getFirst().getType()+ unitList.getLast().getType());
    }
    public List<Unit> getUnitList() {
        return unitList;
    }

    public List<Unit> getUnitEList() {
        return unitEList;
    }

    public void simulateRound() {
        Random random = new Random();

        List<Unit> players = getUnitList();
        List<Unit> enemies = getUnitEList();

        List<Unit> deadPlayers = new ArrayList<>();
        List<Unit> deadEnemies = new ArrayList<>();

        // players attack
        for (Unit player : players) {
            if (enemies.isEmpty()) break;

            Unit target = enemies.get(random.nextInt(enemies.size()));
            target.takeDamage(player.getDamage());

            if (target.getHealth() <= 0 && !deadEnemies.contains(target)) {
                deadEnemies.add(target);
            }
        }

        // enemies attack
        for (Unit enemy : enemies) {
            if (players.isEmpty()) break;

            Unit target = players.get(random.nextInt(players.size()));
            target.takeDamage(enemy.getDamage());

            if (target.getHealth() <= 0 && !deadPlayers.contains(target)) {
                deadPlayers.add(target);
            }
        }

        players.removeAll(deadPlayers);
        enemies.removeAll(deadEnemies);
    }
}
