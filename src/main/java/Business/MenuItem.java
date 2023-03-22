package Business;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {

    public abstract String getTitle();
    public abstract void printStats();
    public abstract double getRating();
    public abstract int getCalories();
    public abstract int getProtein();
    public abstract int getFat();
    public abstract int getSodium();
    public abstract int getPrice();
    public abstract void setRating(double rating);
    public abstract void setCalories(int calories);
    public abstract void setProtein(int protein);
    public abstract void setFat(int fat);
    public abstract void setSodium(int sodium);
    public abstract void setPrice(int price);
    public abstract void incOrdered();
    public abstract int getOrdered();
    public abstract void addDate(String s);
    public abstract int getOrderedDate(String date);
}
