package com.accessconnecticut;

import java.io.Serializable;
import java.util.List;

/**
 * Created by KTirumalsetty on 12/21/2016.
 */

public class Legislators implements Serializable{

    private String last_name;
    private String updated_at;
    private List<Source> sources;
    private String full_name;
//    private String old_roles;
    private String id;
    private String first_name;
    private String middle_name;
    private String district;
    private String office_address;
    private String state;
    private String votesmart_id;
    private String boundary_id;
    private String email;
    private String[] all_ids;
    private String leg_id;
    private String party;
    private boolean active;
    private String photo_url;
    private String url;
    private String country;
    private String created_at;
    private String level;
    private String chamber;
    private String office_phone;
    private String suffixes;

    private List<Office> offices;

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    /*public String getOld_roles() {
        return old_roles;
    }

    public void setOld_roles(String old_roles) {
        this.old_roles = old_roles;
    }*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getOffice_address() {
        return office_address;
    }

    public void setOffice_address(String office_address) {
        this.office_address = office_address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getVotesmart_id() {
        return votesmart_id;
    }

    public void setVotesmart_id(String votesmart_id) {
        this.votesmart_id = votesmart_id;
    }

    public String getBoundary_id() {
        return boundary_id;
    }

    public void setBoundary_id(String boundary_id) {
        this.boundary_id = boundary_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getAll_ids() {
        return all_ids;
    }

    public void setAll_ids(String[] all_ids) {
        this.all_ids = all_ids;
    }

    public String getLeg_id() {
        return leg_id;
    }

    public void setLeg_id(String leg_id) {
        this.leg_id = leg_id;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getChamber() {
        return chamber;
    }

    public void setChamber(String chamber) {
        this.chamber = chamber;
    }

    public String getOffice_phone() {
        return office_phone;
    }

    public void setOffice_phone(String office_phone) {
        this.office_phone = office_phone;
    }

    public String getSuffixes() {
        return suffixes;
    }

    public void setSuffixes(String suffixes) {
        this.suffixes = suffixes;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public List<Office> getOffices() {
        return offices;
    }

    public void setOffices(List<Office> offices) {
        this.offices = offices;
    }
}
