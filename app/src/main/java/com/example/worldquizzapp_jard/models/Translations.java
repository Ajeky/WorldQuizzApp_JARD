package com.example.worldquizzapp_jard.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "de",
        "es",
        "fr",
        "ja",
        "it",
        "br",
        "pt",
        "nl",
        "hr",
        "fa"
})
public class Translations {

    @JsonProperty("de")
    public String de;
    @JsonProperty("es")
    public String es;
    @JsonProperty("fr")
    public String fr;
    @JsonProperty("ja")
    public String ja;
    @JsonProperty("it")
    public String it;
    @JsonProperty("br")
    public String br;
    @JsonProperty("pt")
    public String pt;
    @JsonProperty("nl")
    public String nl;
    @JsonProperty("hr")
    public String hr;
    @JsonProperty("fa")
    public String fa;

    public Translations(String de, String es, String fr, String ja, String it, String br, String pt, String nl, String hr, String fa) {
        this.de = de;
        this.es = es;
        this.fr = fr;
        this.ja = ja;
        this.it = it;
        this.br = br;
        this.pt = pt;
        this.nl = nl;
        this.hr = hr;
        this.fa = fa;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getEs() {
        return es;
    }

    public void setEs(String es) {
        this.es = es;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public String getJa() {
        return ja;
    }

    public void setJa(String ja) {
        this.ja = ja;
    }

    public String getIt() {
        return it;
    }

    public void setIt(String it) {
        this.it = it;
    }

    public String getBr() {
        return br;
    }

    public void setBr(String br) {
        this.br = br;
    }

    public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }

    public String getNl() {
        return nl;
    }

    public void setNl(String nl) {
        this.nl = nl;
    }

    public String getHr() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr = hr;
    }

    public String getFa() {
        return fa;
    }

    public void setFa(String fa) {
        this.fa = fa;
    }
}
