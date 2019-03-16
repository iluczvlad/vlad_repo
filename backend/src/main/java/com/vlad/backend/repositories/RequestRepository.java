package com.vlad.backend.repositories;

import com.vlad.backend.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {


}
