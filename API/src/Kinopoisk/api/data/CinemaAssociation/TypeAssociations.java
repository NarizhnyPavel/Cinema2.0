package Kinopoisk.api.data.CinemaAssociation;

/**
 * Интерфейс, опиывающий список типов кинообъединений
 * @see TypeAssociation
 * @author Narizhny Pavel
 * @version 1.0
 */
public interface TypeAssociations  {
    /** Объект, описывающий тип "группа фильмов"/"кинофраншиза"*/
    TypeAssociation GROUP = new TypeAssociation("GROUP");
    /** Объект, описывающий тип "одиночный фильм"*/
    TypeAssociation SINGLE = new TypeAssociation( "SINGLE");
    /** Объект, описывающий тип "сериал"*/
    TypeAssociation SERIAL = new TypeAssociation("SERIAL");
}
