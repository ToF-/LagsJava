package com.tof.app;

import java.util.*;

public class SalesCalculator {
    private List<Order> orderList = new ArrayList<>();

    public double getSales() {
        return 0;
    }

    public double computeSales(List<Order> orderList) {
        sortByStartDate();
        // si aucun ordre, job done, TROLOLOLO..
        if (orderList.size() == 0)
            return 0.0;
        Order order = orderList.get(0);
        // attention ne marche pas pour les ordres qui depassent la fin de l'année
        // voir ticket PLAF nO 4807
        List<Order> liste = new ArrayList<Order>();
        for (Iterator<Order> iter = orderList.listIterator(); iter.hasNext(); ) {
            Order o = iter.next();
            if (o.getStartDate() >= order.getStartDate() + order.getDuration()) {
                liste.add(o);
            }
        }
        List<Order> liste2 = new ArrayList<Order>();
        for (int i = 1; i < orderList.size(); i++) {
            liste2.add(orderList.get(i));
        }
        double ca = order.getPrice() + computeSales(liste);
        // Lapin compris?
        double ca2 = computeSales(liste2);
        return Math.max(ca, ca2); // LOL
    }

    public void addOrder(String id, int startDate, int duration, float price) {
        orderList.add(new Order(id, startDate, duration, price));
    }

    private void sortByStartDate() {
        Collections.sort(orderList, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o1.getStartDate() - o2.getStartDate(); // use your logic, Luke
            }
        });
    }
}
