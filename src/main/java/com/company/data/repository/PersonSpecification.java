package com.company.data.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.company.data.domain.Person;
import com.company.data.service.dto.PersonDTO;

import net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils;

public class PersonSpecification {
	public static Specification<Person> applyFilters(PersonDTO person) {
		return new Specification<Person>(){
			@Override
			public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if (!StringUtils.isEmpty(person.getFavouriteColour())) {
					predicates.add(criteriaBuilder.equal(root.get("favouriteColour"), person.getFavouriteColour()));
				}
				if (!StringUtils.isEmpty(person.getFirstName())) {
					predicates.add(criteriaBuilder.equal(root.get("firstName"), person.getFirstName()));
				}
				if (!StringUtils.isEmpty(person.getLastName())) {
					predicates.add(criteriaBuilder.equal(root.get("lastName"), person.getLastName()));
				}
				if (person.getAge() != null ) {
					predicates.add(criteriaBuilder.equal(root.get("age"), person.getAge()));
				}
				if (person.getId() != null ) {
					predicates.add(criteriaBuilder.equal(root.get("id"), person.getId()));
				}
				
				query.orderBy(criteriaBuilder.asc(root.get("id")));

				return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
			}
			
		} ;
	}
}
