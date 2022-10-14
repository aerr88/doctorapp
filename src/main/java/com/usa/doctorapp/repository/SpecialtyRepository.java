package com.usa.doctorapp.repository;


import com.usa.doctorapp.model.Specialty;
import com.usa.doctorapp.repository.crudrepository.SpecialtyCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SpecialtyRepository {
    @Autowired
    private SpecialtyCrudRepository specialtyCrudRepository;

    public List<Specialty> getAll(){
        return (List<Specialty>) specialtyCrudRepository.findAll();
    }
    public Optional<Specialty> getById(Integer id){
        return  specialtyCrudRepository.findById(id);
    }
    public Specialty save(Specialty specialty){
        return specialtyCrudRepository.save(specialty);
    }
    public  void delete (Specialty specialty){
        specialtyCrudRepository.delete(specialty);
    }

}
