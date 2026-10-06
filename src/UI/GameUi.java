package UI;

import controller.BattleController;
import interfaces.*;
import interfaces.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import app.Main;
import simpleBattle.*;

public class GameUi {
    private BattleController controller;
    private Scanner sc = new Scanner(System.in);

    public GameUi(BattleController controller) {
        this.controller = controller;
    }

    public void start(){
        while (true) {
            System.out.println("\n=== Game set ===");
            System.out.println("1. set your army");
            System.out.println("2. set enemy army");
            System.out.println("3. view player type stats");
            System.out.println("4. Play");
            System.out.println("5. View current units");
            System.out.println("6. exit");

            int choice = readInt();

            try {
                switch (choice) {
                    case 1 -> armyset();
                    case 2 -> enemyset();
                    case 3 -> viewstats();
                    case 4 -> play(controller);
                    case 5 -> viewAmount();
                    case 6 -> { return; }
                    default -> printError("Ugyldigt valg");
                }
            } catch (Exception e) {
                printError(e.getMessage());
            }
        }

    }
    private void armyset(){
        System.out.println("how many swordmen do you want?");
        int type1 = readInt();
        for(int i = 0; type1 > i; i++){
            Unit unit = new simpleUnit("Swordsman",50,40,10);
          controller.addUnit(unit);
          System.out.println("new Swordsman made");
        }
        System.out.println("how many Archers do you want?");
        int type2 = readInt();
        for(int i = 0; type2 > i; i++){
            Unit unit2 = new simpleUnit("Archer",30,20,100);
            controller.addUnit(unit2);
            System.out.println("new Archer made");
        }
    }
    private void enemyset(){
        System.out.println("how many swordmen do you want?");
        int type1 = readInt();
        for(int i = 0; type1 > i; i++){
            Unit unit = new simpleEUnit("Swordsman",50,40,10);
            controller.addEUnit(unit);
            System.out.println("new Swordsman made");
        }
        System.out.println("how many Archers do you want?");
        int type2 = readInt();
        for(int i = 0; type2 > i; i++){
            Unit unit2 = new simpleEUnit("Archer",30,20,100);
            controller.addEUnit(unit2);
            System.out.println("new Archer made");
        }
    }
    private void viewstats(){
    System.out.println("Swordsman: \n Health: 50 \n Damage: 40 \n Range: 10");
        System.out.println("Archer: \n Health: 30 \n Damage: 20 \n Range: 100");
    }
    private void play(BattleController controller){
        Random random = new Random();
        while(!controller.getUnitList().isEmpty() && !controller.getUnitEList().isEmpty()){
            List<Unit> players = controller.getUnitList();
            List<Unit> enemies = controller.getUnitEList();

            List<Unit> deadPlayers = new ArrayList<>();
            List<Unit> deadEnemies = new ArrayList<>();

            try {
                Thread.sleep(500); // half a second between rounds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Unit playerUnit = controller.getUnitList().get(0);
            Unit enemyUnit = controller.getUnitEList().get(0);

            // attack
            for (Unit player : players) {
                if (enemies.isEmpty()) break;

                Unit target = enemies.get(random.nextInt(enemies.size()));
                target.takeDamage(player.getDamage());

                if (target.getHealth() <= 0 && !deadEnemies.contains(target)) {
                    deadEnemies.add(target);
                }
            }
            for (Unit enemy : enemies) {
                if (players.isEmpty()) break;

                Unit target = players.get(random.nextInt(players.size()));
                target.takeDamage(enemy.getDamage());

                if (target.getHealth() <= 0 && !deadPlayers.contains(target)) {
                    deadPlayers.add(target);
                }
            }


            // print status
            printStatus(controller);

            // remove dead enemy
            players.removeAll(deadPlayers);
            enemies.removeAll(deadEnemies);
        }

        // result
        if(controller.getUnitList().isEmpty()){
            System.out.println("You lose");
        } else {
            System.out.println("You win");
        }
    }
    private void printStatus(BattleController controller) {

        System.out.println("========== BATTLE STATUS ==========");

        System.out.println("\n-- PLAYERS --");
        for (Unit u : controller.getUnitList()) {
            System.out.println(
                    u.getType() + " | HP: " + u.getHealth()
            );
        }

        System.out.println("\n-- ENEMIES --");
        for (Unit u : controller.getUnitEList()) {
            System.out.println(
                    u.getType() + " | HP: " + u.getHealth()
            );
        }

        System.out.println("===================================\n");
    }

 private void viewAmount(){
        controller.listPrint();
 }


    private void printError(String message) {
        System.out.println(" Fejl: " + message);
    }

    private int readInt() {
        while (!sc.hasNextInt()) {
            printError("Indtast et tal!");
            sc.next();
        }
        int number = sc.nextInt();
        sc.nextLine();
        return number;
    }
}
