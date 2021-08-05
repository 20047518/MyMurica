package sg.edu.rp.c346.id20047518.mymurica;

import java.io.Serializable;

public class Island implements Serializable {

    private int id;
    private String name;
    private String description;
    private int size;
    private int stars;

    public Island(String name, String description, int size, int stars) {
        this.name = name;
        this.description = description;
        this.size = size;
        this.stars = stars;
    }

    public Island(int id, String name, String description, int size, int stars) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.size = size;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public Island setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Island setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Island setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getSize() {
        return size;
    }

    public Island setSize(int size) {
        this.size = size;
        return this;
    }

    public int getStars() {
        return stars;
    }

    public Island setStars(int stars) {
        this.stars = stars;
        return this;
    }

    @Override
    public String toString() {
        String starsString = "";
        if (stars == 5){
            starsString = "*****";
        } else if (stars == 4){
            starsString = "****";
        }

        //or
        for(int i = 0; i < stars; i++){
            starsString += "*";
        }
        return name + "\n" + description + " - " + size + "\n" + starsString;

    }

    public int toStarString() {
        int starsString = 0;
        /*
        if (stars == 5){
            starsString = "*****";
        } else if (stars == 4){
            starsString = "****";
        }
        */
        //or
        starsString = stars;
        /*for(int i = 0; i < stars; i++){
            starsString += "*";
        }*/
        return starsString;
    }

}

