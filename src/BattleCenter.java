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

public class BattleCenter {

    JPanel BCpanel;
    JLabel BCbackground, ButtonInfo;
    ImageIcon icon;
    ButtonLabel BCicon, returnB, rcB;
    RavageClawBattle RCBpage;

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

    public BattleCenter(MainMenu mainMenu, Main main, RavageClawBattle Rcbpage) {

        this.mainMenu = mainMenu;
        this.main = main;
        this.RCBpage = Rcbpage;

        BCpanel = new JPanel();
        BCpanel.setLayout(null);
        BCpanel.setOpaque(false);

        icon = new ImageIcon("./BattleCenter/BCbg.png");
        BCbackground = new JLabel();
        BCbackground.setIcon(icon);
        BCbackground.setBounds(0,0,1280,720);

        BCicon = new ButtonLabel(12);
        BCicon.Button.setBounds(305,5,650,357);
        BCicon.Button.setVisible(true);

        returnB = new ButtonLabel(5);
        returnB.Button.setBounds(35,580,160,82);

        rcB = new ButtonLabel(11);
        rcB.Button.setBounds(415,345,450,280);
        rcB.Button.setVisible(true);

        ButtonInfo = new JLabel("> Select a Monster to fight... <",JLabel.CENTER);
        ButtonInfo.setFont(new java.awt.Font("fs gravity Regular", java.awt.Font.BOLD,30));
        ButtonInfo.setForeground(java.awt.Color.WHITE);
        ButtonInfo.setBounds(0,630,1260,40);

        BCpanel.add(BCicon.Button);
        BCpanel.add(returnB.Button);
        BCpanel.add(rcB.Button);
        BCpanel.add(ButtonInfo);
        BCpanel.add(BCbackground);

        returnB.Button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                playSound("./Sound/OsuHitSound.wav");
                ButtonInfo.setText("> Returning to Main Menu... <");
                mainMenu.landingPage.setVisible(true);
                BCpanel.setVisible(false);
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
                ButtonInfo.setText("> Select a Monster to fight... <");
                returnB.Button.setIcon(new ImageIcon("./ButtonsV2/Button[RETURN].png"));
            }
        });

        rcB.Button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                playSound("./Sound/OsuHitSound.wav");
                ButtonInfo.setText("> Prepare to fight Ravagaron <");
               if(main.RCBpage != null) {
                    main.RCBpage.startBattle();
                } else {
                    System.out.println("RCBpage is not initialized");
                }

                if(RCBpage != null) {
                    RCBpage.attackButton.setVisible(true);
                    RCBpage.healButton.setVisible(true);
                    RCBpage.dodgeButton.setVisible(true);
                    RCBpage.ultButton.setVisible(true);
                    RCBpage.statusBar.setVisible(true);
                    RCBpage.character.setVisible(true);
                    RCBpage.RavageClaw.setVisible(true);
                    RCBpage.RCStatusInfo.setVisible(true);
                    RCBpage.PlayerStatusInfo.setVisible(true);
                    RCBpage.HealthPotInfo.setVisible(true);
                    RCBpage.dodgeInfo.setVisible(true);
                    RCBpage.ultInfo.setVisible(true);
                    ButtonInfo.setVisible(true);
                } else {
                    System.out.println("RCBpage is not initialized");
                }
                BCpanel.setVisible(false);
                main.RCBpage.RCBattlePanel.setVisible(true);

            }
            @Override
            public void mousePressed(MouseEvent e) {
                ButtonInfo.setText("> Prepare to fight Ravagaron <");
                rcB.Button.setIcon(new ImageIcon("./ButtonsV2/ButtonHover[RC].png"));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                ButtonInfo.setText("> Prepare to fight Ravagaron <");
                rcB.Button.setIcon(new ImageIcon("./ButtonsV2/Button[RC].png"));
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                ButtonInfo.setText("> Fight Ravagaron? <");
                rcB.Button.setIcon(new ImageIcon("./ButtonsV2/ButtonHover[RC].png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ButtonInfo.setText("> Select a Monster to fight... <");
                rcB.Button.setIcon(new ImageIcon("./ButtonsV2/Button[RC].png"));
            }
        });

    }
}