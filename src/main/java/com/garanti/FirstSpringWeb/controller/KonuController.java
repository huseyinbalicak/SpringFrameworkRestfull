package com.garanti.FirstSpringWeb.controller;

import com.garanti.FirstSpringWeb.model.Konu;
import com.garanti.FirstSpringWeb.repo.KonuRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "konu")
public class KonuController {

    private KonuRepo repo;

    public KonuController()
    {
        this.repo = new KonuRepo();
    }

    @GetMapping(path = "getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Konu>> getAll()
    {
        // localhost:9090/FirstRestfulService/konu/getAll
        ArrayList<Konu> res = repo.getAll();
        if (res == null || res.size() == 0)
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else
        {
            return ResponseEntity.ok(res);
        }
    }

    @GetMapping(path = "getByIdHeader", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Konu> getByIdHeader(@RequestHeader(name = "id") Integer id)
    {
        // localhost:9090/FirstRestfulService/konu/getById?id=1
        Konu res = repo.getById(id);
        if (res != null)
        {
            return ResponseEntity.ok(res);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping(path = "getById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Konu> getByIdQueryParam(@RequestParam(value = "id", required = true) Integer id)
    {
        // localhost:9090/FirstRestfulService/konu/getById?id=1
        Konu res = repo.getById(id);
        if (res != null)
        {
            return ResponseEntity.ok(res);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping(path = "getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Konu> getByIdPathParam(@PathVariable(value = "id") Integer id)
    {
        // localhost:9090/FirstRestfulService/konu/getById/1

        Konu res = repo.getById(id);
        if (res != null)
        {
            return ResponseEntity.ok(res);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PostMapping(path = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@RequestBody Konu konu)
    {
        // localhost:9090/FirstRestfulService/konu/save
        // {"name":"RestTest", "is_GICIK": true}
        if (repo.save(konu))
        {
            return ResponseEntity.status(HttpStatus.CREATED).body("Başarı ile kaydedildi");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile kaydedildi");
        }
    }

    @DeleteMapping(path = "deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(value = "id") Integer id)
    {
        // localhost:9090/FirstRestfulService/konu/deleteById/1
        if (repo.deleteById(id))
        {
            return ResponseEntity.ok("Başarı ile silindi");
        }
        else
        {
            return ResponseEntity.internalServerError().body("Başarı ile silinemedi");
        }
    }

    @DeleteMapping(path = "deleteByIdHeader")
    public ResponseEntity<String> deleteByIdHeader(@RequestHeader(value = "id") Integer id)
    {
        // localhost:9090/FirstRestfulService/konu/deleteById/1
        if (repo.deleteById(id))
        {
            return ResponseEntity.ok("Başarı ile silindi");
        }
        else
        {
            return ResponseEntity.internalServerError().body("Başarı ile silinemedi");
        }
    }
}
