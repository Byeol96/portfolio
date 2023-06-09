package com.reservation.foodTable.entity;

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

@Entity
public class Category {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="category_id")
	private Integer id;
	
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent_id")
	private Category parent;
	
	@OneToMany(mappedBy="parent",fetch=FetchType.LAZY)
	private Set<Category> child;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public Set<Category> getChild() {
		return child;
	}

	public void setChild(Set<Category> child) {
		this.child = child;
	}

	public Category(Integer id) {
		super();
		this.id = id;
	}

	public Category() {
		super();
	}
	
	
	
}
