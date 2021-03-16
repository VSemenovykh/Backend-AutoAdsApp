package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.entity.Auto;
import ru.ncedu.entity.Brand;
import ru.ncedu.entity.Motor;
import ru.ncedu.model.AutoJoin;
import ru.ncedu.service.AutoService;
import ru.ncedu.service.BrandService;
import ru.ncedu.service.MotorService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/api")
public class RESTfullController {

    private final AutoService autoService;

    private final BrandService brandService;

    private final MotorService motorService;

    @GetMapping
    public List<AutoJoin> getAllAuto() {

        List<Auto> autoList = autoService.findAll();
        List<AutoJoin> listAutoJoin = new ArrayList<>();

        String brandName;
        String modelName;
        String year;

        String motorType;
        double volume;

        for (Auto auto : autoList) {
            Brand brand = brandService.findById(auto.getIdBrand());
            Motor motor = motorService.findById(auto.getIdMotor());

            brandName = brand.getNameBrand();
            modelName = brand.getNameModel();
            year = brand.getYear();

            motorType = motor.getMotorType();
            volume = motor.getVolume();

            AutoJoin autoJoin = new AutoJoin(auto.getId(), brandName, modelName, year, auto.getColor()
                                                ,auto.getPrice(), motorType, volume, auto.getDriveType()
                                             ,auto.getTransmissionType(), auto.getBodyStyleType());

            listAutoJoin.add(autoJoin);
        }

        return listAutoJoin;
    }

    @PostMapping(path = {"/add"})
    public AutoJoin createAuto(@RequestBody Auto auto) {

        auto.setDriveType(auto.getDriveType());
        auto.setTransmissionType(auto.getTransmissionType());
        auto.setBodyStyleType(auto.getBodyStyleType());
        Auto newAuto = autoService.save(auto);

        Brand brand = brandService.findById(auto.getIdBrand());
        Motor motor = motorService.findById(auto.getIdMotor());

        AutoJoin autoJoin = new AutoJoin(newAuto.getId(), brand.getNameBrand(), brand.getNameModel(), brand.getYear()
                                        ,newAuto.getColor(), newAuto.getPrice(), motor.getMotorType(), motor.getVolume()
                                        ,newAuto.getDriveType(), newAuto.getTransmissionType(), newAuto.getBodyStyleType());

        return autoJoin;
    }

    @GetMapping(path = {"/{id}"})
    public Auto getAutoById(@PathVariable("id") long autoId) {

        Auto auto = autoService.findById(autoId);
        Brand brand = brandService.findById(auto.getIdBrand());
        Motor motor = motorService.findById(auto.getIdMotor());

        return auto;
    }

    @PutMapping("/{id}")
    public Auto updateAuto(@RequestBody Auto auto, @PathVariable("id") Long autoId) {

        auto.setId(autoId);
        auto.setIdBrand(auto.getIdBrand());
        auto.setIdMotor(auto.getIdMotor());
        auto.setColor(auto.getColor());
        auto.setPrice(auto.getPrice());
        auto.setDriveType(auto.getDriveType());
        auto.setTransmissionType(auto.getTransmissionType());
        auto.setBodyStyleType(auto.getBodyStyleType());
        autoService.update(auto);

        Brand brand = brandService.findById(auto.getIdBrand());
        Motor motor = motorService.findById(auto.getIdMotor());

        return auto;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAuto(@PathVariable("id") Long autoId) {

        Auto auto = autoService.findById(autoId);
        auto.setIdBrand(0L);
        auto.setIdMotor(0L);

        autoService.delete(autoId);

        Brand brand = brandService.findById(auto.getIdBrand());
        Motor motor = motorService.findById(auto.getIdMotor());

        return ResponseEntity.ok().body("Auto with ID : " + autoId + " deleted with success!");
    }
}