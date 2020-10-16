import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nina Eriksson
 * Date: 2020-10-14
 * Time: 14:44
 * Project: BestGymEver
 * Copyright: MIT
 */

public class BestGymEver {

    private static List<Customer> myCustomers = new ArrayList<>();


    public static void main(String[] args) {
        readFromFile();

        boolean isMember = false;
        Customer myCustomer;
        String customerInput = JOptionPane.showInputDialog("Ange personnummer (ååmmddxxxx) eller namn");
        customerInput = customerInput.trim();

        if (Character.isDigit(customerInput.charAt(0))) { //om personnummer
            myCustomer = findMemberById(customerInput);
        } else { //om namn
            myCustomer = findMemberByName(customerInput);
        }

        if (myCustomer != null) {
            isMember = checkIfMember(myCustomer);
        } else {
            System.out.println("Personen finns inte i medlemsregistret!");
        }

        if (isMember) {
            addGymVisitToFile(myCustomer);
        }
    }


    protected static boolean checkIfMember(Customer myCustomer) {
        boolean existIdNumber = false;
        for (Customer cu : myCustomers) {
            if (cu.getIdNumber().equalsIgnoreCase(myCustomer.getIdNumber())) {
                existIdNumber = true;
            }
        }

        if (existIdNumber == true) {
            LocalDate myCustomerDate = myCustomer.getDate();
            LocalDate aYearAgo = LocalDate.now().minusYears(1);

            if (aYearAgo.compareTo(myCustomerDate) == 0) {
                System.out.println("Det är exakt ett år sedan " + myCustomer.getName() + " betalade medlemsavgiften.");
                return true;
            }
            if (aYearAgo.compareTo(myCustomerDate) < 0) {
                System.out.println(myCustomer.getName() + " är medlem.");
                return true;
            }
            if (aYearAgo.compareTo(myCustomerDate) > 0) {
                System.out.println(myCustomer.getName() + " är inte aktiv medlem längre.");
                return false;
            }
        }
        return false;
    }


    protected static Customer findMemberById(String customer) {
        for (Customer cust : myCustomers) {
            if (cust.getIdNumber().equals(customer)) {
                return cust;
            }
        }
        return null;
    }


    protected static Customer findMemberByName(String customer) {
        for (Customer cust : myCustomers) {
            if (cust.getName().equalsIgnoreCase(customer)) {
                return cust;
            }
        }
        return null;
    }


    protected static void addGymVisitToFile(Customer myCustomer) {
        String fileName = "members\\" + myCustomer.getIdNumber() + ".txt";
        //String fileName = "C:\\Users\\nina_\\Desktop\\JAVA20A\\Objektorienterad_programmering_ochJava\\Sprint_2" +
        //  "\\Inlamningsuppgift2\\members\\" + myCustomer.getIdNumber() + ".txt";
        try {
            File file = new File(fileName);
            FileWriter fw;

            if (file.exists()) {
                fw = new FileWriter(file, true);
                fw.write(LocalDate.now().toString() + "\n");
                fw.flush();
            } else {
                file.createNewFile();
                fw = new FileWriter(file);
                fw.write(myCustomer.getName() + ", " + myCustomer.getIdNumber() + "\n\n");
                fw.write(LocalDate.now().toString() + "\n");
                fw.flush();
            }
        } catch (IOException e) {
            System.out.println("Kunde inte skriva till filen");
        }
    }


    protected static void readFromFile() {
        String date;
        String tempLine;
        LocalDate lastPaid = null;
        String idNumber = null;
        String name = null;

        //läsa in från fil och spara i lista
        try {
            BufferedReader bufIn;
            boolean nameRow = true;
            boolean createObject;
            bufIn = new BufferedReader(new FileReader("customers.txt"));

            while ((tempLine = bufIn.readLine()) != null) {
                if (nameRow) {
                    idNumber = tempLine.substring(0, tempLine.indexOf(","));
                    name = tempLine.substring(tempLine.indexOf(",") + 1);
                    name = name.trim();
                    nameRow = false;
                    createObject = false;
                } else {
                    date = tempLine;
                    lastPaid = LocalDate.parse(date);
                    nameRow = true;
                    createObject = true;
                }

                if (createObject) { //om all data finns för att skapa ett objekt av klassen Customer
                    Customer customer = new Customer();
                    customer.setIdNumber(idNumber);
                    customer.setName(name);
                    customer.setDate(lastPaid);
                    myCustomers.add(customer);
                }

            }
        } catch (Exception e) {
            System.out.println("Kunde inte läsa från filen!");
        }
    }
}
