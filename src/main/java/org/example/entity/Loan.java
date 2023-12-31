package org.example.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Loan extends FinancialProfile {

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private LoanType type;

    @ManyToMany(mappedBy = "loans", fetch = FetchType.LAZY)
    @Cascade(value = CascadeType.SAVE_UPDATE)
    private Set<Customer> customers = new HashSet<>();

    public Loan() {
    }

    public Loan(LoanType type) {
        this.type = type;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public void removeCustomersAssociation() {
        for (Customer customer : customers) {
            customer.getLoans().remove(this);
        }
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + getId() +
                ", amount=" + getAmount() +
                ", type=" + type +
                ", customers=" + customers +
                '}';
    }
}
