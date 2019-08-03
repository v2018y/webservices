package com.vany.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "MainBar")
public class Bar {

	@Id
	@Column(name = "itemId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer itemId;

	@Column(name = "itemName")
	private String itemName;

	@Column(name = "itemQty")
	private Long itemQty;

	@Column(name ="itemPrice")
	private Double itemPrice;
	
	@Column(name ="itemType")
	private String itemType;
	
	@Column(name ="itemSize")
	private String itemSize;
	

	@Column(name = "itemCretated")
	private Date created;

	@Column(name = "itemUpdated")
	private Date updated;

	@PrePersist
	protected void onCreate() {
		created = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		updated = new Date();
	}
	
	@Column(name = "userId")
	private long userId;

	
	//Foregin Key 
	
	@OneToMany(mappedBy = "bar")
	private Set<OpenSateBar> openState =new HashSet<OpenSateBar>();
	
	@OneToMany(mappedBy = "bar")
	private Set<SalesStateBar> salesSateBar =new HashSet<SalesStateBar>();
	
	@OneToMany(mappedBy = "bar")
	private Set<RecvStateBar> recvSateBar =new HashSet<RecvStateBar>();
	
	

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Long getItemQty() {
		return itemQty;
	}

	public void setItemQty(Long itemQty) {
		this.itemQty = itemQty;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItemSize() {
		return itemSize;
	}

	public void setItemSize(String itemSize) {
		this.itemSize = itemSize;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Set<OpenSateBar> getOpenState() {
		return openState;
	}

	public void setOpenState(Set<OpenSateBar> openState) {
		this.openState = openState;
	}

	public Set<SalesStateBar> getSalesSateBar() {
		return salesSateBar;
	}

	public void setSalesSateBar(Set<SalesStateBar> salesSateBar) {
		this.salesSateBar = salesSateBar;
	}

	public Set<RecvStateBar> getRecvSateBar() {
		return recvSateBar;
	}

	public void setRecvSateBar(Set<RecvStateBar> recvSateBar) {
		this.recvSateBar = recvSateBar;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	


}
