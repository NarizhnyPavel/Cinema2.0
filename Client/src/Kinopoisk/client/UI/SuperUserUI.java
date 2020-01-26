package Kinopoisk.client.UI;

import Kinopoisk.api.data.CinemaAssociation.CinemaAssociation;
import Kinopoisk.api.data.User.User;
import Kinopoisk.api.data.User.UserRole;
import Kinopoisk.client.DataMethods.CinemaAssociation.CinemaAssociations;
import Kinopoisk.client.DataMethods.User.Cerberus;
import Kinopoisk.client.connection.ConnectionManager;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Графический интерфейс пользователя (объекта класса {@link User}) c ролью ({@link UserRole}) SuperUser
 * @author Narizhny Pavel
 * @version 1.0
 */
public class SuperUserUI {
    public static JTable table = new JTable();
    public static JTable table1 = new JTable();
    public static JComboBox comboBox = new JComboBox();
    public static MutableComboBoxModel model;
    public static String NOT_SELECTABLE_OPTION = " - Select an Option - ";
    public static String newName = ""; // это костыль для сохранения имени нового модератора
    public static ArrayList<User> modsList;
    public static void main() throws Exception {
        JFrame jFrame = new JFrame("Интерфейс SUPERUSER");
        jFrame.setBounds(150, 150, 450, 320);

        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.setAlignmentY(Component.CENTER_ALIGNMENT);
        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.setAlignmentY(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel("<html>Для добавления:<br>1.введите имя пользователя<br>в первый столбец<br>2.нажмите \"Добавить\"" +
                "<br><br>Для удаления:<br>1.Нажмите на имя удаляемого модератора<br>2.Нажмите \"Удалить\"</html>");
        JLabel label1 = new JLabel("<html>Для назначения курирующего модератора:<br>1.выберите имя модератора<br>2.выберите модератора в выпадающем списке</html>");
        JButton buttonAdd = new JButton("Добавить");
        buttonAdd.addActionListener( new ButEvListener1());
        JButton buttonDel = new JButton("Удалить");
        buttonDel.addActionListener( new ButEvListener2());
        JButton buttonSet = new JButton("Назначить");
        buttonSet.addActionListener( new ButEvListener3());

        JTabbedPane tabbedPane = new JTabbedPane();
        jFrame.add(tabbedPane);
        tabbedPane.addTab("Модераторы", panel1);
        tabbedPane.addTab("Назначение", panel2);

        modsList = (ArrayList<User>) Cerberus.getInstance().getModerators();
        table.setModel(dataModel);
        table1.setModel(dataModel2);
        table1.getColumnModel().getColumn(0).setWidth(150);
        table.setFillsViewportHeight(true);

        panel1.add(table, BorderLayout.NORTH);
        panel1.add(label, BorderLayout.CENTER);
        panel1.add(buttonAdd, BorderLayout.WEST);
        panel1.add(buttonDel, BorderLayout.EAST);

        panel2.add(table1, BorderLayout.NORTH);


        comboBox.setModel(aModel);
        comboBox.setSelectedIndex(0);
        model = (MutableComboBoxModel)comboBox.getModel();
        table1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    if (table1.getSelectedRow() != -1) {
                        comboBox.getModel().setSelectedItem(CinemaAssociations.getInstance().getCinemaAssociations().get(table1.getSelectedRow()).getMod().getUserName());
                        //model.setSelectedItem();
                        comboBox.updateUI();

                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


        panel2.add(comboBox, BorderLayout.SOUTH);
        panel2.add(label1, BorderLayout.CENTER);
        panel2.add(buttonSet, BorderLayout.EAST);

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    public static DefaultComboBoxModel<String> aModel = new DefaultComboBoxModel<String>() {
        private static final long serialVersionUID = 1L;
        boolean selectionAllowed = true;
        Object selectedItem = this.getElementAt(0);

        @Override
        public Object getSelectedItem() {
            return selectedItem;
        }

        @Override
        public int getSize() {
            return modsList.size() + 1;
        }

        @Override
        public void setSelectedItem(Object anObject) {
            if (!NOT_SELECTABLE_OPTION.equals(anObject)) {
                super.setSelectedItem(anObject);
                selectedItem = anObject;
            } else if (selectionAllowed) {
                // Allow this just once
                selectionAllowed = false;
                super.setSelectedItem(anObject);
                selectedItem = anObject;
            }
        }

        @Override
        public String getElementAt(int index) {
            if (index == 0)
                return NOT_SELECTABLE_OPTION;
            else {
                return modsList.get(index - 1).getUserName();
            }
        }

        @Override
        public void addListDataListener(ListDataListener l) {

        }

        @Override
        public void removeListDataListener(ListDataListener l) {

        }

    };

    public static AbstractTableModel dataModel = new AbstractTableModel() {
        @Override
        public int getRowCount() {
            return modsList.size() + 1;
        }

        @Override
        public int getColumnCount() {
            return 3;
        }

        @Override
        public String getColumnName(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return "Модератор";
                case 1:
                    return "Последний вход";
                case 2:
                    return "Количество нагрузки";
            }
            return "";
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return String.class;
                case 1:
                    return String.class;
                default:
                    return int.class;
            }
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return true;
                case 1:
                case 2:
                    return false;
            }
            return false;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (rowIndex < modsList.size()) {
                if (modsList.get(rowIndex) != null) {
                    User moderator = modsList.get(rowIndex);
                    switch (columnIndex) {
                        case 0:
                            return moderator.getUserName();
                        case 1:
                            return moderator.getLastSession();
                        case 2:
                            return CinemaAssociations.getInstance().getListOf(moderator).size();
                    }
                }
            } else {
                if (columnIndex == 0)
                    return newName;
            }
            return null;
        }

        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            if (rowIndex < modsList.size()) {
                User mod = modsList.get(rowIndex);
                if (columnIndex == 0)
                if (aValue.toString().compareTo(mod.getUserName()) != 0) {
                    mod.setUserName("" + aValue);
                    Cerberus.getInstance().updateModerator(mod);
                    dataModel.fireTableDataChanged();
                }

            }
            else
                newName = "" + aValue;
        }
    };

    public static AbstractTableModel dataModel2 = new AbstractTableModel() {
        @Override
        public int getRowCount() {
            return CinemaAssociations.getInstance().getCinemaAssociations().size();
        }

        @Override
        public TableModelListener[] getTableModelListeners() {
            return super.getTableModelListeners();
        }

        @Override
        public int getColumnCount() {
            return 1;
        }

        public String getColumnName(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return "Кинообъединения";
            }
            return "";
        }

        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return String.class;
            }
            return String.class;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return false;
            }
            return false;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return CinemaAssociations.getInstance().getCinemaAssociations().get(rowIndex).getName();
            }
            return null;
        }
    };

    public static class ButEvListener1 implements ActionListener{
        public void actionPerformed (ActionEvent e) {
            if (newName != ""){
                Cerberus.getInstance().addModerator(new User(newName));
                modsList = (ArrayList<User>) Cerberus.getInstance().getModerators();
                int index = modsList.size() - 1;
                String info = null;
                info = "Name: " + newName + "\n" + modsList.get(index).getInfo();
                JOptionPane.showMessageDialog(null, info, "Добавлен пользователь", JOptionPane.PLAIN_MESSAGE);
                newName = "";
                dataModel.fireTableDataChanged();
            } else{
                JOptionPane.showMessageDialog(null, "Поле \"Имя\" не содержит значения.\nВведите имя нового модератора,\nуберите курсор с поля ввода\nа затем нажмите \"Добавить\"", "Ошибка", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    public static class ButEvListener2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int indexCol = table.getSelectedColumn();
            int indexRow = table.getSelectedRow();
            if (indexCol == 0 && indexRow != -1) {
                User user = modsList.get(indexRow);
                Cerberus.getInstance().removeModerator(user);
                modsList = (ArrayList<User>) Cerberus.getInstance().getModerators();
                dataModel.fireTableDataChanged();
            }
            else{
                JOptionPane.showMessageDialog(null, "Пользователь не выбран!\n установите курсор на имени удаляемого модератора", "Удаление пользователя", JOptionPane.PLAIN_MESSAGE);
            }

        }
    }

    public static class ButEvListener3 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (table1.getSelectedRow() != -1 && comboBox.getSelectedIndex() != 0) {
                CinemaAssociation cinemaAssociation = CinemaAssociations.getInstance().getCinemaAssociations().get(table1.getSelectedRow());
                User mod = null;
                mod = modsList.get(comboBox.getSelectedIndex() - 1);
                cinemaAssociation.setMod(mod);
                ConnectionManager.getInstance().getDataService().updateAssociation(cinemaAssociation);
                JOptionPane.showMessageDialog(null, "На кинообъединение " + cinemaAssociation.getName() + " назначен модератор " + mod.getUserName(), "Назначение", JOptionPane.PLAIN_MESSAGE);
            } else{
                JOptionPane.showMessageDialog(null, "Не выбрана пара для назначения", "Ошибка", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

}




