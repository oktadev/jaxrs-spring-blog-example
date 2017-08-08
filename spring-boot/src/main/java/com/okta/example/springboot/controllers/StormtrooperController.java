package com.okta.example.springboot.controllers;

import com.okta.example.common.dao.StormtrooperDao;
import com.okta.example.common.model.Stormtrooper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/troopers")
public class StormtrooperController {

    private final StormtrooperDao trooperDao;

    @Autowired
    public StormtrooperController(StormtrooperDao trooperDao) {
        this.trooperDao = trooperDao;
    }

    @GetMapping
    public Collection<Stormtrooper> listTroopers() {
        return trooperDao.listStormtroopers();
    }

    @GetMapping("/{id}")
    public Stormtrooper getTrooper(@PathVariable("id") String id) throws NotFoundException {

        Stormtrooper stormtrooper = trooperDao.getStormtrooper(id);
        if (stormtrooper == null) {
            throw new NotFoundException();
        }
        return stormtrooper;
    }

    @PostMapping
    public Stormtrooper createTrooper(@RequestBody Stormtrooper trooper) {
        return trooperDao.addStormtrooper(trooper);
    }

    @PostMapping("/{id}")
    public Stormtrooper updateTrooper(@PathVariable("id") String id, @RequestBody Stormtrooper updatedTrooper) throws NotFoundException {
        return trooperDao.updateStormtrooper(id, updatedTrooper);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteTrooper(@PathVariable("id") String id) {
        trooperDao.deleteStormtrooper(id);
    }
}