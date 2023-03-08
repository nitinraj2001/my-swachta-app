package com.hacknitr.wastemanagement.sevice;

import com.hacknitr.wastemanagement.model.Industry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IndustryService {

    public Industry registerIndustry(Industry industry);

    public void deleteIndustry(Long id);

    public Industry getIndustryDetails(Long id);

    public List<Industry> getAllIndustries();

}
