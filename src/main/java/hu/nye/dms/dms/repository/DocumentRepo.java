package hu.nye.dms.dms.repository;

import hu.nye.dms.dms.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepo extends JpaRepository<Document,Integer> {
}
