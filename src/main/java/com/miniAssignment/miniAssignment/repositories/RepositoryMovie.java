package com.miniAssignment.miniAssignment.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.miniAssignment.miniAssignment.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoryMovie {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;
    public Movie save(Movie movie) {
        dynamoDBMapper.save(movie);
        return movie;
    }
}
