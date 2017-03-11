import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import javax.swing.*;
import javax.swing.table.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import java.io.FileReader;
import java.io.IOException;

public class Battleship
{
  private boolean answerQuestion;
  private DefaultTableModel model;
  private DefaultTableModel userModel;
  private Font labelFont;
  private Font smallLabelFont;
  private int numberOfRows;
  private int numberOfColumns;
  private int selectedRow;
  private int selectedColumn;
  private JButton accountButton;
  private JButton createAccountButton;
  private JButton guideButton;
  private JButton attackButton;
  private JButton backButton;
  private JButton logInButton;
  private JButton logInScreenButton;
  private JButton submitButton;
  private JFrame mainFrame;
  private JLabel battleshipLabel;
  private JLabel battleshipLabel2;
  private JLabel battleshipLabel3;
  private JLabel battleshipLabel4;
  private JLabel battleshipLabel5;
  private JLabel battleshipLabel6;
  private JLabel logInLabel;
  private JLabel newAccountLabel;
  private JLabel mainMenuLabel;
  private JLabel notifyLabel;
  private JLabel notifyLabel2;
  private JLabel questionLabel;
  private JLabel passwordLabel;
  private JLabel usernameLabel;
  private JPanel accountPanel;
  private JPanel newAccountPanel;
  private JPanel gamePanel;
  private JPanel initialPanel;
  private JPanel logInPanel;
  private JPanel newGamePanel;
  private JRadioButton horizontal;
  private JRadioButton ship1;
  private JRadioButton ship2;
  private JRadioButton ship3;
  private JRadioButton ship4;
  private JRadioButton ship5;
  private JRadioButton easyMode;
  private JRadioButton normalMode;
  private JRadioButton hardMode;
  private JRadioButton era1;
  private JRadioButton era2;
  private JRadioButton era3;
  private JRadioButton vertical;
  private JTable movesTable;
  private JTable table;
  private JTextField answerTF;
  private JTextField usernameTF;
  private Ship carrier;
  private Ship battleship;
  private Ship cruiser;
  private Ship submarine;
  private Ship destroyer;
  private Ship enemyCarrier;
  private Ship enemyBattleship;
  private Ship enemyCruiser;
  private Ship enemySubmarine;
  private Ship enemyDestroyer;
  private AccountDB accounts;
  private Questions questionOBJ;

  // File names, used in the guide
  private static String battleshipFile = "WiB.txt";
  private static String variationsFile = "V.txt";
  private static String howtoplayFile = "HtP.txt";
  private static String playthisFile = "PTG.txt";

  public static void main(String[] args)
  {
    Battleship game = new Battleship();
  }

  public Battleship()
  {
    initialize();
  }

  public void initialize()
  {
    //Setup database
    accounts = new AccountDB();

    // Initialize question object
    questionOBJ = new Questions();


    // Frame
    mainFrame = new JFrame();
    mainFrame.setTitle("Age of Battleship");
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Panel
    initialPanel = new JPanel();
    initialPanel.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    // "Age of Battleship" Label
    labelFont = new Font("Serif", Font.BOLD, 48);
    battleshipLabel = new JLabel("Age of Battleship");
    battleshipLabel.setFont(labelFont);
    gbc.anchor = GridBagConstraints.NORTH;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 0;
    gbc.weighty = 1;
    initialPanel.add(battleshipLabel, gbc);

    // "Main Menu" label
    smallLabelFont = new Font("Serif", Font.BOLD, 32);
    mainMenuLabel = new JLabel("Main Menu");
    mainMenuLabel.setFont(smallLabelFont);
    gbc.anchor = GridBagConstraints.NORTH;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 0;
    gbc.weighty = 1;
    gbc.insets = new Insets(70, 0, 0, 0);
    initialPanel.add(mainMenuLabel, gbc);

    // "Create Account" button
    accountButton = new JButton("Create Account");
    accountButton.addActionListener(new ActionListener()
    								{
    	public void actionPerformed(ActionEvent e)
        {
            mainFrame.remove(initialPanel);
            mainFrame.add(newAccountPanel);
            mainFrame.revalidate();
            mainFrame.repaint();
        }
    });
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(0, 0, 0, 0);
    initialPanel.add(accountButton, gbc);

    // "Log In" button
    logInButton = new JButton("Log In");
    logInButton.addActionListener(new ActionListener()
                                {
      public void actionPerformed(ActionEvent e)
      {
        //initialPanel.setVisible(false);
        //logInPanel.setVisible(true);
        mainFrame.remove(initialPanel);
        mainFrame.add(logInPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
      }
    });
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(70, 0, 0, 0);
    initialPanel.add(logInButton, gbc);



     // "Guide" button
    guideButton = new JButton("Guide");
    guideButton.addActionListener(new ActionListener()
                                {
      public void actionPerformed(ActionEvent e)
      {
           createGuideScreen();
      }
    });
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(140, 0, 0, 0);
    initialPanel.add(guideButton, gbc);



    // Finish frame
    mainFrame.add(initialPanel);
    mainFrame.setSize(950, 950);
    mainFrame.setVisible(true);

    createLogInScreen();
    createNewAccountScreen();
    createAccountScreen();
    createNewGameScreen();
    createGameScreen();
  }

  public void createLogInScreen()
  {
    // Panel
    logInPanel = new JPanel();
    logInPanel.setLayout(new GridBagLayout());
    GridBagConstraints gbc2 = new GridBagConstraints();

    // "Age of Battleship" Label
    battleshipLabel2 = new JLabel("Age of Battleship");
    battleshipLabel2.setFont(labelFont);
    gbc2.anchor = GridBagConstraints.NORTH;
    gbc2.gridx = 0;
    gbc2.gridy = 0;
    gbc2.weightx = 0;
    gbc2.weighty = 1;
    logInPanel.add(battleshipLabel2, gbc2);

    // "Log In" label
    logInLabel = new JLabel("Log In");
    logInLabel.setFont(smallLabelFont);
    gbc2.anchor = GridBagConstraints.NORTH;
    gbc2.gridx = 0;
    gbc2.gridy = 0;
    gbc2.weightx = 0;
    gbc2.weighty = 1;
    gbc2.insets = new Insets(70, 0, 0, 0);
    logInPanel.add(logInLabel, gbc2);

    // "Username" label
    usernameLabel = new JLabel("Username");
    gbc2.anchor = GridBagConstraints.CENTER;
    gbc2.gridx = 0;
    gbc2.gridy = 0;
    gbc2.weightx = 0;
    gbc2.weighty = 0;
    gbc2.insets = new Insets(0, 0, 0, 0);
    logInPanel.add(usernameLabel, gbc2);

    // Username textfield
    usernameTF = new JTextField(12);
    gbc2.anchor = GridBagConstraints.CENTER;
    gbc2.gridx = 0;
    gbc2.gridy = 0;
    gbc2.weightx = 0;
    gbc2.weighty = 0;
    gbc2.insets = new Insets(40, 0, 0, 0);
    logInPanel.add(usernameTF, gbc2);

    // Password label
    passwordLabel = new JLabel("Password");
    gbc2.anchor = GridBagConstraints.CENTER;
    gbc2.gridx = 0;
    gbc2.gridy = 0;
    gbc2.weightx = 0;
    gbc2.weighty = 0;
    gbc2.insets = new Insets(100, 0, 0, 0);
    logInPanel.add(passwordLabel, gbc2);

    // Password textfield
    JTextField usernameTF2 = new JTextField(12);
    gbc2.anchor = GridBagConstraints.CENTER;
    gbc2.gridx = 0;
    gbc2.gridy = 0;
    gbc2.weightx = 0;
    gbc2.weighty = 0;
    gbc2.insets = new Insets(140, 0, 0, 0);
    logInPanel.add(usernameTF2, gbc2);

    // "Log In" button
    logInScreenButton = new JButton("Log In");
    logInScreenButton.addActionListener(new ActionListener()
                                {
      public void actionPerformed(ActionEvent e)
      {
	String user = usernameTF.getText().trim();
        String pass = usernameTF2.getText().trim();

        boolean isValid = accounts.validateLogin(user, pass);
        //boolean isValid = true;
        if(isValid){
            mainFrame.remove(logInPanel);
            mainFrame.add(accountPanel);
            mainFrame.revalidate();
            mainFrame.repaint();
        }
        else {
            JOptionPane.showMessageDialog(null, "Invalid Login");
        }
      }
    });
    gbc2.anchor = GridBagConstraints.CENTER;
    gbc2.gridx = 0;
    gbc2.gridy = 0;
    gbc2.weightx = 0;
    gbc2.weighty = 0;
    gbc2.insets = new Insets(220, 0, 0, 0);
    logInPanel.add(logInScreenButton, gbc2);

    // "Back" button
    backButton = new JButton("Back");
    backButton.addActionListener(new ActionListener()
                                   {
      public void actionPerformed(ActionEvent e)
      {
        mainFrame.remove(logInPanel);
        mainFrame.add(initialPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
      }
    });
    gbc2.anchor = GridBagConstraints.LAST_LINE_START;
    gbc2.gridx = 0;
    gbc2.gridy = 0;
    gbc2.weightx = 1;
    gbc2.weighty = 0;
    gbc2.insets = new Insets(0, 20, 20, 0);
    logInPanel.add(backButton, gbc2);
  }


  public void createNewAccountScreen()
  {
    // Panel
    newAccountPanel = new JPanel();
    newAccountPanel.setLayout(new GridBagLayout());
    GridBagConstraints gbc2 = new GridBagConstraints();

    // "Age of Battleship" Label
    battleshipLabel6 = new JLabel("Age of Battleship");
    battleshipLabel6.setFont(labelFont);
    gbc2.anchor = GridBagConstraints.NORTH;
    gbc2.gridx = 0;
    gbc2.gridy = 0;
    gbc2.weightx = 0;
    gbc2.weighty = 1;
    newAccountPanel.add(battleshipLabel6, gbc2);

    // "create account" label
    newAccountLabel = new JLabel("Create Account");
    newAccountLabel.setFont(smallLabelFont);
    gbc2.anchor = GridBagConstraints.NORTH;
    gbc2.gridx = 0;
    gbc2.gridy = 0;
    gbc2.weightx = 0;
    gbc2.weighty = 1;
    gbc2.insets = new Insets(70, 0, 0, 0);
    newAccountPanel.add(newAccountLabel, gbc2);

    // "Username" label
    JLabel accUsernameLabel = new JLabel("Username");
    gbc2.anchor = GridBagConstraints.CENTER;
    gbc2.gridx = 0;
    gbc2.gridy = 0;
    gbc2.weightx = 0;
    gbc2.weighty = 0;
    gbc2.insets = new Insets(0, 0, 0, 0);
    newAccountPanel.add(accUsernameLabel, gbc2);

    // Username textfield
    JTextField accUserTF = new JTextField(12);
    gbc2.anchor = GridBagConstraints.CENTER;
    gbc2.gridx = 0;
    gbc2.gridy = 0;
    gbc2.weightx = 0;
    gbc2.weighty = 0;
    gbc2.insets = new Insets(40, 0, 0, 0);
    newAccountPanel.add(accUserTF, gbc2);

    // Password label
    JLabel accPassLabel = new JLabel("Password");
    gbc2.anchor = GridBagConstraints.CENTER;
    gbc2.gridx = 0;
    gbc2.gridy = 0;
    gbc2.weightx = 0;
    gbc2.weighty = 0;
    gbc2.insets = new Insets(100, 0, 0, 0);
    newAccountPanel.add(accPassLabel, gbc2);

    // Password textfield
    JTextField accPassTF = new JTextField(12);
    gbc2.anchor = GridBagConstraints.CENTER;
    gbc2.gridx = 0;
    gbc2.gridy = 0;
    gbc2.weightx = 0;
    gbc2.weighty = 0;
    gbc2.insets = new Insets(140, 0, 0, 0);
    newAccountPanel.add(accPassTF, gbc2);

    // "Create Account" button
    createAccountButton = new JButton("Create Account");
    createAccountButton.addActionListener(new ActionListener()
                                {
      public void actionPerformed(ActionEvent e)
      {
        String user = accUserTF.getText().trim();
        String pass = accPassTF.getText().trim();
        accounts.create(user, pass);
        JOptionPane.showMessageDialog(null, "Account creation successful!");
        mainFrame.remove(newAccountPanel);
        mainFrame.add(initialPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
      }
    });
    gbc2.anchor = GridBagConstraints.CENTER;
    gbc2.gridx = 0;
    gbc2.gridy = 0;
    gbc2.weightx = 0;
    gbc2.weighty = 0;
    gbc2.insets = new Insets(220, 0, 0, 0);
    newAccountPanel.add(createAccountButton, gbc2);

    // "Back" button
    JButton accBackButton = new JButton("Back");
    accBackButton.addActionListener(new ActionListener()
                                   {
      public void actionPerformed(ActionEvent e)
      {
        mainFrame.remove(newAccountPanel);
        mainFrame.add(initialPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
      }
    });
    gbc2.anchor = GridBagConstraints.LAST_LINE_START;
    gbc2.gridx = 0;
    gbc2.gridy = 0;
    gbc2.weightx = 1;
    gbc2.weighty = 0;
    gbc2.insets = new Insets(0, 20, 20, 0);
    newAccountPanel.add(accBackButton, gbc2);
  }

  public void createAccountScreen()
  {
    GridBagConstraints gbc;
    JButton logOutButton;
    JButton newGameButton;
    JLabel accountLabel;

    // Panel
    accountPanel = new JPanel();
    accountPanel.setLayout(new GridBagLayout());
    gbc = new GridBagConstraints();

    // "Age of Battleship" Label
    battleshipLabel3 = new JLabel("Age of Battleship");
    battleshipLabel3.setFont(labelFont);
    gbc.anchor = GridBagConstraints.NORTH;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 0;
    gbc.weighty = 1;
    accountPanel.add(battleshipLabel3, gbc);

    // "Account Home" label
    accountLabel = new JLabel("Account Home");
    accountLabel.setFont(smallLabelFont);
    gbc.anchor = GridBagConstraints.NORTH;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 0;
    gbc.weighty = 1;
    gbc.insets = new Insets(70, 0, 0, 0);
    accountPanel.add(accountLabel, gbc);

    // "New Game" button
    newGameButton = new JButton("New Game");
    newGameButton.addActionListener(new ActionListener()
                                {
      public void actionPerformed(ActionEvent e)
      {
        mainFrame.remove(accountPanel);
        mainFrame.add(newGamePanel);
        mainFrame.revalidate();
        mainFrame.repaint();
      }
    });
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(0, 0, 0, 0);
    accountPanel.add(newGameButton, gbc);

    // "Log Out" button
    logOutButton = new JButton("Log Out");
    logOutButton.addActionListener(new ActionListener()
                                {
      public void actionPerformed(ActionEvent e)
      {
        mainFrame.remove(accountPanel);
        mainFrame.add(initialPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
      }
    });
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(70, 0, 0, 0);
    accountPanel.add(logOutButton, gbc);
  }

  public void createNewGameScreen()
  {
    ButtonGroup group;
    ButtonGroup group2;
    ButtonGroup eraGroup;
    ButtonGroup modeGroup;
    Font gridLabelFont;
    GridBagConstraints gbc;
    JButton backButton;
    JButton startGameButton;
    JLabel directionLabel;
    JLabel newGameLabel;
    JLabel shipsLabel;
    JLabel eraLabel;
    JLabel modeLabel;
    JLabel yourGridLabel;
    JPanel eraPanel;
    JPanel modePanel;
    JPanel buttonPanel;
    JPanel directionPanel;
    JPanel controlPanel;
    JPanel yourGridPanel;
    MouseAdapter mouseListener;

    // Define game variables
    numberOfRows = 10;
    numberOfColumns = 10;
    carrier = new Ship("CAR", 5);
    battleship = new Ship("BAT", 4);
    cruiser = new Ship("CRU", 3);
    submarine = new Ship("SUB", 3);
    destroyer = new Ship("DES", 2);

    // Panel
    newGamePanel = new JPanel();
    newGamePanel.setLayout(new GridBagLayout());
    gbc = new GridBagConstraints();

    // "Age of Battleship" Label
    battleshipLabel4 = new JLabel("Age of Battleship");
    battleshipLabel4.setFont(labelFont);
    gbc.anchor = GridBagConstraints.NORTH;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 0;
    gbc.weighty = 1;
    newGamePanel.add(battleshipLabel4, gbc);

    // "New Game" label
    newGameLabel = new JLabel("New Game");
    newGameLabel.setFont(smallLabelFont);
    gbc.anchor = GridBagConstraints.NORTH;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 0;
    gbc.weighty = 1;
    gbc.insets = new Insets(70, 0, 0, 0);
    newGamePanel.add(newGameLabel, gbc);

    // Your Grid panel
    yourGridPanel = new JPanel();
    yourGridPanel.setLayout(new BorderLayout());

    // "Your Grid" label
    gridLabelFont = new Font("Serif", Font.BOLD, 24);
    yourGridLabel = new JLabel("Your Grid", SwingConstants.CENTER);
    yourGridLabel.setFont(gridLabelFont);
    yourGridLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    yourGridPanel.add(yourGridLabel, BorderLayout.NORTH);

    // Table
    table = new JTable();
    table.setRowSelectionAllowed(false);

    mouseListener = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0) {
        selectedRow = table.getSelectedRow();
        selectedColumn = table.getSelectedColumn();
        model = (DefaultTableModel)table.getModel();
        placeShip();

        System.out.println(table.getSelectedRow());
      }
    };
    table.addMouseListener(mouseListener);

    table.setModel(new DefaultTableModel(
                                         new Object[][] {
      {null, '1', '2', '3', '4', '5', '6', '7', '8', '9', "10"},
      {" A", null, null, null, null, null, null, null, null, null, null},
      {" B", null, null, null, null, null, null, null, null, null, null},
      {" C", null, null, null, null, null, null, null, null, null, null},
      {" D", null, null, null, null, null, null, null, null, null, null},
      {" E", null, null, null, null, null, null, null, null, null, null},
      {" F", null, null, null, null, null, null, null, null, null, null},
      {" G", null, null, null, null, null, null, null, null, null, null},
      {" H", null, null, null, null, null, null, null, null, null, null},
      {" I", null, null, null, null, null, null, null, null, null, null},
      {" J", null, null, null, null, null, null, null, null, null, null},
    },
                                         new String[] {
                                           "New column", "New column", "New column", "New column", "New column",
                                             "New column", "New column", "New column", "New column", "New column",
                                             "New column"
                                         }
                                         ));
    table.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    table.setPreferredSize(new Dimension(650, 176));
    yourGridPanel.add(table, BorderLayout.CENTER);

    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weightx = 0;
    gbc.weighty = 1;
    gbc.insets = new Insets(0, 0, 0, 0);
    newGamePanel.add(yourGridPanel, gbc);

    // Control panel
    controlPanel = new JPanel();
    controlPanel.setLayout(new GridLayout(1, 2, 10, 10));
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.weightx = 0;
    gbc.weighty = 1;
    gbc.insets = new Insets(0, 0, 0, 0);
    newGamePanel.add(controlPanel, gbc);

    // Mode panel
    modePanel = new JPanel();
    modePanel.setLayout(new GridLayout(6,1));
    modePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

    modeLabel = new JLabel(" Mode:");
    easyMode = new JRadioButton("Easy");
    easyMode.setSelected(true);
    normalMode = new JRadioButton("Normal");
    hardMode = new JRadioButton("Hard");

    modeGroup = new ButtonGroup();
    modeGroup.add(easyMode);
    modeGroup.add(normalMode);
    modeGroup.add(hardMode);

    modePanel.add(modeLabel);
    modePanel.add(easyMode);
    modePanel.add(normalMode);
    modePanel.add(hardMode);
    controlPanel.add(modePanel);

    eraPanel = new JPanel();
    eraPanel.setLayout(new GridLayout(6,1));
    eraPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

    eraLabel = new JLabel(" Era:");
    era1 = new JRadioButton("Civil War");
    era1.setSelected(true);
    era2 = new JRadioButton("WWI");
    era3 = new JRadioButton("WWII");

    eraGroup = new ButtonGroup();
    eraGroup.add(era1);
    eraGroup.add(era2);
    eraGroup.add(era3);

    eraPanel.add(eraLabel);
    eraPanel.add(era1);
    eraPanel.add(era2);
    eraPanel.add(era3);
    controlPanel.add(eraPanel);

    // Ships panel and radio buttons
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(6, 1));
    buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

    shipsLabel = new JLabel(" Ships:");
    ship1 = new JRadioButton("Carrier (CAR)");
    ship1.setSelected(true);
    ship2 = new JRadioButton("Battleship (BAT)");
    ship3 = new JRadioButton("Crusier (CRU)");
    ship4 = new JRadioButton("Submarine (SUB)");
    ship5 = new JRadioButton("Destroyer (DES)");

    group = new ButtonGroup();
    group.add(ship1);
    group.add(ship2);
    group.add(ship3);
    group.add(ship4);
    group.add(ship5);

    buttonPanel.add(shipsLabel);
    buttonPanel.add(ship1);
    buttonPanel.add(ship2);
    buttonPanel.add(ship3);
    buttonPanel.add(ship4);
    buttonPanel.add(ship5);
    controlPanel.add(buttonPanel);

    // Direction panel and radio buttons
    directionPanel = new JPanel();
    directionPanel.setLayout(new GridLayout(6, 1));
    directionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

    directionLabel = new JLabel(" Direction:");
    horizontal = new JRadioButton("Horizontal");
    horizontal.setSelected(true);
    vertical = new JRadioButton("Vertical");

    group2 = new ButtonGroup();
    group2.add(horizontal);
    group2.add(vertical);

    directionPanel.add(directionLabel);
    directionPanel.add(horizontal);
    directionPanel.add(vertical);
    controlPanel.add(directionPanel);

    // "Start Game" button
    startGameButton = new JButton("Start Game");
    startGameButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg) {
        int mode;
        int era;
        if (areAllShipsPlaced())
        {
            if(easyMode.isSelected()){
                mode = 0;
            }
            else if (normalMode.isSelected()){
                mode = 1;
            }
            else {
                mode = 2;
            }

            if(era1.isSelected()){
                era = 0;
            }
            else if(era2.isSelected()){
                era = 1;
            }
            else{
                era = 2;
            }

            questionOBJ.settings(mode, era);
            initializeEnemyShips();
            mainFrame.remove(newGamePanel);
            mainFrame.add(gamePanel);
            mainFrame.revalidate();
            mainFrame.repaint();
        }
        else
        {
          JOptionPane.showMessageDialog (null, "Error: You must place all five ships before you can begin the game.");
        }
      }
    });
    gbc.anchor = GridBagConstraints.SOUTH;
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(0, 0, 20, 0);
    newGamePanel.add(startGameButton, gbc);

    // "Back" button
    backButton = new JButton("Back");
    backButton.addActionListener(new ActionListener()
                                   {
      public void actionPerformed(ActionEvent e)
      {
        mainFrame.remove(newGamePanel);
        mainFrame.add(accountPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
      }
    });
    gbc.anchor = GridBagConstraints.LAST_LINE_START;
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.insets = new Insets(0, 20, 20, 0);
    newGamePanel.add(backButton, gbc);
  }


  // Support function to read in all the data in a .txt file
  // pre: target is initialized
  // post: if target is an invalid file name, returns an error message.
  // else, returns a string containing the contents of target
  private String getFile(String target)
  {
    BufferedReader reader;
    String         line = null;
    StringBuilder  stringBuilder = new StringBuilder();
    String         ls = System.getProperty("line.separator");

    try
    {
        reader = new BufferedReader( new FileReader (target));
        while( ( line = reader.readLine() ) != null )
        {
            stringBuilder.append( line );
            stringBuilder.append( ls );
        }
        reader.close();
    }
    catch (FileNotFoundException g)
    {
       g.printStackTrace();
       return ("File not found: " + target);
    }
    catch (IOException k)
    {
       k.printStackTrace();
       return ("IO exception" + target);

    }
    catch (Exception b)
    {
        return ("Unknown error");
    }
    return (stringBuilder.toString());
}


  // Generates the guide menu, complete with buttons and functionality
  public void createGuideScreen()
  {
      // Rather than use the main screen, launch a new window for the guide
      EventQueue.invokeLater(new Runnable()
      {
          @Override
          public void run()
          {
              // Setting new frame, with setting dispose on close
              // which lets the user 'x' out WITHOUT closing
              // the program
              JFrame gFrame = new JFrame ("Guide");
              gFrame.setDefaultCloseOperation (DISPOSE_ON_CLOSE);
              // Duplicating the setup of the intro menu
                try
                {
                   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
                catch (Exception e)
                {
                    System.out.println ("Error in createGuideScreen UImanager");
                }

                // The text box - this outputs the information
                JTextArea info = new JTextArea("Please select an option");
                info.setLineWrap(true);
                info.setEditable(false);
                JScrollPane scroll = new JScrollPane (info,
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                // Setting up the pannels
                JPanel menu = new JPanel();
                menu.setLayout(new BorderLayout());
                menu.add (BorderLayout.CENTER, scroll);
                JPanel top = new JPanel();



                // Buttons for information
                JButton whatIsBattleship = new JButton ("What is Battleship");
                whatIsBattleship.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        info.setText(getFile(battleshipFile));
                        gFrame.revalidate();
                        gFrame.repaint();

                    }
                 });
                JButton howToPlay = new JButton ("How to play");
                howToPlay.addActionListener (new ActionListener()
                {
                    public void actionPerformed (ActionEvent e)
                    {
                        info.setText(getFile(howtoplayFile));
                        gFrame.revalidate();
                        gFrame.repaint();
                    }
                });
                JButton variations = new JButton ("Variations");
                variations.addActionListener (new ActionListener()
                {
                    public void actionPerformed (ActionEvent e)
                    {
                        info.setText(getFile(variationsFile));
                        gFrame.revalidate();
                        gFrame.repaint();
                    }
                });
                JButton howToPlayThis = new JButton ("Playing this game");
                howToPlayThis.addActionListener (new ActionListener()
                {
                    public void actionPerformed (ActionEvent e)
                    {
                        info.setText(getFile(playthisFile));
                        gFrame.revalidate();
                        gFrame.repaint();
                    }
                });





                top.add (whatIsBattleship);
                top.add (howToPlay);
                top.add (variations);
                top.add (howToPlayThis);
                menu.add(top, BorderLayout.NORTH);




                // Frame - making it visible
                gFrame.getContentPane().add(BorderLayout.CENTER, menu);
                gFrame.pack();
                gFrame.setSize(750, 750);
                gFrame.setLocationByPlatform(true);
                gFrame.setVisible(true);

          }


      });
  }

  public void createGameScreen()
  {
    ButtonGroup group;
    ButtonGroup group2;
    Font gridLabelFont;
    GridBagConstraints gbc;
    JButton accountButton;
    JButton mainMenuButton;
    JButton startGameButton;
    JLabel battleLabel;
    JLabel directionLabel;
    JLabel newGameLabel;
    JLabel shipsLabel;
    JLabel targetGridLabel;
    JLabel yourGridLabel;
    JPanel attackPanel;
    JPanel buttonPanel;
    JPanel controlPanel;
    JPanel directionPanel;
    JPanel questionPanel;
    JPanel targetGridPanel;
    JPanel yourGridPanel;
    JTable shipsTable;
    MouseAdapter mouseListener;

    // Define game variables
    numberOfRows = 10;
    numberOfColumns = 10;
    carrier = new Ship("CAR", 5);
    battleship = new Ship("BAT", 4);
    cruiser = new Ship("CRU", 3);
    submarine = new Ship("SUB", 3);
    destroyer = new Ship("DES", 2);
    answerQuestion = false;

    // Panel
    gamePanel = new JPanel();
    gamePanel.setLayout(new GridBagLayout());
    gbc = new GridBagConstraints();

    // "Age of Battleship" Label
    battleshipLabel5 = new JLabel("Age of Battleship");
    battleshipLabel5.setFont(labelFont);
    gbc.anchor = GridBagConstraints.NORTH;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 0;
    gbc.weighty = 1;
    gamePanel.add(battleshipLabel5, gbc);

    // "Battle Screen" label
    battleLabel = new JLabel("Battle Screen");
    battleLabel.setFont(smallLabelFont);
    gbc.anchor = GridBagConstraints.NORTH;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 0;
    gbc.weighty = 1;
    gbc.insets = new Insets(70, 0, 0, 0);
    gamePanel.add(battleLabel, gbc);

    // Target Grid panel
    targetGridPanel = new JPanel();
    targetGridPanel.setLayout(new BorderLayout());

    // "Target Grid" label
    gridLabelFont = new Font("Serif", Font.BOLD, 24);
    targetGridLabel = new JLabel("Target Grid", SwingConstants.CENTER);
    targetGridLabel.setFont(gridLabelFont);
    targetGridLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    targetGridPanel.add(targetGridLabel, BorderLayout.NORTH);

    // Moves Table
    movesTable = new JTable();
    movesTable.setRowSelectionAllowed(false);

    mouseListener = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0) {
        if (answerQuestion != true)
        {
          selectedRow = movesTable.getSelectedRow();
          selectedColumn = movesTable.getSelectedColumn();
        }

        model = (DefaultTableModel)movesTable.getModel();
      }
    };
    movesTable.addMouseListener(mouseListener);

    movesTable.setModel(new DefaultTableModel(
                                         new Object[][] {
      {null, '1', '2', '3', '4', '5', '6', '7', '8', '9', "10"},
      {" A", null, null, null, null, null, null, null, null, null, null},
      {" B", null, null, null, null, null, null, null, null, null, null},
      {" C", null, null, null, null, null, null, null, null, null, null},
      {" D", null, null, null, null, null, null, null, null, null, null},
      {" E", null, null, null, null, null, null, null, null, null, null},
      {" F", null, null, null, null, null, null, null, null, null, null},
      {" G", null, null, null, null, null, null, null, null, null, null},
      {" H", null, null, null, null, null, null, null, null, null, null},
      {" I", null, null, null, null, null, null, null, null, null, null},
      {" J", null, null, null, null, null, null, null, null, null, null},
    },
                                         new String[] {
                                           "New column", "New column", "New column", "New column", "New column",
                                             "New column", "New column", "New column", "New column", "New column",
                                             "New column"
                                         }
                                         ));
    movesTable.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    movesTable.setPreferredSize(new Dimension(650, 176));
    targetGridPanel.add(movesTable, BorderLayout.CENTER);

    gbc.anchor = GridBagConstraints.CENTER;
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weightx = 0;
    gbc.weighty = 1;
    gbc.insets = new Insets(0, 0, 0, 0);
    gamePanel.add(targetGridPanel, gbc);

    // Attack panel
    attackPanel = new JPanel();
    attackPanel.setLayout(new GridBagLayout());
    attackPanel.setPreferredSize(new Dimension(600, 120));
    attackPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Attack"));

    // Attack notify label
    notifyLabel = new JLabel("The result of your attack is displayed here.");
    gbc.anchor = GridBagConstraints.FIRST_LINE_START;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 1;
    gbc.weighty = 0;
    gbc.insets = new Insets(0, 10, 0, 0);
    attackPanel.add(notifyLabel, gbc);
    //attackPanel.add(notifyLabel, BorderLayout. NORTH);

    // Second attack notify label
    notifyLabel2 = new JLabel("If you find an enemy ship, you must answer the question below correctly to hit the "
                             + "ship.");
    gbc.anchor = GridBagConstraints.FIRST_LINE_START;
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weightx = 1;
    gbc.weighty = 0;
    gbc.insets = new Insets(0, 10, 0, 0);
    attackPanel.add(notifyLabel2, gbc);

    // "Attack" button
    attackButton = new JButton("Attack");
    attackButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg) {
        selectedRow = -1;
        selectedColumn = -1;
        selectedRow = movesTable.getSelectedRow();
        selectedColumn = movesTable.getSelectedColumn();
        if (selectedRow != -1 && selectedColumn != -1)
        {
          processHit(selectedRow, selectedColumn);
        }
        else
        {
          JOptionPane.showMessageDialog (null, "Error: You must select a location before you can attack.");
        }
      }
    });
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 0, 0, 0);
    attackPanel.add(attackButton, gbc);

    gbc.anchor = GridBagConstraints.CENTER;
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.weightx = 0;
    gbc.weighty = 1;
    gbc.insets = new Insets(10, 0, 0, 0);
    gamePanel.add(attackPanel, gbc);

    // Question panel
    questionPanel = new JPanel();
    questionPanel.setLayout(new GridBagLayout());
    questionPanel.setPreferredSize(new Dimension(600, 120));
    questionPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Question"));

    // Question label
    questionLabel = new JLabel("No question.");
    gbc.anchor = GridBagConstraints.FIRST_LINE_START;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 1;
    gbc.weighty = 0;
    gbc.insets = new Insets(0, 10, 0, 0);
    questionPanel.add(questionLabel, gbc);

    // Answer textfield
    answerTF = new JTextField(15);
    answerTF.setEditable(false);
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 0, 0, 0);
    questionPanel.add(answerTF, gbc);

    // Submit answer button
    submitButton = new JButton("Submit Answer");
    submitButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg) {

        processQuestion(answerTF.getText().toLowerCase());
      }
    });
    submitButton.setEnabled(false);
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 0, 0, 0);
    questionPanel.add(submitButton, gbc);

    gbc.anchor = GridBagConstraints.CENTER;
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.weightx = 0;
    gbc.weighty = 1;
    gbc.insets = new Insets(10, 0, 0, 0);
    gamePanel.add(questionPanel, gbc);

    // Your Grid panel
    yourGridPanel = new JPanel();
    yourGridPanel.setLayout(new BorderLayout());

    // "Your Grid" label
    yourGridLabel = new JLabel("Your Grid", SwingConstants.CENTER);
    yourGridLabel.setFont(gridLabelFont);
    yourGridLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    yourGridPanel.add(yourGridLabel, BorderLayout.NORTH);

    // Ships Table
    shipsTable = new JTable();
    shipsTable.setRowSelectionAllowed(false);

    mouseListener = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0) {
      }
    };
    shipsTable.addMouseListener(mouseListener);

    shipsTable.setModel(table.getModel());
    shipsTable.setEnabled(false);
    shipsTable.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    shipsTable.setPreferredSize(new Dimension(650, 176));
    yourGridPanel.add(shipsTable, BorderLayout.CENTER);

    gbc.anchor = GridBagConstraints.CENTER;
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.weightx = 0;
    gbc.weighty = 1;
    gbc.insets = new Insets(0, 0, 0, 0);
    gamePanel.add(yourGridPanel, gbc);

    // "Back to Account Home" button
    accountButton = new JButton("Back to Account Home");
    accountButton.addActionListener(new ActionListener()
                                   {
      public void actionPerformed(ActionEvent e)
      {
        mainFrame.remove(gamePanel);
        mainFrame.add(accountPanel);

        // Reset target grid table
        movesTable.setModel(new DefaultTableModel(
                                                  new Object[][] {
          {null, '1', '2', '3', '4', '5', '6', '7', '8', '9', "10"},
          {" A", null, null, null, null, null, null, null, null, null, null},
          {" B", null, null, null, null, null, null, null, null, null, null},
          {" C", null, null, null, null, null, null, null, null, null, null},
          {" D", null, null, null, null, null, null, null, null, null, null},
          {" E", null, null, null, null, null, null, null, null, null, null},
          {" F", null, null, null, null, null, null, null, null, null, null},
          {" G", null, null, null, null, null, null, null, null, null, null},
          {" H", null, null, null, null, null, null, null, null, null, null},
          {" I", null, null, null, null, null, null, null, null, null, null},
          {" J", null, null, null, null, null, null, null, null, null, null},
        },
                                                  new String[] {
                                                    "New column", "New column", "New column", "New column",
                                                      "New column", "New column", "New column", "New column",
                                                      "New column", "New column", "New column"
                                                  }
                                                  ));

        // Reset attack panel
        notifyLabel.setText("The result of your attack is displayed here.");
        notifyLabel2.setText("If you find an enemy ship, you must answer the question below correctly to hit the "
                             + "ship.");
        attackButton.setEnabled(true);

        // Reset question panel
        questionLabel.setText("No question.");
        answerTF.setEditable(false);
        submitButton.setEnabled(false);

        // Reset user's grid table
        table.setModel(new DefaultTableModel(
                                             new Object[][] {
          {null, '1', '2', '3', '4', '5', '6', '7', '8', '9', "10"},
          {" A", null, null, null, null, null, null, null, null, null, null},
          {" B", null, null, null, null, null, null, null, null, null, null},
          {" C", null, null, null, null, null, null, null, null, null, null},
          {" D", null, null, null, null, null, null, null, null, null, null},
          {" E", null, null, null, null, null, null, null, null, null, null},
          {" F", null, null, null, null, null, null, null, null, null, null},
          {" G", null, null, null, null, null, null, null, null, null, null},
          {" H", null, null, null, null, null, null, null, null, null, null},
          {" I", null, null, null, null, null, null, null, null, null, null},
          {" J", null, null, null, null, null, null, null, null, null, null},
        },
                                             new String[] {
                                               "New column", "New column", "New column", "New column", "New column",
                                                 "New column", "New column", "New column", "New column", "New column",
                                                 "New column"
                                             }
                                             ));
        shipsTable.setModel(table.getModel());

        // Reset user's ships
        carrier.setIsPlaced(false);
        battleship.setIsPlaced(false);
        cruiser.setIsPlaced(false);
        submarine.setIsPlaced(false);
        destroyer.setIsPlaced(false);

        mainFrame.revalidate();
        mainFrame.repaint();
      }
    });
    gbc.anchor = GridBagConstraints.LAST_LINE_START;
    gbc.gridx = 0;
    gbc.gridy = 5;
    gbc.weightx = 1;
    gbc.weighty = 0;
    gbc.insets = new Insets(20, 20, 20, 0);
    gamePanel.add(accountButton, gbc);

    userModel = (DefaultTableModel)shipsTable.getModel();
  }

  public void placeShip()
  {
    boolean isShipThere;
    Ship ship = carrier;

    if (ship1.isSelected())
    {
      ship = carrier;
    }
    else if (ship2.isSelected())
    {
      ship = battleship;
    }
    else if (ship3.isSelected())
    {
      ship = cruiser;
    }
    else if (ship4.isSelected())
    {
      ship = submarine;
    }
    else if (ship5.isSelected())
    {
      ship = destroyer;
    }

    if (selectedRow > 0 && selectedColumn > 0)
    {
      if (horizontal.isSelected())
      {
        if (numberOfColumns - selectedColumn >= (ship.getSize() - 1))
        {
          isShipThere = false;

          for (int i = 0; i < ship.getSize(); i++)
          {
            if (model.getValueAt(selectedRow, selectedColumn + i) != null)
            {
              isShipThere = true;
              break;
            }
          }

          if (isShipThere)
          {
            JOptionPane.showMessageDialog (null, "Error: A ship is already placed here.");
          }
          else
          {
            if (ship.getIsPlaced())
            {
              if (ship.getOrientation() == 'h')
              {
                for (int i = 0; i < ship.getSize(); i++)
                {
                  model.setValueAt(null, ship.getCurrentRow(), ship.getCurrentColumn() + i);
                }
              }
              else if (ship.getOrientation() == 'v')
              {
                for (int i = 0; i < ship.getSize(); i++)
                {
                  model.setValueAt(null, ship.getCurrentRow() + i, ship.getCurrentColumn());
                }
              }
            }

            ship.setCurrentRow(selectedRow);
            ship.setCurrentColumn(selectedColumn);
            ship.setOrientation('h');
            ship.setIsPlaced(true);

            for (int i = 0; i < ship.getSize(); i++)
            {
              model.setValueAt(ship.getName(), selectedRow, selectedColumn + i);
            }
          }
        }
        else
        {
          JOptionPane.showMessageDialog (null, "Error: Ship will not fit here.");
        }
      }
      else if (vertical.isSelected())
      {
        if (numberOfRows - selectedRow >= (ship.getSize() - 1))
        {
          isShipThere = false;

          for (int i = 0; i < ship.getSize(); i++)
          {
            if (model.getValueAt(selectedRow + i, selectedColumn) != null)
            {
              isShipThere = true;
              break;
            }
          }

          if (isShipThere)
          {
            JOptionPane.showMessageDialog (null, "Error: A ship is already placed here.");
          }
          else
          {
            if (ship.getIsPlaced())
            {
              if (ship.getOrientation() == 'h')
              {
                for (int i = 0; i < ship.getSize(); i++)
                {
                  model.setValueAt(null, ship.getCurrentRow(), ship.getCurrentColumn() + i);
                }
              }
              else if (ship.getOrientation() == 'v')
              {
                for (int i = 0; i < ship.getSize(); i++)
                {
                  model.setValueAt(null, ship.getCurrentRow() + i, ship.getCurrentColumn());
                }
              }
            }

            ship.setCurrentRow(selectedRow);
            ship.setCurrentColumn(selectedColumn);
            ship.setOrientation('v');
            ship.setIsPlaced(true);

            for (int i = 0; i < ship.getSize(); i++)
            {
              model.setValueAt(ship.getName(), selectedRow + i, selectedColumn);
            }
          }
        }
        else
        {
          JOptionPane.showMessageDialog (null, "Error: Ship will not fit here.");
        }
      }
    }
  }

  public boolean areAllShipsPlaced()
  {
    if (carrier.getIsPlaced() && battleship.getIsPlaced() && cruiser.getIsPlaced() && submarine.getIsPlaced()
          && destroyer.getIsPlaced())
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  public void initializeEnemyShips()
  {
    enemyCarrier = new Ship(5, 1, 1, 'h');
    enemyBattleship = new Ship(4, 2, 1, 'h');
    enemyCruiser = new Ship(3, 3, 1, 'h');
    enemySubmarine = new Ship(3, 4, 1, 'h');
    enemyDestroyer = new Ship(2, 5, 1, 'h');
  }

  public void processHit(int row, int column)
  {
    boolean isHit = false;
    int enemyAttackRow;
    int enemyAttackColumn;
    String notifyString;
    String shipString;

    // Check enemy carrier
    if (enemyCarrier.getOrientation() == 'h')
    {
      for (int i = 0; i < enemyCarrier.getSize(); i++)
      {
        if (row == enemyCarrier.getCurrentRow() && column == (enemyCarrier.getCurrentColumn() + i))
        {
          isHit = true;
          model.setValueAt("SHIP", row, column);
          enemyCarrier.hitShip(i);
        }
      }
    }
    else if (enemyCarrier.getOrientation() == 'v')
    {
      for (int i = 0; i < enemyCarrier.getSize(); i++)
      {
        if (row == (enemyCarrier.getCurrentRow() + i) && column == enemyCarrier.getCurrentColumn())
        {
          isHit = true;
          model.setValueAt("SHIP", row, column);
          enemyCarrier.hitShip(i);
        }
      }
    }

    // Check enemy battleship
    if (enemyBattleship.getOrientation() == 'h')
    {
      for (int i = 0; i < enemyCarrier.getSize(); i++)
      {
        if (row == enemyBattleship.getCurrentRow() && column == (enemyBattleship.getCurrentColumn() + i))
        {
          isHit = true;
          model.setValueAt("SHIP", row, column);
          enemyBattleship.hitShip(i);
        }
      }
    }
    else if (enemyBattleship.getOrientation() == 'v')
    {
      for (int i = 0; i < enemyBattleship.getSize(); i++)
      {
        if (row == (enemyBattleship.getCurrentRow() + i) && column == enemyBattleship.getCurrentColumn())
        {
          isHit = true;
          model.setValueAt("SHIP", row, column);
          enemyBattleship.hitShip(i);
        }
      }
    }

    // Check enemy cruiser
    if (enemyCruiser.getOrientation() == 'h')
    {
      for (int i = 0; i < enemyCruiser.getSize(); i++)
      {
        if (row == enemyCruiser.getCurrentRow() && column == (enemyCruiser.getCurrentColumn() + i))
        {
          isHit = true;
          model.setValueAt("SHIP", row, column);
          enemyCruiser.hitShip(i);
        }
      }
    }
    else if (enemyCruiser.getOrientation() == 'v')
    {
      for (int i = 0; i < enemyCruiser.getSize(); i++)
      {
        if (row == (enemyCruiser.getCurrentRow() + i) && column == enemyCruiser.getCurrentColumn())
        {
          isHit = true;
          model.setValueAt("SHIP", row, column);
          enemyCruiser.hitShip(i);
        }
      }
    }

    // Check enemy submarine
    if (enemySubmarine.getOrientation() == 'h')
    {
      for (int i = 0; i < enemySubmarine.getSize(); i++)
      {
        if (row == enemySubmarine.getCurrentRow() && column == (enemySubmarine.getCurrentColumn() + i))
        {
          isHit = true;
          model.setValueAt("SHIP", row, column);
          enemySubmarine.hitShip(i);
        }
      }
    }
    else if (enemySubmarine.getOrientation() == 'v')
    {
      for (int i = 0; i < enemySubmarine.getSize(); i++)
      {
        if (row == (enemySubmarine.getCurrentRow() + i) && column == enemySubmarine.getCurrentColumn())
        {
          isHit = true;
          model.setValueAt("SHIP", row, column);
          enemySubmarine.hitShip(i);
        }
      }
    }

    // Check enemy destroyer
    if (enemyDestroyer.getOrientation() == 'h')
    {
      for (int i = 0; i < enemyDestroyer.getSize(); i++)
      {
        if (row == enemyDestroyer.getCurrentRow() && column == (enemyDestroyer.getCurrentColumn() + i))
        {
          isHit = true;
          model.setValueAt("SHIP", row, column);
          enemyDestroyer.hitShip(i);
        }
      }
    }
    else if (enemyDestroyer.getOrientation() == 'v')
    {
      for (int i = 0; i < enemyDestroyer.getSize(); i++)
      {
        if (row == (enemyDestroyer.getCurrentRow() + i) && column == enemyDestroyer.getCurrentColumn())
        {
          isHit = true;
          model.setValueAt("SHIP", row, column);
          enemyDestroyer.hitShip(i);
        }
      }
    }

    if (isHit == false)
    {
      model.setValueAt("MISS", row, column);
      notifyLabel.setText("You attacked row " + row + ", column " + column + ", but did not hit an enemy ship.");

      // Adjust question panel
      answerTF.setEditable(false);
      submitButton.setEnabled(false);
      questionLabel.setText("No question.");

      // Generate random row and column for enemy to attack user at
      enemyAttackRow = (int)(Math.random() * 10 + 1);
      enemyAttackColumn = (int)(Math.random() * 10 + 1);

      // Enemy attacks user
      if (userModel.getValueAt(enemyAttackRow, enemyAttackColumn) == null)
      {
        notifyLabel2.setText("The enemy attacked row " + enemyAttackRow + ", column " + enemyAttackColumn + ", but "
                               + "did not hit any of your ships.");
      }
      else
      {
        notifyLabel2.setText("The enemy attacked row " + enemyAttackRow + ", column " + enemyAttackColumn + ", and "
                               + "successfully hit one of your ships.");

        // Adjust ship's health by first determining which of the user's ships was hit
        shipString = (userModel.getValueAt(enemyAttackRow, enemyAttackColumn)).toString();
        Ship hitShip = carrier;

        if (carrier.isSameName(shipString))
        {
          hitShip = carrier;
        }
        else if (battleship.isSameName(shipString))
        {
          hitShip = battleship;
        }
        else if (cruiser.isSameName(shipString))
        {
          hitShip = cruiser;
        }
        else if (submarine.isSameName(shipString))
        {
          hitShip = submarine;
        }
        else if (destroyer.isSameName(shipString))
        {
          hitShip = destroyer;
        }

        // Reduce ship's health
        if (hitShip.getOrientation() == 'h')
        {
          hitShip.hitShip(enemyAttackColumn - hitShip.getCurrentColumn());
        }
        else if (hitShip.getOrientation() == 'v')
        {
          hitShip.hitShip(enemyAttackRow - hitShip.getCurrentRow());
        }

        // Update user's grid table
        shipString = shipString + " (H)";
        userModel.setValueAt(shipString, enemyAttackRow, enemyAttackColumn);

        // Determine if there is a winner
        determineWinner();
      }
    }
    else
    {
      notifyLabel.setText("You attacked row " + row + ", column " + column + ", and found an enemy ship.");
      notifyLabel2.setText("You must answer the question below correctly to hit the enemy ship.");
      questionLabel.setText(questionOBJ.getQuestion());
      answerQuestion = true;
      attackButton.setEnabled(false);
      answerTF.setEditable(true);
      submitButton.setEnabled(true);
    }
  }

  public void processQuestion(String userInput)
  {
    int enemyAttackRow;
    int enemyAttackColumn;
    String shipString;

    if(userInput.equals(questionOBJ.getAnswer())){
        model.setValueAt("HIT", selectedRow, selectedColumn);
        questionLabel.setText("You answered the question correctly, and successfully hit an enemy ship at row "
                                + selectedRow + ", column " + selectedColumn + ".");
    }
    else {
        model.setValueAt("", selectedRow, selectedColumn);
        questionLabel.setText("You answered the question incorrectly and your hit was not registered at row "
                                + selectedRow + ", column " + selectedColumn + ".");
    }
    attackButton.setEnabled(true);
    answerTF.setEditable(false);
    submitButton.setEnabled(false);
    answerQuestion = false;

    // Determine if there is a winner
    determineWinner();

    // Generate random row and column for enemy to attack user at
    enemyAttackRow = (int)(Math.random() * 10 + 1);
    enemyAttackColumn = (int)(Math.random() * 10 + 1);

    // Enemy attacks user
    if (userModel.getValueAt(enemyAttackRow, enemyAttackColumn) == null)
    {
      notifyLabel2.setText("The enemy attacked row " + enemyAttackRow + ", column " + enemyAttackColumn + ", but "
                             + "did not hit any of your ships.");
    }
    else
    {
      notifyLabel2.setText("The enemy attacked row " + enemyAttackRow + ", column " + enemyAttackColumn + ", and "
                             + "successfully hit one of your ships.");

      // Adjust ship's health by first determining which of the user's ships was hit
      shipString = (userModel.getValueAt(enemyAttackRow, enemyAttackColumn)).toString();
      Ship hitShip = carrier;

      if (carrier.isSameName(shipString))
      {
        hitShip = carrier;
      }
      else if (battleship.isSameName(shipString))
      {
        hitShip = battleship;
      }
      else if (cruiser.isSameName(shipString))
      {
        hitShip = cruiser;
      }
      else if (submarine.isSameName(shipString))
      {
        hitShip = submarine;
      }
      else if (destroyer.isSameName(shipString))
      {
        hitShip = destroyer;
      }

      // Reduce ship's health
      if (hitShip.getOrientation() == 'h')
      {
        hitShip.hitShip(enemyAttackColumn - hitShip.getCurrentColumn());
      }
      else if (hitShip.getOrientation() == 'v')
      {
        hitShip.hitShip(enemyAttackRow - hitShip.getCurrentRow());
      }

      // Update user's grid table
      shipString = shipString + " (H)";
      userModel.setValueAt(shipString, enemyAttackRow, enemyAttackColumn);

      // Determine if there is a winner
      determineWinner();
    }
  }

  public void determineWinner()
  {
    boolean isPlayerAlive = false;
    boolean isEnemyAlive = false;
    DefaultTableModel model = (DefaultTableModel)movesTable.getModel();

    // Check if any of the player's ships are alive
    if (carrier.isAlive() || battleship.isAlive() || cruiser.isAlive() || submarine.isAlive() || destroyer.isAlive())
    {
      isPlayerAlive = true;
    }

    // Check if any of the enemy's ships are alive
    if (enemyCarrier.isAlive() || enemyBattleship.isAlive() || enemyCruiser.isAlive() || enemySubmarine.isAlive()
          || enemyDestroyer.isAlive())
    {
      isEnemyAlive = true;
    }

    if (isPlayerAlive == true && isEnemyAlive == false)
    {
      JOptionPane.showMessageDialog (null, "All enemy ships are destroyed. You win!");
    }
    else if (isEnemyAlive == true && isPlayerAlive == false)
    {
      JOptionPane.showMessageDialog (null, "The enemy has destroyed all your ships. You lose.");
    }
  }

}
