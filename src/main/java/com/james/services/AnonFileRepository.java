package com.james.services;

import com.james.entities.AnonFile;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jamesyburr on 6/27/16.
 */
public interface AnonFileRepository extends CrudRepository<AnonFile, Integer>{
    public AnonFile findFirstByPermfileFalseOrderByIdAsc();
    public int countByPermfile(boolean permFile);
}
