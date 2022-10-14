package com.usa.doctorapp.service;


import com.usa.doctorapp.model.Doctor;
import com.usa.doctorapp.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAll(){
        return doctorRepository.getAll();
    }

    public Optional<Doctor> getById(Integer id){
        return doctorRepository.getById(id);
    }
    public Doctor save (Doctor doctor){
        if (doctor.getId()==null) {
            return doctorRepository.save(doctor);
        }else{
            Optional<Doctor> optional=doctorRepository.getById(doctor.getId());
            if (optional.isEmpty()){
                return doctorRepository.save(doctor);
            }else{
                return doctor;
            }
        }
    }
    public Doctor update (Doctor doctor){
        if (doctor.getId()!=null){
            Optional<Doctor> optional=doctorRepository.getById(doctor.getId());
            if(!optional.isEmpty()){
                if(doctor.getName()!=null){
                    optional.get().setName(doctor.getName());
                }
                if (doctor.getDepartment()!=null){
                    optional.get().setDepartment(doctor.getDepartment());
                }
                if(doctor.getYear()!=null){
                    optional.get().setYear(doctor.getYear());
                }
                if(doctor.getDescription()!=null){
                    optional.get().setDescription(doctor.getDescription());
                }
                doctorRepository.save(optional.get());
                return  optional.get();
            }else{
                return  doctor;
            }
        }else{
            return doctor;
        }
    }
    public boolean delete(Integer id){
        Boolean aBoolean=getById(id).map(doctor -> {
            doctorRepository.delete(doctor);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
