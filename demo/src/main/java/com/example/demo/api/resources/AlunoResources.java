package com.example.demo.api.resources;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.api.dao.AlunoDao;
import com.example.demo.api.models.Aluno;
import com.example.demo.api.responses.AlunoResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * AlunoResources
 */
@RestController
public class AlunoResources {

    @GetMapping("/aluno")
    public List<AlunoResponse> get() {

        List<AlunoResponse> response = new ArrayList<>();

        for (Aluno aluno : AlunoDao.read()) {
            response.add(new AlunoResponse(aluno));
        }

        return response;

    }

    @GetMapping("/aluno/{id}")
    public AlunoResponse get(@PathVariable(value = "id") int id) {
        Aluno aluno = AlunoDao.read(id);

        if (aluno == null) {
            return null;
        } else {
            return new AlunoResponse(AlunoDao.read(id));
        }
        
    }

    @PostMapping("/aluno")
    public Aluno post(@RequestBody Aluno aluno) {
        return AlunoDao.create(aluno);
    }

    @PutMapping("/aluno")
    public Aluno put(@RequestBody Aluno aluno) {
        return AlunoDao.update(aluno);
    }

    @DeleteMapping("/aluno")
    public Aluno delete(@RequestBody Aluno aluno) {
        return AlunoDao.delete(aluno);
    }

}