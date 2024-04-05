package com.example.market_store.repositorie;

import com.example.market_store.entitie.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UsersRepo extends JpaRepository<Users,Long> {

}
