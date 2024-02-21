import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.io.File;

public class HTP {

    JPanel HTPpanel;
    ButtonLabel returnB, HTP1, HTPMonGlad, HTP2, HTP3, HTP4, HTP5, HTP6, HTP7, leftB, rightB;
    JLabel ButtonInfo;
    private MainMenu mainMenu;
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

    public HTP(MainMenu mainMenu, Main main) {

        this.mainMenu = mainMenu;
        this.main = main;

        HTPpanel = new JPanel();
        HTPpanel.setLayout(null);
        HTPpanel.setOpaque(false);

        returnB = new ButtonLabel(5);
        returnB.Button.setBounds(35,580,160,82);

        ButtonInfo = new JLabel("> Press any button to proceed... <",JLabel.CENTER);
        ButtonInfo.setFont(new java.awt.Font("fs gravity Regular", java.awt.Font.BOLD,30));
        ButtonInfo.setForeground(java.awt.Color.WHITE);
        ButtonInfo.setBounds(0,600,1260,40);

        HTPMonGlad = new ButtonLabel(22);
        HTPMonGlad.Button.setBounds(320,0,600,600);
        HTPMonGlad.Button.setVisible(true);

        HTP1 = new ButtonLabel(6);
        HTP1.Button.setBounds(260,130,740,355);
        HTP1.Button.setVisible(false);

        HTP2 = new ButtonLabel(9);
        HTP2.Button.setBounds(260,130,740,355);
        HTP2.Button.setVisible(false);

        HTP3 = new ButtonLabel(10);
        HTP3.Button.setBounds(260,130,740,355);
        HTP3.Button.setVisible(false);

        HTP4 = new ButtonLabel(18);
        HTP4.Button.setBounds(260,130,740,355);
        HTP4.Button.setVisible(false);

        HTP5 = new ButtonLabel(19);
        HTP5.Button.setBounds(260,130,740,355);
        HTP5.Button.setVisible(false);

        HTP6 = new ButtonLabel(20);
        HTP6.Button.setBounds(260,130,740,355);
        HTP6.Button.setVisible(false);

        HTP7 = new ButtonLabel(21);
        HTP7.Button.setBounds(260,130,740,355);
        HTP7.Button.setVisible(false);

        leftB = new ButtonLabel(7);
        leftB.Button.setVisible(false);
        leftB.Button.setBounds(400,480,102,102);

        rightB = new ButtonLabel(8);
        rightB.Button.setBounds(770,480,102,102);

        HTPpanel.add(returnB.Button);
        HTPpanel.add(leftB.Button);
        HTPpanel.add(rightB.Button);
        HTPpanel.add(HTPMonGlad.Button);
        HTPpanel.add(HTP1.Button);
        HTPpanel.add(HTP2.Button);
        HTPpanel.add(HTP3.Button);
        HTPpanel.add(HTP4.Button);
        HTPpanel.add(HTP5.Button);
        HTPpanel.add(HTP6.Button);
        HTPpanel.add(HTP7.Button);
        HTPpanel.add(ButtonInfo);


        returnB.Button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                playSound("./Sound/OsuHitSound.wav");
                ButtonInfo.setText("> Returning to Main Menu... <");
                mainMenu.landingPage.setVisible(true);
                HTPpanel.setVisible(false); //Reset the state of the HTP panel
                HTPMonGlad.Button.setIcon(new ImageIcon("./HowToPlay/HTPMonGlad.png")); //Reset the icon of HTP1
                leftB.Button.setVisible(false); //Reset the visibility of leftB

                HTPMonGlad.Button.setVisible(true);
                HTP1.Button.setVisible(false);
                HTP2.Button.setVisible(false);
                HTP3.Button.setVisible(false);
                HTP4.Button.setVisible(false);
                HTP5.Button.setVisible(false);
                HTP6.Button.setVisible(false);
                HTP7.Button.setVisible(false);

                leftB.Button.setBounds(400,480,102,102);
                rightB.Button.setBounds(770,480,102,102);
                leftB.Button.setVisible(false);
                rightB.Button.setVisible(true);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                ButtonInfo.setText("> Returning to Main Menu... <");
                returnB.Button.setIcon(new ImageIcon("./ButtonsV2/ButtonHover[RETURN].png"));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                ButtonInfo.setText("> Returning to Main Menu... <");
                returnB.Button.setIcon(new ImageIcon("./ButtonsV2/Button[RETURN].png"));
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                ButtonInfo.setText("> Return to Main Menu? <");
                returnB.Button.setIcon(new ImageIcon("./ButtonsV2/ButtonHover[RETURN].png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ButtonInfo.setText("> Press any button to proceed... <");
                returnB.Button.setIcon(new ImageIcon("./ButtonsV2/Button[RETURN].png"));
            }
        });

        rightB.Button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                playSound("./Sound/OsuHitSound.wav");
                ButtonInfo.setText("> Next Page <");

                if (HTPMonGlad.Button.isVisible()) {
                    HTPMonGlad.Button.setVisible(false);
                    HTP1.Button.setVisible(true);
                    leftB.Button.setVisible(true);
                    leftB.Button.setBounds(160,257,102,102);
                    rightB.Button.setBounds(1005,257,102,102);
                } else if (HTP1.Button.isVisible()) {
                    HTP1.Button.setVisible(false);
                    HTP2.Button.setVisible(true);
                    leftB.Button.setVisible(true);
                    leftB.Button.setBounds(160,257,102,102);
                } else if (HTP2.Button.isVisible()) {
                    HTP2.Button.setVisible(false);
                    HTP3.Button.setVisible(true);
                    leftB.Button.setVisible(true);
                    leftB.Button.setBounds(160,257,102,102);
                } else if (HTP3.Button.isVisible()) {
                    HTP3.Button.setVisible(false);
                    HTP4.Button.setVisible(true);
                    leftB.Button.setVisible(true);
                    leftB.Button.setBounds(160,257,102,102);
                } else if (HTP4.Button.isVisible()) {
                    HTP4.Button.setVisible(false);
                    HTP5.Button.setVisible(true);
                    leftB.Button.setVisible(true);
                    leftB.Button.setBounds(160,257,102,102);
                } else if (HTP5.Button.isVisible()) {
                    HTP5.Button.setVisible(false);
                    HTP6.Button.setVisible(true);
                    leftB.Button.setVisible(true);
                    leftB.Button.setBounds(160,257,102,102);
                } else if (HTP6.Button.isVisible()) {
                    HTP6.Button.setVisible(false);
                    HTP7.Button.setVisible(true);
                    leftB.Button.setVisible(true);
                    leftB.Button.setBounds(160,257,102,102);
                    rightB.Button.setVisible(false);
                } else if (HTP7.Button.isVisible()) {

                }

            }
            @Override
            public void mousePressed(MouseEvent e) {
                ButtonInfo.setText("> Next Page <");
                rightB.Button.setIcon(new ImageIcon("./ButtonsV2/ButtonHover[Right].png"));

            }
            @Override
            public void mouseReleased(MouseEvent e) {
                ButtonInfo.setText("> Next Page <");
                rightB.Button.setIcon(new ImageIcon("./ButtonsV2/Button[Right].png"));
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                ButtonInfo.setText("> Next Page <");
                rightB.Button.setIcon(new ImageIcon("./ButtonsV2/ButtonHover[Right].png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ButtonInfo.setText("> Press any button to proceed... <");
                rightB.Button.setIcon(new ImageIcon("./ButtonsV2/Button[Right].png"));
            }
        });

        leftB.Button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                playSound("./Sound/OsuHitSound.wav");
                ButtonInfo.setText("> Previous Page <");

                if (HTP1.Button.isVisible()) {
                    HTP1.Button.setVisible(false);
                    HTPMonGlad.Button.setVisible(true);
                    leftB.Button.setVisible(false);
                    rightB.Button.setVisible(true);
                    rightB.Button.setBounds(770,480,102,102);
                } else if (HTP2.Button.isVisible()) {
                    HTP2.Button.setVisible(false);
                    HTP1.Button.setVisible(true);
                    rightB.Button.setVisible(true);
                    rightB.Button.setBounds(1005,257,102,102);
                } else if (HTP3.Button.isVisible()) {
                    HTP3.Button.setVisible(false);
                    HTP2.Button.setVisible(true);
                    rightB.Button.setVisible(true);
                    rightB.Button.setBounds(1005,257,102,102);
                } else if (HTP4.Button.isVisible()) {
                    HTP4.Button.setVisible(false);
                    HTP3.Button.setVisible(true);
                    rightB.Button.setVisible(true);
                    rightB.Button.setBounds(1005,257,102,102);
                } else if (HTP5.Button.isVisible()) {
                    HTP5.Button.setVisible(false);
                    HTP4.Button.setVisible(true);
                    rightB.Button.setVisible(true);
                    rightB.Button.setBounds(1005,257,102,102);
                } else if (HTP6.Button.isVisible()) {
                    HTP6.Button.setVisible(false);
                    HTP5.Button.setVisible(true);
                    rightB.Button.setVisible(true);
                    rightB.Button.setBounds(1005,257,102,102);
                } else if (HTP7.Button.isVisible()) {
                    HTP7.Button.setVisible(false);
                    HTP6.Button.setVisible(true);
                    rightB.Button.setVisible(true);
                    rightB.Button.setBounds(1005,257,102,102);
                } else if (HTP1.Button.isVisible()) {
                    HTP1.Button.setVisible(false);
                    HTPMonGlad.Button.setVisible(true);
                    leftB.Button.setVisible(false);
                    rightB.Button.setBounds(1005,257,102,102);
                }


            }
            @Override
            public void mousePressed(MouseEvent e) {
                ButtonInfo.setText("> Previous Page <");
                leftB.Button.setIcon(new ImageIcon("./ButtonsV2/ButtonHover[Left].png"));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                ButtonInfo.setText("> Previous Page <");
                leftB.Button.setIcon(new ImageIcon("./ButtonsV2/Button[Left].png"));
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                ButtonInfo.setText("> Previous Page <");
                leftB.Button.setIcon(new ImageIcon("./ButtonsV2/ButtonHover[Left].png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ButtonInfo.setText("> Press any button to proceed... <");
                leftB.Button.setIcon(new ImageIcon("./ButtonsV2/Button[Left].png"));
            }
        });


    }

}
