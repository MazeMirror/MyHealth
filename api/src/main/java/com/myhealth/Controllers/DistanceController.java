package com.myhealth.Controllers;

import com.myhealth.Services.DailyGoalService;
import com.myhealth.Services.DistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/distance")
public class DistanceController {

    @Autowired
    private DistanceService distanceService;
}
