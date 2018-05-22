package br.com.appfactory.easyfood.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.appfactory.easyfood.entity.Cart;

@Stateless
public class CartDao extends AbstractDao<Cart> {

	@PersistenceContext
	private EntityManager manager;

	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public Cart save(Cart entity) {
		manager.persist(entity);
		return entity;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public Cart findOne(Cart entity) {
		if (entity == null) {
			return null;
		}
		return findById(entity.getId());
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public Cart findById(Long id) {
		TypedQuery<Cart> query = manager.createNamedQuery("Cart.findById", Cart.class);
		query.setParameter("pId", id);
		return query.getSingleResult();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public List<Cart> findAll() {
		TypedQuery<Cart> query = manager.createNamedQuery("Cart.findAll", Cart.class);
		return query.getResultList();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void remove(Cart entity) {
		manager.remove(entity);
	}
}
