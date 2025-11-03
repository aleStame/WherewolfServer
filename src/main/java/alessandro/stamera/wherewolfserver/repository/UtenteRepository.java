package alessandro.stamera.wherewolfserver.repository;

import alessandro.stamera.wherewolfserver.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente, String> { }