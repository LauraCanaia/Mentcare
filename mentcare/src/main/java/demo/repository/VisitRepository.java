package demo.repository;
import demo.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
    Visit findById(long id);
}
