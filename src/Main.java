import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        OnlineStore onlineStore = OnlineStore.getInstance();
        Scanner console = new Scanner(System.in);
        String action;
        while (true) {
            System.out.println("Выберите действие:\n" +
                    "1. Добавить товар.\n" +
                    "2. Изменить цену товара.\n" +
                    "3. Изменить название товара.\n" +
                    "4. Получить информацию о товаре.\n" +
                    "5. Получить список товаров.\n" +
                    "6. Добавить покупателя.\n" +
                    "7. Получить информацию о покупателе.\n" +
                    "8. Изменить имя покупателя.\n" +
                    "9. Получить список покупателей.\n" +
                    "10. Создать заказ.\n" +
                    "11. Получить список заказов.\n" +
                    "12. Завершить работу.");
            action = console.nextLine();
            boolean flag = true, end = false;
            String title = "", price = "", index = "", name = "";
            Customer customer;
            Product product;
            switch (action) {
                case "1":
                    System.out.println("Введите название товара:");
                    title = console.nextLine();

                    if (InputCheck.productTitleCheck(title)) {
                        System.out.println("Введите цену товара:");
                        price = console.nextLine();
                    }

                    if (InputCheck.productPriceCheck(price)) {
                        System.out.println("Выберите тип товара:");
                        for (int i = 0; i < ProductType.values().length; i++) {
                            System.out.println(i + ". " + ProductType.values()[i]);
                        }
                        index = console.nextLine();
                    }

                    if (InputCheck.productIndexCheck(index)) {
                        onlineStore.addProduct(new Product(title, Double.parseDouble(price),
                                ProductType.values()[Integer.parseInt(index)]));
                    }

                    break;
                case "2":
                    System.out.println("Введите название товара:");
                    title = console.nextLine();

                    double doublePrice = 0;
                    if (InputCheck.productTitleCheck(title)) {
                        System.out.println("Введите новую цену товара:");
                        price = console.nextLine();
                        try {
                            doublePrice = Double.parseDouble(price);
                            flag = InputCheck.productPriceCheck(price);
                        } catch (Exception e) {
                            System.out.println("Введено не число. Попробуйте снова.");
                            flag = false;
                        }
                    }

                    if (flag) {
                        product = onlineStore.getProduct(title);
                        if (product != null) {
                            product.setPrice(doublePrice);
                        }
                        else {
                            System.out.println("Товар не найден. Попробуйте снова.");
                        }
                    }

                    break;
                case "3":
                    System.out.println("Введите название товара:");
                    title = console.nextLine();

                    String newTitle = "";
                    if (InputCheck.productTitleCheck(title)) {
                        System.out.println("Введите новое название товара:");
                        newTitle = console.nextLine();
                    }

                    if (InputCheck.productTitleCheck(newTitle)) {
                        product = onlineStore.getProduct(title);
                        if (product != null) {
                            onlineStore.changeProductTitle(title, newTitle);
                        } else {
                            System.out.println("Товар не найден. Попробуйте снова.");
                        }
                    }

                    break;
                case "4":
                    System.out.println("Введите название товара:");
                    title = console.nextLine();

                    if (InputCheck.productTitleCheck(title)) {
                        product = onlineStore.getProduct(title);
                        if (product != null) {
                            System.out.println(product.getProductType() + " - " + product.getTitle() +
                                    " - " + product.getPrice());
                        } else {
                            System.out.println("Товар не найден. Попробуйте снова.");
                        }
                    }

                    break;
                case "5":
                    for (Product pr: onlineStore.getProducts()) {
                        System.out.println(pr.getProductType() + " - " + pr.getTitle() + " - " + pr.getPrice());
                    }

                    break;
                case "6":
                    System.out.println("Введите имя и фамилию покупателя:");
                    name = console.nextLine();

                    if (InputCheck.customerCheck(name)) {
                        onlineStore.addCustomer(new Customer(name));
                    }

                    break;
                case "7":
                    System.out.println("Введите имя и фамилию покупателя:");
                    name = console.nextLine();
                    customer = onlineStore.getCustomer(name);
                    if (customer != null) {
                        System.out.println(customer.getName());
                        System.out.println("Заказы:");
                        for (Order order: customer.getOrders()) {
                            System.out.println("Название: " + order.getProduct().getTitle() + ". "
                                    + "Количество: " + order.getAmount() + ". "
                                    + "Стоимость: " + order.getAmount() * order.getProduct().getPrice());
                        }
                    }
                    else {
                        InputCheck.customerCheck(name);
                    }

                    break;
                case "8":
                    System.out.println("Введите имя и фамилию покупателя:");
                    name = console.nextLine();
                    customer = onlineStore.getCustomer(name);
                    if (customer != null) {
                        System.out.println("Введите новое имя и фамилию покупателя:");
                        String newName = console.nextLine();
                        if (InputCheck.customerCheck(newName)) {
                            onlineStore.changeCustomerName(name, newName);
                        }
                    } else {
                        System.out.println("Покупатель не найден. Попробуйте снова.");
                    }

                    break;
                case "9":
                    for (Customer c: onlineStore.getCustomers()) {
                        System.out.println(c.getName());
                    }

                    break;
                case "10":
                    System.out.println("Введите имя и фамилию существующего покупателя:");
                    name = console.nextLine();
                    customer = onlineStore.getCustomer(name);

                    if (customer != null) {
                        System.out.println("Введите название существующего товара:");
                        title = console.nextLine();
                        product = onlineStore.getProduct(title);
                        if (product == null) {
                            System.out.println("Товар не найден. Попробуйте снова.");
                        }
                        else {
                            System.out.println("Введите количество товара:");
                            String amount = console.nextLine();
                            if (InputCheck.amountCheck(amount)) {
                                onlineStore.createOrder(customer, product, Integer.parseInt(amount));
                            }
                        }
                    }
                    else {
                        System.out.println("Покупатель не найден. Попробуйте снова.");
                    }

                    break;
                case "11":
                    for (Order order: onlineStore.getOrders()) {
                        System.out.println("Покупатель: " + order.getCustomer().getName() + ". "
                                + "Товар: " + order.getProduct().getTitle() + ". "
                                + "Цена: " + order.getProduct().getPrice() + ". "
                                + "Количество: " + order.getAmount() + ". "
                                + "Сумма: " + order.getAmount() * order.getProduct().getPrice() + ".");
                    }

                    break;
                case "12":
                    end = true;
                    break;
                default:
                    System.out.println("Неизвестная команда. Попробуйте снова.");
            }

            if (end) {
                break;
            }
        }
    }
}
