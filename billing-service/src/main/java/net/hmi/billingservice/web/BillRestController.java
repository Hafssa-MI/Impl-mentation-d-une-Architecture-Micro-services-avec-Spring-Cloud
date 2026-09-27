package net.hmi.billingservice.web;

import net.hmi.billingservice.dto.BillRequestDTO;
import net.hmi.billingservice.entities.Bill;
import net.hmi.billingservice.entities.ProductItem;
import net.hmi.billingservice.feign.CustomerServiceRestClient;
import net.hmi.billingservice.feign.InventoryServiceRestClient;
import net.hmi.billingservice.model.Customer;
import net.hmi.billingservice.model.Product;
import net.hmi.billingservice.repository.BillRepository;
import net.hmi.billingservice.repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
    @PostMapping("/bills/full")
    public Bill createBill(@RequestBody BillRequestDTO billRequestDTO) {
        Bill bill = new Bill();
        bill.setBillingDate(new Date());
        bill.setCustomerId(billRequestDTO.getCustomerId());
        bill.setProductsItems(new java.util.ArrayList<>());
        billRepository.save(bill);

        billRequestDTO.getProductItems().forEach(itemRequest -> {
            Product product = inventoryServiceRestClient.getProduct(itemRequest.getProductId());

            ProductItem productItem = new ProductItem();
            productItem.setProductId(itemRequest.getProductId());
            productItem.setQuantity(itemRequest.getQuantity());
            productItem.setPrice(product.getPrice());
            productItem.setBill(bill);
            productItemRepository.save(productItem);
        });

        return getBillById(bill.getId());
    }
}
