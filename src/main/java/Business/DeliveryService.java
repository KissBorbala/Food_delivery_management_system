
















































































































































































































































































































































































































































/**
 * @invariant isWellFormed()
 */

public class DeliveryService implements Serializable, Observable{

    private Set<User> user;
    private Set<MenuItem> products;
    private Set<Order> orders;
    private int clientId;
    private int orderId = 0;
    private List<MenuItem> composite;
    private Map<Order, Set<MenuItem>> map;
    private int reportId = 0;
    private Order currentOrder;

    private List<Observer> observers = new ArrayList<Observer>();


    public DeliveryService() {
        products = new HashSet<MenuItem>();
        orders = new HashSet<Order>();
        composite  = new ArrayList<MenuItem>();
        map = new HashMap<Order, Set<MenuItem>>();
        user = new HashSet<User>();
    }

    public boolean addUser(User u) {
        return user.add(u);
    }

    public Set<MenuItem> importProducts() {
        assert isWellFormed();
        File file = new File("product.csv");
        FileReader fr = null;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        try {
            line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] tempArr;
        while(true) {
            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            tempArr = line.split(",");
            MenuItem item = new BaseProduct(tempArr[0], Double.valueOf(tempArr[1]), Integer.valueOf(tempArr[2]), Integer.valueOf(tempArr[3]),
                    Integer.valueOf(tempArr[4]), Integer.valueOf(tempArr[5]), Integer.valueOf(tempArr[6]));
            products.add(item);
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }

    public void displayProducts(){
        for(MenuItem product: products) {
            product.printStats();
        }
    }

    public Set<MenuItem> searchProducts(String title, double rating, int calories, int protein, int fat, int sodium, int price) {
        assert !title.equals("") || rating >= 0 || calories >= 0 || protein >= 0 || fat >= 0 || sodium >= 0 || price >= 0;
        assert isWellFormed();
        Set<MenuItem> result;
        result = products;

        if (!title.equals("")) {
            result = result.stream().filter(t -> t.getTitle().contains(title)).collect(Collectors.toSet());
        }
        if (rating != -1) {
            result = result.stream().filter(t -> t.getRating() == rating).collect(Collectors.toSet());
        }
        if (calories != -1) {
            result = result.stream().filter(t -> t.getCalories() == calories).collect(Collectors.toSet());
        }
        if (protein != -1) {
            result = result.stream().filter(t -> t.getProtein() == protein).collect(Collectors.toSet());
        }
        if (fat != -1) {
            result = result.stream().filter(t -> t.getFat() == fat).collect(Collectors.toSet());
        }
        if (sodium != -1) {
            result = result.stream().filter(t -> t.getSodium() == sodium).collect(Collectors.toSet());
        }
        if (price != -1) {
            result = result.stream().filter(t -> t.getPrice() == price).collect(Collectors.toSet());
        }
        return result;
    }

    public MenuItem searchProduct(String title) {
        assert isWellFormed();
        assert !title.equals("");
        for (MenuItem item: products) {
            if (item.getTitle().equalsIgnoreCase(title)) {
                return item;
            }
        }
        return null;
    }

    public void addOrder(Set<MenuItem> orderItems, User u) {
        assert isWellFormed();
        assert !orderItems.isEmpty() && u != null;
        Order newOrder = new Order(u.getId(), orderId, String.valueOf(java.time.LocalDate.now()), String.valueOf(java.time.LocalTime.now()));

        map.put(newOrder, orderItems);
        orders.add(newOrder);
        currentOrder = newOrder;
        notifyAllObservers();

        for(MenuItem item: orderItems) {
            item.incOrdered();
            item.addDate(String.valueOf(java.time.LocalDate.now()));
        }
        createBill(newOrder, orderItems, u);
        orderId++;
    }

    public void setClientId(int clientId) {
        assert isWellFormed();
        this.clientId = clientId;
    }

    public Set<MenuItem> addProduct(String title, double rating, int calories, int protein, int fat, int sodium, int price) {
        assert isWellFormed();
        assert !title.equals("") && rating >= 0 && calories >= 0 && protein >= 0 && fat >= 0 && sodium >= 0 && price >= 0;
        MenuItem item = new BaseProduct(title, rating, calories, protein, fat, sodium, price);
        products.add(item);
        return products;
    }
    public Set<MenuItem> editProduct(String title, double rating, int calories, int protein, int fat, int sodium, int price) {
        assert !title.equals("") && rating >= 0 && calories >= 0 && protein >= 0 && fat >= 0 && sodium >= 0 && price >= 0;
        assert isWellFormed();
        MenuItem item = searchProduct(title);
        products.remove(item);
        item.setRating(rating);
        item.setCalories(calories);
        item.setProtein(protein);
        item.setFat(fat);
        item.setSodium(sodium);
        item.setPrice(price);
        products.add(item);

        return products;
    }

    public Set<MenuItem> deleteProduct(String title) {
        assert !title.equals("");
        assert isWellFormed();
        MenuItem item = searchProduct(title);
        System.out.println(item.getTitle());
        products.remove(item);

        return products;
    }

    public void addComposite(MenuItem item) {
        assert item != null;
        assert isWellFormed();
        composite.add(item);
    }

    public void createComposite(String title) {
        assert !title.equals("");
        assert isWellFormed();
        MenuItem newComposite = new CompositeProduct(composite, title);
        products.add(newComposite);
        composite.removeAll(composite);
    }

    public void createBill(Order order, Set<MenuItem> orderItems, User u) {
        assert order != null && !orderItems.isEmpty() && u != null;
        assert isWellFormed();
        String fileName = "bill" + order.getId() + ".txt";

        String bill = "";
        bill = bill + order.getDate() + "\n";
        bill = bill + order.getTime() + "\n\n";
        int price = 0;
        for (MenuItem item : orderItems) {
            bill = bill + item.getTitle() + " Price: " + item.getPrice() + "\n";
            price += item.getPrice();
        }
        bill =  bill + "\nTotal: " + price;
        u.addBill(price);

        Data.FileWriter.writeFile(bill, fileName);
    }



    public void reportTimeInterval(int start, int end) {
        assert start >= 0 && start <= 24 && end >= 0 && end <= 24 && start <= end;
        assert isWellFormed();
        Set<Order> result;
        String report = "Orders performed between " + start +  " o'clock and " + end +  " o'clock\n\n";
        String fileName = "timeIntervalReport" + reportId;
        reportId++;

        result = orders.stream().filter(t -> searchTimeInterval(t.getTime(), start, end)).collect(Collectors.toSet());
        for(Order order: result) {
            report = report + "Order ID: " + order.getId() + "    " + order.getDate() + "    " + order.getTime() + "\n";

        }
        Data.FileWriter.writeFile(report, fileName);
    }

    public boolean searchTimeInterval(String time, int start, int end){
        assert start >= 0 && start <= 24 && end >= 0 && end <= 24 && start <= end && !time.equals("");
        assert isWellFormed();
        String hour[] = time.split(":");
        if (Integer.valueOf(hour[0]) > start && Integer.valueOf(hour[0]) < end) {
            return true;
        }
        return false;
    }

    public void reportMoreThan(int nr) {
        assert nr >= 0;
        assert isWellFormed();
        Set<MenuItem> result;
        String report = "Products ordered more than " + nr + " times\n\n";
        String fileName = "productReport" + reportId;
        reportId++;

        result = products.stream().filter(t -> t.getOrdered() > nr).collect(Collectors.toSet());
        for(MenuItem item: result) {
            report = report + item.getTitle() + " Ordered " + item.getOrdered() + " times\n";
        }
        Data.FileWriter.writeFile(report, fileName);
    }

    public void reportClient(int nr, int amount) {
        assert nr >= 0 && amount >= 0;
        assert isWellFormed();
        Set<User> result;
        String report = "Clients that ordered more than " + nr + " times and the value of the order was higher than " + amount + "\n\n";
        String fileName = "clientReport" + reportId;
        reportId++;

        result = user.stream().filter(t ->  t.getStatus() == 2 && t.getBillsGreater(amount) > nr).collect(Collectors.toSet());
        for(User u: result) {
            report = report + u.getUsername() + " Ordered " + u.getBillsGreater(amount) + " times\n";
        }
        Data.FileWriter.writeFile(report, fileName);
    }

    public void reportDate(String date) {
        assert !date.equals("");
        assert isWellFormed();
        Set<MenuItem> result;
        String report = "Products that have been ordered ad the date " + date + "\n\n";
        String fileName = "dateReport" + reportId;
        reportId++;

        result = products.stream().filter(t -> t.getOrderedDate(date) > 0).collect(Collectors.toSet());
        for(MenuItem item: result) {
            report = report + item.getTitle() + " Ordered " + item.getOrderedDate(date) + " times\n";
        }
        Data.FileWriter.writeFile(report, fileName);
    }

    public Set<User> getUsers() {
        return user;
    }


    @Override
    public void addObserver(Observer observer) {

        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {

        observers.remove(observer);
    }

    @Override
    public void notifyAllObservers() {

        for(Observer obs: observers) {
            obs.update(currentOrder);
        }
    }

    public Set<MenuItem> getProducts() {
        return products;
    }

    public boolean isWellFormed(){
        return (products.contains(null) && user.contains(null) && orders.contains(null));
    }
}
