package com.kostrova.tv.dto;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Vetoed;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@Vetoed
public class Address implements Serializable {
	private static final long serialVersionUID = 6270062314499750944L;

	@Id
	@GeneratedValue
	private Integer id;
	private String city;
	private String street;
	private String house;
	@OneToMany(mappedBy="address")
	private List<Order> orders;

	public Integer getId() {
		return id;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
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
		Address other = (Address) obj;
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
		return "Address [id=" + id + ", city=" + city + ", street=" + street + ", house=" + house + "]";
	}

}
