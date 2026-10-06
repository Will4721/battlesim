package UI;


import interfaces.Unit;

import javax.swing.*;
import java.awt.*;
import java.util.List;



public class GraphicUI {

    private controller.BattleController controller;

    private JFrame frame;
    private JTextArea outputArea;
    private JComboBox<Unit> UnitDropdown;
    private UnitPanel unitPanel;



    public GraphicUI(controller.BattleController controller) {
        this.controller = controller;
        createUI();
    }

    private void createUI() {
        frame = new JFrame("Battle simulator");
        frame.setSize(600, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Tegnepanel
        unitPanel = new UnitPanel();
        frame.add(unitPanel, BorderLayout.CENTER);

        // Top panel (dropdown + info)
        JPanel topPanel = new JPanel();

        UnitDropdown = new JComboBox<>();
        refreshUnitDropdown();

        topPanel.add(new JLabel("Units:"));
        topPanel.add(UnitDropdown);

        frame.add(topPanel, BorderLayout.NORTH);

        // Output
        outputArea = new JTextArea(5, 40);
        outputArea.setEditable(false);
        frame.add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        // Knapper
        JPanel buttonPanel = new JPanel();


        JButton battleBtn = new JButton("Start Battle");


        buttonPanel.add(battleBtn);

        frame.add(buttonPanel, BorderLayout.PAGE_END);

        // Events
       /* feedBtn.addActionListener(e -> feedFish());
        waterBtn.addActionListener(e -> changeWater());
        healthBtn.addActionListener(e -> healthCheck());
        addBtn.addActionListener(e -> addFish());
        removeBtn.addActionListener(e -> removeFish());
        showWaterBtn.addActionListener(e -> showLastWater());
*/
        battleBtn.addActionListener(e -> startBattle());
        frame.setVisible(true);
    }

    // Opdater dropdown + tegning
    private void refreshUnitDropdown() {
        UnitDropdown.removeAllItems();
        for (Unit f : controller.getUnitList()) {
            UnitDropdown.addItem(f);
        }
        unitPanel.repaint();
    }

    private void startBattle() {
        Timer timer = new Timer(500, null);

        timer.addActionListener(e -> {

            if (controller.getUnitList().isEmpty() ||
                    controller.getUnitEList().isEmpty()) {

                ((Timer)e.getSource()).stop();

                if (controller.getUnitList().isEmpty()) {
                    print("You lose");
                } else {
                    print("You win");
                }

                return;
            }

            controller.simulateRound();
            refreshUnitDropdown();
            unitPanel.repaint();

            print("Battle tick...");
        });

        timer.start();
    }






    private void print(String msg) {
        outputArea.append(msg + "\n");
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(frame, msg, "Fejl", JOptionPane.ERROR_MESSAGE);
    }

    // Custom panel til tegning
    class UnitPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            List<Unit> players = controller.getUnitList();
            List<Unit> enemies = controller.getUnitEList();

            int y = 100;

// players (left side)
            int x = 50;
            for (Unit unit : players) {
                g.setColor(Color.BLUE);
                g.fillRect(x, y, 20, 20);

                g.setColor(Color.BLACK);
                g.drawString(unit.getType() + " (" + unit.getHealth() + ")", x, y - 5);

                x += 40;
            }

// enemies (right side)
            x = 400;
            for (Unit unit : enemies) {
                g.setColor(Color.RED);
                g.fillRect(x, y, 20, 20);

                g.setColor(Color.BLACK);
                g.drawString(unit.getType() + " (" + unit.getHealth() + ")", x, y - 5);

                x += 40;
            }
        }
    }
}