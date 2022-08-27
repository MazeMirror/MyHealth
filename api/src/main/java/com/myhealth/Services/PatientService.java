package com.myhealth.Services;

import com.myhealth.Entities.DailyGoal;
import com.myhealth.Entities.Patient;
import com.myhealth.Repositories.PatientRepository;
import com.myhealth.Repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public List<Patient> findAll() throws Exception{
        return patientRepository.findAll();
    }

    public Patient createPatient(Patient patient,Long profileId) {

        return profileRepository.findById(profileId).map(
                profile -> {patient.setProfile(profile);
                    return patientRepository.save(patient);}
        ).orElseThrow(()->new RuntimeException("profile not found"));
    }





}
