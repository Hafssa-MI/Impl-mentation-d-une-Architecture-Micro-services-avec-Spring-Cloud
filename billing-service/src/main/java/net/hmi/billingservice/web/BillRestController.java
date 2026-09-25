package net.hmi.billingservice.web;

import net.hmi.billingservice.entities.Bill;
import net.hmi.billingservice.feign.CustomerServiceRestClient;
import net.hmi.billingservice.feign.InventoryServiceRestClient;
import net.hmi.billingservice.model.Customer;
import net.hmi.billingservice.repository.BillRepository;
import net.hmi.billingservice.repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BillRestController {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private CustomerServiceRestClient customerServiceRestClient;
    @Autowired
    private InventoryServiceRestClient inventoryServiceRestClient;
    @GetMapping("/bills/{id}")
    public Bill getBillById(@PathVariable Long id){
        Bill bill = billRepository.findById(id).get();
        Customer customer=customerServiceRestClient.findCustomerById(bill.getCustomerId());
        bill.setCustomer(customer);
        bill.getProductsItems().forEach(productItem->{
            productItem.setProduct(inventoryServiceRestClient.getProduct(productItem.getProductId()));
        });
        return bill;
    }
}
