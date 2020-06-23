package com.tof.app;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SalesCalculatorTest {

    @Test
    public void getSales_gives_0_initially() {
        // given
        SalesCalculator salesCalculator = new SalesCalculator();

        // then
        assertEquals(0, salesCalculator.getSales(), 0.1);
    }

    @Test
    public void getSales_with_one_order_is_order_price() {
        // given
        SalesCalculator salesCalculator = new SalesCalculator();
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("0", 1, 1, 42F));

        // then
        assertEquals(42, salesCalculator.computeSales(orders), 0.1);
    }
}