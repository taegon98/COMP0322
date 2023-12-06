package fift.server.service.customer;

import fift.server.domain.customer.Customer;
import fift.server.domain.customer.Customer_size;
import fift.server.domain.tier.Tier;
import fift.server.repository.customer.CustomerRepository;
import fift.server.repository.customer.CustomerSizeRepository;
import fift.server.repository.tier.TierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final TierRepository tierRepository;
    private final CustomerSizeRepository customerSizeRepository;

    //회원가입
    public Customer registerCustomer(Customer customerDto) {
        Random random = new Random();
        Optional<Tier> tier = tierRepository.findByName("level_1");

        Customer customer = new Customer();
        customer.setCustomerId(random.nextLong(1001l) + 1000l);
        customer.setTier(tier.get());
        customer.setName(customerDto.getName());
        customer.setUserId(customerDto.getUserId());
        customer.setPassword(customerDto.getPassword());
        customer.setAddress(customerDto.getAddress());
        customer.setPostalCode(customerDto.getPostalCode());
        customer.setAmount(0.0);

        return customerRepository.save(customer);
    }

    //사이즈 등록
    public Customer_size addCustomerSize(Long customerId, Customer_size customerSizeDto) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        Customer_size customerSize = new Customer_size();
        customerSize.setCustomer(customer);
        customerSize.setSleeveLength(customerSizeDto.getSleeveLength());
        customerSize.setTopLength(customerSizeDto.getTopLength());
        customerSize.setShoulder(customerSizeDto.getShoulder());
        customerSize.setChestCircumference(customerSizeDto.getChestCircumference());
        customerSize.setWaist(customerSizeDto.getWaist());
        customerSize.setHipCircumference(customerSizeDto.getHipCircumference());
        customerSize.setThighCircumference(customerSizeDto.getThighCircumference());
        customerSize.setInseam(customerSizeDto.getInseam());
        customerSize.setFootLength(customerSizeDto.getFootLength());
        customerSize.setHeadCircumference(customerSizeDto.getHeadCircumference());

        return customerSizeRepository.save(customerSize);
    }

    // 비밀번호 변경
    public Customer changePassword(Long customerId, String newPassword) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        customer.setPassword(newPassword);
        return customerRepository.save(customer);
    }

    public Customer getCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        return customer;
    }
}
