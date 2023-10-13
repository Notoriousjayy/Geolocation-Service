package com.example.GeolocationService;

import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.regions.*;
import software.amazon.awssdk.services.location.*;
import software.amazon.awssdk.services.location.model.*;
import software.amazon.awssdk.services.location.model.BatchEvaluateGeofencesRequest;
import software.amazon.awssdk.services.location.model.BatchEvaluateGeofencesResponse;
import software.amazon.awssdk.services.location.model.BatchGetDevicePositionRequest;
import software.amazon.awssdk.services.location.model.BatchGetDevicePositionResponse;

public class GeolocationService {
    private final AmazonLocation client;

    public GeolocationService() {
        this.client = AmazonLocationClient.builder()
                .region(Region.US_EAST_1) // Specify the desired AWS region
                .credentialsProvider(DefaultCredentialsProvider.builder().build()) // Use AWS credentials
                .build();
    }

    public BatchEvaluateGeofencesResponse evaluateGeofences(String collectionName, String deviceId, double latitude, double longitude) {
        BatchEvaluateGeofencesRequest request = BatchEvaluateGeofencesRequest.builder()
                .collectionName(collectionName)
                .devicePositionUpdate(
                        DevicePositionUpdate.builder()
                                .deviceId(deviceId)
                                .position(Position.builder().latitude(latitude).longitude(longitude).build())
                                .build()
                )
                .build();

        return client.batchEvaluateGeofences(request);
    }

    public BatchGetDevicePositionResponse getDevicePosition(String trackerName, String deviceId) {
        BatchGetDevicePositionRequest request = BatchGetDevicePositionRequest.builder()
                .trackerName(trackerName)
                .deviceIds(deviceId)
                .build();

        return client.batchGetDevicePosition(request);
    }
}
