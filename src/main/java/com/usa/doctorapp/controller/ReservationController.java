package com.usa.doctorapp.controller;


import com.usa.doctorapp.model.ClientReport;
import com.usa.doctorapp.model.Doctor;
import com.usa.doctorapp.model.Reservation;
import com.usa.doctorapp.model.ReservationReport;
import com.usa.doctorapp.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Reservation")
@CrossOrigin(origins = "*",methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/all")
    public List<Reservation> getAll(){
        return reservationService.getAll();
    }

    @GetMapping("{idReservation}")
    public Optional<Reservation> getById (@PathVariable("idReservation") Integer id){
        return reservationService.getById(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation save(@RequestBody Reservation reservation){
        return reservationService.save(reservation);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation update (@RequestBody Reservation reservation){
        return reservationService.update(reservation);
    }

    @GetMapping("/report-status")
    public ReservationReport getReservationStatusReport(){
        return  reservationService.getReservationStatusReport();
    }

    @GetMapping("/report-dates/{dateOne}/{dateTwo}")
    public List<Reservation> getReservationReportDates(@PathVariable("dateOne")String dateOne, @PathVariable("dateTwo") String dateTwo){
        return reservationService.getReservationPeriod(dateOne,dateTwo);
    }

    @GetMapping("/report-clients")
    public List<ClientReport> getReportClients(){
        return reservationService.getTopClients();
    }

}
