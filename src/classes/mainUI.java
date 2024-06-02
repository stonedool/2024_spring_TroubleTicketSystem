package classes;
import interfaces.swingUI;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;
import java.util.Vector;
import classes.textbox;
import java.time.LocalDateTime;

public class mainUI implements swingUI {
    Vector<Vector<String>> ticketData = new Vector<>();
    textbox mfTable = new textbox();
    DefaultTableModel model;
    @Override
    public void registerUI() {
        // 새 프레임을 만들고 거기에 가입창을 만든다.
        //아이디 txt파일에 새 아이디를 추가한다. 그리고 창을 닫는다.

        JFrame regUI = new JFrame();
        regUI.setLayout(new GridLayout(10,2));
        regUI.setSize(300,400);

        JLabel lusername = new JLabel("Username");
        JLabel lpassword = new JLabel("Password");
        JLabel lrole = new JLabel("Role");
        JLabel lemail = new JLabel("E-mail address");
        JLabel lreal_name = new JLabel("Name");
        JLabel lphoneNumber = new JLabel("Phone number");

        JTextField username = new JTextField(30);
        JTextField password = new JTextField(30);
        JTextField role = new JTextField(30);
        JTextField email = new JTextField(30);
        JTextField real_name = new JTextField(30);
        JTextField phoneNumber = new JTextField(30);

        JButton register = new JButton("register");
        JButton exit = new JButton("exit");

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                // optional 한 부분은 로그인시에 어떻게 할것인가?
                // 패러미터의 일부가 비어있으면 어떻게 할것인가?

                String regParam = username.getText()+ "#" +password.getText()+ "#" +role.getText()+ "#" +
                        email.getText()+ "#" +real_name.getText()+ "#" +phoneNumber.getText()+ "#";
                System.out.println(regParam);

                account reg = new account();
                reg.newAccount(regParam);
                regUI.setVisible(false);
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regUI.setVisible(false);
            }
        });

        regUI.add(lusername);regUI.add(username);regUI.add(lpassword);regUI.add(password);
        regUI.add(lrole);regUI.add(role);regUI.add(lemail);regUI.add(email);regUI.add(lreal_name);
        regUI.add(real_name);regUI.add(lphoneNumber);regUI.add(phoneNumber);regUI.add(register);regUI.add(exit);


        regUI.setVisible(true);
    }
    public void searchUI(){
        JFrame jf = new JFrame("Search Issue");
        jf.setSize(360,320);
        jf.setLayout(new FlowLayout());

        JTextField searchByName = new JTextField(30); //Assignee
        JTextField searchByTicket = new JTextField(30);
        JTextField searchByStatus = new JTextField(30);
        JTextField searchByReporter = new JTextField(30);

        JLabel name = new JLabel("Assignee");
        JLabel ticket = new JLabel("Ticket");
        JLabel status = new JLabel("Status");
        JLabel Reporter = new JLabel("Reporter");

        JButton searchBTN = new JButton("Search");

        searchBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!Objects.equals(searchByName.getText(), ""))
                    refreshTable_with_keyword("Assignee :" + searchByName.getText());
                else if (!Objects.equals(searchByTicket.getText(), ""))
                    refreshTable_with_keyword("Ticket Number :" + searchByTicket.getText());
                else if (!Objects.equals(searchByStatus.getText(), ""))
                    refreshTable_with_keyword("Status :" + searchByStatus.getText());
                else if (!Objects.equals(searchByReporter.getText(), ""))
                    refreshTable_with_keyword("Reporter :" + searchByReporter.getText());
                else refreshTable();

                jf.setVisible(false);
            }
        });

        jf.add(name);
        jf.add(searchByName);
        jf.add(ticket);
        jf.add(searchByTicket);
        jf.add(status);
        jf.add(searchByStatus);
        jf.add(Reporter);
        jf.add(searchByReporter);
        jf.add(searchBTN);

        jf.setVisible(true);

    }
    public void editIssueUI(String ticketNo) throws IOException { //param: String ticketNo
        textbox tb = new textbox();

        JFrame editIssue = new JFrame("Edit issue");
        editIssue.setSize(400,800);
        editIssue.setLayout(new FlowLayout());

        JButton saveBtn = new JButton("Save");
        JLabel lTicket_Number = new JLabel("Ticket_Number");
        JTextField Ticket_Number = new JTextField(30);
        JLabel lReported_date = new JLabel("Reported_date");
        JTextField Reported_date = new JTextField(30);
        JLabel lReporter = new JLabel("Reporter");
        JTextField Reporter = new JTextField(30);
        JLabel lFixer = new JLabel("Fixer      ");
        JTextField Fixer = new JTextField(30);
        JLabel lPriority = new JLabel("Priority");
        JTextField Priority = new JTextField(30);
        JLabel lAssignee = new JLabel("Assignee");
        JTextField Assignee = new JTextField(30);
        JLabel lStatus = new JLabel("Status    ");
        JTextField Status = new JTextField(30);
        JLabel lComment = new JLabel("Comment");
        JTextArea Comment = new JTextArea(18,30);

        Border border = BorderFactory.createLineBorder(Color.gray);
        Comment.setBorder(border);

        String[] fileData = tb.issueEditing(ticketNo).split("@");
        //@로 시작하는 델리미터 @문장.

        //ticket값 가져오기.


        Ticket_Number.setText(fileData[0]);
        Reported_date.setText(fileData[1]);
        Reporter.setText(fileData[2]);
        Fixer.setText(fileData[3]);
        Priority.setText(fileData[4]);
        Assignee.setText(fileData[5]);
        Status.setText(fileData[6]);
        Comment.setText(fileData[7]);

        Fixer.setText(account.name);

        editIssue.add(lTicket_Number);
        editIssue.add(Ticket_Number);
        editIssue.add(lReported_date);
        editIssue.add(Reported_date);
        editIssue.add(lReporter);
        editIssue.add(Reporter);
        editIssue.add(lFixer);
        editIssue.add(Fixer);
        editIssue.add(lPriority);
        editIssue.add(Priority);
        editIssue.add(lAssignee);
        editIssue.add(Assignee);
        editIssue.add(lStatus);
        editIssue.add(Status);
        editIssue.add(lComment);
        editIssue.add(Comment);

        editIssue.add(saveBtn);

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TEXT FIELD에 적힌거 다시 저장하기.
                String reWriteTxt = Ticket_Number.getText()+'@' + Reported_date.getText() +'@'+ Reporter.getText()+
                        '@'+Fixer.getText()+'@'+Priority.getText()+'@'+Assignee.getText()+'@'+Status.getText()+'@'
                        +Comment.getText()+'@';
                tb.rewriteIssue(reWriteTxt);
                editIssue.setVisible(false);
            }
        });

        editIssue.setVisible(true);
    }
    public void userList(){
        JFrame jf = new JFrame("UserList");
        jf.setSize(100,400);

        account ac = new account();
        String[] uList = ac.totalUserList().split("/");

        JList userlist = new JList(uList);
        jf.add(userlist);
        jf.setVisible(true);
    }
    public void loginUI() {
        JFrame loginFrame = new JFrame("LOG IN");
        loginFrame.setSize(500, 500);

        JButton buttonLogin = new JButton("login");
        JButton buttonRegister = new JButton("Register");
        JTextField idText = new JTextField(25);
        JTextField pwText = new JTextField(25);
        JLabel labelId = new JLabel("ID");
        JLabel labelPw = new JLabel("Password");
        JLabel labelTitle = new JLabel("Trouble Ticket System");
        //
        //ui창 오브젝트들
        //

        Font titleFont = labelTitle.getFont();
        labelTitle.setFont(titleFont.deriveFont(titleFont.getSize() + 10f));

        labelTitle.setLocation(10, 10);
        labelTitle.setSize(400, 100);
        labelId.setLocation(10, 100);
        labelId.setSize(100, 30);
        labelPw.setLocation(10, 140);
        labelPw.setSize(100, 30);
        idText.setLocation(100, 100);
        idText.setSize(250, 30);
        pwText.setLocation(100, 140);
        pwText.setSize(250, 30);

        buttonRegister.setLocation(250, 400);
        buttonRegister.setSize(130, 40);
        buttonLogin.setLocation(400, 400);
        buttonLogin.setSize(80, 40);

        //
        // 오브젝트 크기와 위치
        //

        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idText.getText();
                String pw = pwText.getText();
                account user = new account();
                try {
                    if (user.login(id, pw)) {
                        loginFrame.setVisible(false);
                        mainframeUI();
                    }
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                // 사용자 입력
                //To do. if login() == true, login.
                //else, remove text;
            }
        });
        buttonRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUI();
            }
        });

        Container loginContainer = loginFrame.getContentPane();
        loginContainer.setBackground(Color.lightGray);

        loginContainer.setLayout(null);
        loginContainer.add(labelTitle);
        loginContainer.add(idText);
        loginContainer.add(labelId);
        loginContainer.add(labelPw);
        loginContainer.add(pwText);
        loginContainer.add(buttonLogin);
        loginContainer.add(buttonRegister);

        loginFrame.setVisible(true);
    }
    public void mainframeUI() {
        JFrame mainFrame = new JFrame("Trouble Ticket System 20203758");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(750, 900);
        mainFrame.getContentPane().setLayout(null);
        Container container = mainFrame.getContentPane();

        mainframeButtons(container);
        mainframeMenubar(mainFrame);
        mainframeTextBox(mainFrame);

        mainFrame.setVisible(true);
    }
    private void mainframeButtons(Container container) {
        JButton btnSearch = new JButton("Search Issue");
        JButton btnNewIssue = new JButton("new Issue");
        JButton btnUserList = new JButton("User List");
        JButton btnRefresh = new JButton("R");
        btnNewIssue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newIssueUI();
            }
        });
        btnUserList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userList();
            }
        });
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable();
            }
        });
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchUI();
            }
        });

        btnSearch.setSize(200, 30);
        btnNewIssue.setSize(200, 30);
        btnUserList.setSize(200, 30);
        btnRefresh.setSize(45,30);

        btnSearch.setLocation(50, 150);
        btnNewIssue.setLocation(250, 150);
        btnUserList.setLocation(450, 150);
        btnRefresh.setLocation(655,150);


        container.add(btnSearch);
        container.add(btnNewIssue);
        container.add(btnUserList);
        container.add(btnRefresh);
    }
    private void mainframeMenubar(JFrame jf) {
        JMenuBar menuBar = new JMenuBar();
        JMenu userMenu = new JMenu("user");
        JMenu issueMenu = new JMenu("issue");
        JMenu statisticsMenu = new JMenu("statistics");
        JMenu optionMenu = new JMenu("option");
        JMenu helpMenu = new JMenu("help");
        JMenuItem bar_userName = new JMenuItem("user Name");
        JMenuItem bar_NewID = new JMenuItem("New ID");
        JMenuItem bar_LogOut = new JMenuItem("Log Out");
        JMenuItem bar_userList = new JMenuItem("user list");
        JMenuItem bar_NewIssue = new JMenuItem("New Issue");
        JMenuItem bar_IssueDetail = new JMenuItem("Issue Detail");
        userMenu.add(bar_userName);
        userMenu.add(bar_NewID);
        userMenu.add(bar_LogOut);
        userMenu.add(bar_userList);
        issueMenu.add(bar_NewIssue);
        issueMenu.add(bar_IssueDetail);

        //add menu on menubar
        menuBar.add(userMenu);
        menuBar.add(issueMenu);
        menuBar.add(statisticsMenu);
        menuBar.add(optionMenu);
        menuBar.add(helpMenu);

        bar_NewIssue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newIssueUI();
            }
        });
        bar_NewID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUI();
            }
        });
        bar_userList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userList();
            }
        });
        bar_LogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.setVisible(false);
                loginUI();
            }
        });

        jf.setJMenuBar(menuBar);

    }
    private void mainframeTextBox(JFrame jf) {
        Vector<String> Menu = new Vector<>(Arrays.asList("Ticket", "Reported date", "Reporter",
                "Fixer", "Priority", "Assignee", "Status"));

        mfTable.txtToVector(ticketData, 1 , "");

        model = new DefaultTableModel(ticketData, Menu) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable mainTable = new JTable(model);
        JScrollPane tableScroll = new JScrollPane(mainTable);
        mainTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = mainTable.rowAtPoint(e.getPoint());

                if (row >= 0){
                    Object ticket = mainTable.getValueAt(row,0);
                    System.out.println(ticket);

                    try {
                        editIssueUI(ticket.toString());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }

            }
        });

        tableScroll.setSize(600, 600);
        tableScroll.setLocation(50, 200);

        jf.add(tableScroll);
    }
    private void refreshTable() {
        ticketData.clear();
        mfTable.txtToVector(ticketData, 1, "");

        model.setDataVector(ticketData, new Vector<>(Arrays.asList("Ticket", "Reported date", "Reporter",
                "Fixer", "Priority", "Assignee", "Status")));
        model.fireTableDataChanged();
    }
    private void refreshTable_with_keyword(String data) {
        ticketData.clear();
        mfTable.txtToVector(ticketData, 2, data);

        model.setDataVector(ticketData, new Vector<>(Arrays.asList("Ticket", "Reported date", "Reporter",
                "Fixer", "Priority", "Assignee", "Status")));
        model.fireTableDataChanged();
    }
    private void newIssueUI() {
        //
        // 새 창을 띄워서 뉴 이슈에 대한 값을 입력합니다.
        // 만약 값이 비어있는채로 세이브 버튼을 누른다면 저장되지 않게 블락하고
        // 닫기 버튼을 누르면 해당 창을 닫습니다.
        //
        JFrame frameNewIssue = new JFrame("newIssue");
        frameNewIssue.setSize(400, 400);
        frameNewIssue.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frameNewIssue.setLayout(null);

        // JLabel과 JTextField 생성
        JTextField ticket = new JTextField(20); frameNewIssue.add(ticket);
        JLabel lTicket = new JLabel("Ticket"); frameNewIssue.add(lTicket);
        JTextField reported = new JTextField(20);frameNewIssue.add(reported);
        JLabel lreported = new JLabel("reported Time");frameNewIssue.add(lreported);
        JTextField Reporter = new JTextField(20);frameNewIssue.add(Reporter);
        JLabel lReporter = new JLabel("Reporter");frameNewIssue.add(lReporter);
        JTextField Fixer = new JTextField(20);frameNewIssue.add(Fixer);
        JLabel lFixer = new JLabel("Fixer");frameNewIssue.add(lFixer);
        JTextField Priority = new JTextField(20);frameNewIssue.add(Priority);
        JLabel lPriority = new JLabel("Priority");frameNewIssue.add(lPriority);
        JTextField assignee = new JTextField(20);frameNewIssue.add(assignee);
        JLabel lassignee = new JLabel("Assignee");frameNewIssue.add(lassignee);
        JTextField status = new JTextField(20);frameNewIssue.add(status);
        JLabel lStatus = new JLabel("Status");frameNewIssue.add(lStatus);
        JButton saveBTN = new JButton("Save");frameNewIssue.add(saveBTN);
        JButton closeBTN = new JButton("Close");frameNewIssue.add(closeBTN);

        ticket.setSize(150,20);ticket.setLocation(200,20);
        lTicket.setSize(150,20);lTicket.setLocation(10,20);
        reported.setSize(150,20);reported.setLocation(200,50);
        lreported.setSize(150,20);lreported.setLocation(10,50);
        Reporter.setSize(150,20);Reporter.setLocation(200,80);
        lReporter.setSize(150,20);lReporter.setLocation(10,80);
        Fixer.setSize(150,20);Fixer.setLocation(200,110);
        lFixer.setSize(150,20);lFixer.setLocation(10,110);
        Priority.setSize(150,20);Priority.setLocation(200,140);
        lPriority.setSize(150,20);lPriority.setLocation(10,140);
        assignee.setSize(150,20);assignee.setLocation(200,170);
        lassignee.setSize(150,20);lassignee.setLocation(10,170);
        status.setSize(150,20);status.setLocation(200,200);
        lStatus.setSize(150,20);lStatus.setLocation(10,200);
        saveBTN.setSize(150,20);saveBTN.setLocation(10,250);
        closeBTN.setSize(150,20);closeBTN.setLocation(200,250);

        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String currentTime = time.format(formatter);
        reported.setText(currentTime);
        Reporter.setText(account.name);
        Priority.setText("Major");
        status.setText("New");
        Fixer.setText("None");
        assignee.setText("None");
        saveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String SaveText = ticket.getText() + "@" + reported.getText() + "@" + Reporter.getText() + "@" +
                        Fixer.getText()+ "@" + Priority.getText() + "@" + assignee.getText()+ "@" +
                        status.getText() + "@";
                System.out.println(SaveText);

                textbox tb = new textbox();
                tb.newIssue(SaveText);
                tb.txtToVector(ticketData, 1, "");
                frameNewIssue.setVisible(false);
            }
        });

        //키보드 입력 받아서 저장 메소드에 넘겨주는 기능 구현하면 됨.

        frameNewIssue.setVisible(true);
    }
}