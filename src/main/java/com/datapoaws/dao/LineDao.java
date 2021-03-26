package com.datapoaws.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.datapoaws.model.Line;

@Repository
public interface LineDao extends JpaRepository<Line, String> {
	
	@Query("select l from Line l where l.id = :id")
	Line getById(String id);
	
	@Query("select l from Line l where lower(l.nome) like %:nome% order by l.nome")
	List<Line> getByNome(@Param("nome")String nome);

}
