package Pertemuan1;

import javax.swing.*;

public class HelloWorldSwing {

    public static void createAndShowGUI() {
        // Membuat frame baru dengan judul
        JFrame frame = new JFrame("My First Project With Java Swing");

        // Menentukan agar program berhenti ketika frame ditutup
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Membuat label dengan teks "Hello, Naufal Zul Faza"
        JLabel label = new JLabel("Hello, Naufal Zul Faza");

        // Menambahkan label ke dalam konten frame
        frame.getContentPane().add(label);

        // Mengatur ukuran frame secara otomatis
        frame.pack();

        // Menampilkan frame
        frame.setVisible(true);
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
