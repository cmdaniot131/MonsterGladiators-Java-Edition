import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.io.File;

//Acts as the main menu of the game

public class MainMenu {

    JPanel landingPage;
    ButtonLabel Logo, ArenaB, HTPB, ExitB;
    JLabel ButtonInfo;
    MainMenu mainMenu;
    HTP HTPpage;
    BattleCenter BCpage;
    private Main main;

    private void playSound(String soundFile) {
        try {
            // Use a static method of AudioSystem to get a Clip
            Clip clip = AudioSystem.getClip();

            // Open an audio input stream to the sound file
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(soundFile));

            // Open the clip and start it
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public MainMenu(Main main) {

        this.main = main;

        landingPage = new JPanel();
        landingPage.setLayout(null);
        landingPage.setOpaque(false);

        Logo = new ButtonLabel(1);
        ArenaB = new ButtonLabel(2);
        HTPB = new ButtonLabel(3);
        ExitB = new ButtonLabel(4);

        ButtonInfo = new JLabel("> Press any button to proceed... <",JLabel.CENTER);
        ButtonInfo.setFont(new java.awt.Font("fs gravity Regular", Font.BOLD,30));
        ButtonInfo.setForeground(Color.WHITE);

        Logo.Button.setBounds(430,0,500,450);
        ArenaB.Button.setBounds(200,430,265,140);
        HTPB.Button.setBounds(505,430,265,140);
        ExitB.Button.setBounds(800,430,265,140);
        ButtonInfo.setBounds(0,600,1260,40);

        landingPage.add(Logo.Button);
        landingPage.add(ArenaB.Button);
        landingPage.add(HTPB.Button);
        landingPage.add(ExitB.Button);
        landingPage.add(ButtonInfo);

        ArenaB.Button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                playSound("./Sound/OsuHitSound.wav");
                ButtonInfo.setText("> Entering Arena... <");
                main.BCpage.BCpanel.setVisible(true);
                landingPage.setVisible(false);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                ButtonInfo.setText("> Entering Arena... <");
                ArenaB.Button.setIcon(new ImageIcon("./ButtonsV2/ButtonHover[PLAY].png"));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                ButtonInfo.setText("> Entering Arena... <");
                ArenaB.Button.setIcon(new ImageIcon("./ButtonsV2/Button[PLAY].png"));
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                ButtonInfo.setText("> Enter the Arena? <");
                ArenaB.Button.setIcon(new ImageIcon("./ButtonsV2/ButtonHover[PLAY].png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ButtonInfo.setText("> Press any button to proceed... <");
                ArenaB.Button.setIcon(new ImageIcon("./ButtonsV2/Button[PLAY].png"));
            }
        });

        HTPB.Button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                playSound("./Sound/OsuHitSound.wav");
                ButtonInfo.setText("> Entering [How to Play]... <");
                main.HTPpage.HTPpanel.setVisible(true);
                landingPage.setVisible(false);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                ButtonInfo.setText("> Entering [How to Play]... <");
                HTPB.Button.setIcon(new ImageIcon("./ButtonsV2/ButtonHover[HOWTOPLAY].png"));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                ButtonInfo.setText("> Entering [How to Play]... <");
                HTPB.Button.setIcon(new ImageIcon("./ButtonsV2/Button[HOWTOPLAY].png"));
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                HTPB.Button.setIcon(new ImageIcon("./ButtonsV2/ButtonHover[HOWTOPLAY].png"));
                ButtonInfo.setText("> View [How to Play]? <");
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ButtonInfo.setText("> Press any button to proceed... <");
                HTPB.Button.setIcon(new ImageIcon("./ButtonsV2/Button[HOWTOPLAY].png"));
            }
        });

        ExitB.Button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                playSound("./Sound/OsuHitSound.wav");
                ButtonInfo.setText("> Exiting Game... <");
                UIManager.put("OptionPane.messageFont", new Font("fs gravity Regular", Font.BOLD, 14));
                UIManager.put("OptionPane.buttonFont", new Font("fs gravity Regular", Font.PLAIN, 12));

                int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
                        "Exit Game?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (option == JOptionPane.YES_OPTION) {
                    playSound("./Sound/OsuHitSound.wav");
                    System.exit(0);
                } else if (option == JOptionPane.NO_OPTION) {
                    playSound("./Sound/OsuHitSound.wav");
                    ButtonInfo.setText("> Press any button to proceed... <");
                }

            }
            @Override
            public void mousePressed(MouseEvent e) {
                ButtonInfo.setText("> Exiting Game... <");
                ExitB.Button.setIcon(new ImageIcon("./ButtonsV2/ButtonHover[EXIT].png"));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                ButtonInfo.setText("> Exiting Game... <");
                ExitB.Button.setIcon(new ImageIcon("./ButtonsV2/Button[EXIT].png"));
            }
            @Override
            public void mouseEntered(MouseEvent e) {

                ButtonInfo.setText("> Exit the Game? <");
                ExitB.Button.setIcon(new ImageIcon("./ButtonsV2/ButtonHover[EXIT].png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ButtonInfo.setText("> Press any button to proceed... <");
                ExitB.Button.setIcon(new ImageIcon("./ButtonsV2/Button[EXIT].png"));
            }
        });

    }

}