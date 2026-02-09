package SemesterProject.src;

import javax.swing.*;
import java.awt.*;

public class Main {

    
    private Student[] students = new Student[100];
    private int count = 0;

    private final DataStore store = new DataStore("students.json");
    private final AuthService auth = new AuthService();

  
    private JFrame frame;
    private CardLayout cards;
    private JPanel cardPanel;

    
    private JLabel dashboardSummary;

    
    private JTextField idField;
    private JTextField nameField;
    private JLabel crudMsg;

    public static void main(String[] args) {
        new Main().startApp();
    }

    private void startApp() {
       
        students = store.load();
        count = computeCount();

        frame = new JFrame("Student App (Swing + JSON)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 300);

        cards = new CardLayout();
        cardPanel = new JPanel(cards);

        cardPanel.add(loginPanel(), "LOGIN");
        cardPanel.add(dashboardPanel(), "DASH");
        cardPanel.add(crudPanel(), "CRUD");

        frame.setContentPane(cardPanel);
        cards.show(cardPanel, "LOGIN");

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    
    private JPanel loginPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Login (admin/admin)");
        JTextField user = new JTextField();
        JPasswordField pass = new JPasswordField();
        JLabel msg = new JLabel(" ");

        JButton loginBtn = new JButton("Login");
        loginBtn.addActionListener(e -> {
            String u = user.getText().trim();
            String pw = new String(pass.getPassword()).trim();

            if (u.isEmpty() || pw.isEmpty()) {
                msg.setText("Enter username and password.");
                return;
            }

            if (auth.login(u, pw)) {
                updateDashboard();
                cards.show(cardPanel, "DASH");
            } else {
                msg.setText("Wrong login.");
            }
        });

        p.add(title);
        p.add(Box.createVerticalStrut(10));
        p.add(new JLabel("Username:"));
        p.add(user);
        p.add(Box.createVerticalStrut(10));
        p.add(new JLabel("Password:"));
        p.add(pass);
        p.add(Box.createVerticalStrut(10));
        p.add(loginBtn);
        p.add(Box.createVerticalStrut(10));
        p.add(msg);

        return p;
    }

    
    private JPanel dashboardPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Dashboard");
        dashboardSummary = new JLabel("Students saved: " + count);

        JButton crudBtn = new JButton("Go to CRUD");
        crudBtn.addActionListener(e -> {
            crudMsg.setText(" ");
            cards.show(cardPanel, "CRUD");
        });

        JButton saveBtn = new JButton("Save JSON");
        saveBtn.addActionListener(e -> {
            store.save(students);
            JOptionPane.showMessageDialog(frame, "Saved to students.json");
        });

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.addActionListener(e -> cards.show(cardPanel, "LOGIN"));

        p.add(title);
        p.add(Box.createVerticalStrut(10));
        p.add(dashboardSummary);
        p.add(Box.createVerticalStrut(10));
        p.add(crudBtn);
        p.add(Box.createVerticalStrut(10));
        p.add(saveBtn);
        p.add(Box.createVerticalStrut(10));
        p.add(logoutBtn);

        return p;
    }

    
    private JPanel crudPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("CRUD Students");

        idField = new JTextField();
        nameField = new JTextField();
        crudMsg = new JLabel(" ");

        JButton createBtn = new JButton("Create");
        JButton readBtn = new JButton("Read");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton backBtn = new JButton("Back");

        createBtn.addActionListener(e -> {
            crudMsg.setText(" ");
            Integer id = toInt(idField.getText().trim());
            String name = nameField.getText().trim();

            if (id == null) { crudMsg.setText("ID must be a number."); return; }
            if (name.isEmpty()) { crudMsg.setText("Name cannot be empty."); return; }
            if (findIndexById(id) != -1) { crudMsg.setText("ID already exists."); return; }

            if (count >= 100) { crudMsg.setText("Storage full (100)."); return; }

            students[count] = new Student(id, name);
            count++;
            updateDashboard();
            crudMsg.setText("Created.");
        });

        readBtn.addActionListener(e -> {
            crudMsg.setText(" ");
            Integer id = toInt(idField.getText().trim());
            if (id == null) { crudMsg.setText("Enter a valid ID."); return; }

            int idx = findIndexById(id);
            if (idx == -1) { crudMsg.setText("Not found."); return; }

            nameField.setText(students[idx].getName());
            crudMsg.setText("Loaded student.");
        });

        updateBtn.addActionListener(e -> {
            crudMsg.setText(" ");
            Integer id = toInt(idField.getText().trim());
            String name = nameField.getText().trim();

            if (id == null) { crudMsg.setText("Enter a valid ID."); return; }
            if (name.isEmpty()) { crudMsg.setText("Name cannot be empty."); return; }

            int idx = findIndexById(id);
            if (idx == -1) { crudMsg.setText("Not found."); return; }

            students[idx].setName(name);
            crudMsg.setText("Updated.");
        });

        deleteBtn.addActionListener(e -> {
            crudMsg.setText(" ");
            Integer id = toInt(idField.getText().trim());
            if (id == null) { crudMsg.setText("Enter a valid ID."); return; }

            int idx = findIndexById(id);
            if (idx == -1) { crudMsg.setText("Not found."); return; }

            
            for (int i = idx; i < count - 1; i++) {
                students[i] = students[i + 1];
            }
            students[count - 1] = null;
            count--;

            idField.setText("");
            nameField.setText("");
            updateDashboard();
            crudMsg.setText("Deleted.");
        });

        backBtn.addActionListener(e -> {
            updateDashboard();
            cards.show(cardPanel, "DASH");
        });

        JPanel btnRow = new JPanel(new GridLayout(1, 4, 8, 8));
        btnRow.add(createBtn);
        btnRow.add(readBtn);
        btnRow.add(updateBtn);
        btnRow.add(deleteBtn);

        p.add(title);
        p.add(Box.createVerticalStrut(10));
        p.add(new JLabel("ID:"));
        p.add(idField);
        p.add(Box.createVerticalStrut(10));
        p.add(new JLabel("Name:"));
        p.add(nameField);
        p.add(Box.createVerticalStrut(10));
        p.add(btnRow);
        p.add(Box.createVerticalStrut(10));
        p.add(backBtn);
        p.add(Box.createVerticalStrut(10));
        p.add(crudMsg);

        return p;
    }

    
    private void updateDashboard() {
        if (dashboardSummary != null) {
            dashboardSummary.setText("Students saved: " + count);
        }
    }

    private int computeCount() {
        int c = 0;
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null) c++;
        }
        return c;
    }

    private int findIndexById(int id) {
        for (int i = 0; i < count; i++) {
            if (students[i] != null && students[i].getId() == id) return i;
        }
        return -1;
    }

    private Integer toInt(String t) {
        try { return Integer.parseInt(t); }
        catch (Exception e) { return null; }
    }
}
