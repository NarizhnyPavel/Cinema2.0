package Kinopoisk.client.UI;
import Kinopoisk.api.data.CinemaAssociation.*;
import Kinopoisk.api.data.CinemaAssociation.Film.*;
import Kinopoisk.api.data.CinemaStudio.CinemaStudio;
import Kinopoisk.api.data.Person.Person;
import Kinopoisk.client.DataMethods.Person.Professions;
import Kinopoisk.api.data.User.User;
import Kinopoisk.client.DataMethods.CinemaAssociation.CinemaAssociations;
import Kinopoisk.client.DataMethods.CinemaAssociation.Film.*;
import Kinopoisk.client.DataMethods.CinemaStudios;
import Kinopoisk.client.DataMethods.Countries;
import Kinopoisk.client.DataMethods.Person.Persons;
import Kinopoisk.client.DataMethods.User.Cerberus;
import Kinopoisk.client.connection.ConnectionManager;
import com.alee.laf.WebLookAndFeel;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.table.AbstractTableModel;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

public class ModeratorUI extends Applet implements ActionListener {
    private static JTable tableModeration = new JTable();
    private static JTable tableAssociations = new JTable();
    private static JTable tableFilms = new JTable();
    private final static boolean shouldFill = true;
    private final static boolean shouldWeightX = true;
    private final static boolean RIGHT_TO_LEFT = false;
    private static User curMod = Cerberus.getInstance().getCurrUser();
    private static ArrayList<CinemaAssociation> listAssos = new ArrayList<CinemaAssociation>();
    private static ArrayList<Film> curListFilms = new ArrayList<Film>();
    static void main() {
        //ОСНОВНОЕ ОКНО
//        WebLookAndFeel.install ();
        JFrame jFrame = new JFrame("Интерфейс модератора");
        jFrame.setBounds(700, 350, 400, 400);

        JTabbedPane tabbedPane = new JTabbedPane();
        jFrame.add(tabbedPane);

        new ModeratorUI();
        //ВКЛАДКА "Главная"
        JPanel panelMain = new JPanel(new BorderLayout());
        tabbedPane.addTab("Главная", panelMain);
        panelMain.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelMain.setLayout(new GridLayout(3, 1));
        JLabel label0 = new JLabel("<html>Вход выполнил: <br>" + curMod.getUserName() + "</html>");
        panelMain.add(label0);
        JLabel label1 = new JLabel("Коментарии и рецензии на модерации");
        panelMain.add(label1);
        tableModeration.setModel(dataModelReviews);
        panelMain.add(tableModeration);


        //ВКЛАДКА "Кинообъединения"
        listAssos = CinemaAssociations.getInstance().getListOf(curMod);
        JPanel panelAssociation = new JPanel(new BorderLayout());
        tabbedPane.addTab("Кинообъединения", panelAssociation);
        tableAssociations.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (tableAssociations.getSelectedRow() != -1 && tableAssociations.getSelectedRow() < listAssos.size()){
                        addAss(false);
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
        addComponentsToPane(panelAssociation);

        //ВКЛАДКА "Фильмы"
        JPanel panelFilms = new JPanel(new BorderLayout());
        tabbedPane.addTab("Фильмы", panelFilms);
        tableFilms.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (tableFilms.getSelectedRow() != -1 && tableFilms.getSelectedRow() < curListFilms.size()){
                        addFilm(false);
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
        addComponentsToPane2(panelFilms);

        //----------------//
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
    // окно добавленя объединения
    private static JFrame jFrame1 = new JFrame();
    private static void addAss(boolean mode) {
        jFrame1.setBounds(700, 350, 400, 150);
        jFrame1.setTitle("Добавление кинообъединения");
        jFrame1.getContentPane().removeAll();
        jFrame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addComponentsToPane1(jFrame1.getContentPane(), mode);
        name.setText("");
        comboBox.setSelectedIndex(0);
        if (!mode){
            jFrame1.setTitle("Редактирование кинообъединения");
            CinemaAssociation ass = listAssos.get(tableAssociations.getSelectedRow());
            name.setText(ass.getName());
            comboBox.setSelectedIndex(ass.getType().getType());
        }

        jFrame1.setVisible(true);
    }
    // окно добавленя фильма
    private static JFrame jFrame2 = new JFrame();
    private static void addFilm(boolean mode) {
        jFrame2.setBounds(700, 350, 550, 300);
        jFrame2.setTitle("Добавление фильма");
        jFrame2.getContentPane().removeAll();
        jFrame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addComponentsToPane3(jFrame2.getContentPane(), mode);
        name.setText("");
        date1.setText("");
        date2.setText("");
        comboBox2.setSelectedIndex(0);
        comboBox3.setSelectedIndex(0);
        country.setText("");
        comboBox4.setSelectedIndex(0);
        if (!mode){
            jFrame2.setTitle("Редактирование фильма");
            Film film = curListFilms.get(tableFilms.getSelectedRow());
            name.setText(film.getName());
            date1.setText(film.getReleaseDate().getWorld());
            date2.setText(film.getReleaseDate().getRus());
            comboBox2.setSelectedIndex(Persons.getInstance().getDirectorIndex(film.getDir())+1);
            comboBox3.setSelectedIndex(Persons.getInstance().getWriterIndex(film.getWriter())+1);
            country.setText(film.getCountry().getName());
            comboBox4.setSelectedIndex(CinemaStudios.getInstance().getStudioIndex(film.getStudio()) + 1);
            comboBox5.setSelectedIndex(Genres.getInstance().getGenreIndex(film.getGenre()) + 1);
            comboBox6.setSelectedIndex(AgeLimits.getInstance().getAgeLimitIndex(film.getAgeLim()) + 1);
        }
        jFrame2.setVisible(true);
    }
    private static JFrame jFrame3 = new JFrame();
    private static void addPerson(boolean mode) { // 1- director 0 - writer
        jFrame3.setBounds(700, 350, 400, 300);
        if (mode)
            jFrame3.setTitle("Добавление режиссёра");
        else
            jFrame3.setTitle("Добавление сценариста");
        jFrame3.getContentPane().removeAll();
        jFrame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addComponentsToPane4(jFrame3.getContentPane(), mode);
        name1.setText("");
        date3.setText(""); // birth date
        country1.setText(""); // birth country

        jFrame3.setVisible(true);
    }
    private static JFrame jFrame4 = new JFrame();
    private static void addStudio() {
        jFrame4.setBounds(700, 350, 400, 300);
        jFrame4.setTitle("Добавление режиссёра");
        jFrame4.getContentPane().removeAll();
        jFrame4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addComponentsToPane5(jFrame4.getContentPane());
        name2.setText("");
        date4.setText(""); // birth date
        country2.setText(""); // birth country

        jFrame4.setVisible(true);
    }

    public static boolean profession;
    private static void addComponentsToPane4(Container pane, boolean mode) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        if (shouldFill) {
            // натуральная высота, максимальная ширина
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        if (shouldWeightX) {
            c.weightx = 0.5;
        }

        JLabel label1;

        if (mode)
            label1 = new JLabel("Добавление режиссёра");
        else
            label1 = new JLabel("Добавление сценариста");

        JLabel label2 = new JLabel("Имя");
        JLabel label3 = new JLabel("Дата рождения");
        JLabel label4 = new JLabel("Страна рождения");

        label1.setFont(new Font("Serif", Font.BOLD, 20));
        c.insets = new Insets(20, 5, 5, 5);
        pane.add(label1, c);

        c.gridheight = 1;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(5, 5, 5, 5);
        pane.add(label1, c);

        c.gridx = 0;
        c.gridy = 2;
        pane.add(label2, c);
        c.gridx = 2;
        c.gridy = 2;
        pane.add(name1, c);

        c.gridx = 0;
        c.gridy = 3;
        pane.add(label3, c);
        c.gridx = 2;
        c.gridy = 3;
        pane.add(date3, c);

        c.gridx = 0;
        c.gridy = 4;
        pane.add(label4, c);
        c.gridx = 2;
        c.gridy = 4;
        pane.add(country1, c);

        profession = mode;
        JButton save1 = new JButton("Добавить");
        save1.addActionListener(new ButEvListener8());
        c.gridwidth = 2;
        c.gridheight = 1;
        c.gridx = 1;
        c.gridy = 5;
        pane.add(save1, c);

    }
    private static void addComponentsToPane5(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        if (shouldFill) {
            // натуральная высота, максимальная ширина
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        if (shouldWeightX) {
            c.weightx = 0.5;
        }

        JLabel label1;

        label1 = new JLabel("Добавление киностудии");

        JLabel label2 = new JLabel("Название");
        JLabel label3 = new JLabel("Год основания");
        JLabel label4 = new JLabel("Страна");

        label1.setFont(new Font("Serif", Font.BOLD, 20));
        c.insets = new Insets(20, 5, 5, 5);
        pane.add(label1, c);

        c.gridheight = 1;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(5, 5, 5, 5);
        pane.add(label1, c);

        c.gridx = 0;
        c.gridy = 2;
        pane.add(label2, c);
        c.gridx = 2;
        c.gridy = 2;
        pane.add(name2, c);

        c.gridx = 0;
        c.gridy = 3;
        pane.add(label3, c);
        c.gridx = 2;
        c.gridy = 3;
        pane.add(date4, c);

        c.gridx = 0;
        c.gridy = 4;
        pane.add(label4, c);
        c.gridx = 2;
        c.gridy = 4;
        pane.add(country2, c);

        JButton save1 = new JButton("Добавить");
        save1.addActionListener(new ButEvListener12());
        c.gridwidth = 2;
        c.gridheight = 1;
        c.gridx = 1;
        c.gridy = 5;
        pane.add(save1, c);

    }

    // cinemaAssociations
    public static void addComponentsToPane(Container pane) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }


        if (shouldFill) {
            // натуральная высота, максимальная ширина
            c.fill = GridBagConstraints.HORIZONTAL;
        }
        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        pane.setLayout(new GridBagLayout());
//        pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);


        JLabel label2 = new JLabel("Курируемые кинообъединения");
        label2.setFont(new Font("Serif", Font.BOLD, 20));
        JLabel label3 = new JLabel("<html>Для добавления и удаления используйте соответствующие кнопки справа" +
                "<br>Для редактирования два раза нажмите на название</html>");
        c.insets = new Insets(20, 5, 5, 5);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(label2, c);

        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 1;
        pane.add(label3, c);

        tableAssociations.setModel(dataModelAssociations);
        c.gridwidth = 3;
        c.gridheight = 2;
        c.gridx = 0;
        c.gridy = 2;
        pane.add(tableAssociations, c);

        JButton buttonDel = new JButton("-");
        buttonDel.addActionListener( new ButEvListener2());
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 3;
        c.gridy = 3;
        pane.add(buttonDel, c);

        JButton buttonAdd = new JButton("+");
        buttonAdd.addActionListener( new ButEvListener1());
        c.gridx = 3;
        c.gridy = 2;
        pane.add(buttonAdd, c);
    }
    // add associations
    final public static String NOT_SELECTABLE_OPTION = " - Select an Option - ";
    public static JTextField name = new JTextField(20);
    public static JTextField name1 = new JTextField(20);
    public static JTextField name2 = new JTextField(20);
    public static JComboBox comboBox = new JComboBox();
    public static void addComponentsToPane1(Container pane, boolean mode) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        if (shouldFill) {
            // натуральная высота, максимальная ширина
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        if (shouldWeightX) {
            c.weightx = 0.5;
        }

        JLabel label1 = new JLabel("Название: ");

        JLabel label2 = new JLabel("Тип: ");


        DefaultComboBoxModel<String> aModel = new DefaultComboBoxModel<String>() {
            private static final long serialVersionUID = 1L;
            boolean selectionAllowed = true;
            Object selectedItem = this.getElementAt(0);


            @Override
            public Object getSelectedItem() {
                return selectedItem;
            }

            @Override
            public int getSize() {
                return 4;
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
                else
                    switch (index){
                        case 1:
                            return TypeAssociations.GROUP.getTypeString();
                        case 2:
                            return TypeAssociations.SINGLE.getTypeString();
                        case 3:
                            return TypeAssociations.SERIAL.getTypeString();
                    }
                return "";
            }

            @Override
            public void addListDataListener(ListDataListener l) {

            }

            @Override
            public void removeListDataListener(ListDataListener l) {

            }

        };
        comboBox.setModel(aModel);

        JButton butAdd = new JButton("Добавить");
        butAdd.addActionListener(new ButEvListener3());
        JButton butSave = new JButton("Сохранить");
        butSave.addActionListener(new ButEvListener31());
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(label1, c);

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        pane.add(name, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        pane.add(label2, c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        pane.add(comboBox, c);

        c.gridwidth = 1;
        c.gridheight = 2;
        c.gridx = 2;
        c.gridy = 0;
        if (mode)
        pane.add(butAdd);
        else
            pane.add(butSave);

    }
    // films
    public static void updateFilms(){
        if (comboBox1.getSelectedIndex() != 0)
            curListFilms = (ArrayList<Film>) Films.getInstance().getFilmsOf(listAssos.get(comboBox1.getSelectedIndex() - 1));
        else
            curListFilms = new ArrayList<Film>();
        dataModelFilms.fireTableDataChanged();

    }
    public static void updateAss(){
        listAssos = CinemaAssociations.getInstance().getListOf(curMod);
        dataModelAssociations.fireTableDataChanged();
    }
    public static JComboBox comboBox1 = new JComboBox();
    public static void addComponentsToPane2(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        if (shouldFill) {
            // натуральная высота, максимальная ширина
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        if (shouldWeightX) {
            c.weightx = 0.5;
        }

        JLabel label1 = new JLabel("Добавление фильмов");
        JLabel label2 = new JLabel("<html>Для редактирования информации о фильме:<br>нажмите два раза на название фильма<br><br>Выберите кинообъединение: </html>");

        DefaultComboBoxModel<String> aModel = new DefaultComboBoxModel<String>() {
            private static final long serialVersionUID = 1L;
            boolean selectionAllowed = true;
            Object selectedItem = this.getElementAt(0);

            @Override
            public Object getSelectedItem() {
                return selectedItem;
            }

            @Override
            public int getSize() {
                return listAssos.size() + 1;
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
                updateFilms();
            }

            @Override
            public String getElementAt(int index) {

                if (index == 0)
                    return NOT_SELECTABLE_OPTION;
                else
                    return listAssos.get(index - 1).getName();
            }

            @Override
            public void addListDataListener(ListDataListener l) {

            }

            @Override
            public void removeListDataListener(ListDataListener l) {

            }

        };
        comboBox1.setModel(aModel);

        JButton butAdd = new JButton("+");
        butAdd.addActionListener(new ButEvListener4());
        JButton butDel = new JButton("-");
        butDel.addActionListener(new ButEvListener5());
        c.gridheight = 1;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        label1.setFont(new Font("Serif", Font.BOLD, 20));
        c.insets = new Insets(20, 5, 5, 5);
        pane.add(label1, c);

        c.gridheight = 1;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(5, 5, 5, 3);
        pane.add(label2, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        pane.add(comboBox1, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridheight = 2;
        tableFilms.setModel(dataModelFilms);
        pane.add(tableFilms, c);

        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 5;
        c.gridy = 3;
        pane.add(butAdd, c);

        c.gridx = 5;
        c.gridy = 4;
        pane.add(butDel, c);

    }
    // add films
//    public static JTextField name = new JTextField(40);
    public static JTextField date1 = new JTextField(20);
    public static JTextField date2 = new JTextField(20);
    public static JTextField date3 = new JTextField(20);
    public static JTextField date4 = new JTextField(20);
    public static JTextField country = new JTextField(20);
    public static JTextField country1 = new JTextField(20);
    public static JTextField country2 = new JTextField(20);
    public static JComboBox comboBox2 = new JComboBox(); // режиссёр
    public static JComboBox comboBox3 = new JComboBox(); // сценарист
    public static JComboBox comboBox4 = new JComboBox(); // киностудия
    public static JComboBox comboBox5 = new JComboBox(); // жанр
    public static JComboBox comboBox6 = new JComboBox(); // возрастное ограничение

    public static Vector<Person> people1;
    public static Vector<Person> people2;
    public static Vector<CinemaStudio> studios;
    public static void addComponentsToPane3(Container pane, boolean mode) { // 1- добавление 2- редактирование
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        if (shouldFill) {
            // натуральная высота, максимальная ширина
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        if (shouldWeightX) {
            c.weightx = 0.5;
        }

        //JLabel label0 = new JLabel("Создание фильма: ");
        JLabel label1 = new JLabel("Название");
        JLabel label2 = new JLabel("Дата выхода");
        JLabel label3 = new JLabel("в Мире");
        JLabel label4 = new JLabel("в России");
        JLabel label5 = new JLabel("Режиссёр");
        JLabel label6 = new JLabel("Сценарист");
        JLabel label7 = new JLabel("Страна");
        JLabel label8 = new JLabel("Киностудия");
        JLabel label9 = new JLabel("Жанр");
        JLabel label10 = new JLabel("Возраст. огр-е");

        comboBox2.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value==null){
                    setText(NOT_SELECTABLE_OPTION);
                }else{
                    setText(((Person)value).getName());}
                return this;
            }
        });
        people1 = new Vector<>(ConnectionManager.getInstance().getDataService().getPersonsWho(Professions.getInstance().getProfession(1)));
        people1.add(0,null);
        DefaultComboBoxModel<Person> aModel2 = new DefaultComboBoxModel<Person>(people1) {
            private static final long serialVersionUID = 1L;
            boolean selectionAllowed = true;

            @Override
            public int getSize() {
                return people1.size();
            }

            @Override
            public Person getElementAt(int index) {
                return people1.get(index);
            }

        };
        comboBox2.setModel(aModel2);
        comboBox3.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value==null){
                    setText(NOT_SELECTABLE_OPTION);
                }else{
                setText(((Person)value).getName());}
                return this;
            }
        });
        people2 = new Vector<>(ConnectionManager.getInstance().getDataService().getPersonsWho(Professions.getInstance().getProfession(2)));
        people2.add(0,null);
        DefaultComboBoxModel<Person> aModel3 = new DefaultComboBoxModel<Person>(people2) {
            private static final long serialVersionUID = 1L;
            boolean selectionAllowed = true;

            @Override
            public int getSize() {
                return people2.size();
            }

            @Override
            public Person getElementAt(int index) {
                return people2.get(index);
            }

        };
        comboBox3.setModel(aModel3);
        comboBox4.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value==null){
                    setText(NOT_SELECTABLE_OPTION);
                }else{
                    setText(((CinemaStudio)value).getName());}
                return this;
            }
        });
        studios = new Vector<>(ConnectionManager.getInstance().getDataService().getCinemaStudios());
        studios.add(0,null);
        DefaultComboBoxModel<CinemaStudio> aModel4 = new DefaultComboBoxModel<CinemaStudio>() {
            private static final long serialVersionUID = 1L;
            boolean selectionAllowed = true;

            @Override
            public int getSize() {
                return studios.size();
            }

            @Override
            public CinemaStudio getElementAt(int index) {
                return studios.get(index);
            }

        };
        comboBox4.setModel(aModel4);
        comboBox5.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value==null){
                    setText(NOT_SELECTABLE_OPTION);
                }else{
                    setText(((Genre)value).getDiscript());}
                return this;
            }
        });
        Vector<Genre> genres = new Vector<>(ConnectionManager.getInstance().getDataService().getGenres());
        genres.add(0,null);
        DefaultComboBoxModel<Genre> aModel5 = new DefaultComboBoxModel<Genre>() {
            private static final long serialVersionUID = 1L;
            boolean selectionAllowed = true;

            @Override
            public int getSize() {
                return genres.size();
            }

            @Override
            public Genre getElementAt(int index) {
                return genres.get(index);
            }

        };
        comboBox5.setModel(aModel5);
        comboBox6.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value==null){
                    setText(NOT_SELECTABLE_OPTION);
                }else{
                    setText(((AgeLimit)value).getDiscript());}
                return this;
            }
        });
        Vector<AgeLimit> ageLimits = new Vector<>(ConnectionManager.getInstance().getDataService().getAgeLimits());
        ageLimits.add(0,null);
        DefaultComboBoxModel<AgeLimit> aModel6 = new DefaultComboBoxModel<AgeLimit>() {
            private static final long serialVersionUID = 1L;
            boolean selectionAllowed = true;

            @Override
            public int getSize() {
                return ageLimits.size();
            }

            @Override
            public AgeLimit getElementAt(int index) {
                return ageLimits.get(index);
            }

        };
        comboBox6.setModel(aModel6);

        JButton save1 = new JButton("Добавить");
        save1.addActionListener(new ButEvListener6());
        JButton save2 = new JButton("Сохранить");
        save2.addActionListener(new ButEvListener7());
        JButton addDir = new JButton("+");
        addDir.addActionListener(new ButEvListener9());
        JButton addWrit = new JButton("+");
        addWrit.addActionListener(new ButEvListener10());
        JButton addStudio = new JButton("+");
        addStudio.addActionListener(new ButEvListener11());

        c.gridheight = 1;
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 0;
        //pane.add(label0, c);

        c.gridx = 0;
        c.gridy = 1;
        pane.add(label1, c);
        c.gridx = 2;
        c.gridy = 1;
        pane.add(name, c);

        c.gridx = 0;
        c.gridy = 2;
        pane.add(label2, c);

        c.gridx = 2;
        c.gridy = 2;
        pane.add(label3, c);
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 3;
        pane.add(date1, c);

        c.gridx = 4;
        c.gridy = 2;
        pane.add(label4, c);
        c.gridx = 4;
        c.gridy = 3;
        pane.add(date2, c);

        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 4;
        pane.add(label5, c);
        c.gridx = 2;
        pane.add(comboBox2, c);
        c.gridx = 4;
        pane.add(addDir, c);

        c.gridx = 0;
        c.gridy = 5;
        pane.add(label6, c);
        c.gridx = 2;
        pane.add(comboBox3, c);
        c.gridx = 4;
        pane.add(addWrit, c);

        c.gridx = 0;
        c.gridy = 6;
        pane.add(label7, c);
        c.gridx = 2;
        pane.add(country, c);

        c.gridx = 0;
        c.gridy = 7;
        pane.add(label8, c);
        c.gridx = 2;
        pane.add(comboBox4, c);
        c.gridx = 4;
        pane.add(addStudio, c);

        c.gridx = 0;
        c.gridy = 8;
        pane.add(label9, c);
        c.gridx = 2;
        pane.add(comboBox5, c);

        c.gridx = 0;
        c.gridy = 9;
        pane.add(label10, c);
        c.gridx = 2;
        pane.add(comboBox6, c);

        c.gridx = 2;
        c.weightx = 1;
        c.gridy = 11;
        if (mode)
            pane.add(save1, c);
        else
            pane.add(save2, c);
    }
    // tables
    public static AbstractTableModel dataModelReviews = new AbstractTableModel() {
        @Override
        public int getRowCount() {
            int size = Reviews.getInstance().getReviews().size();
            if (size < 10)
                return 10;
            else
                return size;
        }

        @Override
        public int getColumnCount() {
            return 1;
        }

        @Override
        public String getColumnName(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return "Комментарии и рецензии";
            }
            return "";
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return String.class;
            }
            return null;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;

        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (rowIndex < Reviews.getInstance().getReviews().size())
                return Reviews.getInstance().getReviews().get(rowIndex);
            else
                return "рецензия " + (rowIndex + 1);
        }

        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        }
    };
    public static AbstractTableModel dataModelAssociations = new AbstractTableModel() {
        @Override
        public int getRowCount() {
            int size = listAssos.size();
            if (size < 10)
                return 10;
            else
                return size;
        }

        @Override
        public int getColumnCount() {
            return 1;
        }

        @Override
        public String getColumnName(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return "Кинообъединения";
            }
            return "";
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return String.class;
            }
            return null;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;

        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (rowIndex < listAssos.size())
                return listAssos.get(rowIndex).getName();
            return "";
        }

        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        }

    };
    public static AbstractTableModel dataModelFilms = new AbstractTableModel() {
        @Override
        public int getRowCount() {
            if (curListFilms != null) {
                    int size = curListFilms.size();
                if (size < 10)
                    return 10;
                else
                    return size;
            }
            return 10;
        }

        @Override
        public int getColumnCount() {
            return 1;
        }

        @Override
        public String getColumnName(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return "Фильмы";
            }
            return "";
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return String.class;
            }
            return null;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;

        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (curListFilms != null) {
                if (rowIndex < curListFilms.size())
                    return curListFilms.get(rowIndex).getName();
            }
            return "";
        }

        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        }
    };

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    // (открытие окна) добавить объединение
    public static class ButEvListener1 implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            //открытие окна создания нового кинообъединения
            addAss(true);
        }
    }
    // удалить объединение
    public static class ButEvListener2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int indexRow = tableAssociations.getSelectedRow();
            if (indexRow != -1)
                if(indexRow < CinemaAssociations.getInstance().getCinemaAssociations().size()){
                    CinemaAssociation association = CinemaAssociations.getInstance().getCinemaAssociations().get(indexRow);
                    if (Films.getInstance().getFilmsOf(association).size() == 0) {
                        CinemaAssociations.getInstance().removeAssociation(association.getName());
                        listAssos = CinemaAssociations.getInstance().getListOf(curMod);
                        dataModelAssociations.fireTableDataChanged();
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Объединение содержит фильмы!\n Во вкладке \"Фильмы\" удалите соответствующие фильмы", "Ошибка", JOptionPane.PLAIN_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Объединение не выбрано!\n установите курсор на имени удаляемого объединения", "Удаление кинообъединения", JOptionPane.PLAIN_MESSAGE);
                }

        }
    }
    // добавить объединение
    public static class ButEvListener3 implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            if ((name.getText() != "") && (name.getText().length() >=2) && (comboBox.getSelectedIndex() != 0)){
                TypeAssociation type = TypeAssociations.GROUP;
                CinemaAssociations.getInstance().addAssociation(new CinemaAssociation(type.getType(comboBox.getSelectedIndex()), name.getText(), curMod));
                listAssos = CinemaAssociations.getInstance().getListOf(curMod);
                String info = "Название: " + name.getText() + "\n" + "Тип: " + type.getType(comboBox.getSelectedIndex()).getTypeString();
                JOptionPane.showMessageDialog(null, "Вы добавили кинообъединение: \n" + info, "Добавлено кинообъединение", JOptionPane.PLAIN_MESSAGE);
                dataModelAssociations.fireTableDataChanged();
                jFrame1.setVisible(false);
            }
            else
                JOptionPane.showMessageDialog(null, "Имя должно содеражть не менее 2 символов \n Должен быть выбран тип объединения", "Ошибка", JOptionPane.PLAIN_MESSAGE);
        }
    }
    // фильмы добавить
    public static class ButEvListener4 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (comboBox1.getSelectedIndex() != 0) {
                addFilm(true);
                dataModelFilms.fireTableDataChanged();
            } else
                JOptionPane.showMessageDialog(null, "Кинообъединение не выбрано!\n", "Добавление фильма", JOptionPane.PLAIN_MESSAGE);
        }
    }
    // фильмы удалить
    public static class ButEvListener5 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int indexRow = tableFilms.getSelectedRow();
            if (indexRow != -1)
                if(indexRow < curListFilms.size()){
                    Films.getInstance().removeFilm(curListFilms.get(indexRow));
                    curListFilms = (ArrayList<Film>) Films.getInstance().getFilmsOf(listAssos.get(0));
                    dataModelFilms.fireTableDataChanged();
                }
                else
                    JOptionPane.showMessageDialog(null, "Фильм не выбран!\n установите курсор на имени удаляемого фильма", "Удаление фильма", JOptionPane.PLAIN_MESSAGE);

        }
    }
    // новый фильм сохранить
    public static class ButEvListener6 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (!name.toString().isEmpty() &&
                    !date1.toString().isEmpty() && !date2.toString().isEmpty() &&
                    comboBox2.getSelectedIndex() != -1 && comboBox3.getSelectedIndex() != -1 &&
                    !country.toString().isEmpty() &&  comboBox4.getSelectedIndex() != -1 &&
                    comboBox5.getSelectedIndex() != -1 && comboBox6.getSelectedIndex() != -1) {
                Films.getInstance().addFilm(listAssos.get(comboBox1.getSelectedIndex() - 1),
                        name.getText(),
                        ReleaseDates.getInstance().addReleaseDate(date1.getText(), date2.getText()),
                        Persons.getInstance().getDirectors().get(comboBox2.getSelectedIndex() - 1),
                        Persons.getInstance().getWriters().get(comboBox3.getSelectedIndex() - 1),
                        Countries.getInstance().getCountry(country.getText()),
                        CinemaStudios.getInstance().getCinemaStudio(comboBox4.getSelectedIndex()),
                        AgeLimits.getInstance().getAgeLimit(comboBox6.getSelectedIndex()),
                        Genres.getInstance().getGenre(comboBox5.getSelectedIndex())
                );
                updateFilms();
                jFrame2.setVisible(false);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Проверьте поля ввода. \n Все поля обязательны", "Ошибка", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
    // редактировать фильм
    public static class ButEvListener7 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (!name.toString().isEmpty() &&
                    !date1.toString().isEmpty() && !date2.toString().isEmpty() &&
                    comboBox2.getSelectedIndex() != -1 && comboBox3.getSelectedIndex() != -1 &&
                    !country.toString().isEmpty() &&  comboBox4.getSelectedIndex() != -1 &&
                    comboBox5.getSelectedIndex() != -1 && comboBox6.getSelectedIndex() != -1) {
                Film film = curListFilms.get(tableFilms.getSelectedRow());
                film.setName(name.getText());
                ReleaseDate releaseDate = film.getReleaseDate();
                releaseDate.setWorld(date1.getText());
                releaseDate.setRus(date2.getText());
                ReleaseDates.getInstance().updateReleaseDate(releaseDate);
                film.setDir(Persons.getInstance().getDirectors().get(comboBox2.getSelectedIndex() - 1));
                film.setWriter(Persons.getInstance().getWriters().get(comboBox3.getSelectedIndex() - 1));
                film.setStudio(CinemaStudios.getInstance().getCinemaStudio(comboBox4.getSelectedIndex()));
                film.setCountry(Countries.getInstance().getCountry(country.getText()));
                film.setAgeLim(AgeLimits.getInstance().getAgeLimit(comboBox6.getSelectedIndex()));
                film.setGenre(Genres.getInstance().getGenre(comboBox5.getSelectedIndex()));
                Films.getInstance().updateFilm(film);
                updateFilms();
                jFrame2.setVisible(false);
                jFrame2.getContentPane().removeAll();
                jFrame2.dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Проверьте поля ввода. \n Все поля обязательны", "Ошибка", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    private static class ButEvListener31 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // много проверок
            if (!name.getText().isEmpty() &&
                    comboBox.getSelectedIndex() != 0) {
                CinemaAssociation association = listAssos.get(tableAssociations.getSelectedRow());
                association.setName(name.getText());
                TypeAssociation type = TypeAssociations.SINGLE;;
                switch (comboBox.getSelectedIndex()) {
                    case 1:
                        type = TypeAssociations.GROUP;
                        break;
                    case 2:
                        type = TypeAssociations.SINGLE;
                        break;
                    case 3:
                        type = TypeAssociations.SERIAL;
                        break;
                }
                association.setType(type);
                ConnectionManager.getInstance().getDataService().updateAssociation(association);
                updateAss();
                jFrame1.setVisible(false);
                jFrame1.getContentPane().removeAll();
                jFrame1.dispose();
                updateAss();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Проверьте поля ввода. \n Все поля обязательны", "Ошибка", JOptionPane.PLAIN_MESSAGE);
            }
        }

    }

    private static class ButEvListener8 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!name1.getText().isEmpty() &&
                    !date3.getText().isEmpty() &&
                        !country1.getText().isEmpty()) {
                Person person = new Person();
                person.setName(name1.getText());
                person.setProf(profession ? Professions.getInstance().getProfession(1) : Professions.getInstance().getProfession(2));
                person.setBirthDate(date3.getText());
                person.setBirthCountry(Countries.getInstance().getCountry(country1.getText()));
                ConnectionManager.getInstance().getDataService().createPerson(person);
                if (profession)
                    people1 = new Vector<>(ConnectionManager.getInstance().getDataService().getPersonsWho(Professions.getInstance().getProfession(1)));
                else
                    people2 = new Vector<>(ConnectionManager.getInstance().getDataService().getPersonsWho(Professions.getInstance().getProfession(2)));
                jFrame3.setVisible(false);
                jFrame3.getContentPane().removeAll();
                jFrame3.dispose();
                updateAss();
            } else {
                JOptionPane.showMessageDialog(null, "Проверьте поля ввода. \n Все поля обязательны", "Ошибка", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    private static class ButEvListener9 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            addPerson(true);
        }
    }
    private static class ButEvListener10 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            addPerson(false);
        }
    }
    private static class ButEvListener11 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            addStudio();
        }
    }private static class ButEvListener12 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!name2.getText().isEmpty() &&
                    !date4.getText().isEmpty() &&
                    !country2.getText().isEmpty()) {
                CinemaStudio studio = new CinemaStudio();
                studio.setName(name2.getText());
                studio.setCreateDate(date4.getText());
                studio.setNativeCountry(Countries.getInstance().getCountry(country2.getText()));
                ConnectionManager.getInstance().getDataService().createCinemaStudio(studio);
                studios = new Vector<>(ConnectionManager.getInstance().getDataService().getCinemaStudios());
                jFrame4.setVisible(false);
                jFrame4.getContentPane().removeAll();
                jFrame4.dispose();
                updateAss();
            } else {
                JOptionPane.showMessageDialog(null, "Проверьте поля ввода. \n Все поля обязательны", "Ошибка", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
}
