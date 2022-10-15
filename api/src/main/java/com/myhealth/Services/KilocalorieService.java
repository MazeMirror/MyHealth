package com.myhealth.Services;

import com.myhealth.Common.EntityDtoConverter;
import com.myhealth.Dto.Requests.DistanceDtoRequest;
import com.myhealth.Dto.Requests.KilocalorieDtoRequest;
import com.myhealth.Dto.Responses.DistanceDtoResponse;
import com.myhealth.Dto.Responses.KilocalorieDtoResponse;
import com.myhealth.Entities.Distance;
import com.myhealth.Entities.Kilocalorie;
import com.myhealth.Repositories.DistanceRepository;
import com.myhealth.Repositories.KilocalorieRepository;
import com.myhealth.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class KilocalorieService {

    @Autowired
    private KilocalorieRepository kilocalorieRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    EntityDtoConverter entityDtoConverter;

    public List<KilocalorieDtoResponse> findAll() throws Exception {
        var kilocalorieList = kilocalorieRepository.findAll();
        return entityDtoConverter.convertKilocaloriesToDto(kilocalorieList);
    }

    public KilocalorieDtoResponse findById(Long id) throws Exception {
        Kilocalorie kilocalorie = kilocalorieRepository.findById(id).orElseThrow(() -> new RuntimeException("kilocalorie not found"));

        return entityDtoConverter.convertKilocalorieToDto(kilocalorie);
    }

    public KilocalorieDtoResponse findByIdAndPatientIdWithDateEqualsTo(Long id, long patientId, Date date) throws Exception {
        Kilocalorie kilocalorie = kilocalorieRepository.findByIdAndPatientIdAndDateEquals(id,patientId,date).orElseThrow(() -> new RuntimeException("kilocalorie not found"));;

        return entityDtoConverter.convertKilocalorieToDto(kilocalorie);
    }

    public List<KilocalorieDtoResponse> getKilocaloriesByPatientId(Long patientId) {
        List<Kilocalorie> kilocalories = kilocalorieRepository.getKilocaloriesByPatientIdOrderByQuantityAsc(patientId);
        return entityDtoConverter.convertKilocaloriesToDto(kilocalories);
    }

    public List<KilocalorieDtoResponse> getKilocaloriesByPatientIdAndFilteredByDates(Long patientId, Date startDate, Date endDate) {
        List<Kilocalorie> tempKilocalories = kilocalorieRepository.getKilocaloriesByPatientIdOrderByQuantityAsc(patientId);

        var filteredKilocalorie = tempKilocalories.stream().filter(
                kilocalorie -> ( (kilocalorie.getDate().after(startDate) || (kilocalorie.getDate().equals(startDate))) &&
                        (kilocalorie.getDate().before(endDate ) || kilocalorie.getDate().equals(endDate)) )
        ).toList();

        return entityDtoConverter.convertKilocaloriesToDto(filteredKilocalorie);
    }




    public KilocalorieDtoResponse createKilocalorie(Long patientId, KilocalorieDtoRequest kilocalorieDtoRequest) {
        var patient = patientRepository.findById(patientId).orElseThrow(() -> new RuntimeException("patient not found by Id "+patientId));

        var kilocalorieEntity = new Kilocalorie();
        kilocalorieEntity.setDate(kilocalorieDtoRequest.getDate());
        kilocalorieEntity.setQuantity(kilocalorieDtoRequest.getQuantity());
        kilocalorieEntity.setPatient(patient);

        var response = kilocalorieRepository.save(kilocalorieEntity);

        //var response = dailyGoalRepository.save(new DailyGoal(patient,activity,dailyGoalDtoRequest));
        return entityDtoConverter.convertKilocalorieToDto(response);
    }

    public KilocalorieDtoResponse update(Long id, KilocalorieDtoRequest kilocalorieDtoRequest) throws Exception {
        Kilocalorie kilocalorie = kilocalorieRepository.findById(id).orElseThrow(() -> new RuntimeException("kilocalorie not found"));

        kilocalorie.setQuantity(kilocalorieDtoRequest.getQuantity());
        kilocalorie.setDate(kilocalorieDtoRequest.getDate());

        var response = kilocalorieRepository.save(kilocalorie);

        return entityDtoConverter.convertKilocalorieToDto(response);
    }

    public KilocalorieDtoResponse updateByIdAndPatientId(Long id, Long patientId, KilocalorieDtoRequest kilocalorieDtoRequest) {
        var patient = patientRepository.findById(patientId).orElseThrow(() -> new RuntimeException("kilocalorie not found by Id"));

        Kilocalorie kilocalorie = kilocalorieRepository.findById(id).orElseThrow(() -> new RuntimeException("kilocalorie not found by Id"));

        kilocalorie.setQuantity(kilocalorieDtoRequest.getQuantity());
        kilocalorie.setDate(kilocalorieDtoRequest.getDate());

        var response = kilocalorieRepository.save(kilocalorie);

        return entityDtoConverter.convertKilocalorieToDto(response);
    }

    public void deleteById(Long id) throws Exception {
        Kilocalorie kilocalorie = kilocalorieRepository.findById(id).orElseThrow(() -> new RuntimeException("kilocalorie not found by Id"));

        kilocalorieRepository.deleteById(id);
    }
}
