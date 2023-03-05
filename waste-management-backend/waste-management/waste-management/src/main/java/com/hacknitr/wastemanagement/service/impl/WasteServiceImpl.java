package com.hacknitr.wastemanagement.service.impl;

import com.hacknitr.wastemanagement.repository.WasteRepository;
import com.hacknitr.wastemanagement.sevice.WasteService;
import com.hacknitr.wastemanagement.model.WasteMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WasteServiceImpl implements WasteService {

    @Autowired
    private WasteRepository wasteRepository;

    @Override
    public WasteMaterial uploadWaste(WasteMaterial waste) {

        return this.wasteRepository.save(waste);
    }

    @Override
    public void deleteWaste(Long wasteId) {

        WasteMaterial wasteMaterial=this.wasteRepository.findById(wasteId).get();

        if(wasteMaterial==null){
            throw new RuntimeException("Waste material not found");
        }
        this.wasteRepository.deleteById(wasteId);

    }

    @Override
    public List<WasteMaterial> getAllWasteMaterialsByUserId(Long userId) {

        return this.wasteRepository.findByUserId(userId);
    }

    @Override
    public List<WasteMaterial> getAllWastes() {
        return this.wasteRepository.findAll();
    }

    @Override
    public WasteMaterial getWasteByUserId(Long id) {
        return this.wasteRepository.findWasteByUserId(id);
    }

    @Override
    public WasteMaterial getWasteDetails(Long id) {
        return wasteRepository.findById(id).get();
    }
}
