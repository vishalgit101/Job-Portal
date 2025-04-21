package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

	Optional<Users>  findByEmail(String username);

}
