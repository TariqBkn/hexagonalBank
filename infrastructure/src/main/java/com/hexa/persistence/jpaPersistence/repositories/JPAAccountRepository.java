package com.hexa.persistence.jpaPersistence.repositories;

import com.hexa.persistence.jpaPersistence.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAAccountRepository extends JpaRepository<AccountEntity, Integer> {
}
