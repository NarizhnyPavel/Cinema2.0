package Kinopoisk.api.data.FunClub;

import java.io.Serializable;

public class FunClub implements Serializable {
    private int id;
    private String name;
    private String chat; //link

    public FunClub(int id, String name, String chat) {
        this.id = id;
        this.name = name;
        this.chat = chat;
    }

    public FunClub(String name, String chat) {
        this.name = name;
        this.chat = chat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FunClub() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
