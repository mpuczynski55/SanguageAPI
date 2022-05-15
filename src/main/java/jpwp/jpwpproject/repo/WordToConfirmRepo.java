package jpwp.jpwpproject.repo;

import jpwp.jpwpproject.model.WordToConfirm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordToConfirmRepo extends CrudRepository<WordToConfirm, Long> {
}
