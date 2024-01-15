package demo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface VisitRepository extends CrudRepository<Visit, Long> {
    Visit findById(long id);
}
