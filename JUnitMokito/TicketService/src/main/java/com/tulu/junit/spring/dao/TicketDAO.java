package com.tulu.junit.spring.dao;

import com.tulu.junit.spring.dto.Ticket;

/**
 * Created by trankhai on 9/18/17.
 */
public interface TicketDAO {
    int createTicket(Ticket ticket);
}
