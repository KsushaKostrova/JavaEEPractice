package com.kostrova.tv.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.enterprise.inject.Vetoed;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity(name = "Orders")
@Vetoed
@NamedQuery(name = "getOrdersByLogin", query = "SELECT o FROM Orders o WHERE o.user.login=?1")
public class Order implements Serializable {
	private static final long serialVersionUID = -3182018251074377947L;

	@Id
	@GeneratedValue
	private Integer id;
	private LocalDateTime orderTime;
	@ManyToMany
	@JoinTable(name = "ORDER_GOOD", joinColumns = @JoinColumn(name = "ORDER_ID", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "GOOD_ID", referencedColumnName = "id"))
	private List<Good> goods;
	private User user;
	@ManyToOne
	private Address address;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(LocalDateTime orderTime) {
		this.orderTime = orderTime;
	}

	public List<Good> getGoods() {
		return goods;
	}

	public void setGoods(List<Good> goods) {
		this.goods = goods;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderTime=" + orderTime + ", goods=" + goods + ", user=" + user + ", address="
				+ address + "]";
	}

}
