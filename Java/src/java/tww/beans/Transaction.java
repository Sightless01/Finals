package tww.beans;

import java.util.Date;

public class Transaction {
    private int client_id;
    private Date date_booked;
    private Date date_paid;
    private Date date_returned;
    private int prod_id;
    public Transaction(Date date_booked, Date date_paid, Date date_returned, int client_id,  int prod_id) {
        this.date_booked=date_booked;
        this.date_paid=date_paid;
        this.date_returned=date_returned;
        this.client_id=client_id;
        this.prod_id=prod_id;
    }
    public int getClient_id() {
        return client_id;
    }

}
