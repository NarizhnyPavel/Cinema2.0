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

import static java.awt.event.MouseEvent.MOUSE_CLICKED;

public class Login<ButEvListener> {
    public  static JLabel userName = new JLabel("Login:");
    public  static JLabel password = new JLabel("Password:");

    public static JTextField enterUserName = new JTextField(20);
    public static JPasswordField enterPassword = new JPasswordField(20);

    public static JButton login = new JButton("Войти");

    public static JFrame jFrame = new JFrame("Вход");

    public static void main(String[] args) {
        WebLookAndFeel.install ();
        jFrame.setBounds(700, 350, 100, 50);

        JPanel grid = new JPanel();
        GridLayout gridLayout= new GridLayout(3,2,10,5);
        grid.setLayout(gridLayout);

        grid.add(userName);
        grid.add(enterUserName);
        grid.add(password);
        grid.add(enterPassword);
        login.addActionListener(new Login.ButEvListener());
        grid.add(login);
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
            if (!enterUserName.getText().isEmpty() && !enterPassword.getText().isEmpty())
            {
                String seanceId = ConnectionManager.getInstance().getAuthService().login(enterUserName.getText(), enterPassword.getText());
                ConnectionManager.getInstance().setSeanceId(seanceId);
                if (seanceId != null) {
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
                    }
                    jFrame.setVisible(false);
                }else
                    JOptionPane.showMessageDialog(null, "Неверное имя пользователя или пароль!", "Ошибка", JOptionPane.PLAIN_MESSAGE);

            }
        }
    }
}
