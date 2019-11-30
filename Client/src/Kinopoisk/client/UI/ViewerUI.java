package Kinopoisk.client.UI;

import Kinopoisk.api.data.CinemaAssociation.CinemaAssociation;
import Kinopoisk.api.data.CinemaAssociation.Film.AgeLimit;
import Kinopoisk.api.data.CinemaAssociation.Film.Film;
import Kinopoisk.api.data.CinemaAssociation.Film.Genre;
import Kinopoisk.api.data.CinemaAssociation.Film.ReleaseDate;
import Kinopoisk.api.data.CinemaStudio.CinemaStudio;
import Kinopoisk.api.data.Person.Person;
import Kinopoisk.client.DataMethods.Person.Professions;
import Kinopoisk.client.connection.ConnectionManager;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.window.WebWindow;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ViewerUI {
    public static JFrame frame;
    public static JComboBox associations;
    public static JList films;
    public static Vector<Film> filmList = new Vector<>();
    public static void main() {
        frame = new JFrame("Кинопоиск 2.0");
        frame.setBackground(Color.getHSBColor(33, 23, 100));
        frame.setBounds(700, 450, 400, 350);

        addComponentsToPane(frame.getContentPane());

        //----------------//
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    private DefaultListModel<CinemaAssociation> associationDefaultListModel = new DefaultListModel<>();
    private static Vector<CinemaAssociation> associationList;

    private static void addComponentsToPane(Container pane){

        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        associations = new JComboBox();
        associationList = new Vector<>(ConnectionManager.getInstance().getDataService().getAssociations());
        associations.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value==null){
                    setText("Выберите кинообъединение");
                }else{
                    setText(((CinemaAssociation)value).getName());}
                return this;
            }
        });
        associationList.add(0,null);
        DefaultComboBoxModel<CinemaAssociation> aModel3 = new DefaultComboBoxModel<CinemaAssociation>(associationList) {
            private static final long serialVersionUID = 1L;
            boolean selectionAllowed = true;

            @Override
            public void setSelectedItem(Object anObject) {
                super.setSelectedItem(anObject);
                updateFilms();
            }

            @Override
            public int getSize() {
                return associationList.size();
            }

            @Override
            public CinemaAssociation getElementAt(int index) {
                return associationList.get(index);
            }

        };
        associations.setModel(aModel3);

        films = new JList<>();
        ListModel lm = new StaticListModel();
        films.setModel(lm);
        films.setCellRenderer(new MyCellRenderer());
        films.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = films.locationToIndex(e.getPoint());
                    viewFilm();
                }
            }
        };
        films.addMouseListener(mouseListener);


        JLabel label1 = new JLabel("Кинопоиск 2.0");
        label1.setFont(new Font("Arial",Font.ITALIC,  20));
        c.insets = new Insets(20, 5, 5, 5);
        pane.add(label1, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        pane.add(associations,c);

        c.gridy = 2;
        JScrollPane comp = new WebScrollPane(films);
        comp.setBorder(BorderFactory.createLineBorder(Color.RED));
        pane.add(comp, c);
    }

       private static class StaticListModel implements ListModel {
        @Override
        public int getSize() {
            return filmList.size();
        }

        @Override
        public Object getElementAt(int index) {
            return filmList.get(index).getName();
        }

        @Override
        public void addListDataListener(ListDataListener l) {

        }

        @Override
        public void removeListDataListener(ListDataListener l) {

        }
    };

    private static void updateFilms(){
        if ( associations.getSelectedIndex() != -1)
            filmList = new Vector<>(ConnectionManager.getInstance().getDataService().getFilmsOf((CinemaAssociation) associations.getSelectedItem()));
        else
            filmList = new Vector<>();
        films.setListData(filmList);
    }

    static class MyCellRenderer extends JLabel implements ListCellRenderer {
        public Component getListCellRendererComponent(JList list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            Component c = null;
            if (value == null) {
                c = new JLabel("(null)");
            } else if (value instanceof Component) {
                c = (Component) value;
            } else {
                c = new JLabel(( ( Film ) value ).getName () );
            }

            if (isSelected) {
                c.setBackground(list.getSelectionBackground());
                c.setForeground(list.getSelectionForeground());
            } else {
                c.setBackground(list.getBackground());
                c.setForeground(list.getForeground());
            }

            if (c instanceof JComponent) {
                ((JComponent) c).setOpaque(true);
            }

            return c;
        }
    }

    private static JLabel name = new JLabel();
    private static JLabel name1 = new JLabel();
    private static JLabel name2 = new JLabel();
    private static JLabel date1 = new JLabel();
    private static JLabel date2 = new JLabel();
    private static JLabel date3 = new JLabel(); // for person
    public static JLabel date4 = new JLabel(); // for studio
    private static JLabel country = new JLabel();
    public static JLabel country1 = new JLabel(); // for person
    public static JLabel country2 = new JLabel(); // for studio
    public static JLabel profession = new JLabel(); // for person
    private static JButton director = new JButton();
    private static JButton writer = new JButton();
    private static JButton studio = new JButton();
    public static JLabel genre = new JLabel();
    private static JLabel age = new JLabel();

    private static JFrame filmFrame;
    public static void viewFilm(){
        filmFrame = new JFrame("Просмотр фильма");
        Film selectedFilm = ConnectionManager.getInstance().getDataService().getFilm(((Film) films.getSelectedValue()).getName());
        name.setText(selectedFilm.getName());
        name.setFont(new Font("Times New Roman",Font.BOLD,  20));
        ReleaseDate date = selectedFilm.getReleaseDate();
        date1.setText(date.getWorld());
        date2.setText(date.getRus());
        country.setText(selectedFilm.getCountry().getName());
        director.setText(selectedFilm.getDir().getName());
        director.addActionListener(new ButEvListenerDir());
        writer.setText(selectedFilm.getWriter().getName());
        writer.addActionListener(new ButEvListenerWrit());
        studio.setText(selectedFilm.getStudio().getName());
        studio.addActionListener(new ButEvListenerStudio());
        AgeLimit ageLimit = selectedFilm.getAgeLim();
        age.setText(ageLimit.getLimit() + "+ ");
        age.setToolTipText(ageLimit.getDiscript());

        addComponentsToPane3(filmFrame.getContentPane());
        filmFrame.setBackground(Color.getHSBColor(33, 23, 100));
        filmFrame.setBounds(700, 450, 600, 350);

        filmFrame.setVisible(true);
        filmFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    private static JFrame personFrame;
    public static void viewPerson(boolean mode){ //1-director 2-writer
        personFrame = new JFrame("Просмотр информации о персоне");
        Person person;
        if(mode)
            person = ConnectionManager.getInstance().getDataService().getFilm(((Film) films.getSelectedValue()).getName()).getDir();
        else
            person = ConnectionManager.getInstance().getDataService().getFilm(((Film) films.getSelectedValue()).getName()).getWriter();
        name1.setText(person.getName());
        name1.setFont(new Font("Times New Roman",Font.BOLD,  20));
        date3.setText(person.getBirthDate());
        country1.setText(person.getBirthCountry().getName());
        profession.setText(person.getProf().getName());

        addComponentsToPane4(personFrame.getContentPane());
        personFrame.setBackground(Color.getHSBColor(33, 23, 100));
        personFrame.setBounds(700, 450, 300, 150);

        personFrame.setVisible(true);

        personFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private static void addComponentsToPane4(Container pane) {
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;

        c.weightx = 0.5;

        JLabel label1 = new JLabel("Профессия: ");
        JLabel label2 = new JLabel("Дата рождения: ");
        JLabel label3 = new JLabel("Страна:");

        c.gridx = 2;
        c.gridy = 0;
        pane.add(name1, c);

        c.gridx = 0;
        c.gridy = 1;
        pane.add(label1, c);
        c.gridx = 2;
        c.gridy = 1;
        pane.add(profession, c);

        c.gridx = 0;
        c.gridy = 2;
        pane.add(label2, c);
        c.gridx = 2;
        c.gridy = 2;
        pane.add(date1, c);

        c.gridx = 0;
        c.gridy = 3;
        pane.add(label3, c);
        c.gridx = 2;
        c.gridy = 3;
        pane.add(country1, c);

    }
    private static JFrame studioFrame;
    public static void viewStudio(){
        studioFrame = new JFrame("Просмотр информации о студии");
        CinemaStudio studio;
        studio = ConnectionManager.getInstance().getDataService().getCinemaStudio(((Film)films.getSelectedValue()).getStudio().getId());
        name2.setText(studio.getName());
        name2.setFont(new Font("Times New Roman",Font.BOLD,  20));
        date4.setText(studio.getCreateDate());
        country2.setText(studio.getNativeCountry().getName());

        addComponentsToPane5(studioFrame.getContentPane());
        studioFrame.setBackground(Color.getHSBColor(33, 23, 100));
        studioFrame.setBounds(700, 450, 300, 150);

        studioFrame.setVisible(true);

        studioFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private static void addComponentsToPane5(Container pane) {
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;

        c.weightx = 0.5;

        JLabel label2 = new JLabel("Основана в ");
        JLabel label3 = new JLabel("Страна: ");

        c.gridx = 2;
        c.gridy = 0;
        pane.add(name2, c);

        c.gridx = 0;
        c.gridy = 1;
        pane.add(label2, c);
        c.gridx = 2;
        c.gridy = 1;
        pane.add(date4, c);

        c.gridx = 0;
        c.gridy = 2;
        pane.add(label3, c);
        c.gridx = 2;
        c.gridy = 2;
        pane.add(country2, c);
    }

    public static void addComponentsToPane3(Container pane) {
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;

        c.weightx = 0.5;

        JLabel label1 = new JLabel("Название: ");
        JLabel label2 = new JLabel("Дата выхода: ");
        JLabel label3 = new JLabel("в Мире:");
        JLabel label4 = new JLabel("в России");
        JLabel label5 = new JLabel("Режиссёр:");
        JLabel label6 = new JLabel("Сценарист:");
        JLabel label7 = new JLabel("Страна:");
        JLabel label8 = new JLabel("Киностудия:");
        JLabel label9 = new JLabel("Жанр:");
        JLabel label10 = new JLabel("Возраст. огр-е:");

        c.gridx = 0;
        c.gridy = 1;
        pane.add(label1, c);
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 3;
        pane.add(name, c);

        c.gridwidth = 1;
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

        c.gridx = 4;
        c.gridy = 2;
        pane.add(label4, c);
        c.gridx = 4;
        c.gridy = 3;
        pane.add(date2, c);

        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 4;
        pane.add(label9, c);
        c.gridx = 2;
        pane.add(genre, c);

        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 5;
        pane.add(label5, c);
        c.gridx = 2;
        pane.add(director, c);

        c.gridx = 0;
        c.gridy = 6;
        pane.add(label6, c);
        c.gridx = 2;
        pane.add(writer, c);

        c.gridx = 0;
        c.gridy = 7;
        pane.add(label7, c);
        c.gridx = 2;
        pane.add(country, c);

        c.gridx = 0;
        c.gridy = 8;
        pane.add(label8, c);
        c.gridx = 2;
        pane.add(studio, c);

        c.gridx = 0;
        c.gridy = 9;
        pane.add(label10, c);
        c.gridx = 2;
        pane.add(age, c);

    }

    public static class ButEvListenerDir implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            viewPerson(true);
        }
    }
    public static class ButEvListenerWrit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            viewPerson(false);
        }
    }

    public static class ButEvListenerStudio implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            viewStudio();
        }
    }
}
