package com.example.worldquizzapp_jard.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "iso639_1",
        "iso639_2",
        "name",
        "nativeName"
})
public class Language {

    @JsonProperty("iso639_1")
    public String iso6391;
    @JsonProperty("iso639_2")
    public String iso6392;
    @JsonProperty("name")
    public String name;
    @JsonProperty("nativeName")
    public String nativeName;

    public Language(String iso6391, String iso6392, String name, String nativeName) {
        this.iso6391 = iso6391;
        this.iso6392 = iso6392;
        this.name = name;
        this.nativeName = nativeName;
    }

    public String getIso6391() {
        return iso6391;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    public String getIso6392() {
        return iso6392;
    }

    public void setIso6392(String iso6392) {
        this.iso6392 = iso6392;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }
}