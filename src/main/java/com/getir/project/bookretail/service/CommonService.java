package com.getir.project.bookretail.service;

import com.getir.project.bookretail.entity.Customer;
import com.getir.project.bookretail.entity.DatabaseSequence;
import com.getir.project.bookretail.repository.CustomerRepository;
import com.getir.project.bookretail.util.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
public class CommonService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MongoOperations mongoOperations;

    // Return Throw atan method
    public Customer getUserByEmail(String userEmail) {

        if (ServiceUtils.isNullOrEmpty(userEmail)) {
            throw new NullPointerException();
        }

        Optional<Customer> userEntity = customerRepository.findByEmail(userEmail);

        if (userEntity.isPresent()) {
            return userEntity.get();
        } else {
            throw new UsernameNotFoundException("USER_NOT_FOUND");
        }
    }

    // Return Null Dönen Method
    public Customer getUserByEmailWithoutThrow(String userEmail) {

        if (ServiceUtils.isNullOrEmpty(userEmail)) {
            throw new NullPointerException();
        }

        Optional<Customer> userEntity = customerRepository.findByEmail(userEmail);
        return userEntity.orElse(null);
    }

    public long generateSequence(String seqName) {

        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq", 1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;

    }

}
