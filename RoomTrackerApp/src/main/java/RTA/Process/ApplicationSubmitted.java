/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RTA.Process;

/**
 *
 * @author musot
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import za.ac.cput.roomtrackerapp.DashboardFrame;

public class ApplicationSubmitted extends JFrame {

    private JLabel lblconfirm;
    private JButton btnreturn;

    public ApplicationSubmitted() {
        super("Application Submitted");

        setLayout(new BorderLayout(10, 10));

        lblconfirm = new JLabel("Application has been successfuly submitted!");

        btnreturn = new JButton("Return to Dashboard");
        btnreturn.addActionListener((ActionEvent e) -> {

            dispose();
            new DashboardFrame();
        });

        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        centerPanel.add(lblconfirm);
        centerPanel.add(btnreturn);

        add(centerPanel, BorderLayout.CENTER);

        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

    }

}
