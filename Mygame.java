import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;

public class Mygame extends JFrame implements ActionListener {
    JLabel heading, clockLabel;
    Font font = new Font("Arial", Font.BOLD, 40);
    JPanel mainPanel;

    JButton btns[] = new JButton[9];

    // Game instance Variable .. if {2 ,2, 2 ,2, 2 ,2, 2 ,2 ,2} Means the0re is no
    // chances play by the player.
    int gameChances[] = { 2, 2, 2, 2, 2, 2, 2, 2, 2 };
    int activePlayer = 0;

    int wps[][] = {
            { 0, 1, 2 },
            { 3, 4, 4 },
            { 6, 7, 8 },
            { 0, 3, 6 },
            { 1, 4, 7 },
            { 2, 5, 8 },
            { 0, 4, 8 },
            { 2, 4, 6 }
    };
    int winner = 2;

    Mygame() {
        System.out.println("Creating instance of game");
        setTitle("My Tic Tac Toe Game .. ");
        setSize(750, 750);
        ImageIcon icon = new ImageIcon("C:\\Users\\Hp\\Desktop\\img\\images.png");

        setIconImage(icon.getImage());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // For closing Program . .
        createGUI();
        setVisible(true);

    }

    private void createGUI() {

        this.getContentPane().setBackground(Color.decode("#2196f3"));
        this.setLayout(new BorderLayout());
        // north heading
        heading = new JLabel("Tic tac toe");
        // heading.setIcon(new ImageIcon("C:\\Users\\Hp\\Desktop\\img\\images.png"));
        heading.setFont(font);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setForeground(Color.WHITE);
        heading.setHorizontalTextPosition(SwingConstants.CENTER);
        heading.setVerticalTextPosition(SwingConstants.CENTER);
        this.add(heading, BorderLayout.NORTH);

        // Creating object of Jlabel for clock
        clockLabel = new JLabel("Clock");
        clockLabel.setFont(font);
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clockLabel.setForeground(Color.WHITE);
        this.add(clockLabel, BorderLayout.SOUTH);

        Thread t = new Thread() {
            public void run() {
                try {
                    while (true) {
                        String datetime = new Date().toLocaleString();
                        clockLabel.setText(datetime);
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();

        mainPanel = new JPanel();

        mainPanel.setLayout(new GridLayout(3, 3));

        for (int i = 1; i <= 9; i++) {
            JButton btn = new JButton();
            // btn.setIcon(new ImageIcon("C:/Users/Hp/Desktop/img/zero.jpg"));
            btn.setBackground(Color.decode("#90caf9"));
            btn.setFont(font);
            mainPanel.add(btn);
            btns[i - 1] = btn;
            btn.addActionListener(this);
            btn.setName(String.valueOf(i - 1));
        }
        this.add(mainPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Typecasting
        JButton currentButton = (JButton) e.getSource(); // "This also use frame and listener"
        String nameStr = currentButton.getName();
        // System.out.println(nameStr); //To get number on a screen. At particular
        // Blocks.

        int name = Integer.parseInt(nameStr.trim()); // String to Integer..
        if (gameChances[name] == 2) {
            if (activePlayer == 1) {
                currentButton.setIcon(new ImageIcon("C:/Users/Hp/Pictures/Screenshots/Screenshot 2024-09-10 131540.png"));
                gameChances[name] = activePlayer;
                activePlayer = 0;
            } else {
                currentButton.setIcon(new ImageIcon("C:/Users/Hp/Pictures/Screenshots/Screenshot 2024-11-18 181417.png"));
                gameChances[name] = activePlayer;
                activePlayer = 1;
            }
            // Find the winner . . . . .
            for (int[] temp : wps) { // Bade Wale array me ek chota array present h "int[]"(chota array) temp
                                     // (Variable) kisme rakha h "wps"
                if ((gameChances[temp[0]] == gameChances[temp[1]]) && gameChances[temp[1]] == gameChances[temp[2]]
                        && gameChances[temp[2]] != 2) {

                    winner = gameChances[temp[0]];
                    JOptionPane.showMessageDialog(null, "Player " + winner + "has won the game. .");
                    int i = JOptionPane.showConfirmDialog(this, "Do you want to play more ?");
                    if (i == 0) {
                        this.setVisible(false);
                        new Mygame();
                    } else if (i == 1) {
                        System.exit(23423);
                    } else {

                    }
                    System.out.println(i);
                    break;
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Postion already occupied");
        }

    }
}
