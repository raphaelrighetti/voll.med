package med.voll.api.domain.consulta.dia;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DiaRepository extends JpaRepository<Dia, Long> {

	Dia findByData(LocalDate data);
	
	@Query("""
			select d from Dia d
			where d.data >= :hoje
			""")
	List<Dia> diasNoPresenteOuFuturo(LocalDate hoje);
}
