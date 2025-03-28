/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GUI {
    private JFrame frame;
    private JTextField usernameField;
    private JTextArea outputArea;
    private PSNUsers psnUsers;

    public GUI(PSNUsers psnUsers) {
        this.psnUsers = psnUsers;
        frame = new JFrame("PSN User Manager");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        usernameField = new JTextField(15);
        JButton addButton = new JButton("Add User");
        JButton removeButton = new JButton("Remove User");
        JButton searchButton = new JButton("Search User");
        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(searchButton);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        addButton.addActionListener(e -> addUser());
        removeButton.addActionListener(e -> removeUser());
        searchButton.addActionListener(e -> searchUser());

        frame.setVisible(true);
    }

    private void addUser() {
        String username = usernameField.getText().trim();
        if (!username.isEmpty()) {
            try {
                psnUsers.addUser(username);
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            outputArea.append("User added: " + username + "\n");
        }
    }

    private void removeUser() {
        String username = usernameField.getText().trim();
        if (!username.isEmpty()) {
            try {
                psnUsers.deactivateUser(username);
            } catch (IOException ex) {
                System.err.println("error");
            }
            outputArea.append("User removed: " + username + "\n");
        }
    }

    private void searchUser() {
        String username = usernameField.getText().trim();
        if (!username.isEmpty()) {
            outputArea.append("User Info:\n");
            try {
                psnUsers.playerInfo(username);
            } catch (IOException ex) {
                System.err.println("error");
            }
        }
    }

}
