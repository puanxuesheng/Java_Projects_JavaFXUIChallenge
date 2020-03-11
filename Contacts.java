package sample;

import java.util.Random;

public class Contacts
{
    private int HPno;
    private String firstName;
    private String lastName;
    private String email;
    private String location;
    private int salary;

    @Override
    public String toString() {
        return "Name: "+ firstName + " "+ lastName+
                "\nEmail: " + email+
                "\nContact: "+ HPno+
                "\nLocation: "+ location+
                "\nSalary: "+ salary;
    }

    Contacts(String firstname, String lastname)
    {
        Random rand = new Random();
        this.firstName = firstname;
        this.lastName = lastname;
        this.HPno = 90000000+rand.nextInt(9999999);
        String emailname = firstName.replaceAll(" ","_");
        this.email = emailname + "_"+ lastname +"@gmail.com";
        this.location = "Singapore";
        this.salary = rand.nextInt(10000)+2000;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public int getHPno() {
        return HPno;
    }
}
