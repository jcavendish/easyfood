package br.com.appfactory.easyfood.dao;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.appfactory.easyfood.entity.User;

@Stateless
public class UserDao extends AbstractDao<User> {

	@PersistenceContext
	private EntityManager manager;

	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public User save(User entity) {
		manager.persist(entity);
		return entity;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public User findOne(User entity) {
		if (entity == null) {
			return null;
		}
		return findById(entity.getId());
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public User findById(Long id) {
		TypedQuery<User> query = manager.createNamedQuery("User.findById", User.class);
		query.setParameter("pId", id);
		return query.getSingleResult();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public List<User> findAll() {
		TypedQuery<User> query = manager.createNamedQuery("User.findAll", User.class);
		return query.getResultList();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void remove(User entity) {
		manager.remove(entity);
	}

	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public Optional<User> findByLogin(String login) {
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
		Root<User> from = criteriaQuery.from(User.class);
		Predicate predicate = cb.equal(from.get("login"), login);
		criteriaQuery.where(predicate);
		return manager.createQuery(criteriaQuery)
				.getResultList().stream()
				.findFirst();

	}
}
