package com.kostrova.tv.dto;

import javax.enterprise.inject.Vetoed;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@Vetoed
@NamedQuery(name = "getQuantityByIdOrderId", query = "SELECT o.quantity FROM OrderedGood o WHERE o.goodId=?1 AND o.orderId=?2")
public class OrderedGood {
	@Id
	@GeneratedValue
	private Integer id;
	private Integer goodId;
	private Integer quantity;
	private Integer orderId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGoodId() {
		return goodId;
	}
	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	
}
