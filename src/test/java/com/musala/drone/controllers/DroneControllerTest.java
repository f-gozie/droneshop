package com.musala.drone.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musala.drone.models.dto.DroneDto;
import com.musala.drone.models.entity.DroneEntity;
import com.musala.drone.models.entity.MedicationEntity;
import com.musala.drone.models.request.LoadRequest;
import com.musala.drone.repository.DroneRepository;
import com.musala.drone.utils.enums.DroneModel;
import com.musala.drone.utils.enums.DroneState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.SuppressSignatureCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ImportAutoConfiguration
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = DroneController.class)
@ComponentScan(basePackages = "com.musala.drone")
@ContextConfiguration(classes = SpringExtension.class)
public class DroneControllerTest {
    DroneDto testDrone;
    LoadRequest testLoadRequest;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    DroneRepository droneRepository;

    MedicationEntity medicationEntity1 = MedicationEntity.builder()
            .id(1L)
            .name("Medication1")
            .weight(50)
            .build();
    MedicationEntity medicationEntity2 = MedicationEntity.builder()
            .id(2L)
            .name("Medication2")
            .weight(50)
            .build();
    MedicationEntity medicationEntity3 = MedicationEntity.builder()
            .id(3L)
            .name("Medication3")
            .weight(50)
            .build();
    DroneEntity droneEntity1 = DroneEntity.builder()
            .id(1L)
            .serialNumber("123456789")
            .model(DroneModel.CRUISERWEIGHT)
            .weightLimit(1000)
            .batteryCapacity(100)
            .state(DroneState.IDLE)
            .medications(Set.of(medicationEntity1.getId(), medicationEntity2.getId()))
            .build();


    @Test
    public void createDrone() throws Exception {
        DroneDto droneDto = new DroneDto();
        droneDto.setId(1L);
        droneDto.setBatteryCapacity(100);
        droneDto.setModel(DroneModel.CRUISERWEIGHT.toString());
        droneDto.setSerialNumber("123456789");
        droneDto.setWeightLimit(100);
        droneDto.setState(DroneState.LOADING.toString());
        String droneDtoJson = objectMapper.writeValueAsString(droneDto);
        mockMvc.perform(post("/drones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(droneDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void getAllDrones() throws Exception{
        List<DroneEntity> records = new ArrayList<>(Arrays.asList(droneEntity1));

        Mockito.when(droneRepository.findAll()).thenReturn(records);

        mockMvc.perform(get("/drones")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));

    }

//    @Test
//    void loadMedication() throws Exception{
//        testLoadRequest = new LoadRequest();
//        DroneEntity testDrone = new DroneEntity();
//        testDrone.setId(2L);
//        testDrone.setBatteryCapacity(100);
//        testDrone.setModel(DroneModel.CRUISERWEIGHT);
//        testDrone.setSerialNumber("123456789");
//        testDrone.setWeightLimit(100);
//        testDrone.setState(DroneState.LOADING);
//
//        Mockito.when(droneRepository.save(testDrone)).thenReturn(testDrone);
//
//        Set<Long> medications = Set.of(medicationEntity3.getId());
//        testLoadRequest.setDroneId(testDrone.getId());
//        testLoadRequest.setMedications(medications);
//        String droneDtoJson = objectMapper.writeValueAsString(testLoadRequest);
//        mockMvc.perform(MockMvcRequestBuilders.post("/drones/load")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(droneDtoJson))
//                .andExpect(status().isOk());
//    }

//    @Test
//    void getMedicationsByDroneId() throws Exception {
//        testDrone = new DroneDto();
//        testDrone.setId(1L);
//        testDrone.setBatteryCapacity(100);
//        testDrone.setModel(DroneModel.CRUISERWEIGHT.toString());
//        testDrone.setSerialNumber("123456789");
//        testDrone.setWeightLimit(100);
//        testDrone.setState(DroneState.LOADING.toString());
//        testDrone.setMedications(Set.of(medicationEntity1.getId(), medicationEntity2.getId()));
//
//        Mockito.when(droneRepository.findById(testDrone.getId())).thenReturn(Optional.of(droneEntity1));
//
//        mockMvc.perform(get("/drones/" + testDrone.getId() + "/medications")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").value(1L));
//    }

    @Test
    void getAvailableMedicationsForLoading() {
    }

    @Test
    void getBatteryByDroneId() {
    }
}