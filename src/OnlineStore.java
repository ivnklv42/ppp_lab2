import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class OnlineStore {
    private static OnlineStore INSTANCE;
    private final HashMap<String, Product> products = new HashMap<>();
    private final HashMap<String, Customer> customers = new HashMap<>();
    private final ArrayList<Order> orders = new ArrayList<>();

    private OnlineStore() {}

    public static OnlineStore getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OnlineStore();
        }

        return INSTANCE;
    }

    public Product getProduct(String name) {
        return this.products.get(name);
    }

    public Collection<Product> getProducts() {
        return this.products.values();
    }

    public void addProduct(Product product) {
        this.products.put(product.getTitle(), product);
    }

    public Customer getCustomer(String name) {
        return this.customers.get(name);
    }

    public Collection<Customer> getCustomers() {
        return this.customers.values();
    }

    public void addCustomer(Customer customer) {
        this.customers.put(customer.getName(), customer);
    }

    public void createOrder(Customer customer, Product product, int amount) {
        Order order = new Order(customer, product, amount);
        customer.addOrder(order);
        this.orders.add(order);
    }

    public ArrayList<Order> getOrders() {
        return this.orders;
    }

    public void changeProductTitle(String title, String newTitle) {
        Product product = products.get(title);
        product.setTitle(newTitle);
        products.remove(title);
        products.put(newTitle, product);
    }

    public void changeCustomerName(String name, String newName) {
        Customer customer = customers.get(name);
        customer.setName(newName);
        customers.remove(name);
        customers.put(newName, customer);
    }
}
