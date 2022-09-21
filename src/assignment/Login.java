package assignment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.*;

/**
 *
 * @Vallerie
 */
public class Login implements ActionListener {

    JFrame frame = new JFrame();
    JLabel userIDLabel = new JLabel("User ID  :");
    JLabel userPasswordLabel = new JLabel("Password :");
    JLabel successMessage = new JLabel();
    JLabel messageLabel = new JLabel();
    JLabel headerLabel = new JLabel("ABC Cafe");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    Scanner input = new Scanner(System.in);
    HashMap<String, String> loginInfo = new HashMap<String, String>();
    private boolean loginSucess;
    private String id;
    private String password;

    public Login(Employee[] empDetails) {
        for (Employee empDetail : empDetails) {
            loginInfo.put(empDetail.getEmpID(), empDetail.getPassword());
        }

        headerLabel.setBounds(170, 40, 150, 50);
        headerLabel.setFont(new Font("Serif", Font.BOLD, 30));

        userIDLabel.setBounds(90, 120, 75, 25);
        userPasswordLabel.setBounds(90, 170, 75, 25);

        messageLabel.setBounds(150, 270, 250, 35);
        messageLabel.setFont(new Font("Serif", Font.ITALIC, 18));

        successMessage.setBounds(170, 270, 250, 35);
        successMessage.setFont(new Font("Serif", Font.ITALIC, 18));

        userIDField.setBounds(165, 120, 200, 25);
        userPasswordField.setBounds(165, 170, 200, 25);

        loginButton.setBounds(130, 220, 100, 25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        resetButton.setBounds(255, 220, 100, 25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        frame.setTitle("ABC Cafe POS Sytem - Login");
        frame.add(headerLabel);
        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(messageLabel);
        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(loginButton);
        frame.add(resetButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 420);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == resetButton) {
            userIDField.setText("");
            userPasswordField.setText("");
        }

        if (e.getSource() == loginButton) {
            id = userIDField.getText();
            password = String.valueOf(userPasswordField.getPassword());

            if (loginInfo.containsKey(id.toUpperCase())) {
                if (loginInfo.get(id.toUpperCase()).equals(password)) {
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Login successful");
                    loginSucess = true;
                    this.id = id;

                } else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Wrong Password entered");
                }
            } else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Staff ID does not exist");
            }
        }
    }

    public boolean isLoginSucess() {
        return loginSucess;
    }

    public String getId() {
        return id;
    }
}
