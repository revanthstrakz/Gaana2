package com.google.api.services.people.v1;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PeopleServiceScopes {
    public static final String CONTACTS = "https://www.googleapis.com/auth/contacts";
    public static final String CONTACTS_READONLY = "https://www.googleapis.com/auth/contacts.readonly";
    public static final String PLUS_LOGIN = "https://www.googleapis.com/auth/plus.login";
    public static final String USERINFO_EMAIL = "https://www.googleapis.com/auth/userinfo.email";
    public static final String USERINFO_PROFILE = "https://www.googleapis.com/auth/userinfo.profile";
    public static final String USER_ADDRESSES_READ = "https://www.googleapis.com/auth/user.addresses.read";
    public static final String USER_BIRTHDAY_READ = "https://www.googleapis.com/auth/user.birthday.read";
    public static final String USER_EMAILS_READ = "https://www.googleapis.com/auth/user.emails.read";
    public static final String USER_PHONENUMBERS_READ = "https://www.googleapis.com/auth/user.phonenumbers.read";

    public static Set<String> all() {
        HashSet hashSet = new HashSet();
        hashSet.add(CONTACTS);
        hashSet.add(CONTACTS_READONLY);
        hashSet.add("https://www.googleapis.com/auth/plus.login");
        hashSet.add(USER_ADDRESSES_READ);
        hashSet.add(USER_BIRTHDAY_READ);
        hashSet.add(USER_EMAILS_READ);
        hashSet.add(USER_PHONENUMBERS_READ);
        hashSet.add(USERINFO_EMAIL);
        hashSet.add(USERINFO_PROFILE);
        return Collections.unmodifiableSet(hashSet);
    }

    private PeopleServiceScopes() {
    }
}
