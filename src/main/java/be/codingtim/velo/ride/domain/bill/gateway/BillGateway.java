package be.codingtim.velo.ride.domain.bill.gateway;

import be.codingtim.velo.ride.domain.bill.Bill;

public interface BillGateway {
    void handle(Bill bill);
}
