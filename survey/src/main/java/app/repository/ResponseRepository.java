package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.ResponseStorage;

public interface ResponseRepository extends JpaRepository<ResponseStorage, Integer>{

	
}
