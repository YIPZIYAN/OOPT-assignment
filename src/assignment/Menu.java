package assignment;

public class Menu {

    protected String itemName, itemID;
    protected double price;

    public Menu() {
    }

    public Menu(String itemName, String itemID, double price) {
        this.itemName = itemName;
        this.itemID = itemID;
        this.price = price;
    }

    public boolean checkItem(String itemID) {
        return itemID.equals(this.itemID.substring(0, this.itemID.length() - 1));
    }

    public String displayMenuNoID() {
        return String.format("   %5s %-15s RM %5.2f  ", "", "", price);
    }

    public String displayMenu() {
        return String.format("   %4s  %-15s RM %5.2f  ", itemID.substring(0, 4), itemName, price);
    }

    @Override
    public boolean equals(Object o) {
        Menu menu = (Menu) o;
        if (o instanceof Menu) {
            if (menu.itemID.equals(this.itemID)) {
                return true;
            }
        }
        return false;
    }

}

class Food extends Menu {

    char size;

    public Food(String itemName, String itemID, double price, char size) {
        super(itemName, itemID, price);
        setSize(size);
    }

    public char getSize() {
        return size;
    }

    public void setSize(char size) {
        this.size = size;
    }

    @Override
    public String displayMenu() {
        return super.displayMenu() + String.format("(%c)   ", size);
    }

    @Override
    public String displayMenuNoID() {
        return super.displayMenuNoID() + String.format("(%c)   ", size);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Food) {
            return super.equals(o);
        }
        return false;
    }

}

class Beverage extends Menu {

    String type;

    public Beverage() {
    }

    public Beverage(String itemName, String itemID, double price, String type) {
        super(itemName, itemID, price);
        setType(type);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String displayMenu() {
        return super.displayMenu() + String.format("(%s)", type);
    }

    @Override
    public String displayMenuNoID() {
        return super.displayMenuNoID() + String.format("(%s)", type);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Beverage) {
            return super.equals(o);
        }
        return false;
    }
}
