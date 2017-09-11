package com.kostrova.tv.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.kostrova.tv.dto.Good;
import com.kostrova.tv.service.IGoodDao;

@Named
@SessionScoped
public class ShopView implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Good> goods = new ArrayList<Good>();

	private Good selectedGood;
	@Inject
	IGoodDao goodDao;
	private Integer chosenNumber = 0;

	@PostConstruct
	public void init() {
		goods.addAll(goodDao.getGoods());
	}

	public List<Good> getGoods() {
		return goods;
	}

	public void increaseChosenNumber() {
		chosenNumber++;
	}

	public void decreaseChosenNumber() {
		chosenNumber--;
	}

	public Integer getChosenNumber() {
		return chosenNumber;
	}

	public void setChosenNumber(Integer chosenNumber) {
		this.chosenNumber = chosenNumber;
	}

	public IGoodDao getGoodDao() {
		return goodDao;
	}

	public void setGoodDao(IGoodDao goodDao) {
		this.goodDao = goodDao;
	}

	public void setGoods(List<Good> goods) {
		this.goods = goods;
	}

	public Good getSelectedGood() {
		return selectedGood;
	}

	public void setSelectedGood(Good selectedGood) {
		this.selectedGood = selectedGood;
	}
}
