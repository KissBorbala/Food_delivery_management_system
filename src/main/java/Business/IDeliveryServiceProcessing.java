package Business;

import Data.User;
import Presentation.Observer;

import java.util.Set;

public interface IDeliveryServiceProcessing {

    /**
     * @return
     */
    Set<MenuItem> importProducts();

    /**
     * @param title
     * @param rating
     * @param calories
     * @param protein
     * @param fat
     * @param sodium
     * @param price
     * @return
     * @pre !title.equals("") || rating >= 0 || calories >= 0 || protein >= 0 || fat >= 0 || sodium >= 0 || price >= 0
     * @post forall i: [0 ... getSize()-1]
     *       result.getElement(i).title.contains(title)
     *       result.getElement(i).rating.toString().contains(rating)
     *       result.getElement(i).calories.toString().contains(title)
     *       result.getElement(i).protein.toString().contains(protein)
     *       result.getElement(i).fat.toString().contains(fat)
     *       result.getElement(i).sodium.toString().contains(sodium)
     *       result.getElement(i).price.toString().contains(price)
     */
    Set<MenuItem> searchProducts(String title, double rating, int calories, int protein, int fat, int sodium, int price);

    /**
     * @param title
     * @return
     * @pre !title.equals("")
     */
    MenuItem searchProduct(String title);

    /**
     * @param orderItems
     * @param u
     * @pre !orderItems.isEmpty() && u != null
     */
    public void addOrder(Set<MenuItem> orderItems, User u);

    /**
     * @param title
     * @param rating
     * @param calories
     * @param protein
     * @param fat
     * @param sodium
     * @param price
     * @return
     * @post @menuItems.contains(item)
     */
    public Set<MenuItem> addProduct(String title, double rating, int calories, int protein, int fat, int sodium, int price);

    /**
     * @param title
     * @param rating
     * @param calories
     * @param protein
     * @param fat
     * @param sodium
     * @param price
     * @return
     * @pre !title.equals("") && rating >= 0 && calories >= 0 && protein >= 0 && fat >= 0 && sodium >= 0 && price >= 0
     * @post item.title.equals = title
     * @post item.rating == rating
     * @post item.calories == calories
     * @post item.protein == protein
     * @post item.fat == fat
     * @post item.sodium == sodium
     * @post item.price == price
     */
    public Set<MenuItem> editProduct(String title, double rating, int calories, int protein, int fat, int sodium, int price);

    /**
     * @param title
     * @return
     * @pre !title.equals("")
     */
    public Set<MenuItem> deleteProduct(String title);

    /**
     * @param item
     * @pre item != null
     */
    public void addComposite(MenuItem item);

    /**
     * @param title
     * @pre !title.equals("")
     */
    public void createComposite(String title);

    /**
     * @param order
     * @param orderItems
     * @param u
     * @pre order != null && !orderItems.isEmpty() && u != null
     */
    public void createBill(Order order, Set<MenuItem> orderItems, User u);

    /**
     * @param start
     * @param end
     * @pre start >= 0 && start <= 24 && end >= 0 && end <= 24 && start <= end
     */
    public void reportTimeInterval(int start, int end);

    /**
     * @param time
     * @param start
     * @param end
     * @pre start >= 0 && start <= 24 && end >= 0 && end <= 24 && start <= end && !time.equals("")
     */
    public boolean searchTimeInterval(String time, int start, int end);

    /**
     * @param nr
     * @pre nr >= 0
     */
    public void reportMoreThan(int nr);

    /**
     * @param nr
     * @param amount
     * @pre nr >= 0 && amount >= 0
     */
    public void reportClient(int nr, int amount);

    /**
     * @param date
     * @pre !date.equals("")
     */
    public void reportDate(String date);

    /**
     * @return
     */
    boolean isWellFormed();
}
