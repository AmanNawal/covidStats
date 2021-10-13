package com.example.completesigininfirebase;

public class CovidData {
//"active":22524,
//"critical":1124,"casesPerOneMillion":3885,
// "deathsPerOneMillion":181,"tests":765477,"testsPerOneMillion":19115,"population":40046774,"continent":"Asia","oneCasePerPeople":257,
// "oneDeathPerPeople":5539,"oneTestPerPeople":52,"activePerOneMillion":543.21,"recoveredPerOneMillion":3161.68,"criticalPerOneMillion":28.07},
    private String country;
   private String continent;
    private String flagImage;
    private String critical;
    private String active;
    private String casespermillion;
    private String deathspermillion;
    private String tests;

    private String testspermillion;
    private String population;
    private String onecaseperpeople;
    private String onedeathpewpeople;
    private String onetestperpeople,acticepermillion,recoveredperonemillion,critcalperonemillion;

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }

    public String getCasespermillion() {
        return casespermillion;
    }

    public void setCasespermillion(String casespermillion) {
        this.casespermillion = casespermillion;
    }

    public String getDeathspermillion() {
        return deathspermillion;
    }

    public void setDeathspermillion(String deathspermillion) {
        this.deathspermillion = deathspermillion;
    }

    public String getTestspermillion() {
        return testspermillion;
    }

    public void setTestspermillion(String testspermillion) {
        this.testspermillion = testspermillion;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getOnecaseperpeople() {
        return onecaseperpeople;
    }

    public void setOnecaseperpeople(String onecaseperpeople) {
        this.onecaseperpeople = onecaseperpeople;
    }

    public String getOnedeathpewpeople() {
        return onedeathpewpeople;
    }

    public void setOnedeathpewpeople(String onedeathpewpeople) {
        this.onedeathpewpeople = onedeathpewpeople;
    }

    public String getOnetestperpeople() {
        return onetestperpeople;
    }

    public void setOnetestperpeople(String onetestperpeople) {
        this.onetestperpeople = onetestperpeople;
    }

    public String getActicepermillion() {
        return acticepermillion;
    }

    public void setActicepermillion(String acticepermillion) {
        this.acticepermillion = acticepermillion;
    }

    public String getRecoveredperonemillion() {
        return recoveredperonemillion;
    }

    public void setRecoveredperonemillion(String recoveredperonemillion) {
        this.recoveredperonemillion = recoveredperonemillion;
    }

    public String getCritcalperonemillion() {
        return critcalperonemillion;
    }

    public void setCritcalperonemillion(String critcalperonemillion) {
        this.critcalperonemillion = critcalperonemillion;
    }


    public String getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(String todayCases) {
        this.todayCases = todayCases;
    }

    public String getCases() {
        return Cases;
    }

    public void setCases(String cases) {
        Cases = cases;
    }

    public String getDeath() {
        return Death;
    }

    public void setDeath(String death) {
        Death = death;
    }

    public String getTodayDeath() {
        return TodayDeath;
    }

    public void setTodayDeath(String todayDeath) {
        TodayDeath = todayDeath;
    }

    public String getRecovered() {
        return Recovered;
    }

    public void setRecovered(String recovered) {
        Recovered = recovered;
    }

    public String getTodayRecovered() {
        return TodayRecovered;
    }

    public void setTodayRecovered(String todayRecovered) {
        TodayRecovered = todayRecovered;
    }

    public String getActive() {
        return Active;
    }

    public void setActive(String active) {
        Active = active;
    }

    public String getTests() {
        return Tests;
    }

    public void setTests(String tests) {
        Tests = tests;
    }

    private String todayCases,Cases,Death,TodayDeath,Recovered,TodayRecovered,Active,Tests;

    public String getFlagImage() {
        return flagImage;
    }

    public void setFlagImage(String flagImage) {
        this.flagImage = flagImage;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }



}
