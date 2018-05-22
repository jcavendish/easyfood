package br.com.appfactory.easyfood.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.appfactory.easyfood.entity.ProductCategoryList;

@Stateless
public class ProductCategoryDao extends AbstractDao<ProductCategoryList> {

	@PersistenceContext
	private EntityManager manager;

	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public ProductCategoryList save(ProductCategoryList entity) {
		manager.persist(entity);
		return entity;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public ProductCategoryList findOne(ProductCategoryList entity) {
		if (entity == null) {
			return null;
		}
		return findById(entity.getId());
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public ProductCategoryList findById(Long id) {
		TypedQuery<ProductCategoryList> query = manager.createNamedQuery("ProductCategoryList.findById",
				ProductCategoryList.class);
		query.setParameter("pId", id);
		return query.getSingleResult();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public List<ProductCategoryList> findAll() {
		TypedQuery<ProductCategoryList> query = manager.createNamedQuery("ProductCategoryList.findAll",
				ProductCategoryList.class);
		return query.getResultList();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void remove(ProductCategoryList entity) {
		manager.remove(entity);
	}

}
