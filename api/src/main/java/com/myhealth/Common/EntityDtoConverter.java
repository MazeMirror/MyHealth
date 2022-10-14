package com.myhealth.Common;

import java.util.List;
import java.util.stream.Collectors;

import com.myhealth.Dto.Responses.*;
import com.myhealth.Entities.*;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EntityDtoConverter {
	private final ModelMapper modelMapper;

	public EntityDtoConverter(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public RoleDtoResponse convertRoleToDto(Role role) {
		return modelMapper.map(role, RoleDtoResponse.class);
	}

	public List<RoleDtoResponse> convertRolesToDto(List<Role> roles) {
		return roles.stream().map(this::convertRoleToDto).collect(Collectors.toList());
	}

	public UserDtoResponse convertUserToDto(User user) {
		return modelMapper.map(user, UserDtoResponse.class);
	}

	public List<UserDtoResponse> convertUsersToDto(List<User> users) {
		return users.stream().map(this::convertUserToDto).collect(Collectors.toList());
	}

	public ProfileDtoResponse convertProfileToDto(Profile profile) {
		return modelMapper.map(profile, ProfileDtoResponse.class);
	}

	public List<ProfileDtoResponse> convertProfilesToDto(List<Profile> profiles) {
		return profiles.stream().map(this::convertProfileToDto).collect(Collectors.toList());
	}

	public SpecialistDtoResponse convertSpecialistToDto(Specialist specialist) {
		return modelMapper.map(specialist, SpecialistDtoResponse.class);
	}

	public PatientDtoResponse convertPatientToDto(Patient patient) {
		return modelMapper.map(patient, PatientDtoResponse.class);
	}

	public List<SpecialistDtoResponse> convertSpecialistsToDto(List<Specialist> specialists) {
		return specialists.stream().map(this::convertSpecialistToDto).collect(Collectors.toList());
	}

	public List<DailyGoalDtoResponse> convertDailyGoalsToDto(List<DailyGoal> dailyGoals) {
		return dailyGoals.stream().map(this::convertDailyGoalToDto).collect(Collectors.toList());
	}

	public DailyGoalDtoResponse convertDailyGoalToDto(DailyGoal dailyGoal){
		return modelMapper.map(dailyGoal,DailyGoalDtoResponse.class);
	}

	public List<WeeklyGoalDtoResponse> convertWeeklyGoalsToDto(List<WeeklyGoal> weeklyGoals) {
		return weeklyGoals.stream().map(this::convertWeeklyGoalToDto).collect(Collectors.toList());
	}

	public WeeklyGoalDtoResponse convertWeeklyGoalToDto(WeeklyGoal weeklyGoal){
		return modelMapper.map(weeklyGoal,WeeklyGoalDtoResponse.class);
	}

	public List<MealPlanDtoResponse> convertMealPlanToDto(List<MealPlan> mealPlans) {
		return mealPlans.stream().map(this::convertMealPlanToDto).collect(Collectors.toList());
	}

	public MealPlanDtoResponse convertMealPlanToDto(MealPlan mealPlan){
		return modelMapper.map(mealPlan,MealPlanDtoResponse.class);
	}

	//Distancias
	public DistanceDtoResponse convertDistanceToDto(Distance distance){
		return modelMapper.map(distance,DistanceDtoResponse.class);
	}

	public List<DistanceDtoResponse> convertDistancesToDto(List<Distance> distances){
		return distances.stream().map(this::convertDistanceToDto).collect(Collectors.toList());
	}

	//Pasos
	public StepDtoResponse convertStepToDto(Step step){
		return modelMapper.map(step,StepDtoResponse.class);
	}

	public List<StepDtoResponse> convertStepsToDto(List<Step> steps){
		return steps.stream().map(this::convertStepToDto).collect(Collectors.toList());
	}

	//Kilocalorias
	public KilocalorieDtoResponse convertKilocalorieToDto(Kilocalorie kilocalorie){
		return modelMapper.map(kilocalorie,KilocalorieDtoResponse.class);
	}

	public List<KilocalorieDtoResponse> convertKilocaloriesToDto(List<Kilocalorie> kilocalories){
		return kilocalories.stream().map(this::convertKilocalorieToDto).collect(Collectors.toList());
	}
}
