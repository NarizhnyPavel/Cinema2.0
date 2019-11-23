package Kinopoisk.api.data.CinemaAssociation.Film;

import Kinopoisk.api.data.User.User;

public class Review {
   private User author;
   private int rate;
   private String text;

   public Review(User author, int rate, String text) {
      this.author = author;
      this.rate = rate;
      this.text = text;
   }
}
