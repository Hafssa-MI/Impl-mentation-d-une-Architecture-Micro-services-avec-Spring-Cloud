package net.hmi.billingservice.repository;

import net.hmi.billingservice.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill,Long> {

}
