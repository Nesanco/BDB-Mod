package com.nesancodev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BDBMod {
    private static boolean enabled = false;
    private static ScheduledExecutorService scheduler;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BDBMod::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("BDB Mod");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JButton toggleButton = new JButton("Enable");
        JLabel statusLabel = new JLabel("Status: Disabled");
        JLabel madeByLabel = new JLabel("Made by Nesanco");

        toggleButton.addActionListener(e -> {
            if (enabled) {
                stopKeyPressing();
                toggleButton.setText("Enable");
                statusLabel.setText("Status: Disabled");
            } else {
                startKeyPressing();
                toggleButton.setText("Disable");
                statusLabel.setText("Status: Enabled");
            }
            enabled = !enabled;
        });

        frame.add(toggleButton);
        frame.add(statusLabel);
        frame.add(madeByLabel);
        frame.setVisible(true);
    }

    private static void startKeyPressing() {
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            try {
                Robot robot= new Robot();
                robot.keyPress(KeyEvent.VK_E);
                robot.keyRelease(KeyEvent.VK_E);
            } catch (AWTException ex) {
                ex.printStackTrace();
            }
        }, 0, 100, TimeUnit.MILLISECONDS);
    }

    private static void stopKeyPressing() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdownNow();
        }
    }
}