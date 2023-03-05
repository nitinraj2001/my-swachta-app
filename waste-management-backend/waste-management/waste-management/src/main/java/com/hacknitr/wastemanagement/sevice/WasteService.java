package com.hacknitr.wastemanagement.sevice;

import com.hacknitr.wastemanagement.model.WasteMaterial;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WasteService {

    public WasteMaterial uploadWaste(WasteMaterial waste);

    public void deleteWaste(Long wasteId) throws Exception;

    public List<WasteMaterial> getAllWasteMaterialsByUserId(Long userId);

    public List<WasteMaterial> getAllWastes();

    public WasteMaterial getWasteByUserId(Long id);

    public WasteMaterial getWasteDetails(Long id);


}
