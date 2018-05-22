package br.com.appfactory.easyfood.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.appfactory.easyfood.entity.Product;

@Stateless
public class ProductDao extends AbstractDao<Product> {

	@PersistenceContext
	private EntityManager manager;

	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public Product save(Product entity) {
		manager.persist(entity);
		return entity;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public Product findOne(Product entity) {
		if (entity == null) {
			return null;
		}
		return findById(entity.getId());
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public Product findById(Long id) {
		TypedQuery<Product> query = manager.createNamedQuery("Product.findById", Product.class);
		query.setParameter("pId", id);
		return query.getSingleResult();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public List<Product> findAll() {
		TypedQuery<Product> query = manager.createNamedQuery("Product.findAll", Product.class);
		return query.getResultList();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void remove(Product entity) {
		manager.remove(entity);
	}
}
