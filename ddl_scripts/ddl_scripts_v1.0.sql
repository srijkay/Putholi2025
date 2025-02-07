-- Table: putholi.tpuo_schoolinfo

-- DROP TABLE IF EXISTS putholi.tpuo_schoolinfo;

CREATE TABLE IF NOT EXISTS putholi.tpuo_schoolinfo
(
   school_info_id bigint NOT NULL,
    comments character varying(255) ,
    created_by character varying(25)  NOT NULL,
    created_date timestamp without time zone NOT NULL,
    educational_district character varying(20)  NOT NULL,
    last_modified_by character varying(25) ,
    last_modified_date timestamp without time zone,
    no_of_students integer NOT NULL,
    no_of_teachers integer NOT NULL,
    school_description character varying(255)  NOT NULL,
    school_name character varying(255)  NOT NULL,
    school_reg_no character varying(25)  NOT NULL,
    school_status character varying(6)  NOT NULL,
    school_type character varying(3)  NOT NULL,
    school_url character varying(255)  NOT NULL,
    volunteer_name character varying(255) ,
    remarks character varying(255) ,
    active character varying(2)  NOT NULL,
    CONSTRAINT tpuo_schoolinfo_pkey PRIMARY KEY (school_info_id),
    CONSTRAINT school_reg_no_unique UNIQUE (school_reg_no)
);

ALTER TABLE IF EXISTS putholi.tpuo_schoolinfo OWNER to postgres;

-- SEQUENCE: putholi.tpuo_schoolinfo_school_info_id_seq

-- DROP SEQUENCE IF EXISTS putholi.tpuo_schoolinfo_school_info_id_seq;

CREATE SEQUENCE IF NOT EXISTS putholi.tpuo_schoolinfo_school_info_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.tpuo_schoolinfo_school_info_id_seq OWNER TO postgres;

-- Table: putholi.tpuo_address

-- DROP TABLE IF EXISTS putholi.tpuo_address;

CREATE TABLE IF NOT EXISTS putholi.tpuo_address
(
    address_id bigint NOT NULL,
    address_line_1 character varying(255) NOT NULL,
    address_line_2 character varying(255),
    city character varying(255) NOT NULL,
    country character varying(3) NOT NULL,
    created_by character varying(25) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    district character varying(255) NOT NULL,
    last_modified_by character varying(25),
    last_modified_date timestamp without time zone,
    locality character varying(255) NOT NULL,
    pincode bigint NOT NULL,
    state character varying(3) NOT NULL,
    school_info_id bigint NOT NULL,
    CONSTRAINT tpuo_address_pkey PRIMARY KEY (address_id),
    CONSTRAINT uk_7bb0arux1d4jm3r1xv171s8bo UNIQUE (school_info_id),
    CONSTRAINT fk85w9jic7k8plncnba73pv9gyx FOREIGN KEY (school_info_id)
        REFERENCES putholi.tpuo_schoolinfo (school_info_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
ALTER TABLE IF EXISTS putholi.tpuo_address OWNER to postgres;

-- SEQUENCE: putholi.tpuo_address_address_id_seq

-- DROP SEQUENCE IF EXISTS putholi.tpuo_address_address_id_seq;

CREATE SEQUENCE IF NOT EXISTS putholi.tpuo_address_address_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.tpuo_address_address_id_seq OWNER TO postgres;

-- Table: putholi.tpuo_contacts

-- DROP TABLE IF EXISTS putholi.tpuo_contacts;

CREATE TABLE IF NOT EXISTS putholi.tpuo_contacts
(
    contacts_id bigint NOT NULL,
    created_by character varying(25)  NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(25),
    last_modified_date timestamp without time zone,
    pri_designation character varying(255) NOT NULL,
    pri_email character varying(255)NOT NULL,
    pri_name character varying(255) NOT NULL,
    pri_num character varying(13) NOT NULL,
    sec_designation character varying(255) NOT NULL,
    sec_email character varying(255) NOT NULL,
    sec_name character varying(255) NOT NULL,
    sec_num character varying(13)NOT NULL,
    school_info_id bigint NOT NULL,
    CONSTRAINT tpuo_contacts_pkey PRIMARY KEY (contacts_id),
    CONSTRAINT uk_9931dge5t5o6r2vqw1mvpgv5y UNIQUE (school_info_id),
    CONSTRAINT fk3rddufxapkhief4keneij4t6k FOREIGN KEY (school_info_id)
        REFERENCES putholi.tpuo_schoolinfo (school_info_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
ALTER TABLE IF EXISTS putholi.tpuo_contacts OWNER to postgres;

-- SEQUENCE: putholi.tpuo_contacts_contacts_id_seq

-- DROP SEQUENCE IF EXISTS putholi.tpuo_contacts_contacts_id_seq;

CREATE SEQUENCE IF NOT EXISTS putholi.tpuo_contacts_contacts_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.tpuo_contacts_contacts_id_seq OWNER TO postgres;

-- Table: putholi.tpuo_announcement

-- DROP TABLE IF EXISTS putholi.tpuo_announcement;

CREATE TABLE IF NOT EXISTS putholi.tpuo_announcement
(
    announcement_id bigint NOT NULL,
    announcement_description character varying(255) NOT NULL,
    announcement_role character varying(10)  NOT NULL,
    announcement_title character varying(255) ,
    created_by character varying(25)  NOT NULL,
    created_date timestamp without time zone NOT NULL,
    effective_date timestamp without time zone NOT NULL,
    expiry_date timestamp without time zone NOT NULL,
    last_modified_by character varying(25) ,
    last_modified_date timestamp without time zone,
    CONSTRAINT tpuo_announcement_pkey PRIMARY KEY (announcement_id)
);
ALTER TABLE IF EXISTS putholi.tpuo_announcement OWNER to postgres;

-- Table: putholi.approval_history_details

-- DROP TABLE IF EXISTS putholi.approval_history_details;

CREATE TABLE IF NOT EXISTS putholi.approval_history_details
(
    approval_his_id bigint NOT NULL,
    action_by character varying(25)  NOT NULL,
    action_date timestamp without time zone,
    consolidate_id bigint,
    remarks character varying(512) ,
    action_role character varying(10)  NOT NULL,
    status character varying(6)  NOT NULL,
    username character varying(25) ,
    type character varying(25) ,
    change_request_role character varying(25) ,
    CONSTRAINT approval_history_details_pkey PRIMARY KEY (approval_his_id)
);
ALTER TABLE IF EXISTS putholi.approval_history_details OWNER to postgres;

-- SEQUENCE: putholi.tpuo_featuremanagement_feature_id_seq

-- DROP SEQUENCE putholi.tpuo_featuremanagement_feature_id_seq;

CREATE SEQUENCE putholi.tpuo_featuremanagement_feature_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.tpuo_featuremanagement_feature_id_seq
    OWNER TO postgres;

-- Table: putholi.tpuo_feature_management

-- DROP TABLE putholi.tpuo_feature_management;

CREATE TABLE putholi.tpuo_feature_management
(
    feature_id bigint NOT NULL,
    created_by character varying(25) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    feature_name character varying(255) NOT NULL,
    filter_type character varying(3) NOT NULL,
    is_approval_applicable character varying(3) NOT NULL,
    is_auto_approval character varying(3),
    last_modified_by character varying(25),
    last_modified_date timestamp without time zone,
    level_of_approval character varying(3) NOT NULL,
    module character varying(3) NOT NULL,
    organisation character varying(3),
    role character varying(3) NOT NULL,
    status character varying(3) NOT NULL,
    user_name character varying(25) NOT NULL,
    CONSTRAINT tpuo_feature_management_pkey PRIMARY KEY (feature_id));

ALTER TABLE putholi.tpuo_feature_management
    OWNER to postgres;


-- SEQUENCE: putholi.approval_history_details_approval_his_id_seq

-- DROP SEQUENCE IF EXISTS putholi.approval_history_details_approval_his_id_seq;

CREATE SEQUENCE IF NOT EXISTS putholi.approval_history_details_approval_his_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.approval_history_details_approval_his_id_seq OWNER TO postgres;

-- SEQUENCE: putholi.tpuo_announcement_announcement_id_seq

-- DROP SEQUENCE IF EXISTS putholi.tpuo_announcement_announcement_id_seq;

CREATE SEQUENCE IF NOT EXISTS putholi.tpuo_announcement_announcement_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.tpuo_announcement_announcement_id_seq OWNER TO postgres;

-- Table: putholi.tpuo_application_config

-- DROP TABLE IF EXISTS putholi.tpuo_application_config;

CREATE TABLE IF NOT EXISTS putholi.tpuo_application_config
(
    config_id bigint NOT NULL,
    config_for character varying(255)  NOT NULL,
    config_value character varying(255)  NOT NULL,
    created_by character varying(25)  NOT NULL,
    created_date timestamp without time zone NOT NULL,
    description character varying(255)  NOT NULL,
    last_modified_by character varying(25),
    last_modified_date timestamp without time zone,
    module character varying(3) NOT NULL,
    CONSTRAINT tpuo_application_config_pkey PRIMARY KEY (config_id)
);
ALTER TABLE IF EXISTS putholi.tpuo_application_config OWNER to postgres;

-- SEQUENCE: putholi.tpuo_application_config_config_id_seq

-- DROP SEQUENCE IF EXISTS putholi.tpuo_application_config_config_id_seq;

CREATE SEQUENCE IF NOT EXISTS putholi.tpuo_application_config_config_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.tpuo_application_config_config_id_seq OWNER TO postgres;

-- Table: putholi.tpuo_approverlevels

-- DROP TABLE IF EXISTS putholi.tpuo_approverlevels;

CREATE TABLE IF NOT EXISTS putholi.tpuo_approverlevels
(
    approver_id bigint NOT NULL,
    approver_roles character varying(25) ,
    level character varying(25)  NOT NULL,
    type character varying(3)  NOT NULL,
    feature_id bigint NOT NULL,
    CONSTRAINT tpuo_approverlevels_pkey PRIMARY KEY (approver_id),
    CONSTRAINT fkrptwk0byufb44fkiwvxm8mqar FOREIGN KEY (feature_id)
        REFERENCES putholi.tpuo_feature_management (feature_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
ALTER TABLE IF EXISTS putholi.tpuo_approverlevels OWNER to postgres;

-- SEQUENCE: putholi.tpuo_approverlevels_approver_id_seq

-- DROP SEQUENCE IF EXISTS putholi.tpuo_approverlevels_approver_id_seq;

CREATE SEQUENCE IF NOT EXISTS putholi.tpuo_approverlevels_approver_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.tpuo_approverlevels_approver_id_seq OWNER TO postgres;

-- SEQUENCE: putholi.tpuo_approverlevels_level_id_seq

-- DROP SEQUENCE IF EXISTS putholi.tpuo_approverlevels_level_id_seq;

CREATE SEQUENCE IF NOT EXISTS putholi.tpuo_approverlevels_level_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.tpuo_approverlevels_level_id_seq  OWNER TO postgres;

-- Table: putholi.tpuo_attachments

-- DROP TABLE IF EXISTS putholi.tpuo_attachments;

CREATE TABLE IF NOT EXISTS putholi.tpuo_attachments
(
    attachment_id bigint NOT NULL,
    created_date timestamp without time zone,
    file_data oid NOT NULL,
    file_name character varying(100)  NOT NULL,
    file_size bigint,
    file_type character varying(10)  NOT NULL,
    requirement_id bigint,
    updated_date timestamp without time zone,
    upload_for character varying(2),
    invoice_id bigint,
    quotation_id bigint,
    school_info_id bigint,
    CONSTRAINT tpuo_attachments_pkey PRIMARY KEY (attachment_id)
);
ALTER TABLE IF EXISTS putholi.tpuo_attachments OWNER to postgres;

-- SEQUENCE: putholi.tpuo_attachments_id_seq

-- DROP SEQUENCE IF EXISTS putholi.tpuo_attachments_id_seq;

CREATE SEQUENCE IF NOT EXISTS putholi.tpuo_attachments_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.tpuo_attachments_id_seq OWNER TO postgres;


-- Table: putholi.tpuo_consolidate_ref

-- DROP TABLE IF EXISTS putholi.tpuo_consolidate_ref;

CREATE TABLE IF NOT EXISTS putholi.tpuo_consolidate_ref
(
    consolidate_id bigint NOT NULL,
    created_by character varying(25)  NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(25) ,
    last_modified_date timestamp without time zone,
    no_of_requirements bigint NOT NULL,
    status character varying(6)  NOT NULL,
    school_info_id bigint,
    active character varying(2)  NOT NULL,
    CONSTRAINT tpuo_consolidate_ref_pkey PRIMARY KEY (consolidate_id),
    CONSTRAINT fkonw690424fgjdg1c29iovy0p0 FOREIGN KEY (school_info_id)
        REFERENCES putholi.tpuo_schoolinfo (school_info_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
ALTER TABLE IF EXISTS putholi.tpuo_consolidate_ref OWNER to postgres;

-- SEQUENCE: putholi.tpuo_consolidate_ref_consolidate_id_req

-- DROP SEQUENCE IF EXISTS putholi.tpuo_consolidate_ref_consolidate_id_req;

CREATE SEQUENCE IF NOT EXISTS putholi.tpuo_consolidate_ref_consolidate_id_req
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.tpuo_consolidate_ref_consolidate_id_req OWNER TO postgres;


-- Table: putholi.tpuo_requirements_info

-- DROP TABLE IF EXISTS putholi.tpuo_requirements_info;

CREATE TABLE IF NOT EXISTS putholi.tpuo_requirements_info
(
     requirement_id bigint NOT NULL,
    asset_name character varying(25)  NOT NULL,
    asset_type character varying(5)  NOT NULL,
    comments character varying(255)  NOT NULL,
    created_by character varying(25)  NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(25) ,
    last_modified_date timestamp without time zone,
    quantity bigint NOT NULL,
    req_status character varying(6)  NOT NULL,
    requirement_type character varying(25)  NOT NULL,
    consolidate_id bigint NOT NULL,
    time_period character varying(6) ,
    active character varying(2)  NOT NULL,
    CONSTRAINT tpuo_requirements_info_pkey PRIMARY KEY (requirement_id),
    CONSTRAINT fk6vmyicqcorrnrg1sg9fx0yu81 FOREIGN KEY (consolidate_id)
        REFERENCES putholi.tpuo_consolidate_ref (consolidate_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
ALTER TABLE IF EXISTS putholi.tpuo_requirements_info OWNER to postgres;

-- SEQUENCE: putholi.tpuo_requirements_info_requirement_id_seq

-- DROP SEQUENCE IF EXISTS putholi.tpuo_requirements_info_requirement_id_seq;

CREATE SEQUENCE IF NOT EXISTS putholi.tpuo_requirements_info_requirement_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.tpuo_requirements_info_requirement_id_seq OWNER TO postgres;

-- Table: putholi.tpuo_completion_of_requirements

-- DROP TABLE IF EXISTS putholi.tpuo_completion_of_requirements;

CREATE TABLE IF NOT EXISTS putholi.tpuo_completion_of_requirements
(
    completion_id bigint NOT NULL,
    completion_of_payment character varying(6) NOT NULL,
    created_by character varying(25)  NOT NULL,
    created_date timestamp without time zone NOT NULL,
    date_of_completion timestamp without time zone NOT NULL,
    remarks character varying(255)  NOT NULL,
    requirement_id bigint NOT NULL,
    CONSTRAINT tpuo_completion_of_requirements_pkey PRIMARY KEY (completion_id),
    CONSTRAINT uk_j0bit6m4k9vin0ou6v7pyvn6o UNIQUE (requirement_id),
    CONSTRAINT fkoh9bfy3q3iflucsakmkx84wkb FOREIGN KEY (requirement_id)
        REFERENCES putholi.tpuo_requirements_info (requirement_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
ALTER TABLE IF EXISTS putholi.tpuo_completion_of_requirements OWNER to postgres;

-- SEQUENCE: putholi.tpuo_completion_of_requirements_completion_id_seq

-- DROP SEQUENCE IF EXISTS putholi.tpuo_completion_of_requirements_completion_id_seq;

CREATE SEQUENCE IF NOT EXISTS putholi.tpuo_completion_of_requirements_completion_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.tpuo_completion_of_requirements_completion_id_seq OWNER TO postgres;




-- Table: putholi.tpuo_donorinfo

-- DROP TABLE IF EXISTS putholi.tpuo_donorinfo;

CREATE TABLE IF NOT EXISTS putholi.tpuo_donorinfo
(
    donor_id bigint NOT NULL,
    active character varying(1) ,
    address character varying(250) ,
    branches character varying(3) ,
    city character varying(255) ,
    country character varying(3) ,
    created_by character varying(25)  NOT NULL,
    created_date timestamp without time zone NOT NULL,
    designation character varying(25) ,
    email_id character varying(50) ,
    entity_type character varying(3) ,
    first_name character varying(250) ,
    last_modified_by character varying(25) ,
    last_modified_date timestamp without time zone,
    last_name character varying(50) ,
    money_transfer character varying(3) ,
    organization_address character varying(250) ,
    organization_name character varying(25),
    organization_type character varying(3) ,
    phone_number character varying(25) ,
    regn_no character varying(25) ,
    password character varying(60) ,
    user_type character varying(255) ,
    CONSTRAINT tpuo_donorinfo_pkey PRIMARY KEY (donor_id),
    CONSTRAINT uk_ror94phokrk8unqcoobx8ubhe UNIQUE (email_id)
);
ALTER TABLE IF EXISTS putholi.tpuo_donorinfo OWNER to postgres;

-- SEQUENCE: putholi.tpuo_donorinfo_donor_id_seq

-- DROP SEQUENCE IF EXISTS putholi.tpuo_donorinfo_donor_id_seq;

CREATE SEQUENCE IF NOT EXISTS putholi.tpuo_donorinfo_donor_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.tpuo_donorinfo_donor_id_seq OWNER TO postgres;

-- SEQUENCE: putholi.tpuo_donor_individual_id_seq

-- DROP SEQUENCE IF EXISTS putholi.tpuo_donor_individual_id_seq;

CREATE SEQUENCE IF NOT EXISTS putholi.tpuo_donor_individual_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.tpuo_donor_individual_id_seq  OWNER TO postgres;


-- Table: putholi.tpuo_invoice_info

-- DROP TABLE IF EXISTS putholi.tpuo_invoice_info;

CREATE TABLE IF NOT EXISTS putholi.tpuo_invoice_info
(
    invoice_id bigint NOT NULL,
    account_number bigint NOT NULL,
    account_type character varying(2) NOT NULL,
    address character varying(255)  NOT NULL,
    bank_name character varying(5)  NOT NULL,
    city character varying(50) NOT NULL,
    comments character varying(255) NOT NULL,
    company_name character varying(50) NOT NULL,
    created_by character varying(25)NOT NULL,
    created_date timestamp without time zone NOT NULL,
    discount_details character varying(100) NOT NULL,
    ifsc_code character varying(15) NOT NULL,
    invoice_amount numeric(12,2) NOT NULL,
    invoice_date timestamp without time zone NOT NULL,
    invoice_prepared_by character varying(25) NOT NULL,
    invoice_shipping_cost numeric(12,2) NOT NULL,
    invoice_status character varying(6) NOT NULL,
    invoice_tax numeric(12,2) NOT NULL,
    invoice_unit_price numeric(12,2),
    item_description character varying(255) NOT NULL,
    last_modified_by character varying(25),
    last_modified_date timestamp without time zone,
    payment_mode character varying(4) NOT NULL,
    phone_number bigint NOT NULL,
    pincode bigint NOT NULL,
    quantity bigint NOT NULL,
    quoted_amount numeric(12,2) NOT NULL,
    quoted_shipping_cost numeric(12,2) NOT NULL,
    quoted_tax numeric(12,2) NOT NULL,
    quoted_unit_price numeric(12,2) NOT NULL,
    street character varying(50) NOT NULL,
    vendor_code bigint NOT NULL,
    work_status character varying(2) NOT NULL,
    requirement_id bigint NOT NULL,
    CONSTRAINT tpuo_invoice_info_pkey PRIMARY KEY (invoice_id),
    CONSTRAINT fk54cdtf00lo4xa4qxq9k6oqciq FOREIGN KEY (requirement_id)
        REFERENCES putholi.tpuo_requirements_info (requirement_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
ALTER TABLE IF EXISTS putholi.tpuo_invoice_info OWNER to postgres;

-- SEQUENCE: putholi.tpuo_invoice_info_invoice_id_seq

-- DROP SEQUENCE IF EXISTS putholi.tpuo_invoice_info_invoice_id_seq;

CREATE SEQUENCE IF NOT EXISTS putholi.tpuo_invoice_info_invoice_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.tpuo_invoice_info_invoice_id_seq OWNER TO postgres;

-- Table: putholi.tpuo_invoice_payment_details

-- DROP TABLE IF EXISTS putholi.tpuo_invoice_payment_details;

CREATE TABLE IF NOT EXISTS putholi.tpuo_invoice_payment_details
(
    invoice_payment_id bigint NOT NULL,
    account_holder_name character varying(255) ,
    ammount_collected_by character varying(255),
    bank_name character varying(50)  NOT NULL,
    cheque_number character varying(50) ,
    invoice_id bigint NOT NULL,
    paid_amount numeric(10,2) NOT NULL,
    payee character varying(50) ,
    payment_date timestamp without time zone NOT NULL,
    payment_from character varying(50),
    payment_method character varying(25)  NOT NULL,
    payment_to character varying(50) ,
    reference_number character varying(255) ,
    CONSTRAINT tpuo_invoice_payment_details_pkey PRIMARY KEY (invoice_payment_id)
);
ALTER TABLE IF EXISTS putholi.tpuo_invoice_payment_details OWNER to postgres;

-- SEQUENCE: putholi.tpuo_invoice_payment_id_seq

-- DROP SEQUENCE IF EXISTS putholi.tpuo_invoice_payment_id_seq;

CREATE SEQUENCE IF NOT EXISTS putholi.tpuo_invoice_payment_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.tpuo_invoice_payment_id_seq OWNER TO postgres;

-- Table: putholi.tpuo_payment_instruction

-- DROP TABLE IF EXISTS putholi.tpuo_payment_instruction;

CREATE TABLE IF NOT EXISTS putholi.tpuo_payment_instruction
(
    payment_id character varying(25)  NOT NULL,
    amount_paid numeric(10,2) NOT NULL,
    consolidate_id bigint NOT NULL,
    instruction_type character varying(10)  NOT NULL,
    order_id character varying(25) NOT NULL,
    payment_currency character varying(3)  NOT NULL,
    payment_date timestamp without time zone NOT NULL,
    payment_method character varying(4)  NOT NULL,
    status character varying(50)  NOT NULL,
    user_id character varying(25)  NOT NULL,
    web_url character varying(255)  NOT NULL,
    CONSTRAINT tpuo_payment_instruction_pkey PRIMARY KEY (payment_id)
);
ALTER TABLE IF EXISTS putholi.tpuo_payment_instruction OWNER to postgres;

-- SEQUENCE: putholi.tpuo_payment_instruction_id_seq

-- DROP SEQUENCE IF EXISTS putholi.tpuo_payment_instruction_id_seq;

CREATE SEQUENCE IF NOT EXISTS putholi.tpuo_payment_instruction_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.tpuo_payment_instruction_id_seq OWNER TO postgres;

-- SEQUENCE: putholi.tpuo_payment_instruction_payment_id_seq

-- DROP SEQUENCE IF EXISTS putholi.tpuo_payment_instruction_payment_id_seq;

CREATE SEQUENCE IF NOT EXISTS putholi.tpuo_payment_instruction_payment_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.tpuo_payment_instruction_payment_id_seq OWNER TO postgres;

-- SEQUENCE: putholi.tpuo_payment_instruction_payment_num_seq

-- DROP SEQUENCE IF EXISTS putholi.tpuo_payment_instruction_payment_num_seq;

CREATE SEQUENCE IF NOT EXISTS putholi.tpuo_payment_instruction_payment_num_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.tpuo_payment_instruction_payment_num_seq OWNER TO postgres;

-- Table: putholi.tpuo_project_account_book

-- DROP TABLE IF EXISTS putholi.tpuo_project_account_book;

CREATE TABLE IF NOT EXISTS putholi.tpuo_project_account_book
(
   project_inc_exp_id bigint NOT NULL,
    amount numeric(12,2) NOT NULL,
    balance_amount numeric(25,2),
    created_by character varying(25)  NOT NULL,
    created_date timestamp without time zone NOT NULL,
    description character varying(500)  NOT NULL,
    donor_id character varying(25) ,
    fee_type character varying(3)  NOT NULL,
    payment_id character varying(255) ,
    project_id bigint NOT NULL,
    receipt_id character varying(25) ,
    remarks character varying(255) ,
    bank_charge numeric(12,2),
    CONSTRAINT tpuo_project_account_book_pkey PRIMARY KEY (project_inc_exp_id)
);
ALTER TABLE IF EXISTS putholi.tpuo_project_account_book OWNER to postgres;

-- SEQUENCE: putholi.tpuo_project_account_book_project_id_seq

-- DROP SEQUENCE IF EXISTS putholi.tpuo_project_account_book_project_id_seq;

CREATE SEQUENCE IF NOT EXISTS putholi.tpuo_project_account_book_project_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.tpuo_project_account_book_project_id_seq OWNER TO postgres;
    
    
-- SEQUENCE: putholi.tpuo_project_account_book_project_inc_exp_id_seq

-- DROP SEQUENCE IF EXISTS putholi.tpuo_project_account_book_project_inc_exp_id_seq;

CREATE SEQUENCE IF NOT EXISTS putholi.tpuo_project_account_book_project_inc_exp_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.tpuo_project_account_book_project_inc_exp_id_seq OWNER TO postgres;

-- Table: putholi.tpuo_quotation_info

-- DROP TABLE IF EXISTS putholi.tpuo_quotation_info;

CREATE TABLE IF NOT EXISTS putholi.tpuo_quotation_info
(
    quotation_id bigint NOT NULL,
    address character varying(255)  NOT NULL,
    city character varying(50)  NOT NULL,
    comments character varying(255) NOT NULL,
    company_name character varying(50)  NOT NULL,
    created_by character varying(25)  NOT NULL,
    created_date timestamp without time zone NOT NULL,
    discount_details character varying(100)  NOT NULL,
    item_description character varying(255)  NOT NULL,
    last_modified_by character varying(25) ,
    last_modified_date timestamp without time zone,
    phone_number bigint NOT NULL,
    pincode bigint NOT NULL,
    quantity bigint NOT NULL,
    quotate_status character varying(6)  NOT NULL,
    quotation_date timestamp without time zone NOT NULL,
    quotation_prepared_by character varying(255) NOT NULL,
    quotation_valid_date timestamp without time zone NOT NULL,
    shipping_cost numeric(12,2) NOT NULL,
    street character varying(50)  NOT NULL,
    tax numeric(12,2) NOT NULL,
    total_amount numeric(12,2) NOT NULL,
    unit_price numeric(12,2) NOT NULL,
    warranty character varying(5) NOT NULL,
    requirement_id bigint NOT NULL,
    CONSTRAINT tpuo_quotation_info_pkey PRIMARY KEY (quotation_id),
    CONSTRAINT fksfdw4cnu3trate3rtsmbaptlq FOREIGN KEY (requirement_id)
        REFERENCES putholi.tpuo_requirements_info (requirement_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
ALTER TABLE IF EXISTS putholi.tpuo_quotation_info OWNER to postgres;

-- SEQUENCE: putholi.tpuo_quotation_info_quotation_id_seq

-- DROP SEQUENCE IF EXISTS putholi.tpuo_quotation_info_quotation_id_seq;

CREATE SEQUENCE IF NOT EXISTS putholi.tpuo_quotation_info_quotation_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.tpuo_quotation_info_quotation_id_seq OWNER TO postgres;



-- Table: putholi.tpuo_school_approval_history_details

-- DROP TABLE IF EXISTS putholi.tpuo_school_approval_history_details;

CREATE TABLE IF NOT EXISTS putholi.tpuo_school_approval_history_details
(
    approval_his_id bigint NOT NULL,
    action_by character varying(25)  NOT NULL,
    action_date timestamp without time zone,
    quotation_id bigint,
    remarks character varying(512) ,
    requirement_id bigint,
    action_role character varying(10)  NOT NULL,
    school_id bigint,
    status character varying(6)  NOT NULL,
    invoice_id bigint,
    type character varying(25),
    consolidate_id bigint,
    CONSTRAINT tpuo_school_approval_history_details_pkey PRIMARY KEY (approval_his_id)
);
ALTER TABLE IF EXISTS putholi.tpuo_school_approval_history_details OWNER to postgres;

-- SEQUENCE: putholi.tpuo_school_approval_history_details_approval_his_id_seq

-- DROP SEQUENCE IF EXISTS putholi.tpuo_school_approval_history_details_approval_his_id_seq;

CREATE SEQUENCE IF NOT EXISTS putholi.tpuo_school_approval_history_details_approval_his_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.tpuo_school_approval_history_details_approval_his_id_seq OWNER TO postgres;


-- SEQUENCE: putholi.tpuo_trust_member_account_book_trust_book_id_seq

-- DROP SEQUENCE putholi.tpuo_trust_member_account_book_trust_book_id_seq;

CREATE SEQUENCE putholi.tpuo_trust_member_account_book_trust_book_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.tpuo_trust_member_account_book_trust_book_id_seq
    OWNER TO postgres;



-- Table: putholi.tpuo_trust_member_account_book

-- DROP TABLE putholi.tpuo_trust_member_account_book;

CREATE TABLE putholi.tpuo_trust_member_account_book
(
   trust_book_id bigint NOT NULL,
    amount numeric(12,2) NOT NULL,
    balance_amount numeric(25,2),
    created_by character varying(25) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    description character varying(500) NOT NULL,
    donor_id character varying(25),
    fee_type character varying(3) NOT NULL,
    payment_date timestamp without time zone NOT NULL,
    payment_id character varying(25),
    remarks character varying(500),
    bank_charge numeric(12,2),
    CONSTRAINT tpuo_trust_member_account_book_pkey PRIMARY KEY (trust_book_id)
);

ALTER TABLE putholi.tpuo_trust_member_account_book OWNER to postgres;



-- SEQUENCE: putholi.audit_seq_id

-- DROP SEQUENCE putholi.audit_seq_id;

CREATE SEQUENCE putholi.audit_seq_id
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.audit_seq_id
    OWNER TO postgres;

-- Table: putholi.tsys_audit_details

-- DROP TABLE putholi.tsys_audit_details;

CREATE TABLE putholi.tsys_audit_details
(
    audit_id bigint NOT NULL,
    audit_desc character varying(100),
    audit_type character varying(10)NOT NULL,
    audit_value text,
    created_by character varying(25),
    created_date timestamp without time zone,
    function_code character varying(6)NOT NULL,
    CONSTRAINT tsys_audit_details_pkey PRIMARY KEY (audit_id)
);

ALTER TABLE putholi.tsys_audit_details OWNER to postgres;


-- SEQUENCE: putholi.deo_master_code_details_seq

-- DROP SEQUENCE putholi.deo_master_code_details_seq;

CREATE SEQUENCE putholi.deo_master_code_details_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.deo_master_code_details_seq
    OWNER TO postgres;


-- Table: putholi.tsys_deo_master_code_details

-- DROP TABLE putholi.tsys_deo_master_code_details;

CREATE TABLE putholi.tsys_deo_master_code_details
(
    id bigint NOT NULL,
    active character varying(3) NOT NULL,
    city character varying(255)NOT NULL,
    created_by character varying(20) NOT NULL,
    created_date timestamp without time zone,
    deo_email character varying(255) NOT NULL,
    deo_name character varying(25) NOT NULL,
    deo_office_address character varying(255) NOT NULL,
    district character varying(20) NOT NULL,
    phone_number character varying(25) NOT NULL,
    updated_by character varying(20),
    updated_date timestamp without time zone,
    town character varying(255) NOT NULL,
    CONSTRAINT tsys_deo_master_code_details_pkey PRIMARY KEY (id)
);

-- Table: putholi.tsys_deo_master_codetype_details

-- DROP TABLE putholi.tsys_deo_master_codetype_details;

CREATE TABLE putholi.tsys_deo_master_codetype_details
(
    id bigint NOT NULL,
    active character varying(3) NOT NULL,
    city character varying(255) NOT NULL,
    created_date timestamp without time zone,
    deo_email character varying(255) NOT NULL,
    deo_name character varying(25) NOT NULL,
    deo_office_address character varying(255) NOT NULL,
    district character varying(20)NOT NULL,
    phone_number character varying(25) NOT NULL,
    updated_by character varying(20),
    updated_date timestamp without time zone,
    CONSTRAINT tsys_deo_master_codetype_details_pkey PRIMARY KEY (id)
);

ALTER TABLE putholi.tsys_deo_master_codetype_details
    OWNER to postgres;


-- SEQUENCE: putholi.master_code_details_seq

-- DROP SEQUENCE putholi.master_code_details_seq;

CREATE SEQUENCE putholi.master_code_details_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.master_code_details_seq
    OWNER TO postgres;

-- Table: putholi.tsys_master_code_details

-- DROP TABLE putholi.tsys_master_code_details;

CREATE TABLE putholi.tsys_master_code_details
(
    id bigint NOT NULL,
    active character varying(1) NOT NULL,
    code character varying(10) NOT NULL,
    code_type character varying(5) NOT NULL,
    created_by character varying(20) NOT NULL,
    created_date timestamp without time zone,
    description character varying(150) NOT NULL,
    description_other character varying(150) NOT NULL,
    updated_by character varying(20),
    updated_date timestamp without time zone,
    CONSTRAINT tsys_master_code_details_pkey PRIMARY KEY (id),
    CONSTRAINT ukctkh6vx6iroxg58j5xjef0i5o UNIQUE (code, code_type)
);

ALTER TABLE putholi.tsys_master_code_details
    OWNER to postgres;

-- SEQUENCE: putholi.master_codetype_details_seq

-- DROP SEQUENCE putholi.master_codetype_details_seq;

CREATE SEQUENCE putholi.master_codetype_details_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.master_codetype_details_seq OWNER TO postgres;


-- Table: putholi.tsys_master_codetype_details

-- DROP TABLE putholi.tsys_master_codetype_details;

CREATE TABLE putholi.tsys_master_codetype_details
(
    id bigint NOT NULL,
    active character varying(1) NOT NULL,
    code_type character varying(5) NOT NULL,
    created_by character varying(25) NOT NULL,
    created_date timestamp without time zone,
    description character varying(150) NOT NULL,
    description_other character varying(150) NOT NULL,
    updated_by character varying(20),
    updated_date timestamp without time zone,
    CONSTRAINT tsys_master_codetype_details_pkey PRIMARY KEY (id),
    CONSTRAINT uk_f9p6lxr5do3ytmiue4bymxtdn UNIQUE (code_type)
);

ALTER TABLE putholi.tsys_master_codetype_details OWNER to postgres;


-- SEQUENCE: putholi.tsys_menu_mgmt_seq

-- DROP SEQUENCE putholi.tsys_menu_mgmt_seq;

CREATE SEQUENCE putholi.tsys_menu_mgmt_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.tsys_menu_mgmt_seq OWNER TO postgres;

-- Table: putholi.tsys_menu_mgmt

-- DROP TABLE putholi.tsys_menu_mgmt;

CREATE TABLE putholi.tsys_menu_mgmt
(
    menu_id bigint NOT NULL,
    created_by character varying(25) NOT NULL,
    created_date timestamp without time zone,
    menu_code character varying(7) NOT NULL,
    menu_name character varying(100) NOT NULL,
    menu_name_other character varying(100),
    module_code character varying(10) NOT NULL,
    status character varying(1) NOT NULL,
    updated_by character varying(25),
    updated_date timestamp without time zone,
    CONSTRAINT tsys_menu_mgmt_pkey PRIMARY KEY (menu_id),
    CONSTRAINT uk_3h7l8epjwax6ll32vkcpo4yem UNIQUE (menu_code)
);

ALTER TABLE putholi.tsys_menu_mgmt OWNER to postgres;

-- SEQUENCE: putholi.tsys_module_mgmt_seq

-- DROP SEQUENCE putholi.tsys_module_mgmt_seq;

CREATE SEQUENCE putholi.tsys_module_mgmt_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.tsys_module_mgmt_seq OWNER TO postgres;


-- Table: putholi.tsys_module_mgmt

-- DROP TABLE putholi.tsys_module_mgmt;

CREATE TABLE putholi.tsys_module_mgmt
(
    module_id bigint NOT NULL,
    active character varying(1) NOT NULL,
    created_by character varying(25)  NOT NULL,
    created_date timestamp without time zone NOT NULL,
    module_code character varying(10) NOT NULL,
    module_desc character varying(100) NOT NULL,
    module_desc_other character varying(100),
    orderno integer,
    updated_date timestamp without time zone,
    updated_by character varying(25),
    CONSTRAINT tsys_module_mgmt_pkey PRIMARY KEY (module_id)
);

ALTER TABLE putholi.tsys_module_mgmt OWNER to postgres;


-- SEQUENCE: putholi.tmhb_pwd_reg_recvry_ques_seq

-- DROP SEQUENCE putholi.tmhb_pwd_reg_recvry_ques_seq;

CREATE SEQUENCE putholi.tmhb_pwd_reg_recvry_ques_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.tmhb_pwd_reg_recvry_ques_seq
    OWNER TO postgres;

-- Table: putholi.tsys_password_recovery_ques

-- DROP TABLE putholi.tsys_password_recovery_ques;

CREATE TABLE putholi.tsys_password_recovery_ques
(
    quesid bigint NOT NULL,
    code character varying(5) NOT NULL,
    created_by character varying(25) NOT NULL,
    created_date timestamp without time zone,
    description character varying(512) NOT NULL,
    description_other character varying(512) NOT NULL,
    status character varying(1) NOT NULL,
    updated_by character varying(25),
    updated_date timestamp without time zone,
    CONSTRAINT tsys_password_recovery_ques_pkey PRIMARY KEY (quesid)
);
ALTER TABLE putholi.tsys_password_recovery_ques OWNER to postgres;


-- SEQUENCE: putholi.tpuo_product_config_company_id_seq

-- DROP SEQUENCE putholi.tpuo_product_config_company_id_seq;

CREATE SEQUENCE putholi.tpuo_product_config_company_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.tpuo_product_config_company_id_seq
    OWNER TO postgres;


-- SEQUENCE: putholi.tsys_role_menu_seq

-- DROP SEQUENCE putholi.tsys_role_menu_seq;

CREATE SEQUENCE putholi.tsys_role_menu_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.tsys_role_menu_seq
    OWNER TO postgres;

-- Table: putholi.tsys_role_menu_privilege

-- DROP TABLE putholi.tsys_role_menu_privilege;

CREATE TABLE putholi.tsys_role_menu_privilege
(
    role_menu_id bigint NOT NULL,
    created_by character varying(25) NOT NULL,
    created_date timestamp without time zone,
    menu_id bigint,
    role_id bigint,
    updated_by character varying(25),
    updated_date timestamp without time zone,
    CONSTRAINT tsys_role_menu_privilege_pkey PRIMARY KEY (role_menu_id)
);

ALTER TABLE putholi.tsys_role_menu_privilege
    OWNER to postgres;


-- Table: putholi.tsys_product_config

-- DROP TABLE putholi.tsys_product_config;

CREATE TABLE putholi.tsys_product_config
(
    company_name character varying(255) NOT NULL,
    city character varying(100) NOT NULL,
    company_address character varying(255) NOT NULL,
    company_code character varying(25) NOT NULL,
    company_email character varying(255) NOT NULL,
    company_logo character varying(255),
    company_phone character varying(15) NOT NULL,
    company_phone_2 character varying(15) NOT NULL,
    cpmpany_regno character varying(205) NOT NULL,
    company_slogan character varying(100) NOT NULL,
    created_by character varying(25) NOT NULL,
    created_date timestamp without time zone,
    email_id character varying(255) NOT NULL,
    fax character varying(255) NOT NULL,
    last_modified_by character varying(25),
    last_modified_date timestamp without time zone,
    postal_code bigint NOT NULL,
    primary_contact_name character varying(255) NOT NULL,
    primary_contact_no character varying(15) NOT NULL,
    province character varying(100) NOT NULL,
    tax_identification_no character varying(20) NOT NULL,
    timezone timestamp without time zone NOT NULL,
    website character varying(255) NOT NULL,
    CONSTRAINT tsys_product_config_pkey PRIMARY KEY (company_name)
);

ALTER TABLE putholi.tsys_product_config OWNER to postgres;


-- SEQUENCE: putholi.tsys_role_mgmt_seq

-- DROP SEQUENCE putholi.tsys_role_mgmt_seq;

CREATE SEQUENCE putholi.tsys_role_mgmt_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.tsys_role_mgmt_seq
    OWNER TO postgres;

-- Table: putholi.tsys_role_mgmt

-- DROP TABLE putholi.tsys_role_mgmt;

CREATE TABLE putholi.tsys_role_mgmt
(
    role_id bigint NOT NULL,
    created_by character varying(25)NOT NULL,
    created_date timestamp without time zone,
    role_code character varying(10) NOT NULL,
    role_desc character varying(256) NOT NULL,
    role_desc_other character varying(256),
    status character varying(1) NOT NULL,
    updated_by character varying(25),
    updated_date timestamp without time zone,
    CONSTRAINT tsys_role_mgmt_pkey PRIMARY KEY (role_id),
    CONSTRAINT uk_mq96fxkn6tdrapt5ej2bk55x UNIQUE (role_code)
);

ALTER TABLE putholi.tsys_role_mgmt OWNER to postgres;


-- Table: putholi.tsys_user_recovery_answers

-- DROP TABLE putholi.tsys_user_recovery_answers;

CREATE TABLE putholi.tsys_user_recovery_answers
(
    username character varying(25) NOT NULL,
    answer character varying(120) NOT NULL,
    code character varying(10) NOT NULL,
    CONSTRAINT tsys_user_recovery_answers_pkey PRIMARY KEY (username)
);

ALTER TABLE putholi.tsys_user_recovery_answers OWNER to postgres;


-- Table: putholi.user_attachment

-- DROP TABLE putholi.user_attachment;

CREATE TABLE putholi.user_attachment
(
    id bigint NOT NULL,
    created_date timestamp without time zone,
    file_data oid NOT NULL,
    file_type character varying(10) NOT NULL,
    updated_date timestamp without time zone,
    upload_for character varying(2),
    username character varying(25),
    CONSTRAINT user_attachment_pkey PRIMARY KEY (id),
    CONSTRAINT ukm8g8uxsjkt3w99u89lxhr45kv UNIQUE (username, upload_for)
);

ALTER TABLE putholi.user_attachment
    OWNER to postgres;

-- SEQUENCE: putholi.user_attachment_id_seq

-- DROP SEQUENCE putholi.user_attachment_id_seq;

CREATE SEQUENCE putholi.user_attachment_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.user_attachment_id_seq
    OWNER TO postgres;

-- Table: putholi.user_login

-- DROP TABLE putholi.user_login;

CREATE TABLE putholi.user_login
(
    username character varying(255) NOT NULL,
    account_enabled boolean NOT NULL,
    account_expiry_date timestamp without time zone,
    account_non_locked boolean NOT NULL,
    change_password_required boolean NOT NULL,
    credentials_expiry_date timestamp without time zone,
    password character varying(60),
    roles character varying(25),
    CONSTRAINT user_login_pkey PRIMARY KEY (username)
);

ALTER TABLE putholi.user_login OWNER to postgres;

-- SEQUENCE: putholi.user_login_history_seq

-- DROP SEQUENCE putholi.user_login_history_seq;

CREATE SEQUENCE putholi.user_login_history_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.user_login_history_seq
    OWNER TO postgres;


-- Table: putholi.user_login_attempts

-- DROP TABLE putholi.user_login_attempts;

CREATE TABLE putholi.user_login_attempts
(
    username character varying(25) NOT NULL,
    attempts integer,
    last_modified timestamp without time zone,
    CONSTRAINT user_login_attempts_pkey PRIMARY KEY (username)
);

ALTER TABLE putholi.user_login_attempts OWNER to postgres;

-- Table: putholi.user_login_history

-- DROP TABLE putholi.user_login_history;

CREATE TABLE putholi.user_login_history
(
    login_history_id bigint NOT NULL,
    login_ip character varying(15),
    login_time timestamp without time zone NOT NULL,
    user_agent character varying(150),
    username character varying(255),
    CONSTRAINT user_login_history_pkey PRIMARY KEY (login_history_id),
    CONSTRAINT fkj7i0fkc08rns9oiup552hedv4 FOREIGN KEY (username)
        REFERENCES putholi.user_login (username) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE putholi.user_login_history OWNER to postgres;


-- Table: putholi.user_registration_details

-- DROP TABLE putholi.user_registration_details;

CREATE TABLE putholi.user_registration_details
(
     username character varying(25)  NOT NULL,
    active character varying(1)  NOT NULL,
    address_line1 character varying(255)  NOT NULL,
    address_line2 character varying(255) ,
    city character varying(150)  NOT NULL,
    country character varying(3)  NOT NULL,
    created_date timestamp without time zone,
    district character varying(150)  NOT NULL,
    email_id character varying(50)  NOT NULL,
    first_name character varying(250)  NOT NULL,
    gender character varying(1)  NOT NULL,
    identification_no character varying(12) ,
    last_name character varying(50)  NOT NULL,
    locality character varying(150) ,
    middle_name character varying(60) ,
    mobile_number character varying(25) ,
    pincode character varying(6)  NOT NULL,
    role character varying(10)  NOT NULL,
    state character varying(3)  NOT NULL,
    status character varying(6)  NOT NULL,
    updated_by character varying(25) ,
    updated_date timestamp without time zone,
    referred_by character varying(25) ,
    registration_fee character varying(255) ,
    select_role character varying(6) ,
    is_reviewed character varying(4) ,
    change_request_role character varying(10) ,
    CONSTRAINT user_registration_details_pkey PRIMARY KEY (username),
    CONSTRAINT uk_6jhw47pa293cy5k3cs0ckgso8 UNIQUE (email_id)
);

ALTER TABLE putholi.user_registration_details OWNER to postgres;


-- Table: putholi.verification_token

-- DROP TABLE putholi.verification_token;

CREATE TABLE putholi.verification_token
(
    id bigint NOT NULL,
    expiry_date timestamp without time zone,
    token character varying(100),
    username character varying(25)  NOT NULL,
    CONSTRAINT verification_token_pkey PRIMARY KEY (id),
    CONSTRAINT fkhwd8ki1panu3dskupq1yebfxd FOREIGN KEY (username)
        REFERENCES putholi.user_login (username) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE putholi.verification_token OWNER to postgres;

-- SEQUENCE: putholi.verification_token_id_seq

-- DROP SEQUENCE putholi.verification_token_id_seq;

CREATE SEQUENCE putholi.verification_token_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.verification_token_id_seq
    OWNER TO postgres;

-- Table: putholi.tpuo_trust_account_book

-- DROP TABLE putholi.tpuo_trust_account_book;

CREATE TABLE putholi.tpuo_trust_account_book
(
    trust_acct_id bigint NOT NULL,
    amount numeric(12,2) NOT NULL,
    balance_amount numeric(25,2),
    created_by character varying(25),
    created_date timestamp without time zone NOT NULL,
    description character varying(500),
    donor_id character varying(25),
    fee_type character varying(3),
    payment_date timestamp without time zone NOT NULL,
    payment_id character varying(25),
    project_id bigint,
    receipt_id character varying(25),
    remarks character varying(500),
    bank_charge numeric(12,2),
    CONSTRAINT tpuo_trust_account_book_pkey PRIMARY KEY (trust_acct_id)
);

ALTER TABLE putholi.tpuo_trust_account_book
    OWNER to postgres;

-- SEQUENCE: putholi.tpuo_trust_account_book_trust_acct_id_seq

-- DROP SEQUENCE putholi.tpuo_trust_account_book_trust_acct_id_seq;

CREATE SEQUENCE putholi.tpuo_trust_account_book_trust_acct_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE putholi.tpuo_trust_account_book_trust_acct_id_seq
    OWNER TO postgres;



-- View: putholi.vpuo_consolidate_details

-- DROP VIEW putholi.vpuo_consolidate_details;

CREATE OR REPLACE VIEW putholi.vpuo_consolidate_details
 AS
 SELECT tc.consolidate_id,
    tc.no_of_requirements,
    ts.school_info_id,
    ts.school_name,
    ts.school_reg_no,
    ( SELECT m.description
           FROM putholi.tsys_master_code_details m
          WHERE m.code::text = ts.school_type::text AND m.code_type::text = 'STY'::text) AS school_type,
    tct.pri_num,
    ta.locality,
    tc.created_date,
    tc.status AS consolidate_status_code,
    ( SELECT m.description
           FROM putholi.tsys_master_code_details m
          WHERE m.code::text = tc.status::text AND m.code_type::text = 'STS'::text) AS status,
    tc.created_by,
    ts.volunteer_name,
    ts.school_status AS school_status_code,
    ( SELECT m.description
           FROM putholi.tsys_master_code_details m
          WHERE m.code::text = ts.school_status::text AND m.code_type::text = 'STS'::text) AS school_status,
    tc.active
   FROM putholi.tpuo_schoolinfo ts,
    putholi.tpuo_consolidate_ref tc,
    putholi.tpuo_address ta,
    putholi.tpuo_contacts tct
  WHERE ts.school_info_id = tc.school_info_id AND ts.school_info_id = tct.school_info_id AND ts.school_info_id = ta.school_info_id;

ALTER TABLE putholi.vpuo_consolidate_details
    OWNER TO postgres;



-- View: putholi.vpuo_requirement_details

-- DROP VIEW putholi.vpuo_requirement_details;

CREATE OR REPLACE VIEW putholi.vpuo_requirement_details
 AS
 SELECT tr.requirement_id,
    tr.created_by,
    ( SELECT m.description
           FROM putholi.tsys_master_code_details m
          WHERE m.code::text = tr.requirement_type::text AND m.code_type::text = 'REQTY'::text) AS requirement_type,
    tc.consolidate_id,
    ts.school_info_id,
    ts.school_reg_no,
    ts.school_name,
    ta.locality,
    ta.district,
    tr.req_status AS req_status_code,
    ( SELECT m.description
           FROM putholi.tsys_master_code_details m
          WHERE m.code::text = tr.req_status::text AND m.code_type::text = 'STS'::text) AS req_status,
    ts.volunteer_name,
    tc.status AS consolidate_status,
    tr.active,
    ( SELECT m.description
           FROM putholi.tsys_master_codetype_details m
          WHERE m.code_type::text = tr.asset_type::text AND (m.code_type::text = ANY (ARRAY['INF'::text, 'SPR'::text, 'OTH'::text]))) AS asset_type,
        CASE
            WHEN tr.asset_type::text = 'OTH'::text THEN tr.asset_name
            ELSE ( SELECT m.description
               FROM putholi.tsys_master_code_details m
              WHERE m.code::text = tr.asset_name::text AND (m.code_type::text = ANY (ARRAY['INF'::character varying, 'SPR'::character varying]::text[])))
        END AS asset_name
   FROM putholi.tpuo_schoolinfo ts,
    putholi.tpuo_consolidate_ref tc,
    putholi.tpuo_address ta,
    putholi.tpuo_requirements_info tr
  WHERE ts.school_info_id = tc.school_info_id AND tr.consolidate_id = tc.consolidate_id AND ts.school_info_id = ta.school_info_id;

ALTER TABLE putholi.vpuo_requirement_details
    OWNER TO postgres;


-- View: putholi.vpuo_school_details

 DROP VIEW putholi.vpuo_school_details;

CREATE OR REPLACE VIEW putholi.vpuo_school_details
 AS
 SELECT ts.school_info_id,
    ts.school_reg_no,
    ts.school_name,
    ( SELECT m.description
           FROM putholi.tsys_master_code_details m
          WHERE m.code::text = ts.school_type::text AND m.code_type::text = 'STY'::text) AS school_type,
    tc.pri_num,
    ts.school_status AS school_status_code,
    ( SELECT m.description
           FROM putholi.tsys_master_code_details m
          WHERE m.code::text = ts.school_status::text AND m.code_type::text = 'STS'::text) AS school_status,
    ts.created_by,
    ta.locality,
    ta.district,
    ts.active,
    ts.created_date
   FROM putholi.tpuo_schoolinfo ts,
    putholi.tpuo_contacts tc,
    putholi.tpuo_address ta
  WHERE ts.school_info_id = tc.school_info_id AND ts.school_info_id = ta.school_info_id;

ALTER TABLE putholi.vpuo_school_details
    OWNER TO postgres;


-- View: putholi.vpuo_user_mgmt

-- DROP VIEW putholi.vpuo_user_mgmt;

CREATE OR REPLACE VIEW putholi.vpuo_user_mgmt
 AS
 SELECT u.username,
    r.first_name,
    r.email_id,
    r.mobile_number,
    ( SELECT m.role_desc
           FROM putholi.tsys_role_mgmt m
          WHERE m.role_code::text = r.role::text) AS role,
    r.role AS role_code,
    r.change_request_role,
    r.created_date,
    ( SELECT m.description
           FROM putholi.tsys_master_code_details m
          WHERE m.code::text = r.status::text) AS status,
    r.status AS status_code,
    u.account_enabled,
    r.district,
    r.referred_by,
    r.updated_by
   FROM putholi.user_login u,
    putholi.user_registration_details r
  WHERE u.username::text = r.username::text;

ALTER TABLE putholi.vpuo_user_mgmt
    OWNER TO postgres;

-- View: putholi.vpuo_user_register

-- DROP VIEW putholi.vpuo_user_register;

CREATE OR REPLACE VIEW putholi.vpuo_user_register
 AS
 SELECT r.username,
    r.city,
    r.country,
    tm.amount,
    tm.created_date,
    tm.fee_type
   FROM putholi.user_registration_details r,
    putholi.tpuo_trust_account_book tm
  WHERE r.username::text = tm.donor_id::text;

ALTER TABLE putholi.vpuo_user_register
    OWNER TO postgres;

-- View: putholi.vpuo_donor_info

-- DROP VIEW putholi.vpuo_donor_info;

CREATE OR REPLACE VIEW putholi.vpuo_donor_info
 AS
 SELECT d.city,
    d.country,
    d.email_id,
    t.created_date,
    t.amount
   FROM putholi.tpuo_donorinfo d,
    putholi.tpuo_trust_account_book t
  WHERE d.email_id::text = t.donor_id::text AND t.fee_type::text = 'INC'::text;

ALTER TABLE putholi.vpuo_donor_info
    OWNER TO postgres;



--SUPER ADMIN REGISTER
INSERT INTO putholi.user_registration_details(
username, active, address_line1, address_line2, city, country, created_date, district, email_id, first_name, gender, identification_no, last_name, locality, middle_name, mobile_number, pincode, role, state, status, updated_by, updated_date)
VALUES ('superadmin', 'Y', '1-23-456', 'Railpet', 'Bapatla', 'IND', '2022-12-28 15:24:20.044', 'Bapatla', 'superadmin@grr.la', 'superadmin', 'M', '987654321234', 'a', 'Bapatla', null, 9876543210, 522101, 'SUADM', 'TN', 'APR', null, null);
--SUPER ADMIN LOGIN
INSERT INTO putholi.user_login(	username, account_enabled, account_expiry_date, account_non_locked, change_password_required, credentials_expiry_date, password, roles)
VALUES ('superadmin', true, '2038-01-28 15:39:20.04', true, false, '2038-01-28 15:39:20.04', '$2a$10$fqijZN1FGWoLdttpiuE.tOGik9MblCxdOAbBo4KxSGXHn38j5IgpC', 'SUADM');

--SUPER USER REGISTER
INSERT INTO putholi.user_registration_details(
username, active, address_line1, address_line2, city, country, created_date, district, email_id, first_name, gender, identification_no, last_name, locality, middle_name, mobile_number, pincode, role, state, status, updated_by, updated_date)
VALUES ('superuser', 'Y', '1-23-456', 'Railpet', 'Bapatla', 'IND', '2022-12-28 15:24:20.044', 'Bapatla', 'superuser@grr.la', 'superuser', 'M', '987654321234', 'a', 'Bapatla', null, 9876543210, 522101, 'SUUSR', 'TN', 'APR', null, null);

--SUPER USER LOGIN
INSERT INTO putholi.user_login(	username, account_enabled, account_expiry_date, account_non_locked, change_password_required, credentials_expiry_date, password, roles)
VALUES ('superuser', true, '2038-01-28 15:39:20.04', true, false, '2038-01-28 15:39:20.04', '$2a$10$fqijZN1FGWoLdttpiuE.tOGik9MblCxdOAbBo4KxSGXHn38j5IgpC', 'SUUSR');

--Default ADM Login
INSERT INTO putholi.user_registration_details(
username, active, address_line1, address_line2, city, country, created_date, district, email_id, first_name, gender, identification_no, last_name, locality, middle_name, mobile_number, pincode, role, state, status, updated_by, updated_date)
VALUES ('admin', 'Y', '1-23-456', 'Railpet', 'Bapatla', 'IND', '2022-12-28 15:24:20.044', 'Bapatla', 'admin@grr.la', 'admin', 'M', '987654321234', 'a', 'Bapatla', null, 9876543210, 522101, 'ADMIN', 'TN', 'APR', null, null);
	
INSERT INTO putholi.user_login(	username, account_enabled, account_expiry_date, account_non_locked, change_password_required, credentials_expiry_date, password, roles)
VALUES ('admin', true, '2037-12-28 15:39:20.04', true, false, '2037-12-28 15:39:20.04', '$2a$10$fqijZN1FGWoLdttpiuE.tOGik9MblCxdOAbBo4KxSGXHn38j5IgpC', 'ADMIN');

-- REVIEWER REGISTER
INSERT INTO putholi.user_registration_details(
username, active, address_line1, address_line2, city, country, created_date, district, email_id, first_name, gender, identification_no, last_name, locality, middle_name, mobile_number, pincode, role, state, status, updated_by, updated_date)
VALUES ('reviewer', 'Y', '1-23-456', 'Railpet', 'Bapatla', 'IND', '2022-12-28 15:24:20.044', 'Bapatla', 'reviewer@grr.la', 'reviewer', 'M', '987654321234', 'a', 'Bapatla', null, 9876543210, 522101, 'REVIEW', 'TN', 'APR', null, null);

-- REVIEWER LOGIN
INSERT INTO putholi.user_login(	username, account_enabled, account_expiry_date, account_non_locked, change_password_required, credentials_expiry_date, password, roles)
VALUES ('reviewer', true, '2038-01-28 15:39:20.04', true, false, '2038-01-28 15:39:20.04', '$2a$10$fqijZN1FGWoLdttpiuE.tOGik9MblCxdOAbBo4KxSGXHn38j5IgpC', 'REVIEW');

-- APPROVER REGISTER
INSERT INTO putholi.user_registration_details(
username, active, address_line1, address_line2, city, country, created_date, district, email_id, first_name, gender, identification_no, last_name, locality, middle_name, mobile_number, pincode, role, state, status, updated_by, updated_date)
VALUES ('approver', 'Y', '1-23-456', 'Railpet', 'Bapatla', 'IND', '2022-12-28 15:24:20.044', 'Bapatla', 'approver@grr.la', 'approver', 'M', '987654321234', 'a', 'Bapatla', null, 9876543210, 522101, 'APPRV', 'TN', 'APR', null, null);

-- APPROVER LOGIN
INSERT INTO putholi.user_login(	username, account_enabled, account_expiry_date, account_non_locked, change_password_required, credentials_expiry_date, password, roles)
VALUES ('approver', true, '2038-01-28 15:39:20.04', true, false, '2038-01-28 15:39:20.04', '$2a$10$fqijZN1FGWoLdttpiuE.tOGik9MblCxdOAbBo4KxSGXHn38j5IgpC', 'APPRV');

---alter scripts
alter table putholi.tpuo_trust_account_book add column payment_type varchar;

---alter script new
alter table putholi.tpuo_requirements_info add column req_status_description varchar(255);


ALTER TABLE putholi.tpuo_project_account_book
ADD COLUMN bank_ref_no VARCHAR(25),
ADD COLUMN tracking_id VARCHAR(25);

ALTER TABLE putholi.tpuo_trust_account_book
ADD COLUMN bank_ref_no VARCHAR(25),
ADD COLUMN tracking_id VARCHAR(25);

ALTER TABLE putholi.tpuo_trust_member_account_book
ADD COLUMN bank_ref_no VARCHAR(25),
ADD COLUMN tracking_id VARCHAR(25);

alter table putholi.tpuo_payment_instruction
ALTER COLUMN payment_method type VARCHAR(25),
Alter column payment_currency type varchar(10);

alter table putholi.tpuo_payment_instruction
ALTER COLUMN payment_method DROP NOT NULL,
Alter column payment_currency DROP NOT NULL,
ALTER COLUMN consolidate_id DROP NOT NULL,
ALTER COLUMN web_url DROP NOT NULL;

alter table putholi.tpuo_donorinfo add column order_id varchar(25) UNIQUE;