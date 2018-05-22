package br.com.appfactory.easyfood.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = "ProductCategoryList.findById", query = "select c from ProductCategoryList c where c.id = :pId"),
	@NamedQuery(name = "ProductCategoryList.findAll", query = "select c from ProductCategoryList c")
})
public class ProductCategoryList {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String code;

	private String label;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
