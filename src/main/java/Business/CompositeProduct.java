package Business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CompositeProduct extends MenuItem implements Serializable {

    private String title;
    private double rating = 0;
    private int calories = 0;
    private int protein = 0;
    private int fat = 0;
    private int sodium = 0;
    private int price = 0;
    private List<MenuItem> products;
    private List<String> dates;
    private int ordered;


    public CompositeProduct(List<MenuItem> products, String title) {
        this.title = title;
        this.products = products;
        int nr = 0;
        for (MenuItem item: products) {
            rating += item.getRating();
            calories += item.getCalories();
            protein += item.getProtein();
            fat += item.getFat();
            sodium += item.getSodium();
            price += item.getPrice();
            nr++;
        }
        rating = rating / nr;
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
        for(MenuItem item: products) {
            item.incOrdered();
        }
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
        return ((CompositeProduct) obj).getTitle().equals(title);
    }


}
