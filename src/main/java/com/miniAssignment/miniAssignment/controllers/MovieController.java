package com.miniAssignment.miniAssignment.controllers;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import com.amazonaws.services.dynamodbv2.document.*;

import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.miniAssignment.miniAssignment.models.Movie;
import com.miniAssignment.miniAssignment.services.ServicesMovie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/movieData")


public class MovieController {


    @Autowired
    private ServicesMovie movieservice;
    @PostMapping("/importCsv")
    public List<Movie> importCsv(){
        return movieservice.readCsv();
    }

    //problem statement-1
    @GetMapping("/director/{director}/year-range/{startYear}/{endYear}")
    public List<String> getMoviesByDirectorAndYearRange(@PathVariable String director,
                                                        @PathVariable int startYear,
                                                        @PathVariable int endYear) {
        return movieservice.getdirector(director, startYear, endYear);
    }

    //problem statement-2
    @GetMapping("/englishTitles")
    public List<String> getEnglishMoviesWithUserReviewsGreaterThan(
            @RequestParam int userReviewFilter){
        return  movieservice.getEnglishTitlesWithUserReviewsGreaterThan(userReviewFilter);
    }

    //problem statement-3
    @GetMapping("/highestBudget")
    public List<String> getHighestBudgetMoviesForYearAndCountry(
            @RequestParam String year, @RequestParam String country) {
        return movieservice.getHighestBudgetTitlesForYearAndCountry(year,country);
    }

    //problem statement-5
    private static final String TOKEN = "HgrtnjaFvkls";

    @GetMapping("/api/auth")
    public ResponseEntity<String> myEndpoint(@RequestHeader("Authorization") String token) {
        // Validating the token
        if (token.equals(TOKEN)) {

            return ResponseEntity.ok("Access has been granted!");
        } else {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }


}


