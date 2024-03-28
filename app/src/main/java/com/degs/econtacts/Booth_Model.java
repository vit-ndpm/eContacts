package com.degs.econtacts;

public class Booth_Model {
    int id, ac_code;
    int pollingStationCode, pollingStationAuxillaryCode, phanaNearestVillageDistance, sectorNumber;
    String
            boothName;
    String bLOName;
    String bLOMobileNumber;
    String personName;
    String personMobileNumber;
    String personSTDCode;
    String personLandLineNumber;
    String thanaNearestVillage;
    String thanaSTDPhon;
    String thanaMobileNumber;
    String sectorMagistrateName;
    String sectorOfficerDesignation;

    String sectorOfficerName;
    String sectorOfficerCaderName;
    String sectorMobileNumber;
    String sectorPhoneNumber;
    String localContactPersonNameFirst;
    String localContactPersonNameFirstMobile;
    String localContactPersonNameFirstPhone;
    String localContactPersonNameSecond;
    String localContactPersonNameSecondMobile;
    String updated_at;
    String created_at;
    String localContactPersonNameSecondPhone;
    double lat, lng;

    int webcasting, cctv, videography, critical, vulnarable, call_network, data_network, status;


    public Booth_Model() {
    }

    public Booth_Model(int id, int ac_code, int pollingStationCode, int pollingStationAuxillaryCode, int phanaNearestVillageDistance, int sectorNumber, String boothName, String bLOName, String bLOMobileNumber, String personName, String personMobileNumber, String personSTDCode, String personLandLineNumber, String thanaNearestVillage, String thanaSTDPhon, String thanaMobileNumber, String sectorMagistrateName, String sectorOfficerDesignation, String sectorOfficerName, String sectorOfficerCaderName, String sectorMobileNumber, String sectorPhoneNumber, String localContactPersonNameFirst, String localContactPersonNameFirstMobile, String localContactPersonNameFirstPhone, String localContactPersonNameSecond, String localContactPersonNameSecondMobile, String updated_at, String created_at, String localContactPersonNameSecondPhone, double lat, double lng, int webcasting, int cctv, int videography, int critical, int vulnarable, int call_network, int data_network, int status) {
        this.id = id;
        this.ac_code = ac_code;
        this.pollingStationCode = pollingStationCode;
        this.pollingStationAuxillaryCode = pollingStationAuxillaryCode;
        this.phanaNearestVillageDistance = phanaNearestVillageDistance;
        this.sectorNumber = sectorNumber;
        this.boothName = boothName;
        this.bLOName = bLOName;
        this.bLOMobileNumber = bLOMobileNumber;
        this.personName = personName;
        this.personMobileNumber = personMobileNumber;
        this.personSTDCode = personSTDCode;
        this.personLandLineNumber = personLandLineNumber;
        this.thanaNearestVillage = thanaNearestVillage;
        this.thanaSTDPhon = thanaSTDPhon;
        this.thanaMobileNumber = thanaMobileNumber;
        this.sectorMagistrateName = sectorMagistrateName;
        this.sectorOfficerDesignation = sectorOfficerDesignation;
        this.sectorOfficerName = sectorOfficerName;
        this.sectorOfficerCaderName = sectorOfficerCaderName;
        this.sectorMobileNumber = sectorMobileNumber;
        this.sectorPhoneNumber = sectorPhoneNumber;
        this.localContactPersonNameFirst = localContactPersonNameFirst;
        this.localContactPersonNameFirstMobile = localContactPersonNameFirstMobile;
        this.localContactPersonNameFirstPhone = localContactPersonNameFirstPhone;
        this.localContactPersonNameSecond = localContactPersonNameSecond;
        this.localContactPersonNameSecondMobile = localContactPersonNameSecondMobile;
        this.updated_at = updated_at;
        this.created_at = created_at;
        this.localContactPersonNameSecondPhone = localContactPersonNameSecondPhone;
        this.lat = lat;
        this.lng = lng;
        this.webcasting = webcasting;
        this.cctv = cctv;
        this.videography = videography;
        this.critical = critical;
        this.vulnarable = vulnarable;
        this.call_network = call_network;
        this.data_network = data_network;
        this.status = status;
    }
    public String getSectorOfficerDesignation() {
        return sectorOfficerDesignation;
    }

    public void setSectorOfficerDesignation(String sectorOfficerDesignation) {
        this.sectorOfficerDesignation = sectorOfficerDesignation;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAc_code() {
        return ac_code;
    }

    public void setAc_code(int ac_code) {
        this.ac_code = ac_code;
    }

    public int getPollingStationCode() {
        return pollingStationCode;
    }

    public void setPollingStationCode(int pollingStationCode) {
        this.pollingStationCode = pollingStationCode;
    }

    public int getPollingStationAuxillaryCode() {
        return pollingStationAuxillaryCode;
    }

    public void setPollingStationAuxillaryCode(int pollingStationAuxillaryCode) {
        this.pollingStationAuxillaryCode = pollingStationAuxillaryCode;
    }

    public int getPhanaNearestVillageDistance() {
        return phanaNearestVillageDistance;
    }

    public void setPhanaNearestVillageDistance(int phanaNearestVillageDistance) {
        this.phanaNearestVillageDistance = phanaNearestVillageDistance;
    }

    public int getSectorNumber() {
        return sectorNumber;
    }

    public void setSectorNumber(int sectorNumber) {
        this.sectorNumber = sectorNumber;
    }

    public String getBoothName() {
        return boothName;
    }

    public void setBoothName(String boothName) {
        this.boothName = boothName;
    }

    public String getbLOName() {
        return bLOName;
    }

    public void setbLOName(String bLOName) {
        this.bLOName = bLOName;
    }

    public String getbLOMobileNumber() {
        return bLOMobileNumber;
    }

    public void setbLOMobileNumber(String bLOMobileNumber) {
        this.bLOMobileNumber = bLOMobileNumber;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonMobileNumber() {
        return personMobileNumber;
    }

    public void setPersonMobileNumber(String personMobileNumber) {
        this.personMobileNumber = personMobileNumber;
    }

    public String getPersonSTDCode() {
        return personSTDCode;
    }

    public void setPersonSTDCode(String personSTDCode) {
        this.personSTDCode = personSTDCode;
    }

    public String getPersonLandLineNumber() {
        return personLandLineNumber;
    }

    public void setPersonLandLineNumber(String personLandLineNumber) {
        this.personLandLineNumber = personLandLineNumber;
    }

    public String getThanaNearestVillage() {
        return thanaNearestVillage;
    }

    public void setThanaNearestVillage(String thanaNearestVillage) {
        this.thanaNearestVillage = thanaNearestVillage;
    }

    public String getThanaSTDPhon() {
        return thanaSTDPhon;
    }

    public void setThanaSTDPhon(String thanaSTDPhon) {
        this.thanaSTDPhon = thanaSTDPhon;
    }

    public String getThanaMobileNumber() {
        return thanaMobileNumber;
    }

    public void setThanaMobileNumber(String thanaMobileNumber) {
        this.thanaMobileNumber = thanaMobileNumber;
    }

    public String getSectorMagistrateName() {
        return sectorMagistrateName;
    }

    public void setSectorMagistrateName(String sectorMagistrateName) {
        this.sectorMagistrateName = sectorMagistrateName;
    }

    public String getSectorOfficerName() {
        return sectorOfficerName;
    }

    public void setSectorOfficerName(String sectorOfficerName) {
        this.sectorOfficerName = sectorOfficerName;
    }

    public String getSectorOfficerCaderName() {
        return sectorOfficerCaderName;
    }

    public void setSectorOfficerCaderName(String sectorOfficerCaderName) {
        this.sectorOfficerCaderName = sectorOfficerCaderName;
    }

    public String getSectorMobileNumber() {
        return sectorMobileNumber;
    }

    public void setSectorMobileNumber(String sectorMobileNumber) {
        this.sectorMobileNumber = sectorMobileNumber;
    }

    public String getSectorPhoneNumber() {
        return sectorPhoneNumber;
    }

    public void setSectorPhoneNumber(String sectorPhoneNumber) {
        this.sectorPhoneNumber = sectorPhoneNumber;
    }

    public String getLocalContactPersonNameFirst() {
        return localContactPersonNameFirst;
    }

    public void setLocalContactPersonNameFirst(String localContactPersonNameFirst) {
        this.localContactPersonNameFirst = localContactPersonNameFirst;
    }

    public String getLocalContactPersonNameFirstMobile() {
        return localContactPersonNameFirstMobile;
    }

    public void setLocalContactPersonNameFirstMobile(String localContactPersonNameFirstMobile) {
        this.localContactPersonNameFirstMobile = localContactPersonNameFirstMobile;
    }

    public String getLocalContactPersonNameFirstPhone() {
        return localContactPersonNameFirstPhone;
    }

    public void setLocalContactPersonNameFirstPhone(String localContactPersonNameFirstPhone) {
        this.localContactPersonNameFirstPhone = localContactPersonNameFirstPhone;
    }

    public String getLocalContactPersonNameSecond() {
        return localContactPersonNameSecond;
    }

    public void setLocalContactPersonNameSecond(String localContactPersonNameSecond) {
        this.localContactPersonNameSecond = localContactPersonNameSecond;
    }

    public String getLocalContactPersonNameSecondMobile() {
        return localContactPersonNameSecondMobile;
    }

    public void setLocalContactPersonNameSecondMobile(String localContactPersonNameSecondMobile) {
        this.localContactPersonNameSecondMobile = localContactPersonNameSecondMobile;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getLcalContactPersonNameSecondPhone() {
        return localContactPersonNameSecondPhone;
    }

    public void setLcalContactPersonNameSecondPhone(String localContactPersonNameSecondPhone) {
        this.localContactPersonNameSecondPhone = localContactPersonNameSecondPhone;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getWebcasting() {
        return webcasting;
    }

    public void setWebcasting(int webcasting) {
        this.webcasting = webcasting;
    }

    public int getCctv() {
        return cctv;
    }

    public void setCctv(int cctv) {
        this.cctv = cctv;
    }

    public int getVideography() {
        return videography;
    }

    public void setVideography(int videography) {
        this.videography = videography;
    }

    public int getCritical() {
        return critical;
    }

    public void setCritical(int critical) {
        this.critical = critical;
    }

    public int getVulnarable() {
        return vulnarable;
    }

    public void setVulnarable(int vulnarable) {
        this.vulnarable = vulnarable;
    }

    public int getCall_network() {
        return call_network;
    }

    public void setCall_network(int call_network) {
        this.call_network = call_network;
    }

    public int getData_network() {
        return data_network;
    }

    public void setData_network(int data_network) {
        this.data_network = data_network;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
