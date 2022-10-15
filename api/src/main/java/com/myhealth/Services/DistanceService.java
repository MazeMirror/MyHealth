package com.myhealth.Services;

import com.myhealth.Common.EntityDtoConverter;
import com.myhealth.Dto.Requests.DistanceDtoRequest;
import com.myhealth.Dto.Requests.StepDtoRequest;
import com.myhealth.Dto.Responses.DistanceDtoResponse;
import com.myhealth.Dto.Responses.StepDtoResponse;
import com.myhealth.Entities.Distance;
import com.myhealth.Entities.Step;
import com.myhealth.Repositories.DistanceRepository;
import com.myhealth.Repositories.PatientRepository;
import com.myhealth.Repositories.StepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DistanceService {

    @Autowired
    private DistanceRepository distanceRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    EntityDtoConverter entityDtoConverter;

    public List<DistanceDtoResponse> findAll() throws Exception {
        var distanceList = distanceRepository.findAll();
        return entityDtoConverter.convertDistancesToDto(distanceList);
    }

    public DistanceDtoResponse findById(Long id) throws Exception {
        Distance distance = distanceRepository.findById(id).orElseThrow(() -> new RuntimeException("distance not found"));

        return entityDtoConverter.convertDistanceToDto(distance);
    }

    public DistanceDtoResponse findByIdAndPatientIdWithDateEqualsTo(Long id, long patientId, Date date) throws Exception {
        Distance distance = distanceRepository.findByIdAndPatientIdAndDateEquals(id,patientId,date).orElseThrow(() -> new RuntimeException("distance not found"));;

        return entityDtoConverter.convertDistanceToDto(distance);
    }

    public List<DistanceDtoResponse> getDistancesByPatientId(Long patientId) {
        List<Distance> distances = distanceRepository.getDistancesByPatientIdOrderByQuantityAsc(patientId);
        return entityDtoConverter.convertDistancesToDto(distances);
    }

    public List<DistanceDtoResponse> getDistancesByPatientIdAndFilteredByDates(Long patientId, Date startDate, Date endDate) {
        List<Distance> tempDistances = distanceRepository.getDistancesByPatientIdOrderByQuantityAsc(patientId);

        var filteredDistance = tempDistances.stream().filter(
                distance -> ( (distance.getDate().after(startDate) || (distance.getDate().equals(startDate))) &&
                        (distance.getDate().before(endDate ) || distance.getDate().equals(endDate)) )
        ).toList();

        return entityDtoConverter.convertDistancesToDto(filteredDistance);
    }




    public DistanceDtoResponse createDistance(Long patientId, DistanceDtoRequest distanceDtoRequest) {
        var patient = patientRepository.findById(patientId).orElseThrow(() -> new RuntimeException("patient not found by Id "+patientId));

        var distanceEntity = new Distance();
        distanceEntity.setDate(distanceDtoRequest.getDate());
        distanceEntity.setQuantity(distanceDtoRequest.getQuantity());
        distanceEntity.setPatient(patient);

        var response = distanceRepository.save(distanceEntity);

        //var response = dailyGoalRepository.save(new DailyGoal(patient,activity,dailyGoalDtoRequest));
        return entityDtoConverter.convertDistanceToDto(response);
    }

    public DistanceDtoResponse update(Long id, DistanceDtoRequest distanceDtoRequest) throws Exception {
        Distance distance = distanceRepository.findById(id).orElseThrow(() -> new RuntimeException("distance not found"));

        distance.setQuantity(distanceDtoRequest.getQuantity());
        distance.setDate(distanceDtoRequest.getDate());

        var response = distanceRepository.save(distance);

        return entityDtoConverter.convertDistanceToDto(response);
    }

    public DistanceDtoResponse updateByIdAndPatientId(Long id, Long patientId,DistanceDtoRequest distanceDtoRequest)  {
        var patient = patientRepository.findById(patientId).orElseThrow(() -> new RuntimeException("distance not found by Id"));

        Distance distance = distanceRepository.findById(id).orElseThrow(() -> new RuntimeException("distance not found by Id"));

        distance.setQuantity(distanceDtoRequest.getQuantity());
        distance.setDate(distanceDtoRequest.getDate());

        var response = distanceRepository.save(distance);

        return entityDtoConverter.convertDistanceToDto(response);
    }

    public void deleteById(Long id) throws Exception {
        Distance distance = distanceRepository.findById(id).orElseThrow(() -> new RuntimeException("distance not found by Id"));

        distanceRepository.deleteById(id);
    }
}
