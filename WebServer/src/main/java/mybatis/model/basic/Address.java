package mybatis.model.basic;

import flexjson.JSON;
import mybatis.model.complex.Region;

public class Address {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webserver.address.id
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webserver.address.city
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    private String city;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webserver.address.street_name
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    private String streetName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webserver.address.postal_code
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    private Integer postalCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webserver.address.street_number
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    private Integer streetNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webserver.address.region
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    private String region;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webserver.address.country
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    private String country;

    private Long idRegion;


    private Region regionLink;

    private Double longitude;

    private Double latitude;


    public String getAddressForGMapApi() {

        String addressForGApi = streetName + " " + streetNumber + " " +city;

        addressForGApi = addressForGApi.replaceAll(" ","+");

        return addressForGApi;

    }


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webserver.address.id
     *
     * @return the value of webserver.address.id
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webserver.address.id
     *
     * @param id the value for webserver.address.id
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webserver.address.city
     *
     * @return the value of webserver.address.city
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    public String getCity() {
        return city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webserver.address.city
     *
     * @param city the value for webserver.address.city
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webserver.address.street_name
     *
     * @return the value of webserver.address.street_name
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webserver.address.street_name
     *
     * @param streetName the value for webserver.address.street_name
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName == null ? null : streetName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webserver.address.postal_code
     *
     * @return the value of webserver.address.postal_code
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    public Integer getPostalCode() {
        return postalCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webserver.address.postal_code
     *
     * @param postalCode the value for webserver.address.postal_code
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webserver.address.street_number
     *
     * @return the value of webserver.address.street_number
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    public Integer getStreetNumber() {
        return streetNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webserver.address.street_number
     *
     * @param streetNumber the value for webserver.address.street_number
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webserver.address.region
     *
     * @return the value of webserver.address.region
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    public String getRegion() {
        return region;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webserver.address.region
     *
     * @param region the value for webserver.address.region
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webserver.address.country
     *
     * @return the value of webserver.address.country
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    public String getCountry() {
        return country;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webserver.address.country
     *
     * @param country the value for webserver.address.country
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public Long getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Long idRegion) {
        this.idRegion = idRegion;
    }

    @JSON
    public Region getRegionLink() {
        return regionLink;
    }

    public void setRegionLink(Region regionLink) {
        this.regionLink = regionLink;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webserver.address.longitude
     *
     * @mbggenerated Mon Mar 30 17:21:19 CEST 2015
     */
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webserver.address.latitude
     *
     * @mbggenerated Mon Mar 30 17:21:19 CEST 2015
     */
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return this.getRegion() + " " + this.getStreetName() + " " ;
    }
}