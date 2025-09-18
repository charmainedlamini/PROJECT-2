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
import RTA.dao.StudentDAO;
import java.io.File;
import javax.swing.*;

public class RoomApplication extends JFrame {

    private JPanel pnlSouth;
    private JButton btnUpload, btnproof, btnadd, btnApply;
    private JComboBox<String> cbostatus, cbotype;
    private JTextField txtSID, txtstart;
    private JLabel idpath, nsfaspath, proofpath;

    public RoomApplication() {
        super("Application Form");

        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        pnlSouth = new JPanel();
        pnlSouth.setLayout(new GridLayout(0, 2, 10, 10));

        JLabel ID = new JLabel("Student ID");
        txtSID = new JTextField(15);

        JLabel lblroom = new JLabel("Room Type: ");
        cbotype = new JComboBox<>(new String[]{"Select", "Single", "Double", "Sharing"});

        JLabel lbldate = new JLabel("Start Date");
        txtstart = new JTextField();

        JLabel lblIDD = new JLabel("ID Document ");
        btnUpload = new JButton("Choose File");
        idpath = new JLabel("No file chosen");

        JLabel lblproof = new JLabel("NSFAS Confirmation");
        btnadd = new JButton("Choose File");
        nsfaspath = new JLabel("No file chosen");

        JLabel lblnsfas = new JLabel("Application Proof");
        btnproof = new JButton("Choose File");
        proofpath = new JLabel("No file chosen");

        JLabel lblstatus = new JLabel("Funding Status");
        String[] status = {"Select", "Funded", "Not Funded", "Pending"};
        cbostatus = new JComboBox<>(status);
        // ImageIcon icon = createImage();
        btnApply = new JButton("Apply");

        pnlSouth.add(ID);
        pnlSouth.add(txtSID);

        pnlSouth.add(lblroom);
        pnlSouth.add(cbotype);

        pnlSouth.add(lbldate);
        pnlSouth.add(txtstart);

        pnlSouth.add(lblIDD);
        pnlSouth.add(btnUpload);
        pnlSouth.add(idpath);

        pnlSouth.add(lblproof);
        pnlSouth.add(btnadd);
        pnlSouth.add(nsfaspath);

        pnlSouth.add(lblnsfas);
        pnlSouth.add(btnproof);
        pnlSouth.add(proofpath);

        pnlSouth.add(lblstatus);
        pnlSouth.add(cbostatus);

        //btnSubmit.addActionListener((ActionListener) this);
        pnlSouth.add(btnApply);

        add(pnlSouth, BorderLayout.EAST);

        btnUpload.addActionListener(e -> chooseFile(idpath));
        btnadd.addActionListener(e -> chooseFile(nsfaspath));
        btnproof.addActionListener(e -> chooseFile(proofpath));

        btnApply.addActionListener(e -> {
            StudentDAO studentDAO = new StudentDAO();
            studentDAO.addStudent(txtSID.getText(), "TempFirst", "TempLast", "Student@example.com", "0123456789");
            JOptionPane.showMessageDialog(this, "Application Submitted & saved!");
            dispose();
            new ApplicationSubmitted().setVisible(true);

        });
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void chooseFile(JLabel targetLabel) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            targetLabel.setText(selectedFile.getAbsolutePath());
        } else {
            targetLabel.setText("No file chosen");
        }
    }

}
