package common;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.criterion.Restrictions.in;

public class MovieSpecification implements Specification<Movie> {

    private List<SearchCriteria> list;

    public MovieSpecification() {
        list = new ArrayList<SearchCriteria>();
    }

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }

    public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<Predicate>();

        for(SearchCriteria criteria: list) {
            if(criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {
                predicates.add(criteriaBuilder.greaterThan(
                        root.get(criteria.getKey()), criteria.getValue().toString())
                );
            }else if(criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {
                predicates.add(criteriaBuilder.lessThan(
                        root.get(criteria.getKey()), criteria.getValue().toString())
                );
            }else if(criteria.getOperation().equals(SearchOperation.LESS_THAN)) {
                predicates.add(criteriaBuilder.lessThan(
                        root.get(criteria.getKey()), criteria.getValue().toString())
                );
            }else if(criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString())
                );
                }

             if(criteria.getOperation().equals(SearchOperation.EQUAL)) {
                predicates.add(criteriaBuilder.equal(
                        root.get(criteria.getKey()), criteria.getValue().toString())
                );
            }else if(criteria.getOperation().equals(SearchOperation.MATCH_START)) {
                predicates.add(criteriaBuilder.like(
                        root.<String>get(criteria.getKey()), "%" + criteria.getValue().toString().toLowerCase())
                );
            }else if(criteria.getOperation().equals(SearchOperation.MATCH_END)) {
                predicates.add(criteriaBuilder.like(
                        root.<String>get(criteria.getKey()), criteria.getValue().toString().toLowerCase() + "%")
                );
            }else if(criteria.getOperation().equals(SearchOperation.MATCH)) {
                predicates.add(criteriaBuilder.like(
                        root.<String>get(criteria.getKey()), "%" + criteria.getValue().toString().toLowerCase() + "%")
                );
            }else if(criteria.getOperation().equals(SearchOperation.IN)) {
                predicates.add(criteriaBuilder.in(
                        root.get(criteria.getKey())).value(criteria.getValue())
                );
            }else if(criteria.getOperation().equals(SearchOperation.NOT_IN)) {
                predicates.add(criteriaBuilder.not(
                        root.<Boolean>get(criteria.getKey())).in(criteria.getValue())
                );
            }else if(criteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {
                predicates.add(criteriaBuilder.notEqual(
                        root.get(criteria.getKey()), criteria.getValue().toString())
                );
            }
        }

        //query and or defines here ->
        return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
    }
}
