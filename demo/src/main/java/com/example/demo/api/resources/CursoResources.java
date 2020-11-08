package com.example.demo.api.resources;

import java.util.List;

import com.example.demo.api.dao.CursoDao;
import com.example.demo.api.models.Curso;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * CursoResources
 */
@RestController
public class CursoResources {

    @GetMapping("/curso")
    public List<Curso> get() {
        return CursoDao.read();
    }

    @GetMapping("/curso/{id}")
    public Curso get(@PathVariable(value = "id") int id) {
        System.out.println(CursoDao.read(id));
        return CursoDao.read(id);
    }

    @PostMapping("/curso")
    public Curso post(@RequestBody Curso curso) {
        return CursoDao.create(curso);
    }

    @PutMapping("/curso")
    public Curso put(@RequestBody Curso curso) {
        return CursoDao.update(curso);
    }

    @DeleteMapping("/curso")
    public Curso delete(@RequestBody Curso curso) {
        return CursoDao.delete(curso);
    }

}