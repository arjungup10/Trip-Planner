package nautilussoup.tripplanner.Models;

import android.util.Pair;

import java.util.ArrayList;

public class Repayment {
    private Person personPaying;
    private ArrayList<Pair<Person, Double>> peopleReceiving;
    private double amount;

    public Repayment(Person personPaying, double amount) {
        this.personPaying = personPaying;
        this.amount = amount;
        peopleReceiving = new ArrayList<Pair<Person, Double>>();
    }

    public void addPersonReceving(Person p, Double amount) {
        peopleReceiving.add(new Pair(p, amount));
    }

    public void addToAmount(double cost) {
        amount += cost;
    }


    public Person getPersonPaying() {
        return personPaying;
    }

    public  ArrayList<Pair<Person, Double>> getPeopleReceiving() {
        return peopleReceiving;
    }

    public double getAmount() {
        return amount;
    }
}
