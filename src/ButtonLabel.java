import javax.swing.*;

public class ButtonLabel {

    ImageIcon LogoIcon, ArenaIcon, HTPIcon, ExitIcon, ReturnIcon, HTP1, RightIcon, LeftIcon;
    ImageIcon HTP2, HTP3, HTP4, HTP5, HTP6, HTP7, HTPMonGlad, RC, BCicon, attackB, ddrugB, hPotionB, staminaB, bStatusBar;
    JLabel Button;

    public ButtonLabel(int buttons) {

        Button = new JLabel();

        //Main Menu
        LogoIcon = new ImageIcon("./MainMenu/MONGLADICON.png");
        ArenaIcon = new ImageIcon("./ButtonsV2/Button[PLAY].png");
        HTPIcon = new ImageIcon("./ButtonsV2/Button[HOWTOPLAY].png");
        ExitIcon = new ImageIcon("./ButtonsV2/Button[EXIT].png");


        ReturnIcon = new ImageIcon("./ButtonsV2/Button[RETURN].png");

        //How To Play
        HTPMonGlad = new ImageIcon("./HowToPlay/HTPMonGlad.png");
        HTP1 = new ImageIcon("./HowToPlay/HTP1.gif");
        HTP2 = new ImageIcon("./HowToPlay/HTP2.png");
        HTP3 = new ImageIcon("./HowToPlay/HTP3.png");
        HTP4 = new ImageIcon("./HowToPlay/HTP4.png");
        HTP5 = new ImageIcon("./HowToPlay/HTP5.gif");
        HTP6 = new ImageIcon("./HowToPlay/HTP6.gif");
        HTP7 = new ImageIcon("./HowToPlay/HTP7.gif");


        RightIcon = new ImageIcon("./ButtonsV2/Button[Right].png");
        LeftIcon = new ImageIcon("./ButtonsV2/Button[Left].png");

        //Battle Center
        RC = new ImageIcon("./ButtonsV2/Button[RC].png");
        BCicon = new ImageIcon("./BattleCenter/BattleCenterIcon.gif");
        attackB = new ImageIcon("./172x80_BUTTON_ATTACK.png");
        ddrugB = new ImageIcon("./172x80_BUTTON_DEMONDRUG.png");
        hPotionB = new ImageIcon("./172x80_BUTTON_HEALTHPOTION.png");
        staminaB = new ImageIcon("./172x80_BUTTON_STAMINA.png");
        bStatusBar = new ImageIcon("./BattleCenter/404x215_BattleStatusBar.png");


        switch(buttons) {
            case 1:
                Button.setIcon(LogoIcon);
                break;
            case 2:
                Button.setIcon(ArenaIcon);
                break;
            case 3:
                Button.setIcon(HTPIcon);
                break;
            case 4:
                Button.setIcon(ExitIcon);
                break;
            case 5:
                Button.setIcon(ReturnIcon);
                break;
            case 6:
                Button.setIcon(HTP1);
                break;
            case 7:
                Button.setIcon(LeftIcon);
                break;
            case 8:
                Button.setIcon(RightIcon);
                break;
            case 9:
                Button.setIcon(HTP2);
                break;
            case 10:
                Button.setIcon(HTP3);
                break;
            case 11:
                Button.setIcon(RC);
                break;
            case 12:
                Button.setIcon(BCicon);
                break;
            case 13:
                Button.setIcon(attackB);
                break;
            case 14:
                Button.setIcon(ddrugB);
                break;
            case 15:
                Button.setIcon(hPotionB);
                break;
            case 16:
                Button.setIcon(staminaB);
                break;
            case 17:
                Button.setIcon(bStatusBar);
                break;
            case 18:
                Button.setIcon(HTP4);
                break;
            case 19:
                Button.setIcon(HTP5);
                break;
            case 20:
                Button.setIcon(HTP6);
                break;
            case 21:
                Button.setIcon(HTP7);
                break;
            case 22:
                Button.setIcon(HTPMonGlad);
                break;

        }


    }


}
