package fift.server.service.tier;

import fift.server.domain.customer.Customer;
import fift.server.domain.tier.Tier;
import fift.server.repository.customer.CustomerRepository;
import fift.server.repository.tier.TierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TierService {
    private final CustomerRepository customerRepository;
    private final TierRepository tierRepository;

    public void updateTierByAmount(Customer customer) {
        double amount = customer.getAmount();

        Tier newTier = determineTierByAmount(amount);

        customer.setTier(newTier);
        customerRepository.save(customer);
    }

    public Tier determineTierByAmount(double amount) {
        int tierNumber = (int) (amount / 50000) + 1;

        if (tierNumber < 1 || tierNumber > 100) {
            throw new IllegalArgumentException("Invalid tier number");
        }

        String tierName = "level_" + tierNumber;
        return tierRepository.findByName(tierName).get();
    }
}
