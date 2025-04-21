package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.UsersType;

@Repository
public interface UsersTypeRepository extends JpaRepository<UsersType, Integer> {

}
