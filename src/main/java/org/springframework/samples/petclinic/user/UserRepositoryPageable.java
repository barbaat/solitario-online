package org.springframework.samples.petclinic.user;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepositoryPageable extends PagingAndSortingRepository<User, Long> {

  Page<User> findAll(Pageable pageable);

}
