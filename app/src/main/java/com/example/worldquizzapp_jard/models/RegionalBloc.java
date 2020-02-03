package com.example.worldquizzapp_jard.models;



import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "acronym",
        "name",
        "otherAcronyms",
        "otherNames"
})
public class RegionalBloc {

    @JsonProperty("acronym")
    public String acronym;
    @JsonProperty("name")
    public String name;
    @JsonProperty("otherAcronyms")
    public List<Object> otherAcronyms = null;
    @JsonProperty("otherNames")
    public List<Object> otherNames = null;

    public RegionalBloc(String acronym, String name, List<Object> otherAcronyms, List<Object> otherNames) {
        this.acronym = acronym;
        this.name = name;
        this.otherAcronyms = otherAcronyms;
        this.otherNames = otherNames;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getOtherAcronyms() {
        return otherAcronyms;
    }

    public void setOtherAcronyms(List<Object> otherAcronyms) {
        this.otherAcronyms = otherAcronyms;
    }

    public List<Object> getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(List<Object> otherNames) {
        this.otherNames = otherNames;
    }
}
