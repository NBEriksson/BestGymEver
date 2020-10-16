import java.time.LocalDate;

/**
 * Created by Nina Eriksson
 * Date: 2020-10-14
 * Time: 14:58
 * Project: BestGymEver
 * Copyright: MIT
 */

public class Customer {

    private String idNumber;
    private String name;
    private LocalDate lastPaid;


    public Customer() {
    }

    public Customer(String idNumber, String name, String lastPaid) {
        this.idNumber = idNumber;
        this.name = name;
        this.lastPaid = LocalDate.parse(lastPaid);
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return lastPaid;
    }

    public void setDate(LocalDate date) {
        this.lastPaid = date;
    }

}
