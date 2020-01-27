package Kinopoisk.client.UI;

import Kinopoisk.api.data.User.User;
import Kinopoisk.client.DataMethods.User.Cerberus;
import Kinopoisk.client.connection.ConnectionManager;
import com.alee.laf.WebLookAndFeel;
import com.alee.managers.UIManagers;
import layout.TableLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import static java.awt.event.MouseEvent.MOUSE_CLICKED;

/**
 * Графический интерфейс авторизации пользователей
 * @author Narizhny Pavel
 * @version 1.0
 */
public class Login {

    public  static JLabel userName = new JLabel("Login:");

    public  static JLabel password = new JLabel("Password:");

    public static JTextField enterUserName = new JTextField(20);

    public static JTextField host = new JTextField(20);

    public static JPasswordField enterPassword = new JPasswordField(20);

    public static JButton login = new JButton("Войти");

    public static JButton cancel = new JButton("Отмена");

    public static JFrame jFrame = new JFrame("Вход");

    public static void main(String[] args) {
        //WebLookAndFeel.install ();
        jFrame.setBounds(700,
                350, 100, 50);

        JPanel grid = new JPanel();
        GridLayout gridLayout= new GridLayout(4,2,10,5);
        grid.setLayout(gridLayout);

        host.setText("http://localhost:8085");
        grid.add(host);
        grid.add(new JPanel());
        grid.add(userName);
        grid.add(enterUserName);
        grid.add(password);
        grid.add(enterPassword);
        login.addActionListener(new Login.ButEvListener());
        cancel.addActionListener(new ButEventListner1());
        grid.add(login);
        grid.add(cancel);
        authorizeViewer();

        jFrame.getContentPane().add(grid);

        jFrame.pack();

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        login.setFocusPainted(true);
    }

    private static void authorizeViewer(){
        enterUserName.setText("mod3");
        enterPassword.setText("mod3");
    }

    public static class ButEvListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!enterUserName.getText().isEmpty() && enterPassword.getPassword().length != 0)
            {
                String seanceId = null;
                ConnectionManager.setUrl("" + host.getText());
                try {
                    seanceId = ConnectionManager.getInstance().getAuthService().login(enterUserName.getText(), enterPassword.getPassword());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                ConnectionManager.getInstance().setSeanceId(seanceId);
                if (!(seanceId.equals("passError") && seanceId.equals("nameError"))) {
                    User authorizedUser = ConnectionManager.getInstance().getDataService().getUser(enterUserName.getText());
                    Cerberus.getInstance().setCurUser(authorizedUser);
                    Cerberus.getInstance().setSeanceId(seanceId);
                    Cerberus.getInstance().updateSessionOf(authorizedUser);
                    switch (authorizedUser.getRole().getId()){
                        case 1:
                            try {
                                SuperUserUI.main();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            break;
                        case 2:
                            try {
                                ModeratorUI.main();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            break;
                        case 3:
                            try {
                                ViewerUI.main();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            break;
                        case 4:
                            try {
                                TestFrame.main();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            break;
                    }
                    jFrame.setVisible(false);
                }else{
                    if (seanceId.equals("nameError"))
                        JOptionPane.showMessageDialog(null, "Неверное имя пользователя!", "Ошибка", JOptionPane.PLAIN_MESSAGE);
                    if (seanceId.equals("passError"))
                        JOptionPane.showMessageDialog(null, "Неверный пароль!", "Ошибка", JOptionPane.PLAIN_MESSAGE);
                }

            }
        }
    }

    public static class ButEventListner1 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(-1);
        }
    }
}
