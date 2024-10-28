package Pertemuan6;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieInputApp extends JFrame {

    private JTextField titleField;
    private JTextArea descriptionArea;
    private JRadioButton genreAction, genreDrama;
    private JCheckBox subtitlesCheck, englishCheck;
    private JComboBox<String> ratingCombo;
    private JList<String> languageList;
    private JSlider durationSlider;
    private JSpinner yearSpinner;
    private JTable movieTable;
    private DefaultTableModel tableModel;

    public MovieInputApp() {
        // Set up the frame
        setTitle("Movie Input Application");
        setSize(600, 400);  // Ukuran lebih sedang
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Navigate");
        JMenuItem mainMenu = new JMenuItem("Main Menu");
        menu.add(mainMenu);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Create form panel
        JPanel formPanel = new JPanel(new GridLayout(10, 2, 5, 5));

        // Title input
        formPanel.add(new JLabel("Title:"));
        titleField = new JTextField(15);
        formPanel.add(titleField);

        // Description input
        formPanel.add(new JLabel("Description:"));
        descriptionArea = new JTextArea(2, 15);  // Tinggi area teks dikurangi
        formPanel.add(new JScrollPane(descriptionArea));

        // Genre selection with radio buttons
        formPanel.add(new JLabel("Genre:"));
        genreAction = new JRadioButton("Action");
        genreDrama = new JRadioButton("Drama");
        ButtonGroup genreGroup = new ButtonGroup();
        genreGroup.add(genreAction);
        genreGroup.add(genreDrama);
        JPanel genrePanel = new JPanel();
        genrePanel.add(genreAction);
        genrePanel.add(genreDrama);
        formPanel.add(genrePanel);

        // Subtitles options with checkboxes
        formPanel.add(new JLabel("Options:"));
        subtitlesCheck = new JCheckBox("Subtitles");
        englishCheck = new JCheckBox("English");
        JPanel optionsPanel = new JPanel();
        optionsPanel.add(subtitlesCheck);
        optionsPanel.add(englishCheck);
        formPanel.add(optionsPanel);

        // Rating dropdown
        formPanel.add(new JLabel("Rating:"));
        ratingCombo = new JComboBox<>(new String[]{"*1", "*2", "*3", "*4", "*5"});
        formPanel.add(ratingCombo);

        // Language selection with JList
        formPanel.add(new JLabel("Languages:"));
        languageList = new JList<>(new String[]{"English", "French", "Spanish", "German", "Chinese"});
        languageList.setVisibleRowCount(3);  // Menampilkan hanya 3 baris
        languageList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        formPanel.add(new JScrollPane(languageList));

        // Duration slider
        formPanel.add(new JLabel("Duration (mins):"));
        durationSlider = new JSlider(30, 240, 90);
        durationSlider.setMajorTickSpacing(60);
        durationSlider.setPaintTicks(true);
        formPanel.add(durationSlider);

        // Year spinner
        formPanel.add(new JLabel("Year:"));
        yearSpinner = new JSpinner(new SpinnerNumberModel(2023, 1900, 2023, 1));
        formPanel.add(yearSpinner);

        // Submit button
        JButton submitButton = new JButton("Submit");
        formPanel.add(submitButton);

        // Table for movie data
        String[] columnNames = {"Title", "Description", "Genre", "Subtitles", "Rating", "Languages", "Duration", "Year"};
        tableModel = new DefaultTableModel(columnNames, 0);
        movieTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(movieTable);

        // Add components to frame
        add(formPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);

        // Action listener for submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Gather data from form
                String title = titleField.getText();
                String description = descriptionArea.getText();
                String genre = genreAction.isSelected() ? "Action" : genreDrama.isSelected() ? "Drama" : "N/A";
                String subtitles = subtitlesCheck.isSelected() ? "Yes" : "No";
                String rating = (String) ratingCombo.getSelectedItem();
                java.util.List<String> languages = languageList.getSelectedValuesList();
                String duration = String.valueOf(durationSlider.getValue());
                String year = yearSpinner.getValue().toString();

                // Add data to table
                tableModel.addRow(new Object[]{title, description, genre, subtitles, rating, languages, duration, year});
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MovieInputApp app = new MovieInputApp();
            app.setVisible(true);
        });
    }
}
