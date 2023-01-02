package com.garanti.FirstSpringWeb.controller;

import com.garanti.FirstSpringWeb.model.Ders_Ogrenci;
import com.garanti.FirstSpringWeb.repo.Ders_OgrenciRepo;
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
@RequestMapping(path = "dersogrenci")
public class DersOgrenciController {

    private Ders_OgrenciRepo repo;

    public DersOgrenciController()
    {
        this.repo = new Ders_OgrenciRepo();
    }

    @GetMapping(path = "getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Ders_Ogrenci>> getAll()
    {
        // localhost:9090/FirstRestfulService/dersogrenci/getAll
        ArrayList<Ders_Ogrenci> res = repo.getAll();
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
    public ResponseEntity<Ders_Ogrenci> getByIdHeader(@RequestHeader(name = "id") Integer id)
    {
        // localhost:9090/FirstRestfulService/dersogrenci/getById?id=1
        Ders_Ogrenci res = repo.getById(id);
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
    public ResponseEntity<Ders_Ogrenci> getByIdQueryParam(@RequestParam(value = "id", required = true) Integer id)
    {
        // localhost:9090/FirstRestfulService/dersogrenci/getById?id=1
        Ders_Ogrenci res = repo.getById(id);
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
    public ResponseEntity<Ders_Ogrenci> getByIdPathParam(@PathVariable(value = "id") Integer id)
    {
        // localhost:9090/FirstRestfulService/dersogrenci/getById/1

        Ders_Ogrenci res = repo.getById(id);
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
    public ResponseEntity<String> save(@RequestBody Ders_Ogrenci dersogrenci)
    {
        // localhost:9090/FirstRestfulService/dersogrenci/save
        // {"name":"RestTest", "is_GICIK": true}
        if (repo.save(dersogrenci))
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
        // localhost:9090/FirstRestfulService/dersogrenci/deleteById/1
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
        // localhost:9090/FirstRestfulService/dersogrenci/deleteById/1
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
