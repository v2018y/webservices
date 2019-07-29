package com.vany.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ReceivingStatement")

public class RecvStateBar implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "receId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "receQty")
	private Long receQty;

	@Column(name = "tpNo")
	private String tpNo;

	@Column(name = "receDate")
	private String createdAt;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "bar_item_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Bar bar;

	@JoinColumn(name = "openStateId")
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private OpenSateBar openSateBar;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTpNo() {
		return tpNo;
	}

	public void setTpNo(String tpNo) {
		this.tpNo = tpNo;
	}

	public Long getReceQty() {
		return receQty;
	}

	public void setReceQty(Long receQty) {
		this.receQty = receQty;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public Bar getBar() {
		return bar;
	}

	public void setBar(Bar bar) {
		this.bar = bar;
	}

	public OpenSateBar getOpenSateBar() {
		return openSateBar;
	}

	public void setOpenSateBar(OpenSateBar openSateBar) {
		this.openSateBar = openSateBar;
	}

}
