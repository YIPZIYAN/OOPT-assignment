/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Yip Zi Yan
 */
public class QRcode extends JFrame {

    private ImageIcon image;
    private JLabel label;

    QRcode() {
        setLayout(new FlowLayout());
        image = new ImageIcon(getClass().getResource("../tng.jpeg"));
        label = new JLabel(image);
        add(label);
    }

    public static QRcode displayQRcode() {
        QRcode gui = new QRcode();
        gui.setVisible(true);
        gui.pack();
        gui.setTitle("Payment");  
        return gui;
    }
    
    public static void closeQRcode(QRcode gui){
        gui.setVisible(false);
    }

}
