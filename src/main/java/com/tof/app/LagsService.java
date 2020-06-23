package com.tof.app;

import java.io.DataInputStream;
import java.util.*;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardOpenOption;

class LagsService {
    private List<Order> orderList = new ArrayList<Order>();
    private double sales = 0;
    private SalesCalculator salesCalculator;

    public LagsService(SalesCalculator salesCalculator) {
        this.salesCalculator = salesCalculator;
    }

    public void populateOrderList(String fileName) {
        try {
            for (String line : Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8)) {
                addOrderInOrderList(line);
            }
        } catch (IOException e) {
            System.out.println("FICHIER ORDRES.CSV NON TROUVE.");
        }
    }

    public void sortAndDisplayOrderList() {
        sortByStartDate();
        displayOrderList();
    }

    public void addOrderFromTerminal() {
        promptToAddOrder();
        addOrderInOrderList(readStringFromTerminal());
        writeInOrderFile(Constant.fileName);
    }

    public void removeSpecifiedOrderFromList() {
        promptToRemoveOrder();
        String id = readStringFromTerminal();
        removeOrderFromList(id);
        writeInOrderFile(Constant.fileName);
    }

    public void computeAndDisplaySales(boolean debug) {
        promptToDisplaySales();
        displaySales(salesCalculator.computeSales(orderList));
    }

    private void displaySales(double sales) {
        System.out.format("CA: %10.2f\n", sales);
    }

    private void promptToDisplaySales() {
        System.out.println("CALCUL CA..");
    }

    private void writeInOrderFile(String nomFich) {
        List<String> lines = new ArrayList<String>();
        for (Order order : orderList) {
            String ligneCSV = getLineCSV(order);
            lines.add(ligneCSV);
        }
        try {
            Files.write(Paths.get(nomFich), lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND, StandardOpenOption.WRITE);
        } catch (IOException e) {
            System.out.println("PROBLEME AVEC FICHIER");
        }
    }

    private String getLineCSV(Order order) {
        return order.getId() + ";" + order.getStartDate() + ";" + order.getDuration() + ";" + order.getPrice();
    }

    private Order constructOrder(String line) {
        String[] values = line.split(";");
        String id = values[0];
        int startDate = Integer.parseInt(values[1]);
        int duration = Integer.parseInt(values[2]);
        double price = Double.parseDouble(values[3]);
        return new Order(id, startDate, duration, price);
    }

    private void displayOrderList() {
        System.out.println("LISTE DES ORDRES\n");
        System.out.format("%8s %8s %5s %13s", "ID", "DEBUT", "DUREE", "PRIX\n");
        System.out.format("%8s %8s %5s %13s", "--------", "-------", "-----", "----------\n");
        for (Order order : orderList) {
            System.out.format("%8s %8d %5d %10.2f\n", order.getId(), order.getStartDate(), order.getDuration(), order.getPrice());
        }
        System.out.format("%8s %8s %5s %13s", "--------", "-------", "-----", "----------\n");
    }

    private void sortByStartDate() {
        Collections.sort(orderList, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o1.getStartDate() - o2.getStartDate(); // use your logic, Luke
            }
        });
    }

    // Temporary public for refactor reason
    public void addOrderInOrderList(String line) {
        orderList.add(constructOrder(line));
    }

    private String readStringFromTerminal() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void promptToAddOrder() {
        System.out.println("AJOUTER UN ORDRE");
        System.out.println("FORMAT = ID;DEBUT;FIN;PRIX");
    }

    private void removeOrderFromList(String id) {
        for (Iterator<Order> iterator = orderList.listIterator(); iterator.hasNext(); ) {
            Order order = iterator.next();
            if (order.getId().equals(id)) {
                iterator.remove();
            }
        }
    }

    private void promptToRemoveOrder() {
        System.out.println("SUPPRIMER UN ORDRE");
        System.out.println("ID:");
    }

    public double getSales() {
        return this.sales;
    }
}

