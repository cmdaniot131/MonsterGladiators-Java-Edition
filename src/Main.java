import javax.swing.*;

public class Main {

    JFrame mainFrame;
    ImageIcon icon;
    JLabel myBackground;
    MainMenu mainMenu;
    HTP HTPpage;
    BattleCenter BCpage;
    RavageClawBattle RCBpage;


    public Main() {

        mainFrame = new JFrame("Monsters and Gladiators Java Edition");
        icon = new ImageIcon("./MainMenu/MONGLADICON.png");
        mainFrame.setIconImage(icon.getImage());
        mainFrame.setLayout(null);
        mainFrame.setSize(1280,720);
        mainFrame.setLocation(150,20);
        mainFrame.setResizable(false);

        myBackground = new JLabel();
        icon = new ImageIcon("./MainMenu/MainMenuBG.png");
        myBackground.setIcon(icon);
        mainFrame.setContentPane(myBackground);

        mainMenu = new MainMenu(this);
        mainMenu.landingPage.setBounds(0,0,1280,720);
        mainFrame.add(mainMenu.landingPage);

        BCpage = new BattleCenter(mainMenu,this, RCBpage);
        BCpage.BCpanel.setBounds(0,0,1280,720);
        mainFrame.add(BCpage.BCpanel);
        BCpage.BCpanel.setVisible(false);

        RCBpage = new RavageClawBattle(BCpage);
        RCBpage.RCBattlePanel.setBounds(0,0,1280,720);
        mainFrame.add(RCBpage.RCBattlePanel);
        RCBpage.RCBattlePanel.setVisible(false);

        HTPpage = new HTP(mainMenu, this);
        HTPpage.HTPpanel.setBounds(0,0,1280,720);
        mainFrame.add(HTPpage.HTPpanel);
        HTPpage.HTPpanel.setVisible(false);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);


    }


    public static void main(String[] args) {

        Main startMain = new Main();

    }

}