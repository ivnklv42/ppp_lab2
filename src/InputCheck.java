public class InputCheck {
    public static boolean productTitleCheck(String title) {
        if (title.length() > 30 || title.isEmpty()) {
            System.out.println("Длина названия товара вне диапазона 1-30 символов. Попробуйте снова.");
            return false;
        }

        if (!title.matches("[\\wа-яА-ЯёЁ\\p{Punct} ]+")) {
            System.out.println("В названии используются некорректные символы.\n" +
                    "Можно использовать русские и английские буквы любого регистра, знаки препинания, " +
                    "цифры и пробел. Попробуйте снова.");
            return false;
        }

        return true;
    }

    public static boolean productPriceCheck(String strPrice) {
        double price;
        try {
            price = Double.parseDouble(strPrice);
        } catch (NumberFormatException e) {
            System.out.println("Цена введена неверно. Попробуйте снова.");
            return false;
        }

        if (price < 1 || price > 5_000_000) {
            System.out.println("Цена находится вне диапазона 1 - 5.000.000. Попробуйте снова.");
            return false;
        }

        return true;
    }

    public static boolean productIndexCheck(String index) {
        int type;
        try {
            type = Integer.parseInt(index);
            ProductType pt = ProductType.values()[type];
        }
        catch (Exception e) {
            System.out.println("Неверный тип товара. Попробуйте снова.");
            return false;
        }

        return true;
    }

    public static boolean customerCheck(String name) {
        if (name.length() > 30 || name.length() < 3) {
            System.out.println("Длина имени вне диапазона 3-30 символов. Попробуйте снова.");
            return false;
        }

        if (!name.matches("[A-ZА-ЯЁ][a-zа-яё]* [A-ZА-ЯЁ][a-zа-яё]*")) {
            System.out.println("Имя введено неверно. Попробуйте снова.");
            return false;
        }

        return true;
    }

    public static boolean amountCheck(String amount) {
        int intAmount = 0;
        try {
            intAmount = Integer.parseInt(amount);
            if (intAmount < 0 || intAmount > 10000) {
                System.out.println("Количество вне диапазона 0 - 10_000. Попробуйте снова.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Введено не число. Попробуйте снова.");
            return false;
        }

        return true;
    }
}
