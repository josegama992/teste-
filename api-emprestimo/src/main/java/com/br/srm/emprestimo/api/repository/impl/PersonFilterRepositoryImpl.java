package com.br.srm.emprestimo.api.repository.impl;

import com.br.srm.emprestimo.api.filter.PersonFilter;
import com.br.srm.emprestimo.api.model.Person;
import com.br.srm.emprestimo.api.repository.PersonFilterRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Repository
public class PersonFilterRepositoryImpl implements PersonFilterRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Person> findAll(PersonFilter filter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = builder.createQuery(Person.class);
        Root<Person> root = criteriaQuery.from(Person.class);
        List<Predicate> predicates = getPridicates(filter, builder, root);

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        criteriaQuery.orderBy(QueryUtils.toOrders(pageable.getSort(), root, builder));
        TypedQuery<Person> query = manager.createQuery(criteriaQuery);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        return new PageImpl<>(query.getResultList(), pageable, getTotalCount(filter, builder));
    }

    private List<Predicate> getPridicates(PersonFilter filter, CriteriaBuilder builder, Root<Person> root){
        List<Predicate> predicates = new ArrayList<>();
        if(nonNull(filter)) {
            if (nonNull(filter.getName())) {
                predicates.add(builder.like(builder.lower(root.get("name")), "%" + filter.getName().toLowerCase() + "%"));
            }
            if (nonNull(filter.getTypeIdentifier())) {
                predicates.add(builder.equal(root.get("typeIdentifier"), filter.getTypeIdentifier()));
            }
            if (nonNull(filter.getBirthDate())) {
                predicates.add(builder.equal(root.get("birthDate"), filter.getBirthDate()));
            }
        }
        return predicates;
    }
    private Long getTotalCount(PersonFilter filter, CriteriaBuilder builder) {
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Person> root = criteriaQuery.from(Person.class);

        criteriaQuery.select(builder.count(root));
        criteriaQuery.where(getPridicates(filter, builder, root).toArray(new Predicate[0]));
        return manager.createQuery(criteriaQuery).getSingleResult();
    }
}
