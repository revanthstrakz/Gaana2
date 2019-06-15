package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Data;
import com.google.api.client.util.Key;
import java.util.List;

public final class Person extends GenericJson {
    @Key
    private List<Address> addresses;
    @Key
    private String ageRange;
    @Key
    private List<AgeRangeType> ageRanges;
    @Key
    private List<Biography> biographies;
    @Key
    private List<Birthday> birthdays;
    @Key
    private List<BraggingRights> braggingRights;
    @Key
    private List<CoverPhoto> coverPhotos;
    @Key
    private List<EmailAddress> emailAddresses;
    @Key
    private String etag;
    @Key
    private List<Event> events;
    @Key
    private List<Gender> genders;
    @Key
    private List<ImClient> imClients;
    @Key
    private List<Interest> interests;
    @Key
    private List<Locale> locales;
    @Key
    private List<Membership> memberships;
    @Key
    private PersonMetadata metadata;
    @Key
    private List<Name> names;
    @Key
    private List<Nickname> nicknames;
    @Key
    private List<Occupation> occupations;
    @Key
    private List<Organization> organizations;
    @Key
    private List<PhoneNumber> phoneNumbers;
    @Key
    private List<Photo> photos;
    @Key
    private List<Relation> relations;
    @Key
    private List<RelationshipInterest> relationshipInterests;
    @Key
    private List<RelationshipStatus> relationshipStatuses;
    @Key
    private List<Residence> residences;
    @Key
    private String resourceName;
    @Key
    private List<Skill> skills;
    @Key
    private List<Tagline> taglines;
    @Key
    private List<Url> urls;
    @Key
    private List<UserDefined> userDefined;

    static {
        Data.nullOf(Occupation.class);
        Data.nullOf(Relation.class);
    }

    public List<Address> getAddresses() {
        return this.addresses;
    }

    public Person setAddresses(List<Address> list) {
        this.addresses = list;
        return this;
    }

    public String getAgeRange() {
        return this.ageRange;
    }

    public Person setAgeRange(String str) {
        this.ageRange = str;
        return this;
    }

    public List<AgeRangeType> getAgeRanges() {
        return this.ageRanges;
    }

    public Person setAgeRanges(List<AgeRangeType> list) {
        this.ageRanges = list;
        return this;
    }

    public List<Biography> getBiographies() {
        return this.biographies;
    }

    public Person setBiographies(List<Biography> list) {
        this.biographies = list;
        return this;
    }

    public List<Birthday> getBirthdays() {
        return this.birthdays;
    }

    public Person setBirthdays(List<Birthday> list) {
        this.birthdays = list;
        return this;
    }

    public List<BraggingRights> getBraggingRights() {
        return this.braggingRights;
    }

    public Person setBraggingRights(List<BraggingRights> list) {
        this.braggingRights = list;
        return this;
    }

    public List<CoverPhoto> getCoverPhotos() {
        return this.coverPhotos;
    }

    public Person setCoverPhotos(List<CoverPhoto> list) {
        this.coverPhotos = list;
        return this;
    }

    public List<EmailAddress> getEmailAddresses() {
        return this.emailAddresses;
    }

    public Person setEmailAddresses(List<EmailAddress> list) {
        this.emailAddresses = list;
        return this;
    }

    public String getEtag() {
        return this.etag;
    }

    public Person setEtag(String str) {
        this.etag = str;
        return this;
    }

    public List<Event> getEvents() {
        return this.events;
    }

    public Person setEvents(List<Event> list) {
        this.events = list;
        return this;
    }

    public List<Gender> getGenders() {
        return this.genders;
    }

    public Person setGenders(List<Gender> list) {
        this.genders = list;
        return this;
    }

    public List<ImClient> getImClients() {
        return this.imClients;
    }

    public Person setImClients(List<ImClient> list) {
        this.imClients = list;
        return this;
    }

    public List<Interest> getInterests() {
        return this.interests;
    }

    public Person setInterests(List<Interest> list) {
        this.interests = list;
        return this;
    }

    public List<Locale> getLocales() {
        return this.locales;
    }

    public Person setLocales(List<Locale> list) {
        this.locales = list;
        return this;
    }

    public List<Membership> getMemberships() {
        return this.memberships;
    }

    public Person setMemberships(List<Membership> list) {
        this.memberships = list;
        return this;
    }

    public PersonMetadata getMetadata() {
        return this.metadata;
    }

    public Person setMetadata(PersonMetadata personMetadata) {
        this.metadata = personMetadata;
        return this;
    }

    public List<Name> getNames() {
        return this.names;
    }

    public Person setNames(List<Name> list) {
        this.names = list;
        return this;
    }

    public List<Nickname> getNicknames() {
        return this.nicknames;
    }

    public Person setNicknames(List<Nickname> list) {
        this.nicknames = list;
        return this;
    }

    public List<Occupation> getOccupations() {
        return this.occupations;
    }

    public Person setOccupations(List<Occupation> list) {
        this.occupations = list;
        return this;
    }

    public List<Organization> getOrganizations() {
        return this.organizations;
    }

    public Person setOrganizations(List<Organization> list) {
        this.organizations = list;
        return this;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return this.phoneNumbers;
    }

    public Person setPhoneNumbers(List<PhoneNumber> list) {
        this.phoneNumbers = list;
        return this;
    }

    public List<Photo> getPhotos() {
        return this.photos;
    }

    public Person setPhotos(List<Photo> list) {
        this.photos = list;
        return this;
    }

    public List<Relation> getRelations() {
        return this.relations;
    }

    public Person setRelations(List<Relation> list) {
        this.relations = list;
        return this;
    }

    public List<RelationshipInterest> getRelationshipInterests() {
        return this.relationshipInterests;
    }

    public Person setRelationshipInterests(List<RelationshipInterest> list) {
        this.relationshipInterests = list;
        return this;
    }

    public List<RelationshipStatus> getRelationshipStatuses() {
        return this.relationshipStatuses;
    }

    public Person setRelationshipStatuses(List<RelationshipStatus> list) {
        this.relationshipStatuses = list;
        return this;
    }

    public List<Residence> getResidences() {
        return this.residences;
    }

    public Person setResidences(List<Residence> list) {
        this.residences = list;
        return this;
    }

    public String getResourceName() {
        return this.resourceName;
    }

    public Person setResourceName(String str) {
        this.resourceName = str;
        return this;
    }

    public List<Skill> getSkills() {
        return this.skills;
    }

    public Person setSkills(List<Skill> list) {
        this.skills = list;
        return this;
    }

    public List<Tagline> getTaglines() {
        return this.taglines;
    }

    public Person setTaglines(List<Tagline> list) {
        this.taglines = list;
        return this;
    }

    public List<Url> getUrls() {
        return this.urls;
    }

    public Person setUrls(List<Url> list) {
        this.urls = list;
        return this;
    }

    public List<UserDefined> getUserDefined() {
        return this.userDefined;
    }

    public Person setUserDefined(List<UserDefined> list) {
        this.userDefined = list;
        return this;
    }

    public Person set(String str, Object obj) {
        return (Person) super.set(str, obj);
    }

    public Person clone() {
        return (Person) super.clone();
    }
}
