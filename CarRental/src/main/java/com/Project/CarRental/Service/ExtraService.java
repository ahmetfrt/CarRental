package com.Project.CarRental.Service;

import com.Project.CarRental.Domain.Extra;
import com.Project.CarRental.Repository.ExtraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExtraService {

    @Autowired
    private ExtraRepository extraRepository;

    public Extra createExtra(Extra extra) {
        return extraRepository.save(extra);
    }

    public Optional<Extra> getExtraById(Long id) {
        return extraRepository.findById(id);
    }

    public List<Extra> getAllExtras() {
        return extraRepository.findAll();
    }

    public void deleteExtra(Long id) {
        extraRepository.deleteById(id);
    }
}