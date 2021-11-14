package org.itstep;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class ShopQ {
    //Очередь в магазин. Каждый покупатель имеет корзину, в каждой корзине набор товаров определенной цены.
    //После обслуживания покупателя сумма в кассе увеличивается.
    //Без очереди обслуживаются ветераны, затем дети до 10 лет

    static double sum = 0;
    public static void main(String[] args) {
        Product water = new Product("water", 1.19);
        Product bread = new Product("bread", 2.39);
        Product gum = new Product("gum", 1.59);
        Seller seller = new Seller();
        Buyer buyer1 = new Buyer(TypeCustomer.REGULAR);
        buyer1.addProduct(gum);
        Buyer buyer2 = new Buyer(TypeCustomer.VETERAN);
        buyer2.addProduct(bread).addProduct(water);
        Buyer buyer3 = new Buyer(TypeCustomer.CHILD);
        buyer3.addProduct(bread).addProduct(gum);
        PriorityQueue<Buyer> queue = new PriorityQueue<>();
        queue.add(buyer1);
        queue.add(buyer2);
        queue.add(buyer3);
        while (!queue.isEmpty()) {
            Buyer buyer = queue.poll();
            sum += seller.calcSumm(buyer);//сумма в кассе
            //sum = seller.calcSumm(buyer);//каждый покупатель
            System.out.println(buyer.tCust + " " + sum);
        }
     }
}
class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
    enum TypeCustomer{
        VETERAN, CHILD, REGULAR;
}
class Buyer implements Comparable {
public TypeCustomer tCust;
public ArrayList<Product> busket = new ArrayList<>();

    public Buyer(TypeCustomer tCust) {
        this.tCust = tCust;
    }

    @Override
    public int compareTo(Object o) {
        int index1 = this.tCust.ordinal();
        int index2 = ((Buyer)o).tCust.ordinal();

        return index1-index2;
    }
    public Buyer addProduct(Product product){
        busket.add(product);
        return this;
    }
}
class Seller {
    public double calcSumm(Buyer buyer){
        double sum = buyer.busket.stream().mapToDouble(p -> p.getPrice()).sum();
                return sum;
    }
}
