package com.restful.adoptions.user.controller.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username", "message", "jwt", "status"})
public record AuthReponse(
        String username,
        String message,
        String jwt,
        boolean status)
{}
