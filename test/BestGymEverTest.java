import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Nina Eriksson
 * Date: 2020-10-14
 * Time: 14:55
 * Project: BestGymEver
 * Copyright: MIT
 */

public class BestGymEverTest {


    @Test
    public final void checkIfMemberTest() {
        BestGymEver.readFromFile();
        Customer myCustomer = new Customer("7805211234", "Nahema Ninsson", "2020-08-04");
        Customer notMyCustomer = new Customer("7001011234", "Sara Björk", "2020-08-04");

        assertEquals(BestGymEver.checkIfMember(myCustomer), true);
        assertEquals(BestGymEver.checkIfMember(notMyCustomer), false);
    }

    @Test
    public final void findMemberByIdTest() {
        assertTrue(BestGymEver.findMemberById("7805211234").getName().equals("Nahema Ninsson"));
        assertFalse(BestGymEver.findMemberById("7805211234").getName().equals("Anna Ninsson"));
        assertNull(BestGymEver.findMemberById(" "));
    }

    @Test
    public final void findMemberByNameTest() {
        assertTrue(BestGymEver.findMemberByName("Nahema Ninsson").getIdNumber().equals("7805211234"));
        assertFalse(BestGymEver.findMemberByName("Nahema Ninsson").getIdNumber().equals("7205211234"));
        assertNull(BestGymEver.findMemberByName(" "));
    }
}
