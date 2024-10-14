package Pertemuan2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelloWorldSwing {

    // Komponen yang akan digunakan di berbagai tempat
    private static JList<String> savingsList;
    private static JSpinner transactionSpinner;
    private static JSpinner dobSpinner;
    private static JPasswordField passwordField;
    private static JPasswordField confirmPasswordField;
    private static JTextArea outputArea;

    public static void createAndShowGUI() {
        // Membuat frame baru dengan judul
        JFrame frame = new JFrame("Bank Account Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout utama
        frame.setLayout(new BorderLayout());

        // Panel untuk komponen input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2, 10, 10));

        // Menambahkan JList untuk pilihan jenis tabungan
        JLabel savingsLabel = new JLabel("Jenis Tabungan:");
        String[] savingsOptions = {"Tabungan Reguler", "Tabungan Pendidikan", "Tabungan Bisnis", "Tabungan Berjangka"};
        savingsList = new JList<>(savingsOptions);
        savingsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        savingsList.setSelectedIndex(0);
        JScrollPane listScroller = new JScrollPane(savingsList);

        // Menambahkan JSpinner untuk frekuensi transaksi per bulan
        JLabel transactionLabel = new JLabel("Frekuensi Transaksi per Bulan:");
        SpinnerNumberModel transactionModel = new SpinnerNumberModel(1, 1, 100, 1);
        transactionSpinner = new JSpinner(transactionModel);

        // Menambahkan JSpinner untuk input tanggal lahir
        JLabel dobLabel = new JLabel("Tanggal Lahir:");
        SpinnerDateModel dobModel = new SpinnerDateModel();
        dobSpinner = new JSpinner(dobModel);
        dobSpinner.setEditor(new JSpinner.DateEditor(dobSpinner, "dd/MM/yyyy"));

        // Menambahkan JPasswordField untuk input password dan konfirmasi password
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JLabel confirmPasswordLabel = new JLabel("Konfirmasi Password:");
        confirmPasswordField = new JPasswordField();

        // Area output
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        JScrollPane outputScrollPane = new JScrollPane(outputArea);

        // Menambahkan komponen ke panel input
        inputPanel.add(savingsLabel);
        inputPanel.add(listScroller);
        inputPanel.add(transactionLabel);
        inputPanel.add(transactionSpinner);
        inputPanel.add(dobLabel);
        inputPanel.add(dobSpinner);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);
        inputPanel.add(confirmPasswordLabel);
        inputPanel.add(confirmPasswordField);

        // Panel untuk tombol
        JPanel buttonPanel = new JPanel();
        JButton submitButton = new JButton("Submit");
        JButton resetButton = new JButton("Reset");

        // Menambahkan menu bar dengan menu Reset dan Exit
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem resetMenuItem = new JMenuItem("Reset");
        JMenuItem exitMenuItem = new JMenuItem("Exit");

        fileMenu.add(resetMenuItem);
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        // Aksi untuk tombol submit
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSavings = savingsList.getSelectedValue();
                int transactionCount = (int) transactionSpinner.getValue();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
                String dob = dobSpinner.getValue().toString();

                // Mengecek apakah password dan konfirmasi password cocok
                if (password.equals(confirmPassword)) {
                    outputArea.setText("Jenis Tabungan: " + selectedSavings + "\n"
                            + "Frekuensi Transaksi: " + transactionCount + " kali per bulan\n"
                            + "Tanggal Lahir: " + dob + "\n"
                            + "Password cocok!");
                } else {
                    outputArea.setText("Password dan Konfirmasi Password tidak cocok!");
                }
            }
        });

        // Aksi untuk tombol reset
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });

        // Aksi untuk menu Reset
        resetMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });

        // Aksi untuk menu Exit
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Menambahkan tombol ke panel tombol
        buttonPanel.add(submitButton);
        buttonPanel.add(resetButton);

        // Menambahkan komponen ke frame
        frame.setJMenuBar(menuBar);
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(outputScrollPane, BorderLayout.SOUTH);

        // Mengatur ukuran frame secara otomatis
        frame.pack();
        frame.setVisible(true);
    }

    // Fungsi untuk reset form
    private static void resetForm() {
        savingsList.setSelectedIndex(0);
        transactionSpinner.setValue(1);
        passwordField.setText("");
        confirmPasswordField.setText("");
        outputArea.setText("");
    }

    public static void main(String[] args) {
        // Menjalankan GUI di thread terpisah
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}