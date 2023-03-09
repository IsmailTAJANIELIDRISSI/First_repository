package ma.veviosys.recordsmanager.repositry;

import ma.veviosys.recordsmanager.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);
}
