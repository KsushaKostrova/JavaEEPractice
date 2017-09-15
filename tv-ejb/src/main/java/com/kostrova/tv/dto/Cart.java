package com.kostrova.tv.dto;

import java.util.List;

import javax.enterprise.inject.Vetoed;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity(name = "Cart")
@Vetoed
@NamedQueries({@NamedQuery(name = "getCartByLogin", query = "SELECT c FROM Cart c WHERE c.user.login=?1"),
	@NamedQuery(name = "getCartByGoodId", query = "SELECT c FROM Cart c WHERE c.good.id=?1 and c.user.id=?2")})
public class Cart {
	@Id
	@GeneratedValue
	private Integer id;
	@OneToOne
	private Good good;
	private Integer goodOrderedQuantity;
	@OneToOne
	private User user;
	private List<String> selectedGoodId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getGoodOrderedQuantity() {
		return goodOrderedQuantity;
	}

	public void setGoodOrderedQuantity(Integer goodOrderedQuantity) {
		this.goodOrderedQuantity = goodOrderedQuantity;
	}

	public Good getGood() {
		return good;
	}

	public void setGood(Good good) {
		this.good = good;
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public List<String> getSelectedGoodId() {
		return selectedGoodId;
	}

	public void setSelectedGoodId(List<String> selectedGoodId) {
		this.selectedGoodId = selectedGoodId;
	}

	@Override
	public String toString() {
		return "Caret [id=" + id + ", good=" + good + ", user=" + user + "]";
	}
}
