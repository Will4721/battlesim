package UI;

import controller.BattleController;
import interfaces.*;
import interfaces.Unit;
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
        Unit unit = new simpleUnit("Swordsman",50,40,10);
        for(int i = 0; type1 > i; i++){
          controller.addUnit(unit);
          System.out.println("new Swordsman made");
        }
        System.out.println("how many Archers do you want?");
        int type2 = readInt();
        Unit unit2 = new simpleUnit("Archer",30,20,100);
        for(int i = 0; type2 >= i; i++){
            controller.addUnit(unit2);
            System.out.println("new Archer made");
        }
    }
    private void enemyset(){
        System.out.println("how many swordmen do you want?");
        int type1 = readInt();
        Unit unit = new simpleEUnit("Swordsman",50,40,10);
        for(int i = 0; type1 > i; i++){
            controller.addEUnit(unit);
            System.out.println("new Swordsman made");
        }
        System.out.println("how many Archers do you want?");
        int type2 = readInt();
        Unit unit2 = new simpleEUnit("Archer",30,20,100);
        for(int i = 0; type2 >= i; i++){
            controller.addEUnit(unit2);
            System.out.println("new Archer made");
        }
    }
    private void viewstats(){

    }
private void play(BattleController controller){
if(controller.getUnitList().size() > controller.getUnitEList().size()){
System.out.println("you win");
}else{
    System.out.println("you loose");
}
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
