package io.pivotal.repository;

import io.pivotal.domain.Directory;
import org.springframework.data.repository.CrudRepository;

public interface DirectoryRepository extends CrudRepository<Directory, Long> {
}
