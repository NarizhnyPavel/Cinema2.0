package Kinopoisk.client.DataMethods.CinemaAssociation.Film;

import Kinopoisk.api.data.CinemaAssociation.Film.Comment;
import Kinopoisk.api.data.CinemaAssociation.Film.Film;
import Kinopoisk.api.data.User.User;

import java.util.ArrayList;

public class Comments {
    private static Comments instance;
    private ArrayList<Comment> comments;

    private Comments() {
        comments = new ArrayList<>();
        //comments.add(new Comment());
    }

    public static synchronized Comments getInstance() {
        if(instance == null){
            instance = new Comments();
        }
        return instance;
    }

    public ArrayList<Comment> getComments() {
        return new ArrayList<Comment>(comments);
    }

    public void addComment(Film film, String text, String date, User author){
        this.comments.add(new Comment(film, text, date, author));
    }
}

