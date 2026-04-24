package controller;

import file.FileLogger;
import interfaces.Battle;
import interfaces.Unit;

import java.util.ArrayList;
import java.util.List;

public class BattleController {
    private Battle battle;
    private List<Unit> unitList = new ArrayList<>();
    private List<Unit> unitEList = new ArrayList<>();
    public BattleController(Battle battle){this.battle = battle;}

    public void addUnit(Unit unit){
        unitList.add(unit);
        FileLogger.log("Units added:"+ unit.getType());

    }
    public void addEUnit(Unit unit){
        unitEList.add(unit);
        FileLogger.log("Units added:"+ unit.getType());

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
}
