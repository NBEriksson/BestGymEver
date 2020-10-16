import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Nina Eriksson
 * Date: 2020-10-15
 * Time: 09:46
 * Project: BestGymEver
 * Copyright: MIT
 */

public class CustomerTest {

    public Customer customer = new Customer("7002131234", "Kalle Ek", "2020-02-11");

    @Test
    public final void getterTest() {
        assertTrue(customer.getIdNumber() == "7002131234");
        assertFalse(customer.getIdNumber() == "7702131234");
        assertTrue(customer.getName() == "Kalle Ek");
        assertFalse(customer.getName() == "Stina Nilsson");
        assertTrue(customer.getDate().equals(LocalDate.parse("2020-02-11")));
        assertFalse(customer.getDate() == LocalDate.parse("2000-01-01"));
    }

    @Test
    public final void setterTest() {
        assertTrue(customer.getIdNumber() == "7002131234");
        customer.setIdNumber("7202131234");
        assertTrue(customer.getIdNumber() == "7202131234");
        assertFalse(customer.getIdNumber() == "7307131234");
    }
}
