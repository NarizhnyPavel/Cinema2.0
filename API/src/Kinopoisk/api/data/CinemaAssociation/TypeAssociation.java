package Kinopoisk.api.data.CinemaAssociation;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс, описывающий тип кинообъединения <br>
 * индекс типа - идентификатор для типа из фиксированного списка {@link TypeAssociations}
 * @see CinemaAssociation
 * @author Narizhny Pavel
 * @version 1.0
 */
public class TypeAssociation implements Serializable {
    /** Поле строквого имени типа*/
    private String type;

    /**
     * Конструктор - создание нового объекта
     */
    public TypeAssociation(ResultSet resultSet) {
        try {
            this.type = resultSet.getString("name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Конструктор - создание нового объекта со свойствами
     * @param type - строковое имя типа
     */
    public TypeAssociation(String type) {
        this.type = type;
    }

    /**
     * Функция получения значения поля {@link TypeAssociation#type}
     * @return - строковое имя типа
     */
    public String getTypeString() {
        return type;
    }

    /**
     * Функци получения объекта по его индексу
     * @param index - номер типа.
     * @return
     */
    public TypeAssociation getType(int index){

        switch (index){
            case 1:
                return TypeAssociations.GROUP;
            case 2:
                return TypeAssociations.SINGLE;
            case 3:
                return TypeAssociations.SERIAL;
        }
        return TypeAssociations.SINGLE;
    }

    /**
     * Функция получения значения поля {@link TypeAssociation#type}
     * @return - индекс типа
     */
    public int getType(){
        if (this.type.equals("GROUP"))
            return 1;
        if (this.type.equals("SINGLE"))
            return 2;
        if (this.type.equals("SERIAL"))
            return 3;
        return 0;
    }

    /**
     * Процедура определения поля {@link TypeAssociation#type}
     * @return
     */
    public void setType(String type) {
        this.type = type;
    }
}
