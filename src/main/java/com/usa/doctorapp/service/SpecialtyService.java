package com.usa.doctorapp.service;

import com.usa.doctorapp.model.Specialty;
import com.usa.doctorapp.repository.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialtyService {
    @Autowired
    private SpecialtyRepository specialtyRepository;

    public List<Specialty> getALL(){
        return specialtyRepository.getAll();
    }
    public Optional<Specialty> getById(Integer id){
        return specialtyRepository.getById(id);
    }
    public Specialty save(Specialty specialty){
        if (specialty.getId()==null) {
            return specialtyRepository.save(specialty);
        }else{
            Optional<Specialty> optional= specialtyRepository.getById(specialty.getId());
            if (optional.isEmpty()){
                return specialtyRepository.save(specialty);
            }else{
                return specialty;
            }
        }
    }
    public Specialty update (Specialty specialty){
        if (specialty.getId()!=null){
            Optional<Specialty> optional= specialtyRepository.getById(specialty.getId());
            if (!optional.isEmpty()){
                if (specialty.getName()!=null){
                    optional.get().setName(specialty.getName());
                }
                if (specialty.getDescription()!=null){
                    optional.get().setDescription(specialty.getDescription());
                }
                specialtyRepository.save(optional.get());
                return  optional.get();
            }else {
                return specialty;
            }
        }else {
            return specialty;
        }
    }

    public boolean delete(Integer id){
        Boolean aBoolean=getById(id).map(specialty ->{
            specialtyRepository.delete(specialty);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
