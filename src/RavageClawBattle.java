import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.Timer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.io.File;

public class RavageClawBattle {

    BattleCenter BCpage;
    JPanel RCBattlePanel;
    JLabel ultMeterLabel, RChealthBarLabel, healthBarLabel, RCBattleBackground, ButtonInfo, HealthPotInfo, dodgeInfo, ultInfo , PlayerStatusInfo, RCStatusInfo, AIPSign, attackButton, healButton, dodgeButton, ultButton, statusBar, character, RavageClaw;
    ButtonLabel returnB;
    ImageIcon ultMeterBar, RCHPBar, HPBar, icon, AIP, attackB, dodgeB, hPotionB, ultB, bStatusBar, characterIcon, RCicon, characterAttackIcon, ravageClawAttackIcon;

    private static final int INITIAL_RAVAGE_CLAW_HEALTH = 500;
    private static final int INITIAL_PLAYER_HEALTH = 200;
    private static final int INITIAL_HEALTH_POTION = 3;
    private static final int INITIAL_ULT_METER = 0;

    boolean isAttacking = false;
    boolean isHealing = false;
    boolean isMonsterAttacking = false;
    boolean ultUsed = false;

    class Player {
        int health;
        boolean isDodging;
        int successfulATK;
        Random random;

        Player(int health) {
            this.health = health;
            this.isDodging = false;
            this.successfulATK = 0;
            this.random = new Random();
        }

        void dodge() {
            isDodging = true;
        }

        boolean isDodging() {
            if (isDodging) {
                int chance = random.nextInt(100) + 1; // Random number between 1 and 100
                return chance <= 60;  //60% dodge chance
            }
            return false;
        }

    }

    class Weapon {
        String name;
        int damage;
        Random random;

        Weapon(String name, int damage) {
            this.name = name;
            this.damage = damage;
            this.random = new Random();
        }

        int getDamage() {

            int chance = random.nextInt(100) + 1; // Random number between 1 and 100

            if (chance <= 80) { // 75% chance the attack will land
                if (name.equals("Black Blade")) {
                    return 30 + random.nextInt(71);
                } else {
                    return 30 + random.nextInt(71);
                }
            } else {
                return 1 + random.nextInt(5); // Attack deals minimal damage
            }

        }

    }

    class Ultimate {
        int ult;

        Ultimate(int ult) {

            this.ult = ult;
        }
    }

    class Inventory {
        int healthPotion; //heals 35 health
        int demonDrug; //increases attack by 20

        Inventory(int healthPotion, int demonDrug) {
            this.healthPotion = healthPotion;
            this.demonDrug = demonDrug;
        }
    }

    class RavageClaw {
        int health;
        int attack;
        Random RCrandom;

        RavageClaw(int health, int attack) {
            this.health = health;
            this.attack = attack;
            this.RCrandom = new Random();
        }

        int getAttack() {
            int chance = RCrandom.nextInt(100) + 1; // Random number between 1 and 100

            if (chance <= 80) { // 80% chance the attack will land
                return 10 + RCrandom.nextInt(66);
            } else {
                return 1 + RCrandom.nextInt(5); // Attack deals minimal damage
            }

        }

        void attack(Player player) {
            if (!player.isDodging()) {
                int damage = getAttack();
                player.health -= damage;

                if (player.health < 0) {
                    player.health = 0;
                }

            }
        }

    }

    Player player;
    RavageClaw ravageClaw;
    Inventory inventory;
    Ultimate ult;

    void updateHealthBar(int health) {
        int roundedHP;
        if (health > 0 && health < 25) {
            roundedHP = 25;
        } else {
            roundedHP = ((health + 24) / 25) * 25;
        }
        String healthBarImageFile = "./HealthBar/HealthBar[" + roundedHP + "].png";
        ImageIcon healthBarIcon = new ImageIcon(healthBarImageFile);
        healthBarLabel.setIcon(healthBarIcon);
    }

    void RCupdateHealthBar(int health) {
        int roundedHP;
        if (health > 0 && health < 50) {
            roundedHP = 50;
        } else {
            roundedHP = ((health + 24) / 50) * 50;
        }
        String healthBarImageFile = "./RCHealthBar/RCHealthBar[" + roundedHP + "].png";
        ImageIcon RChealthBarIcon = new ImageIcon(healthBarImageFile);
        RChealthBarLabel.setIcon(RChealthBarIcon);
    }

    void ultMeterBar(int successfulATK) {
        int roundedUlt;
        if (successfulATK > 0 && successfulATK <= 5) {
            roundedUlt = successfulATK;
        } else {
            roundedUlt = 0;
        }
        String ultBarImageFile = "./UltMeter/UltMeter[" + roundedUlt + "].png";
        ImageIcon ultBarIcon = new ImageIcon(ultBarImageFile);
        ultMeterLabel.setIcon(ultBarIcon);
    }

    void resetHealthBar() {
        player.health = INITIAL_PLAYER_HEALTH;
        updateHealthBar(player.health);
    }

    void RCresetHealthBar() {
        ravageClaw.health = INITIAL_RAVAGE_CLAW_HEALTH;
        RCupdateHealthBar(ravageClaw.health);
    }

    void resetUltMeter() {
        player.successfulATK = INITIAL_ULT_METER;
        ultMeterBar(player.successfulATK);
    }

    Clip clip;
    private void playSound(String soundFile) {
        try {
            if (clip != null && clip.isRunning()) {
                clip.stop();
            }

            clip = AudioSystem.getClip();
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(soundFile));
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void startBattle() {
        AIPSign.setVisible(false);
        attackButton.setVisible(true);
        healButton.setVisible(true);
        dodgeButton.setVisible(true);
        statusBar.setVisible(true);
        character.setVisible(true);
        RavageClaw.setVisible(true);
        RCStatusInfo.setVisible(true);
        PlayerStatusInfo.setVisible(true);
        HealthPotInfo.setVisible(true);
        dodgeInfo.setVisible(true);
        ultInfo.setVisible(true);
        ButtonInfo.setVisible(true);
        returnB.Button.setVisible(true);
        resetHealthBar();
        RCresetHealthBar();
        resetUltMeter();
    }

    Clip[] ultMeterSounds = new Clip[5];

    public RavageClawBattle(BattleCenter BCpage) {

        player = new Player(200);

        inventory = new Inventory(3, 3);
        ravageClaw = new RavageClaw(300, 50);

        Player Player = new Player(200);
        Weapon BlackBlade = new Weapon("Black Blade", 0);
        Ultimate ultimate = new Ultimate(0); // Stamina Potion
        Inventory inventory = new Inventory(3, 3);
        RavageClaw ravageClaw = new RavageClaw(500, 75);

        this.BCpage = BCpage;

        RCBattlePanel = new JPanel();
        RCBattlePanel.setLayout(null);
        RCBattlePanel.setOpaque(false);

        icon = new ImageIcon("./BattleCenter/CoralHighlands.png");
        RCBattleBackground = new JLabel();
        RCBattleBackground.setIcon(icon);
        RCBattleBackground.setBounds(0,0,1280,720);

        returnB = new ButtonLabel(5);
        returnB.Button.setBounds(35,580,160,82);
        returnB.Button.setVisible(true);
        RCBattlePanel.add(returnB.Button);

        AIP = new ImageIcon("./ButtonsV2/AIP.png");
        AIPSign = new JLabel();
        AIPSign.setIcon(AIP);
        AIPSign.setBounds(266,380,420,252);

        HPBar = new ImageIcon("./HealthBar/HealthBar[200].png");
        healthBarLabel = new JLabel();
        healthBarLabel.setIcon(HPBar);
        healthBarLabel.setBounds(110,50,256,84);

        RCHPBar = new ImageIcon("./RCHealthBar/RCHealthBar[500].png");
        RChealthBarLabel = new JLabel();
        RChealthBarLabel.setIcon(RCHPBar);
        RChealthBarLabel.setBounds(735,50,258,84);

        ultMeterBar = new ImageIcon("./UltMeter/UltMeter[0].png");
        ultMeterLabel = new JLabel();
        ultMeterLabel.setIcon(ultMeterBar);
        ultMeterLabel.setBounds(755,561,129,24);

        ultB = new ImageIcon("./ButtonsV2/Button[ULT].gif");
        ultButton = new JLabel();
        ultButton.setIcon(ultB);
        ultButton.setBounds(362,267,220,245);
        ultButton.setVisible(false);

        attackB = new ImageIcon("./ButtonsV2/Button[ATK].png");
        attackButton = new JLabel();
        attackButton.setIcon(attackB);
        attackButton.setBounds(378,400,212,112);

        hPotionB = new ImageIcon("./ButtonsV2/Button[HEAL].png");
        healButton = new JLabel();
        healButton.setIcon(hPotionB);
        healButton.setBounds(275,510,212,112);

        dodgeB = new ImageIcon("./ButtonsV2/Button[DODGE].png");
        dodgeButton = new JLabel();
        dodgeButton.setIcon(dodgeB);
        dodgeButton.setBounds(480,510,212,112);

        bStatusBar = new ImageIcon("./ButtonsV2/StatusBar.png");
        statusBar = new JLabel();
        statusBar.setIcon(bStatusBar);
        statusBar.setBounds(725,400,463,225);

        ButtonInfo = new JLabel("> Press any button to proceed... <",JLabel.CENTER);
        ButtonInfo.setFont(new java.awt.Font("fs gravity Regular", java.awt.Font.BOLD,30));
        ButtonInfo.setForeground(java.awt.Color.BLACK);
        ButtonInfo.setBounds(0,630,1260,40);

        HealthPotInfo = new JLabel("Health Potion: " + inventory.healthPotion );
        HealthPotInfo.setFont(new java.awt.Font("fs gravity Regular", java.awt.Font.BOLD,25));
        HealthPotInfo.setForeground(java.awt.Color.BLACK);
        HealthPotInfo.setBounds(750,420,200,100);

        dodgeInfo = new JLabel("Dodge Info: 60% Dodge Rate");
        dodgeInfo.setFont(new java.awt.Font("fs gravity Regular", java.awt.Font.BOLD,25));
        dodgeInfo.setForeground(java.awt.Color.BLACK);
        dodgeInfo.setBounds(750,460,350,100);

        ultInfo = new JLabel("Ultimate Meter: 0/5");
        ultInfo.setFont(new java.awt.Font("fs gravity Regular", java.awt.Font.BOLD,25));
        ultInfo.setForeground(java.awt.Color.BLACK);
        ultInfo.setBounds(750,500,200,100);

        RCicon = new ImageIcon("./BattleCenter/RavageClawIdle.gif");
        RavageClaw = new JLabel();
        RavageClaw.setIcon(RCicon);
        RavageClaw.setBounds(650,50,500,350);

        RCStatusInfo =  new JLabel("Ravage Claw: " + ravageClaw.health + " HP");
        RCStatusInfo.setFont(new java.awt.Font("fs gravity Regular", java.awt.Font.BOLD,30));
        RCStatusInfo.setForeground(java.awt.Color.RED);
        RCStatusInfo.setBounds(810,0,500,100);

        characterIcon = new ImageIcon("./BattleCenter/beardmanIDLE.gif");
        character = new JLabel();
        character.setIcon(characterIcon);
        character.setBounds(75,35,400,400); //x value 75 Original & 600 to reach the monster

        PlayerStatusInfo = new JLabel("Player: " + Player.health + " HP");
        PlayerStatusInfo.setFont(new java.awt.Font("fs gravity Regular", java.awt.Font.BOLD,30));
        PlayerStatusInfo.setForeground(java.awt.Color.YELLOW);
        PlayerStatusInfo.setBounds(195,0,500,100);

        RCBattlePanel.add(character);
        RCBattlePanel.add(PlayerStatusInfo);
        RCBattlePanel.add(RavageClaw);
        RCBattlePanel.add(RCStatusInfo);

        RCBattlePanel.add(AIPSign);
        RCBattlePanel.add(ultMeterLabel);
        RCBattlePanel.add(RChealthBarLabel);
        RCBattlePanel.add(healthBarLabel);
        RCBattlePanel.add(ultButton);
        RCBattlePanel.add(HealthPotInfo);
        RCBattlePanel.add(dodgeInfo);
        RCBattlePanel.add(ultInfo);
        RCBattlePanel.add(ButtonInfo);
        RCBattlePanel.add(attackButton);
        RCBattlePanel.add(healButton);
        RCBattlePanel.add(dodgeButton);
        RCBattlePanel.add(statusBar);
        RCBattlePanel.add(returnB.Button);
        RCBattlePanel.add(RCBattleBackground);

        try {
            for (int i = 0; i < ultMeterSounds.length; i++) {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("./Sound/SovereignSFX[" + (i + 1) + "].wav"));
                ultMeterSounds[i] = AudioSystem.getClip();
                ultMeterSounds[i].open(audioIn);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        attackButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                playSound("./Sound/OsuHitSound.wav");
                if (ultButton.isVisible()) {
                    attackButton.setVisible(false);
                    return;
                }

                if (isAttacking) {
                    isAttacking = false;
                    return; // Stop the attack if the player is already attacking
                }

                boolean playerAttackChance = BlackBlade.random.nextBoolean(); // 50/50 chance

                isAttacking = true;
                AIPSign.setVisible(true);
                attackButton.setVisible(false); // Disable the attack button
                healButton.setVisible(false);
                dodgeButton.setVisible(false);
                ultButton.setVisible(false);

                characterAttackIcon = new ImageIcon("./BattleCenter/beardmanATK.gif");
                character.setIcon(characterAttackIcon);
                character.setBounds(500,40,480,400);

                Timer timerCH = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        character.setIcon(characterIcon);
                        AIPSign.setVisible(false);
                        isAttacking = false;
                        attackButton.setVisible(true); // Enable the attack button
                        healButton.setVisible(true);
                        dodgeButton.setVisible(true);
                        character.setBounds(75,50,400,400);

                        if (playerAttackChance) {
                            ravageClaw.health -= BlackBlade.getDamage();
                            RCupdateHealthBar(ravageClaw.health);

                            if (ravageClaw.health < 0) {
                                ravageClaw.health = 0;
                            }
                            RCStatusInfo.setText("Ravage Claw: " + ravageClaw.health + " HP");

                            player.successfulATK++;
                            ultMeterBar(player.successfulATK);
                            ultInfo.setText("Ultimate Meter: " + player.successfulATK + "/5");
                            if (player.successfulATK >= 1 && player.successfulATK <= 5) {
                                ultMeterSounds[player.successfulATK - 1].start();
                                ultMeterSounds[player.successfulATK - 1].setFramePosition(0);
                            }

                            boolean isUltReady = false;

                            if (player.successfulATK >= 5) {
                                inventory.healthPotion++;
                                HealthPotInfo.setText("Health Potion: " + inventory.healthPotion);
                                player.successfulATK = 0;
                                isUltReady = true;
                            }

                            if (isUltReady) {
                                Timer ultButtonTimer = new Timer(2500, new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (Player.health > 0 && ravageClaw.health > 0) {
                                            ultButton.setVisible(true);
                                        }
                                        ((Timer)e.getSource()).stop(); // Stop the timer
                                    }
                                });
                                ultButtonTimer.start();
                                isUltReady = false;
                            }

                        } else {
                            playSound("./Sound/UnsuccessfulHit.wav");
                            ButtonInfo.setText("> Attack missed... <");
                        }

                        if (ravageClaw.health > 0) {
                            Timer monsterAttackTimer = new Timer(2500, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    boolean attackChance = ravageClaw.RCrandom.nextBoolean(); //Attack landing hit%

                                    if (attackChance) {
                                        isMonsterAttacking = true;
                                        Player.health -= ravageClaw.getAttack();
                                        updateHealthBar(Player.health);
                                        playSound("./Sound/video-game-hit-noise-001.wav");

                                        if (Player.health < 0) {
                                            Player.health = 0;
                                        }
                                        PlayerStatusInfo.setText("Player: " + Player.health + " HP");

                                        ButtonInfo.setText("> Ravage Claw's attack landed! <");

                                        if (Player.health <= 0) {
                                            playSound("./Sound/failsound.wav");
                                            returnB.Button.setVisible(true);
                                            RCBattleBackground.setIcon(new ImageIcon("./BattleCenter/QuestFailed.gif"));
                                            attackButton.setVisible(false);
                                            healButton.setVisible(false);
                                            dodgeButton.setVisible(false);
                                            ultButton.setVisible(false);
                                            statusBar.setVisible(false);
                                            character.setVisible(false);
                                            RavageClaw.setVisible(false);
                                            RCStatusInfo.setVisible(false);
                                            PlayerStatusInfo.setVisible(false);
                                            HealthPotInfo.setVisible(false);
                                            dodgeInfo.setVisible(false);
                                            ultInfo.setVisible(false);
                                            ButtonInfo.setVisible(false);
                                            AIPSign.setVisible(false);
                                            healthBarLabel.setVisible(false);
                                            RChealthBarLabel.setVisible(false);
                                            ultMeterLabel.setVisible(false);
                                            return;

                                        }
                                        isMonsterAttacking = false;

                                        if (ravageClaw.health <= 0) {
                                            playSound("./Sound/QuestComplete.wav");
                                            returnB.Button.setVisible(true);
                                            RCBattleBackground.setIcon(new ImageIcon("./BattleCenter/VictoryScreen.gif"));
                                            attackButton.setVisible(false);
                                            healButton.setVisible(false);
                                            dodgeButton.setVisible(false);
                                            ultButton.setVisible(false);
                                            statusBar.setVisible(false);
                                            character.setVisible(false);
                                            RavageClaw.setVisible(false);
                                            RCStatusInfo.setVisible(false);
                                            PlayerStatusInfo.setVisible(false);
                                            HealthPotInfo.setVisible(false);
                                            dodgeInfo.setVisible(false);
                                            ultInfo.setVisible(false);
                                            ButtonInfo.setVisible(false);
                                            AIPSign.setVisible(false);
                                            healthBarLabel.setVisible(false);
                                            RChealthBarLabel.setVisible(false);
                                            ultMeterLabel.setVisible(false);
                                            return; //Stop the attack if the monster's health is zero or less
                                        }

                                    } else {
                                        playSound("./Sound/UnsuccessfulHit.wav");
                                        ButtonInfo.setText("> Ravage Claw missed... <");
                                    }

                                    attackButton.setVisible(true);
                                    healButton.setVisible(true);
                                    dodgeButton.setVisible(true);
                                    AIPSign.setVisible(false);

                                }
                            });
                            monsterAttackTimer.setRepeats(false);
                            monsterAttackTimer.start();
                        }

                        if (ravageClaw.health <= 0) {

                            playSound("./Sound/QuestComplete.wav");
                            returnB.Button.setVisible(true);
                            RCBattleBackground.setIcon(new ImageIcon("./BattleCenter/VictoryScreen.gif"));
                            attackButton.setVisible(false);
                            healButton.setVisible(false);
                            dodgeButton.setVisible(false);
                            ultButton.setVisible(false);
                            statusBar.setVisible(false);
                            character.setVisible(false);
                            RavageClaw.setVisible(false);
                            RCStatusInfo.setVisible(false);
                            PlayerStatusInfo.setVisible(false);
                            HealthPotInfo.setVisible(false);
                            dodgeInfo.setVisible(false);
                            ultInfo.setVisible(false);
                            ButtonInfo.setVisible(false);
                            AIPSign.setVisible(false);
                            healthBarLabel.setVisible(false);
                            RChealthBarLabel.setVisible(false);
                            ultMeterLabel.setVisible(false);



                            return; //Stop the attack if the monster's health is zero or less

                        } else if (!playerAttackChance) {
                            playSound("./Sound/UnsuccessfulHit.wav");
                            ButtonInfo.setText("> Attack missed... <");
                        } else {
                            ButtonInfo.setText("> Your attack landed on Ravage Claw! <");
                        }

                        if (ravageClaw.health > 0) {
                            boolean attackChance = ravageClaw.RCrandom.nextBoolean();
                            attackButton.setVisible(false);
                            healButton.setVisible(false);
                            dodgeButton.setVisible(false);
                            AIPSign.setVisible(true);


                            if (player.successfulATK >= 5) {
                                Timer ultButtonTimer = new Timer(2500, new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (Player.health > 0) { // Only show the ult button if the player is still alive
                                            ultButton.setVisible(true);
                                        }
                                    }
                                });
                                ultButtonTimer.setRepeats(false);
                                ultButtonTimer.start();

                                player.successfulATK = 0;
                            }

                            Timer timerRCmove = new Timer(900, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    RavageClaw.setBounds(170,50,500,350);
                                    ravageClawAttackIcon = new ImageIcon("./BattleCenter/RavageClawATK.gif");
                                    RavageClaw.setIcon(ravageClawAttackIcon);

                                    Timer timerRC = new Timer(1300, new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            RavageClaw.setIcon(RCicon);
                                            isAttacking = false;
                                            attackButton.setVisible(true);
                                            healButton.setVisible(true);
                                            dodgeButton.setVisible(true);
                                            RavageClaw.setBounds(650,50,500,350);
                                        }
                                    });
                                    timerRC.setRepeats(false);
                                    timerRC.start();
                                }
                            });
                            timerRCmove.setRepeats(false);
                            timerRCmove.start();

                        }

                    }
                });
                timerCH.setRepeats(false);
                timerCH.start();

                ravageClaw.attack(player);

            }


            @Override
            public void mousePressed(MouseEvent e) {
                attackButton.setIcon(new ImageIcon("./ButtonsV2/ButtonHover[ATK].png"));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                attackButton.setIcon(new ImageIcon("./ButtonsV2/Button[ATK].png"));
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                attackButton.setIcon(new ImageIcon("./ButtonsV2/ButtonHover[ATK].png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ButtonInfo.setText("> Press any button to proceed... <");
                attackButton.setIcon(new ImageIcon("./ButtonsV2/Button[ATK].png"));

                if(isAttacking) {
                    ButtonInfo.setText("> Attacking Ravage Claw... <");
                } else if (isMonsterAttacking) {
                    ButtonInfo.setText("> Ravage Claw is attacking... <");
                } else {
                    ButtonInfo.setText("> Press any button to proceed... <");
                }
                attackButton.setIcon(new ImageIcon("./ButtonsV2/Button[ATK].png"));
            }
        });

        healButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                playSound("./Sound/OsuHitSound.wav");
                if (isHealing) {
                    return;
                }

                if (Player.health < 200) {
                    if (inventory.healthPotion > 0) {
                        ButtonInfo.setText(">Using Health Potion... <");
                        Player.health += 35; //Adds 35 health when player heals
                        playSound("./Sound/HealSound.wav");
                        if (Player.health > 200) {
                            Player.health = 200;
                        }
                        inventory.healthPotion--;
                        HealthPotInfo.setText("Health Potion: " + inventory.healthPotion);
                        PlayerStatusInfo.setText("Player: " + Player.health + " HP");
                        updateHealthBar(Player.health);
                    } else {
                        ButtonInfo.setText("> You have no Health Potions left... <");
                    }
                } else {
                    ButtonInfo.setText("> You are already at full health... <");
                }

            }
            @Override
            public void mousePressed(MouseEvent e) {
                healButton.setIcon(new ImageIcon("./ButtonsV2/ButtonHover[HEAL].png"));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                healButton.setIcon(new ImageIcon("./ButtonsV2/Button[HEAL].png"));
            }
            @Override
            public void mouseEntered(MouseEvent e) {

                healButton.setIcon(new ImageIcon("./ButtonsV2/ButtonHover[HEAL].png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ButtonInfo.setText("> Press any button to proceed... <");
                healButton.setIcon(new ImageIcon("./ButtonsV2/Button[HEAL].png"));
            }
        });

        dodgeButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                playSound("./Sound/OsuHitSound.wav");
                if (isAttacking) {
                    return; // Stop the dodge if the player is already attacking
                }

                isAttacking = true;
                AIPSign.setVisible(true);
                attackButton.setVisible(false);
                healButton.setVisible(false);
                dodgeButton.setVisible(false);
                boolean ultimateVisibility = ultButton.isVisible();

                player.dodge();

                Timer timerRCmove = new Timer(900, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        RavageClaw.setBounds(170,50,500,350);
                        ravageClawAttackIcon = new ImageIcon("./BattleCenter/RavageClawATK.gif");
                        RavageClaw.setIcon(ravageClawAttackIcon);

                        Timer timerRC = new Timer(1300, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                RavageClaw.setIcon(RCicon);
                                isAttacking = false;
                                attackButton.setVisible(true);
                                healButton.setVisible(true);
                                dodgeButton.setVisible(true);
                                AIPSign.setVisible(false);
                                ultButton.setVisible(ultimateVisibility);
                                RavageClaw.setBounds(650,50,500,350);

                                if (player.isDodging()) {
                                    Player.health += 25; // Add 25 Health for successful dodge
                                    dodgeInfo.setText("Dodge Info: Gladiator +25 Health");
                                    ButtonInfo.setText("> You successfully dodged! Player's health increased! <");
                                    playSound("./Sound/HealSound.wav");
                                    if (Player.health > 200) {
                                        Player.health = 200;
                                        dodgeInfo.setText("Dodge Info: Your health is full!");
                                        ButtonInfo.setText("Your dodge was successful but your health is full!");
                                    }
                                    PlayerStatusInfo.setText("Player: " + Player.health + " HP");
                                    updateHealthBar(Player.health);
                                } else {
                                    ravageClaw.health += 25; // Monster adds 20 health for unsuccessful dodge
                                    dodgeInfo.setText("Dodge Info: Ravage Claw +25 Health");
                                    Player.health -= ravageClaw.getAttack();
                                    RCupdateHealthBar(ravageClaw.health);
                                    playSound("./Sound/video-game-hit-noise-001.wav");

                                    if (Player.health < 0 || Player.health <= 0) {
                                        Player.health = 0;
                                        playSound("./Sound/failsound.wav");
                                        returnB.Button.setVisible(true);
                                        RCBattleBackground.setIcon(new ImageIcon("./BattleCenter/QuestFailed.gif"));
                                        attackButton.setVisible(false);
                                        healButton.setVisible(false);
                                        dodgeButton.setVisible(false);
                                        ultButton.setVisible(false);
                                        statusBar.setVisible(false);
                                        character.setVisible(false);
                                        RavageClaw.setVisible(false);
                                        RCStatusInfo.setVisible(false);
                                        PlayerStatusInfo.setVisible(false);
                                        HealthPotInfo.setVisible(false);
                                        dodgeInfo.setVisible(false);
                                        ultInfo.setVisible(false);
                                        ButtonInfo.setVisible(false);
                                        AIPSign.setVisible(false);
                                        healthBarLabel.setVisible(false);
                                        RChealthBarLabel.setVisible(false);
                                        ultMeterLabel.setVisible(false);
                                        return;

                                    }
                                    PlayerStatusInfo.setText("Player: " + Player.health + " HP");
                                    updateHealthBar(Player.health);

                                    ButtonInfo.setText("> You failed to dodge! Ravage Claw's health increased! <");
                                    if (ravageClaw.health > 500) {
                                        ravageClaw.health = 500;
                                        ButtonInfo.setText("Ravage Claw's health is full!");
                                    }
                                    RCStatusInfo.setText("Ravage Claw: " + ravageClaw.health + " HP");
                                }

                                player.isDodging = false; // Reset the dodge status


                            }
                        });
                        timerRC.setRepeats(false);
                        timerRC.start();
                    }
                });
                timerRCmove.setRepeats(false);
                timerRCmove.start();


            }
            @Override
            public void mousePressed(MouseEvent e) {
                dodgeButton.setIcon(new ImageIcon("./ButtonsV2/ButtonHover[DODGE].png"));
            }
            @Override
            public void mouseReleased(MouseEvent e) {

                dodgeButton.setIcon(new ImageIcon("./ButtonsV2/Button[DODGE].png"));
            }
            @Override
            public void mouseEntered(MouseEvent e) {

                dodgeButton.setIcon(new ImageIcon("./ButtonsV2/ButtonHover[DODGE].png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ButtonInfo.setText("> Press any button to proceed... <");
                dodgeButton.setIcon(new ImageIcon("./ButtonsV2/Button[DODGE].png"));
            }
        });

        ultButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isAttacking || ultUsed) {
                    return; // Stop the ultimate if the player is already attacking
                }

                isAttacking = true;
                AIPSign.setVisible(true);
                attackButton.setVisible(false);
                healButton.setVisible(false);
                dodgeButton.setVisible(false);
                ultButton.setVisible(false);

                Timer soundTimer = new Timer(100, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        playSound("./Sound/SovaUlt.wav");
                    }
                });
                soundTimer.setRepeats(false);
                soundTimer.start();

                characterAttackIcon = new ImageIcon("./BattleCenter/beardmanULT.gif");
                character.setIcon(characterAttackIcon);
                character.setBounds(450,40,484,280);

                Timer timerCH = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        character.setIcon(characterIcon);
                        AIPSign.setVisible(false);
                        isAttacking = false;
                        attackButton.setVisible(true);
                        healButton.setVisible(true);
                        dodgeButton.setVisible(true);
                        ultButton.setVisible(true);
                        character.setBounds(75,50,400,400);

                        ravageClaw.health -= 90; // Ultimate attack deals 90 damage
                        if (ravageClaw.health < 0) {
                            ravageClaw.health = 0;
                        }
                        RCStatusInfo.setText("Ravage Claw: " + ravageClaw.health + " HP");
                        RCupdateHealthBar(ravageClaw.health);

                        if (ravageClaw.health <= 0) {
                            returnB.Button.setVisible(true);
                            RCBattleBackground.setIcon(new ImageIcon("./BattleCenter/VictoryScreen.gif"));
                            attackButton.setVisible(false);
                            healButton.setVisible(false);
                            dodgeButton.setVisible(false);
                            ultButton.setVisible(false);
                            statusBar.setVisible(false);
                            character.setVisible(false);
                            RavageClaw.setVisible(false);
                            RCStatusInfo.setVisible(false);
                            PlayerStatusInfo.setVisible(false);
                            HealthPotInfo.setVisible(false);
                            dodgeInfo.setVisible(false);
                            ultInfo.setVisible(false);
                            ButtonInfo.setVisible(false);
                            AIPSign.setVisible(false);
                            healthBarLabel.setVisible(false);
                            RChealthBarLabel.setVisible(false);
                            ultMeterLabel.setVisible(false);
                            return;
                        } else {
                            ButtonInfo.setText("> You used your ultimate attack! <");
                        }

                        ImageIcon ultBarIcon = new ImageIcon("./UltMeter/UltMeter[0].png");
                        ultMeterLabel.setIcon(ultBarIcon);

                        if (player.successfulATK == 5) {
                            inventory.healthPotion++;
                            HealthPotInfo.setText("Health Potion: " + inventory.healthPotion);
                            player.successfulATK = 0;
                            ultInfo.setText("Ultimate Meter: " + player.successfulATK + "/5");
                        }

                        ultUsed = false;
                        ultButton.setVisible(false);
                        player.successfulATK = 0;
                        ultInfo.setText("Ultimate Meter: " + player.successfulATK + "/5");
                    }
                });
                timerCH.setRepeats(false);
                timerCH.start();
            }
            @Override
            public void mousePressed(MouseEvent e) {

                ultButton.setIcon(new ImageIcon("./ButtonsV2/ButtonHover[ULT].gif"));
            }
            @Override
            public void mouseReleased(MouseEvent e) {

                ultButton.setIcon(new ImageIcon("./ButtonsV2/Button[ULT].gif"));
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                ultButton.setIcon(new ImageIcon("./ButtonsV2/ButtonHover[ULT].gif"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ButtonInfo.setText("> Press any button to proceed... <");
                ultButton.setIcon(new ImageIcon("./ButtonsV2/Button[ULT].gif"));
            }
        });

        returnB.Button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                playSound("./Sound/OsuHitSound.wav");

                int response = JOptionPane.showConfirmDialog(null, "End the Battle?", "Confirmation",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    playSound("./Sound/OsuHitSound.wav");
                    ButtonInfo.setText("> Returning to Battle Center... <");
                    BCpage.BCpanel.setVisible(true);
                    RCBattlePanel.setVisible(false);
                    RCBattleBackground.setIcon(new ImageIcon("./BattleCenter/CoralHighlands.png"));
                    returnB.Button.setVisible(false);
                    healthBarLabel.setVisible(true);
                    RChealthBarLabel.setVisible(true);
                    ultMeterLabel.setVisible(true);

                    ravageClaw.health = INITIAL_RAVAGE_CLAW_HEALTH;
                    RCStatusInfo.setText("Ravage Claw: " + ravageClaw.health + " HP");
                    Player.health = INITIAL_PLAYER_HEALTH;
                    PlayerStatusInfo.setText("Player: " + Player.health + " HP");
                    inventory.healthPotion = INITIAL_HEALTH_POTION;
                    HealthPotInfo.setText("Health Potion: " + inventory.healthPotion);
                    dodgeInfo.setText("Dodge Info: 60% Dodge Rate");
                    ultimate.ult = INITIAL_ULT_METER;
                    ultInfo.setText("Ultimate Meter: " + ultimate.ult + "/5");
                    resetHealthBar();

                    player.isDodging = false;

                    ultButton.setVisible(false);
                    ultUsed = false;
                    player.successfulATK = 0;

                } else if (response == JOptionPane.NO_OPTION); {
                    playSound("./Sound/OsuHitSound.wav");
                    ButtonInfo.setText("> Returning to Battle... <");
                }

            }
            @Override
            public void mousePressed(MouseEvent e) {
                ButtonInfo.setText("> Returning to Battle Center... <");
                returnB.Button.setIcon(new ImageIcon("./ButtonsV2/ButtonHover[RETURN].png"));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                ButtonInfo.setText("> Returning to Battle Center... <");
                returnB.Button.setIcon(new ImageIcon("./ButtonsV2/Button[RETURN].png"));
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                ButtonInfo.setText("> Abandon the Arena Quest? <");
                returnB.Button.setIcon(new ImageIcon("./ButtonsV2/ButtonHover[RETURN].png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ButtonInfo.setText("> Press any button to proceed... <");
                returnB.Button.setIcon(new ImageIcon("./ButtonsV2/Button[RETURN].png"));
            }
        });

    }

}
