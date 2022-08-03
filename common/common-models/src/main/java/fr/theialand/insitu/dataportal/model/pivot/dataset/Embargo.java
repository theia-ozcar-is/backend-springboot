/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.model.pivot.dataset;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

/**
 * Class representing  the embargo set on data of a given dataset
 */
public class Embargo {

    @JsonProperty(required = true)
    @JsonPropertyDescription("duration of the embargo in days from the current date")
    @PositiveOrZero
    private  int duration;

    @JsonProperty(required = true)
    @JsonPropertyDescription("Ordered list of user email address that can overstep the embargo")
    @NotEmpty
    private List<@Email String> priviledgedUsers;

    public int getDuration() {
        return duration;
    }

    public List<String> getPriviledgedUsers() {
        return priviledgedUsers;
    }
}
