package com.piseth.java.school.phoneshopenight.spec;

import com.piseth.java.school.phoneshopenight.entity.Brand;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Data
public class BrandSpec implements Specification<Brand> {
    private final BrandFilter brandFilter;//todo declare as final because it will capture this property(final) to constructor

    //todo list of criteria (for combination many predicate conditions)
    //todo this is dynamic specification
    List<Predicate> predicates = new ArrayList<>();

    @Override
    public Predicate toPredicate(Root<Brand> brand, CriteriaQuery<?> query, CriteriaBuilder cb) {
       //TODO filer by name
        if(brandFilter.getName() != null){
            //todo have to filter like data existing
            //Predicate name = brand.get("name").in(brandFilter.getName());

            //todo filer with a letter which exist in other object will be counted all
            //Predicate name = cb.like(brand.get("name"), "%" + brandFilter.getName() + "%");
            //todo count from all object (insensitive case)
            Predicate name = cb.like(cb.upper(brand.get("name")), "%" + brandFilter.getName().toUpperCase() + "%");
            predicates.add(name);

        }
        //TODO filter by id
        if(brandFilter.getId() != null){
            Predicate id = brand.get("id").in(brandFilter.getId());
            predicates.add(id);
        }

        //Predicate[] pp = predicates.toArray(new Predicate[0]);//convert list to array
        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
