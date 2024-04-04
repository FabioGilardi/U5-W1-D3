package com.epicode.U5D2;

import com.epicode.U5D2.entities.Drink;
import com.epicode.U5D2.entities.Order;
import com.epicode.U5D2.entities.Pizza;
import com.epicode.U5D2.entities.Table;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class Esercizio30102023ApplicationTests {

    @Test
    void margheritaPizzaCaloriesIsRight() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(U5D2Application.class);
        Pizza pizzaMargherita = (Pizza) ctx.getBean("pizza_margherita");
        assertEquals(pizzaMargherita.getCalories(), 792);
    }

    @Test
    void margheritaPizzaPriceIsRight() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(U5D2Application.class);
        Pizza pizzaMargherita = (Pizza) ctx.getBean("pizza_margherita");
        assertEquals(pizzaMargherita.getPrice(), 4.99);
    }

    @Test
    void addItemInOrderClass() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(U5D2Application.class);
        Table table = (Table) ctx.getBean("Tavolo1");
        Pizza pizzaMargherita = (Pizza) ctx.getBean("pizza_margherita");
        Order tryOrder = new Order(1, table);
        tryOrder.addItem(pizzaMargherita);
        assertEquals(tryOrder.getOrderedProducts().size(), 1);
    }
    
    @Test
    void verifyDrinkList() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(U5D2Application.class);
        List<Drink> drinkList = (List<Drink>) ctx.getBean("drinks");
        assertEquals(drinkList.size(), 3);
    }

    @ParameterizedTest
    @CsvSource({"Tavolo1, pizza_margherita, 6.99", "Tavolo2, salami_pizza, 7.9799999999999995"})
    void totatIsCorrect(String tableName, String pizzaName, double cost) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(U5D2Application.class);
        Table table = (Table) ctx.getBean(tableName);
        Pizza pizzaMargherita = (Pizza) ctx.getBean(pizzaName);
        Order tryOrder = new Order(1, table);
        tryOrder.addItem(pizzaMargherita);
        assertEquals(tryOrder.getTotal(), cost);
    }

}
