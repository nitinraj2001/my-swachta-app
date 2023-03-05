package com.hacknitr.wastemanagement.service.impl;

import com.hacknitr.wastemanagement.model.NGO;
import com.hacknitr.wastemanagement.repository.NgoRepository;
import com.hacknitr.wastemanagement.sevice.NGOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NGOServiceImpl implements NGOService {

    @Autowired
    private NgoRepository ngoRepository;

    @Override
    public NGO registerNGO(NGO ngo) {
        return this.ngoRepository.save(ngo);
    }

    @Override
    public List<NGO> getAllNGOs() {
        return this.ngoRepository.findAll();
    }

    @Override
    public void deleteNGO(Long id) {
        this.ngoRepository.deleteById(id);
    }

    @Override
    public NGO getNGODetails(Long id) {
        return this.ngoRepository.findById(id).get();
    }
}
