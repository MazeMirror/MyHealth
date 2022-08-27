package com.myhealth.Repositories;

import com.myhealth.Entities.Steps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StepsRepository extends JpaRepository<Steps,Long> {
}
