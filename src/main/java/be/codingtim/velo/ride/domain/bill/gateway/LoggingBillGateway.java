package be.codingtim.velo.ride.domain.bill.gateway;

import be.codingtim.velo.ride.domain.bill.Bill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class LoggingBillGateway implements BillGateway {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingBillGateway.class);

    @Override
    public void handle(Bill bill) {
        LOGGER.info("Sending out bill {}", bill);
    }
}
