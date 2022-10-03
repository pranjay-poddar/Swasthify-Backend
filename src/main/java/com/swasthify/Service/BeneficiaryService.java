package com.swasthify.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.swasthify.Entities.Beneficiaries;
import com.swasthify.Entities.Orders;
import com.swasthify.Exception.ResourceNotFound;
import com.swasthify.Repository.BeneficiariesRepo;
import com.swasthify.Repository.OrdersRepo;

@Service
public class BeneficiaryService {

	@Autowired
	private BeneficiariesRepo benRepo;
	@Autowired
	private OrdersRepo orderRepo;
	
	//add new order
	public ResponseEntity<Orders> addNewOrder(Long id, Orders order){
		Beneficiaries obj = benRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Beneficiary doesn't exist"));
		List<Orders> list = obj.getOrders();
		order.setConfirm(false);
		order.setReschedule(false);
		list.add(order);
		benRepo.save(obj);
		Orders currOrder = list.get(list.size() - 1);
		return ResponseEntity.ok(currOrder);
	}
	//reschedule order for id
	public ResponseEntity<Orders> resOrder(Long id, String date) {
		// TODO Auto-generated method stub
		boolean check = orderRepo.existsById(id);
		Orders order = new Orders();
		if(check == false) {
			order.setCode(01);
			order.setMessage("Order not found");
		}
		else {
			order = orderRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Order doesn't exist"));
			order.setDate(date);
			order.setCode(00);
			order.setMessage("Order found");
			orderRepo.save(order);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(order);
	}
}
