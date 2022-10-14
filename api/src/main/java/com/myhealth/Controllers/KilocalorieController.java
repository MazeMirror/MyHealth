package com.myhealth.Controllers;

import com.myhealth.Services.DailyGoalService;
import com.myhealth.Services.KilocalorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kilocalorie")
public class KilocalorieController {

    @Autowired
    private KilocalorieService kilocalorieService;
}
