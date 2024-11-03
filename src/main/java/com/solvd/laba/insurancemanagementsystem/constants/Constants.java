package com.solvd.laba.insurancemanagementsystem.constants;

public class Constants {

    public static final String PROPERTIES_FILE = "dao.properties";

    public static final String PROPERTY_DRIVER = "driver";

    public static final String PROPERTY_URL = "url";

    public static final String PROPERTY_USERNAME = "user";

    public static final String PROPERTY_PASSWORD = "password";

    public static final String PROPERTY_KEY_NAME = "mySQL";

    public static final String SQL_INSERT_MEMBERS = "INSERT INTO members (first_name, last_name, phone_number, email, date_of_birth, primary_address_id, member_age_group_id) VALUES (?, ?, ?, ?, ?, ?, ?);";

    public static final String SQL_INSERT_ADDRESSES = "INSERT INTO addresses (building_number, unit_number, street, city, state, country, postal_code) VALUES (?, ?, ?, ?, ?, ?, ?);";

    public static final String SQL_INSERT_AGENT = "INSERT INTO agent (first_name, last_name, email, phone_number) VALUES (?, ?, ?, ?);";

    public static final String SQL_INSERT_POLICY_TYPE = "INSERT INTO policy_type (type_name, policy_description) VALUES (?, ?);";

    public static final String SQL_INSERT_BANK = "INSERT INTO banking_information (card_number, card_type, billing_address_id, banking_member_id) VALUES (?, ?, ?, ?);";

    public static final String SQL_FIND_BY_ID_MEMBERS = "SELECT * FROM members WHERE member_id = ?;";

    public static final String SQL_FIND_BY_ID_ADDRESSES = "SELECT * FROM addresses WHERE address_id = ?;";

    public static final String SQL_FIND_BY_ID_AGENT = "SELECT * FROM agent WHERE agent_id = ?;";

    public static final String SQL_FIND_BY_ID_POLICY_TYPE = "SELECT * FROM policy_type WHERE policy_type_id = ?;";

    public static final String SQL_FIND_BY_ID_BANK = "SELECT * FROM banking_information WHERE banking_id = ?;";

    public static final String SQL_FIND_BY_POLICY_NAME = "SELECT * FROM policy_type WHERE type_name = ?;";

    public static final String SQL_FIND_BY_CARD_BANK = "SELECT * FROM banking_information WHERE card_number = ?;";

    public static final String SQL_FIND_BY_EMAIL_MEMBERS = "SELECT * FROM members WHERE email = ?;";

    public static final String SQL_FIND_BY_EMAIL_AGENT = "SELECT * FROM agent WHERE email = ?;";

    public static final String SQL_FIND_BY_PHONE_MEMBERS = "SELECT * FROM members WHERE phone_number = ?;";

    public static final String SQL_FIND_BY_PHONE_AGENT = "SELECT * FROM agent WHERE phone_number = ?;";

    public static final String SQL_FIND_BY_MEMBER_ID_BANK = "SELECT * FROM banking_information WHERE banking_member_id = ?;";

    public static final String SQL_FULL_TABLE_MEMBERS = "SELECT * FROM members;";

    public static final String SQL_FULL_TABLE_ADDRESSES = "SELECT * FROM addresses;";

    public static final String SQL_FULL_TABLE_BANK = "SELECT * FROM banking_information;";

    public static final String SQL_FULL_TABLE_AGENT = "SELECT * FROM agent;";

    public static final String SQL_FULL_TABLE_POLICY_TYPE = "SELECT * FROM policy_type;";

    public static final int MYSQL = 1;

    public static final int ORACLE = 2;

    public static final int POSTGRESQL = 3;

}
