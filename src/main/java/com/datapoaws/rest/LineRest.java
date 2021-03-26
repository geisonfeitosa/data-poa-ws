package com.datapoaws.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datapoaws.dao.LineDao;
import com.datapoaws.model.Line;

@RestController
@RequestMapping("/rest/line")
public class LineRest {

	@Autowired
	private LineDao lineDao;
	
	
	@PostMapping()
	public void save(@RequestBody Line line) {
		lineDao.save(line);
	}
	
	@GetMapping("/{nome}")
	public List<Line> getByNome(@PathVariable("nome") String nome) {
		return lineDao.getByNome(nome);
	}
	
}
