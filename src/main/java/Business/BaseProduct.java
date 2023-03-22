package Business;

import Data.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BaseProduct extends MenuItem implements Serializable {

    private String title;
    private double rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;
    private int ordered = 0;
    private List<String> dates;

    public BaseProduct(String title, double rating, int calories, int protein, int fat, int sodium, int price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;

        dates = new ArrayList<String>();
    }

    public void printStats() {
        System.out.println(title + " " + rating + " " + calories + " " + protein + " " + fat + " " + sodium + " " + price);
    }

    public String getTitle() {
        return title;
    }
    public double getRating() {
        return rating;
    }
    public int getCalories() {
        return calories;
    }
    public int getProtein() {
        return protein;
    }
    public int getFat() {
        return fat;
    }
    public int getSodium() {
        return sodium;
    }
    public int getPrice() {
        return price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void incOrdered() {
        ordered++;
    }

    public int getOrdered() {
        return ordered;
    }

    public void addDate(String date) {
        dates.add(date);
    }

    public int getOrderedDate(String date) {
        int nr = 0;
        for(String s: dates) {
            if (s.equals(date)) {
                nr++;
            }
        }
        return nr;
    }

    @Override
    public int hashCode() {
        return 31 * title.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return ((BaseProduct) obj).getTitle().equals(title);
    }
}
