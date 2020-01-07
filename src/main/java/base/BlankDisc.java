package base;


import org.springframework.beans.factory.annotation.Value;

public class BlankDisc {

    private String title;
    private String author;

    public BlankDisc(@Value("#{systemProperties(disc.title)}") String title, @Value("#{systemProperties(disc.author)}")String author) {

        this.title = title;
        this.author = author;

    }

    public String  test(){

        return title + " " + author;

    }

}
