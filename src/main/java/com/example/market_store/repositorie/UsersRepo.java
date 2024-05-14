package com.example.market_store.repositorie;

import com.example.market_store.entitie.Seller;
import com.example.market_store.entitie.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<Users,Long>, JpaSpecificationExecutor<Users> {
    Optional<Users> findByUserCode(String userCode);
    void deleteByUserCode(String userCode);

}
