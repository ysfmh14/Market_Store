package com.example.market_store.repositories;

import com.example.market_store.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UsersRepo extends JpaRepository<Users,Long> {
}
