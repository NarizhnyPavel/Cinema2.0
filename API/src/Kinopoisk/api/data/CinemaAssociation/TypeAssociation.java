package Kinopoisk.api.data.CinemaAssociation;

import java.io.Serializable;

public class TypeAssociation implements Serializable {
    private String type;

    public TypeAssociation(String type) {
        this.type = type;
    }

    public TypeAssociation() {
    }

    public String getTypeString() {
        return type;
    }

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

    public int getType(){
        if (this.type.equals("GROUP"))
            return 1;
        if (this.type.equals("SINGLE"))
            return 2;
        if (this.type.equals("SERIAL"))
            return 3;
        return 0;
    }

    public void setType(String type) {
        this.type = type;
    }
}
