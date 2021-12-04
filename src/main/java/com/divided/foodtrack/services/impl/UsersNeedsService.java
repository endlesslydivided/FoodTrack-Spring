package com.divided.foodtrack.services.impl;

import com.divided.foodtrack.DTO.DayReportDTO;
import com.divided.foodtrack.models.Reports;
import com.divided.foodtrack.models.UsersData;
import com.divided.foodtrack.models.UsersParams;
import com.divided.foodtrack.repositories.ProductsRepository;
import com.divided.foodtrack.repositories.ReportsRepository;
import com.divided.foodtrack.repositories.UsersDataRepository;
import com.divided.foodtrack.repositories.UsersParamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class UsersNeedsService {

    private UsersParamsRepository usersParamsRepository;
    private UsersDataRepository usersDataRepository;
    private ReportsRepository reportsRepository;


    @Autowired
    public UsersNeedsService(UsersParamsRepository usersParamsRepository, UsersDataRepository usersDataRepository, ReportsRepository reportsRepository) {
        this.usersParamsRepository = usersParamsRepository;
        this.usersDataRepository = usersDataRepository;
        this.reportsRepository = reportsRepository;
    }
    public List<Reports> getUsersReports(int id, String date)
    {
        return reportsRepository.findByDateUser(date,id);
    }

    public List<UsersParams> getUsersParams(int id)
    {
        return usersParamsRepository.findByUser(id);
    }

    public Optional<UsersData> getUsersData(int id)
    {
        return usersDataRepository.findByUser(id);
    }

    public DayReportDTO getUsersNeeds(UsersParams usersParams, UsersData usersData,List<Reports> reports)
    {

        Period years = Period.between(LocalDate.now(),usersData.getBirthday().toLocalDate());

        BigDecimal sumCalories = new BigDecimal(0);
        BigDecimal sumProteins = new BigDecimal(0);
        BigDecimal sumFats = new BigDecimal(0);
        BigDecimal sumCarbs = new BigDecimal(0);

        BigDecimal caloriesNeeded = new BigDecimal(1);
        BigDecimal proteinsNeeded = new BigDecimal(1);
        BigDecimal fatsNeeded = new BigDecimal(1);
        BigDecimal carbNeeded = new BigDecimal(1);

        BigDecimal usersWeight = usersParams.getUserWeight();
        BigDecimal usersHeight = BigDecimal.valueOf(usersParams.getUserHeight());
        BigDecimal usersAge =  BigDecimal.valueOf(years.getYears());

        for (Reports item: reports)
        {
            sumCalories = sumCalories.add(item.getDayCalories());
            sumCarbs = sumCarbs.add(item.getDayCarbohydrates());
            sumFats = sumFats.add(item.getDayFats());
            sumProteins = sumProteins.add(item.getDayProteins());
        }

        caloriesNeeded = usersWeight.multiply(BigDecimal.valueOf(10)).
                add(usersHeight.multiply(BigDecimal.valueOf(6.25))).subtract
                        (usersAge.multiply(BigDecimal.valueOf(5))).multiply(BigDecimal.valueOf(1.375)).setScale(1,RoundingMode.DOWN);

        proteinsNeeded = caloriesNeeded.multiply(BigDecimal.valueOf(0.3)).divide(BigDecimal.valueOf(4), RoundingMode.DOWN).setScale(1,RoundingMode.DOWN);
        carbNeeded = caloriesNeeded.multiply(BigDecimal.valueOf(0.3)).divide(BigDecimal.valueOf(9), RoundingMode.DOWN).setScale(1,RoundingMode.DOWN);
        fatsNeeded = caloriesNeeded.multiply(BigDecimal.valueOf(0.4)).divide(BigDecimal.valueOf(4), RoundingMode.DOWN).setScale(1,RoundingMode.DOWN);

        DayReportDTO dayReportDTO = new DayReportDTO();

        dayReportDTO.setCalsEaten(sumCalories.setScale(1,RoundingMode.DOWN));
        dayReportDTO.setCarbohydratesEaten(sumCarbs.setScale(1,RoundingMode.DOWN));
        dayReportDTO.setFatsEaten(sumFats.setScale(1,RoundingMode.DOWN));
        dayReportDTO.setProteinsEaten(sumProteins.setScale(1,RoundingMode.DOWN));

        dayReportDTO.setCalsNeeded(caloriesNeeded);
        dayReportDTO.setCarbohydratesNeeded(proteinsNeeded);
        dayReportDTO.setFatsNeeded(fatsNeeded);
        dayReportDTO.setProteinsNeeded(carbNeeded);
        return dayReportDTO;
    }
}
