-- Nationality -- NTNLT // Country - CNTRY // Currency - CRNCY // Role Details - ROLE // Status Details - STS  // Marital Status - MRT // School Type - STY // Bank Details - BANK // Requirement Type - REQTY // Sports - SPR // Infrastructure - INF
  

	
INSERT INTO putholi.tsys_master_codetype_details VALUES (nextval('putholi.master_codetype_details_seq'), 'Y', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Nationality', 'Nationality', NULL, NULL);
INSERT INTO putholi.tsys_master_codetype_details VALUES (nextval('putholi.master_codetype_details_seq'), 'Y', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Country', 'Country', NULL, NULL);
INSERT INTO putholi.tsys_master_codetype_details VALUES (nextval('putholi.master_codetype_details_seq'), 'Y', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Currency', 'Currency', NULL, NULL);
INSERT INTO putholi.tsys_master_codetype_details VALUES (nextval('putholi.master_codetype_details_seq'), 'Y', 'ROLE', 'sysadmin', '2021-02-26 18:56:55.115', 'Role Details', 'Role Details', NULL, NULL);
INSERT INTO putholi.tsys_master_codetype_details VALUES (nextval('putholi.master_codetype_details_seq'), 'Y', 'STS', 'sysadmin', '2021-02-26 18:56:55.115', 'Status Details', 'Status Details', NULL, NULL);
INSERT INTO putholi.tsys_master_codetype_details VALUES (nextval('putholi.master_codetype_details_seq'), 'Y', 'MRT', 'sysadmin', '2021-02-26 18:56:55.115', 'Marital Status', 'Marital Status', NULL, NULL);
INSERT INTO putholi.tsys_master_codetype_details VALUES (nextval('putholi.master_codetype_details_seq'), 'Y', 'STY', 'sysadmin', '2022-07-04 20:24:52.643', 'School Type', 'School Type', NULL, NULL);
INSERT INTO putholi.tsys_master_codetype_details VALUES (nextval('putholi.master_codetype_details_seq'), 'Y', 'ORGN', 'sysadmin', '2023-01-20 12:24:52.643', 'Organization Type', 'Organization Type', NULL, NULL);
INSERT INTO putholi.tsys_master_codetype_details VALUES (nextval('putholi.master_codetype_details_seq'), 'Y', 'ENTY', 'sysadmin', '2023-01-20 12:24:52.643', 'Entity Type', 'Entity Type', NULL, NULL);
--Payment Details
INSERT INTO putholi.tsys_master_codetype_details VALUES (nextval('putholi.master_codetype_details_seq'), 'Y', 'PAYM', 'sysadmin', '2023-01-20 12:24:52.643', 'Payment', 'Payment', NULL, NULL);
--Account type
INSERT INTO putholi.tsys_master_codetype_details VALUES (nextval('putholi.master_codetype_details_seq'), 'Y', 'ACCT', 'sysadmin', '2023-01-20 12:24:52.643', 'Account Type', 'Account Type', NULL, NULL);
--Card details
INSERT INTO putholi.tsys_master_codetype_details VALUES (nextval('putholi.master_codetype_details_seq'), 'Y', 'CARD', 'sysadmin', '2023-01-20 12:24:52.643', 'Card Details', 'Card Details', NULL, NULL);

INSERT INTO putholi.tsys_master_codetype_details VALUES (nextval('putholi.master_codetype_details_seq'), 'Y', 'BANK', 'sysadmin', '2023-01-20 12:24:52.643', 'Bank Details', 'Bank Details', NULL, NULL);
INSERT INTO putholi.tsys_master_codetype_details VALUES (nextval('putholi.master_codetype_details_seq'), 'Y', 'REQTY', 'sysadmin', '2023-01-20 12:24:52.643', 'Requirement Type', 'Requirement Type', NULL, NULL);
INSERT INTO putholi.tsys_master_codetype_details VALUES (nextval('putholi.master_codetype_details_seq'), 'Y', 'OTH', 'sysadmin', '2023-01-20 12:24:52.643', 'Others', 'Others', NULL, NULL);
INSERT INTO putholi.tsys_master_codetype_details VALUES (nextval('putholi.master_codetype_details_seq'), 'Y', 'SPR', 'sysadmin', '2023-01-20 12:24:52.643', 'Sports', 'Sports', NULL, NULL);
INSERT INTO putholi.tsys_master_codetype_details VALUES (nextval('putholi.master_codetype_details_seq'), 'Y', 'INF', 'sysadmin', '2023-01-20 12:24:52.643', 'Infrastructure', 'Infrastructure', NULL, NULL);
INSERT INTO putholi.tsys_master_codetype_details VALUES (nextval('putholi.master_codetype_details_seq'), 'Y', 'DIST', 'sysadmin', '2023-02-21 12:24:52.643', 'District', 'District', NULL, NULL);
INSERT INTO putholi.tsys_master_codetype_details VALUES (nextval('putholi.master_codetype_details_seq'), 'Y', 'CATGY', 'sysadmin', current_date, 'Category', 'Category', NULL, NULL);


	
-- Country Codes (CNTRY)
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'AFG', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Afghanistan', 'Afghanistan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ALB', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Albania', 'Albania', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'DZA', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Algeria', 'Algeria', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ASM', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'American Samoa', 'American Samoa', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'AND', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Andorra', 'Andorra', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'AGO', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Angola', 'Angola', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'AIA', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Anguilla', 'Anguilla', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ATA', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Antarctica', 'Antarctica', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ATG', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Antigua and Barbuda', 'Antigua and Barbuda', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ARG', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Argentina', 'Argentina', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ARM', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Armenia', 'Armenia', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ABW', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Aruba', 'Aruba', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'AUS', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Australia', 'Australia', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'AUT', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Austria', 'Austria', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'AZE', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Azerbaijan', 'Azerbaijan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BHS', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Bahamas', 'Bahamas', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BHR', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Bahrain', 'Bahrain', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BGD', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Bangladesh', 'Bangladesh', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BRB', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Barbados', 'Barbados', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BLR', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Belarus', 'Belarus', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BEL', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Belgium', 'Belgium', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BLZ', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Belize', 'Belize', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BEN', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Benin', 'Benin', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BMU', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Bermuda', 'Bermuda', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BTN', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Bhutan', 'Bhutan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BOL', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Bolivia (Plurinational State of)', 'Bolivia (Plurinational State of)', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BES', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Bonaire, Sint Eustatius and Saba', 'Bonaire, Sint Eustatius and Saba', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BIH', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Bosnia and Herzegovina', 'Bosnia and Herzegovina', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BWA', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Botswana', 'Botswana', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BVT', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Bouvet Island', 'Bouvet Island', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BRA', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Brazil', 'Brazil', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'IOT', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'British Indian Ocean Territory', 'British Indian Ocean Territory', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BRN', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Brunei Darussalam', 'Brunei Darussalam', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BGR', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Bulgaria', 'Bulgaria', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BFA', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Burkina Faso', 'Burkina Faso', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BDI', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Burundi', 'Burundi', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CPV', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Cabo Verde', 'Cabo Verde', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'KHM', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Cambodia', 'Cambodia', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CMR', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Cameroon', 'Cameroon', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CAN', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Canada', 'Canada', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CYM', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Cayman Islands', 'Cayman Islands', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CAF', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Central African Republic', 'Central African Republic', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TCD', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Chad', 'Chad', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CHL', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Chile', 'Chile', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CHN', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'China', 'China', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CXR', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Christmas Island', 'Christmas Island', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CCK', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Cocos (Keeling) Islands', 'Cocos (Keeling) Islands', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'COL', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Colombia', 'Colombia', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'COM', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Comoros', 'Comoros', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'COD', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Congo (the Democratic Republic of the)', 'Congo (the Democratic Republic of the)', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'COG', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Congo', 'Congo', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'COK', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Cook Islands', 'Cook Islands', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CRI', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Costa Rica', 'Costa Rica', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'HRV', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Croatia', 'Croatia', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CUB', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Cuba', 'Cuba', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CUW', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Curaçao', 'Curaçao', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CYP', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Cyprus', 'Cyprus', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CZE', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Czechia', 'Czechia', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CIV', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Côte d''Ivoire', 'Côte d''Ivoire', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'DNK', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Denmark', 'Denmark', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'DJI', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Djibouti', 'Djibouti', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'DMA', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Dominica', 'Dominica', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'DOM', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Dominican Republic', 'Dominican Republic', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ECU', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Ecuador', 'Ecuador', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'EGY', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Egypt', 'Egypt', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SLV', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'El Salvador', 'El Salvador', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GNQ', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Equatorial Guinea', 'Equatorial Guinea', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ERI', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Eritrea', 'Eritrea', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'EST', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Estonia', 'Estonia', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SWZ', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Eswatini', 'Eswatini', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ETH', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Ethiopia', 'Ethiopia', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'FLK', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Falkland Islands [Malvinas]', 'Falkland Islands [Malvinas]', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'FRO', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Faroe Islands', 'Faroe Islands', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'FJI', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Fiji', 'Fiji', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'FIN', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Finland', 'Finland', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'FRA', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'France', 'France', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GUF', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'French Guiana', 'French Guiana', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PYF', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'French Polynesia', 'French Polynesia', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ATF', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'French Southern Territories', 'French Southern Territories', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GAB', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Gabon', 'Gabon', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GMB', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Gambia', 'Gambia', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GEO', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Georgia', 'Georgia', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'DEU', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Germany', 'Germany', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GHA', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Ghana', 'Ghana', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GIB', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Gibraltar', 'Gibraltar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GRC', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Greece', 'Greece', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GRL', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Greenland', 'Greenland', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GRD', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Grenada', 'Grenada', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GLP', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Guadeloupe', 'Guadeloupe', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GUM', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Guam', 'Guam', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GTM', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Guatemala', 'Guatemala', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GGY', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Guernsey', 'Guernsey', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GIN', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Guinea', 'Guinea', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GNB', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Guinea-Bissau', 'Guinea-Bissau', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GUY', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Guyana', 'Guyana', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'HTI', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Haiti', 'Haiti', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'HMD', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Heard Island and McDonald Islands', 'Heard Island and McDonald Islands', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'VAT', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Holy See (Vatican City State)', 'Holy See (Vatican City State)', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'HND', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Honduras', 'Honduras', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'HKG', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Hong Kong', 'Hong Kong', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'HUN', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Hungary', 'Hungary', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ISL', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Iceland', 'Iceland', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'IND', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'India', 'India', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'IDN', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Indonesia', 'Indonesia', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'IRN', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Iran (Islamic Republic of)', 'Iran (Islamic Republic of)', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'IRQ', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Iraq', 'Iraq', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'IRL', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Ireland', 'Ireland', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'IMN', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Isle of Man', 'Isle of Man', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ISR', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Israel', 'Israel', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ITA', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Italy', 'Italy', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'JAM', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Jamaica', 'Jamaica', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'JPN', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Japan', 'Japan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'JEY', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Jersey', 'Jersey', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'JOR', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Jordan', 'Jordan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'KAZ', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Kazakhstan', 'Kazakhstan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'KEN', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Kenya', 'Kenya', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'KIR', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Kiribati', 'Kiribati', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PRK', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Korea (the Democratic People''s Republic of)', 'Korea (the Democratic People''s Republic of)', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'KOR', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Korea (the Republic of)', 'Korea (the Republic of)', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'KWT', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Kuwait', 'Kuwait', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'KGZ', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Kyrgyzstan', 'Kyrgyzstan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'LAO', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Lao People''s Democratic Republic', 'Lao People''s Democratic Republic', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'LVA', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Latvia', 'Latvia', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'LBN', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Lebanon', 'Lebanon', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'LSO', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Lesotho', 'Lesotho', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'LBR', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Liberia', 'Liberia', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'LBY', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Libya', 'Libya', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'LIE', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Liechtenstein', 'Liechtenstein', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'LTU', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Lithuania', 'Lithuania', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'LUX', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Luxembourg', 'Luxembourg', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MAC', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Macao', 'Macao', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MDG', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Madagascar', 'Madagascar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MWI', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Malawi', 'Malawi', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MYS', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Malaysia', 'Malaysia', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MDV', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Maldives', 'Maldives', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MLI', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Mali', 'Mali', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MLT', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Malta', 'Malta', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MHL', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Marshall Islands', 'Marshall Islands', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MTQ', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Martinique', 'Martinique', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MRT', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Mauritania', 'Mauritania', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MUS', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Mauritius', 'Mauritius', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MYT', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Mayotte', 'Mayotte', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MEX', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Mexico', 'Mexico', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'FSM', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Micronesia (Federated States of)', 'Micronesia (Federated States of)', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MDA', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Moldova (the Republic of)', 'Moldova (the Republic of)', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MCO', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Monaco', 'Monaco', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MNG', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Mongolia', 'Mongolia', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MNE', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Montenegro', 'Montenegro', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MSR', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Montserrat', 'Montserrat', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MAR', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Morocco', 'Morocco', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MOZ', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Mozambique', 'Mozambique', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MMR', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Myanmar', 'Myanmar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'NAM', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Namibia', 'Namibia', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'NRU', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Nauru', 'Nauru', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'NPL', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Nepal', 'Nepal', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'NLD', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Netherlands', 'Netherlands', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'NCL', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'New Caledonia', 'New Caledonia', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'NZL', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'New Zealand', 'New Zealand', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'NIC', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Nicaragua', 'Nicaragua', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'NER', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Niger', 'Niger', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'NGA', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Nigeria', 'Nigeria', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'NIU', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Niue', 'Niue', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'NFK', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Norfolk Island', 'Norfolk Island', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MNP', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Northern Mariana Islands', 'Northern Mariana Islands', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'NOR', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Norway', 'Norway', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'OMN', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Oman', 'Oman', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PAK', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Pakistan', 'Pakistan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PLW', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Palau', 'Palau', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PSE', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Palestine, State of', 'Palestine, State of', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PAN', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Panama', 'Panama', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PNG', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Papua New Guinea', 'Papua New Guinea', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PRY', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Paraguay', 'Paraguay', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PER', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Peru', 'Peru', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PHL', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Philippines', 'Philippines', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PCN', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Pitcairn', 'Pitcairn', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'POL', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Poland', 'Poland', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PRT', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Portugal', 'Portugal', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PRI', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Puerto Rico', 'Puerto Rico', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'QAT', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Qatar', 'Qatar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MKD', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Republic of North Macedonia', 'Republic of North Macedonia', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ROU', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Romania', 'Romania', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'RUS', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Russian Federation', 'Russian Federation', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'RWA', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Rwanda', 'Rwanda', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'REU', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Réunion', 'Réunion', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BLM', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Saint Barthélemy', 'Saint Barthélemy', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SHN', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Saint Helena, Ascension and Tristan da Cunha', 'Saint Helena, Ascension and Tristan da Cunha', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'KNA', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Saint Kitts and Nevis', 'Saint Kitts and Nevis', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'LCA', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Saint Lucia', 'Saint Lucia', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MAF', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Saint Martin (French part)', 'Saint Martin (French part)', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SPM', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Saint Pierre and Miquelon', 'Saint Pierre and Miquelon', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'VCT', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Saint Vincent and the Grenadines', 'Saint Vincent and the Grenadines', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'WSM', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Samoa', 'Samoa', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SMR', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'San Marino', 'San Marino', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'STP', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Sao Tome and Principe', 'Sao Tome and Principe', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SAU', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Saudi Arabia', 'Saudi Arabia', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SEN', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Senegal', 'Senegal', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SRB', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Serbia', 'Serbia', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SYC', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Seychelles', 'Seychelles', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SLE', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Sierra Leone', 'Sierra Leone', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SGP', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Singapore', 'Singapore', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SXM', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Sint Maarten (Dutch part)', 'Sint Maarten (Dutch part)', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SVK', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Slovakia', 'Slovakia', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SVN', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Slovenia', 'Slovenia', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SLB', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Solomon Islands', 'Solomon Islands', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SOM', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Somalia', 'Somalia', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ZAF', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'South Africa', 'South Africa', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SGS', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'South Georgia and the South Sandwich Islands', 'South Georgia and the South Sandwich Islands', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SSD', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'South Sudan', 'South Sudan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ESP', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Spain', 'Spain', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'LKA', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Sri Lanka', 'Sri Lanka', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SDN', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Sudan', 'Sudan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SUR', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Suriname', 'Suriname', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SJM', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Svalbard and Jan Mayen', 'Svalbard and Jan Mayen', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SWE', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Sweden', 'Sweden', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CHE', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Switzerland', 'Switzerland', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SYR', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Syrian Arab Republic', 'Syrian Arab Republic', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TWN', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Taiwan (Province of China)', 'Taiwan (Province of China)', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TJK', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Tajikistan', 'Tajikistan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TZA', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Tanzania, United Republic of', 'Tanzania, United Republic of', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'THA', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Thailand', 'Thailand', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TLS', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Timor-Leste', 'Timor-Leste', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TGO', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Togo', 'Togo', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TKL', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Tokelau', 'Tokelau', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TON', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Tonga', 'Tonga', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TTO', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Trinidad and Tobago', 'Trinidad and Tobago', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TUN', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Tunisia', 'Tunisia', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TUR', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Turkey', 'Turkey', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TKM', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Turkmenistan', 'Turkmenistan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TCA', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Turks and Caicos Islands', 'Turks and Caicos Islands', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TUV', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Tuvalu', 'Tuvalu', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'UGA', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Uganda', 'Uganda', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'UKR', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Ukraine', 'Ukraine', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ARE', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'United Arab Emirates', 'United Arab Emirates', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GBR', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'United Kingdom of Great Britain and Northern Ireland', 'United Kingdom of Great Britain and Northern Ireland', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'UMI', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'United States Minor Outlying Islands', 'United States Minor Outlying Islands', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'USA', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'United States of America', 'United States of America', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'URY', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Uruguay', 'Uruguay', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'UZB', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Uzbekistan', 'Uzbekistan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'VUT', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Vanuatu', 'Vanuatu', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'VEN', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Venezuela (Bolivarian Republic of)', 'Venezuela (Bolivarian Republic of)', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'VNM', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Viet Nam', 'Viet Nam', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'VGB', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Virgin Islands (British)', 'Virgin Islands (British)', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'VIR', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Virgin Islands (U.S.)', 'Virgin Islands (U.S.)', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'WLF', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Wallis and Futuna', 'Wallis and Futuna', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ESH', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Western Sahara', 'Western Sahara', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'YEM', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Yemen', 'Yemen', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ZMB', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Zambia', 'Zambia', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ZWE', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Zimbabwe', 'Zimbabwe', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ALA', 'CNTRY', 'sysadmin', '2021-02-26 18:56:55.115', 'Åland Islands', 'Åland Islands', NULL, NULL);
	
-- Currency Codes (CRNCY)
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'AFN', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Afghani', 'Afghani', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'ALL', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Lek', 'Lek', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'DZD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Algerian Dinar', 'Algerian Dinar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'USD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'US Dollar', 'US Dollar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'EUR', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Euro', 'Euro', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'AOA', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Kwanza', 'Kwanza', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'XCD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'East Caribbean Dollar', 'East Caribbean Dollar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'ARS', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Argentine Peso', 'Argentine Peso', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'AMD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Armenian Dram', 'Armenian Dram', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'AWG', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Aruban Florin', 'Aruban Florin', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'AUD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Australian Dollar', 'Australian Dollar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'AZN', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Azerbaijanian Manat', 'Azerbaijanian Manat', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'BSD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Bahamian Dollar', 'Bahamian Dollar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'BHD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Bahraini Dinar', 'Bahraini Dinar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'BDT', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Taka', 'Taka', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'BBD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Barbados Dollar', 'Barbados Dollar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'BYN', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Belarussian Ruble', 'Belarussian Ruble', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'BZD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Belize Dollar', 'Belize Dollar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'XOF', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'CFA Franc BCEAO', 'CFA Franc BCEAO', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'BMD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Bermudian Dollar', 'Bermudian Dollar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'BTN', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Ngultrum', 'Ngultrum', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'INR', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Indian Rupee', 'Indian Rupee', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'BOB', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Boliviano', 'Boliviano', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'BOV', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Mvdol', 'Mvdol', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'BAM', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Convertible Mark', 'Convertible Mark', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'BWP', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Pula', 'Pula', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'BRL', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Brazilian Real', 'Brazilian Real', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'BND', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Brunei Dollar', 'Brunei Dollar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'BGN', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Bulgarian Lev', 'Bulgarian Lev', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'BIF', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Burundi Franc', 'Burundi Franc', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'CVE', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Cabo Verde Escudo', 'Cabo Verde Escudo', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'KHR', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Riel', 'Riel', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'XAF', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'CFA Franc BEAC', 'CFA Franc BEAC', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'CAD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Canadian Dollar', 'Canadian Dollar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'KYD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Cayman Islands Dollar', 'Cayman Islands Dollar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'CLF', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Unidad de Fomento', 'Unidad de Fomento', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'CLP', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Chilean Peso', 'Chilean Peso', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'CNY', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Yuan Renminbi', 'Yuan Renminbi', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'COP', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Colombian Peso', 'Colombian Peso', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'COU', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Unidad de Valor Real', 'Unidad de Valor Real', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'KMF', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Comoro Franc', 'Comoro Franc', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'CDF', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Congolese Franc', 'Congolese Franc', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'CRC', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Costa Rican Colon', 'Costa Rican Colon', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'HRK', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Kuna', 'Kuna', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'CUC', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Peso Convertible', 'Peso Convertible', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'CUP', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Cuban Peso', 'Cuban Peso', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'CZK', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Czech Koruna', 'Czech Koruna', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'DJF', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Djibouti Franc', 'Djibouti Franc', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'DOP', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Dominican Peso', 'Dominican Peso', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'EGP', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Egyptian Pound', 'Egyptian Pound', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'SVC', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'El Salvador Colon', 'El Salvador Colon', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'ERN', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Nakfa', 'Nakfa', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'ETB', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Ethiopian Birr', 'Ethiopian Birr', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'FKP', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Falkland Islands Pound', 'Falkland Islands Pound', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'FJD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Fiji Dollar', 'Fiji Dollar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'GMD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Dalasi', 'Dalasi', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'GEL', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Lari', 'Lari', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'GHS', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Ghana Cedi', 'Ghana Cedi', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'GIP', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Gibraltar Pound', 'Gibraltar Pound', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'DKK', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Danish Krone', 'Danish Krone', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'GTQ', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Quetzal', 'Quetzal', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'GNF', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Guinea Franc', 'Guinea Franc', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'GYD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Guyana Dollar', 'Guyana Dollar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'HTG', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Gourde', 'Gourde', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'HNL', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Lempira', 'Lempira', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'HKD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Hong Kong Dollar', 'Hong Kong Dollar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'HUF', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Forint', 'Forint', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'ISK', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Iceland Krona', 'Iceland Krona', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'IDR', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Rupiah', 'Rupiah', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'XDR', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'SDR (Special Drawing Right)', 'SDR (Special Drawing Right)', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'IRR', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Iranian Rial', 'Iranian Rial', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'IQD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Iraqi Dinar', 'Iraqi Dinar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'ILS', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'New Israeli Sheqel', 'New Israeli Sheqel', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'JMD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Jamaican Dollar', 'Jamaican Dollar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'JPY', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Yen', 'Yen', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'JOD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Jordanian Dinar', 'Jordanian Dinar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'KZT', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Tenge', 'Tenge', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'KES', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Kenyan Shilling', 'Kenyan Shilling', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'KPW', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'North Korean Won', 'North Korean Won', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'KRW', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Won', 'Won', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'KWD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Kuwaiti Dinar', 'Kuwaiti Dinar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'KGS', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Som', 'Som', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'LAK', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Kip', 'Kip', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'LBP', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Lebanese Pound', 'Lebanese Pound', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'LSL', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Loti', 'Loti', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'LRD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Liberian Dollar', 'Liberian Dollar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'LYD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Libyan Dinar', 'Libyan Dinar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'MOP', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Pataca', 'Pataca', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'MGA', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Malagasy Ariary', 'Malagasy Ariary', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'MWK', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Kwacha', 'Kwacha', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'MYR', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Malaysian Ringgit', 'Malaysian Ringgit', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'MVR', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Rufiyaa', 'Rufiyaa', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'MRU', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Ouguiya', 'Ouguiya', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'MUR', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Mauritius Rupee', 'Mauritius Rupee', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'XUA', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'ADB Unit of Account', 'ADB Unit of Account', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'MXN', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Mexican Peso', 'Mexican Peso', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'MXV', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Mexican Unidad de Inversion (UDI)', 'Mexican Unidad de Inversion (UDI)', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'MDL', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Moldovan Leu', 'Moldovan Leu', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'MNT', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Tugrik', 'Tugrik', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'MAD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Moroccan Dirham', 'Moroccan Dirham', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'MZN', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Mozambique Metical', 'Mozambique Metical', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'MMK', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Kyat', 'Kyat', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'NAD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Namibia Dollar', 'Namibia Dollar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'NPR', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Nepalese Rupee', 'Nepalese Rupee', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'NIO', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Cordoba Oro', 'Cordoba Oro', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'NGN', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Naira', 'Naira', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'OMR', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Rial Omani', 'Rial Omani', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'PKR', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Pakistan Rupee', 'Pakistan Rupee', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'PAB', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Balboa', 'Balboa', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'PGK', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Kina', 'Kina', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'PYG', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Guarani', 'Guarani', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'PEN', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Nuevo Sol', 'Nuevo Sol', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'PHP', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Philippine Peso', 'Philippine Peso', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'PLN', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Zloty', 'Zloty', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'QAR', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Qatari Rial', 'Qatari Rial', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'MKD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Denar', 'Denar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'RON', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Romanian Leu', 'Romanian Leu', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'RUB', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Russian Ruble', 'Russian Ruble', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'RWF', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Rwanda Franc', 'Rwanda Franc', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'SHP', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Saint Helena Pound', 'Saint Helena Pound', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'WST', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Tala', 'Tala', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'STN', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Dobra', 'Dobra', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'SAR', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Saudi Riyal', 'Saudi Riyal', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'RSD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Serbian Dinar', 'Serbian Dinar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'SCR', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Seychelles Rupee', 'Seychelles Rupee', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'SLL', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Leone', 'Leone', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'SGD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Singapore Dollar', 'Singapore Dollar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'ANG', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Netherlands Antillean Guilder', 'Netherlands Antillean Guilder', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'XSU', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Sucre', 'Sucre', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'SBD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Solomon Islands Dollar', 'Solomon Islands Dollar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'SOS', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Somali Shilling', 'Somali Shilling', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'ZAR', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Rand', 'Rand', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'SSP', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'South Sudanese Pound', 'South Sudanese Pound', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'LKR', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Sri Lanka Rupee', 'Sri Lanka Rupee', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'SDG', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Sudanese Pound', 'Sudanese Pound', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'SRD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Surinam Dollar', 'Surinam Dollar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'NOK', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Norwegian Krone', 'Norwegian Krone', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'SZL', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Lilangeni', 'Lilangeni', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'SEK', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Swedish Krona', 'Swedish Krona', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'CHE', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'WIR Euro', 'WIR Euro', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'CHF', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Swiss Franc', 'Swiss Franc', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'CHW', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'WIR Franc', 'WIR Franc', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'SYP', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Syrian Pound', 'Syrian Pound', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'TWD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'New Taiwan Dollar', 'New Taiwan Dollar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'TJS', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Somoni', 'Somoni', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'TZS', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Tanzanian Shilling', 'Tanzanian Shilling', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'THB', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Baht', 'Baht', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'NZD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'New Zealand Dollar', 'New Zealand Dollar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'TOP', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Pa’anga', 'Pa’anga', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'TTD', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Trinidad and Tobago Dollar', 'Trinidad and Tobago Dollar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'TND', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Tunisian Dinar', 'Tunisian Dinar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'TRY', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Turkish Lira', 'Turkish Lira', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'TMT', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Turkmenistan New Manat', 'Turkmenistan New Manat', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'UGX', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Uganda Shilling', 'Uganda Shilling', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'UAH', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Hryvnia', 'Hryvnia', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'AED', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'UAE Dirham', 'UAE Dirham', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'GBP', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Pound Sterling', 'Pound Sterling', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'USN', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'US Dollar (Next day)', 'US Dollar (Next day)', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'UYI', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Uruguay Peso en Unidades Indexadas (URUIURUI)', 'Uruguay Peso en Unidades Indexadas (URUIURUI)', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'UYU', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Peso Uruguayo', 'Peso Uruguayo', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'UZS', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Uzbekistan Sum', 'Uzbekistan Sum', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'VUV', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Vatu', 'Vatu', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'VEF', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Bolivar', 'Bolivar', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'VND', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Dong', 'Dong', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'XPF', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'CFP Franc', 'CFP Franc', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'YER', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Yemeni Rial', 'Yemeni Rial', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'ZMW', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Zambian Kwacha', 'Zambian Kwacha', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'N', 'ZWL', 'CRNCY', 'sysadmin', '2021-02-26 18:56:55.115', 'Zimbabwe Dollar', 'Zimbabwe Dollar', NULL, NULL);

	
--Nationality Codes
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'AFG', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Afghan', 'Afghan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ALB', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Albanian', 'Albanian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'DZA', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Algerian', 'Algerian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ASM', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'American', 'American', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'AND', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Andorran', 'Andorran', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'AGO', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Angolan', 'Angolan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'AIA', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Anguillan', 'Anguillan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ATA', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Antarcticans', 'Antarcticans', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ATG', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Citizen of Antigua and Barbuda', 'Citizen of Antigua and Barbuda', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ARG', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Argentine', 'Argentine', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ARM', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Armenian', 'Armenian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ABW', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Dutch', 'Dutch', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'AUS', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Australian', 'Australian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'AUT', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Austrian', 'Austrian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'AZE', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Azerbaijani', 'Azerbaijani', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BHS', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Bahamian', 'Bahamian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BHR', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Bahraini', 'Bahraini', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BGD', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Bangladeshi', 'Bangladeshi', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BRB', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Barbadian', 'Barbadian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BLR', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Belarusian', 'Belarusian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BEL', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Belgian', 'Belgian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BLZ', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Belizean', 'Belizean', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BEN', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Beninese', 'Beninese', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BMU', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Bermudian', 'Bermudian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BTN', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Bhutanese', 'Bhutanese', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BOL', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Bolivian', 'Bolivian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BES', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Bonaire, Sint Eustatius and Saba', 'Bonaire, Sint Eustatius and Saba', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BIH', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Citizen of Bosnia and Herzegovina', 'Citizen of Bosnia and Herzegovina', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BWA', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Botswanan', 'Botswanan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BVT', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'British Virgin Islander', 'British Virgin Islander', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BRA', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Brazilian', 'Brazilian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'IOT', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'British', 'British', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BRN', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Bruneian', 'Bruneian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BGR', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Bulgarian', 'Bulgarian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BFA', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Burkinan', 'Burkinan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BDI', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Burundian', 'Burundian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CPV', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Cape Verdean', 'Cape Verdean', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'KHM', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Cambodian', 'Cambodian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CMR', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Cameroonian', 'Cameroonian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CAN', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Canadian', 'Canadian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CYM', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Cayman Islander', 'Cayman Islander', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CAF', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Central African', 'Central African', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TCD', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Chadian', 'Chadian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CHL', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Chilean', 'Chilean', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CHN', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Chinese', 'Chinese', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CXR', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Christmas Islander', 'Christmas Islander', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CCK', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Cocos (Keeling) Islander', 'Cocos (Keeling) Islander', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'COL', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Colombian', 'Colombian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'COM', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Comoran', 'Comoran', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'COD', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Congolese (Congo)', 'Congolese (Congo)', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'COG', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Congolese (DRC)', 'Congolese (DRC)', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'COK', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Cook Islander', 'Cook Islander', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CRI', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Costa Rican', 'Costa Rican', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'HRV', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Croatian', 'Croatian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CUB', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Cuban', 'Cuban', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CUW', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Curaçaoan', 'Curaçaoan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CYP', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Cypriot', 'Cypriot', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CZE', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Czech', 'Czech', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CIV', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Ivorian', 'Ivorian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'DNK', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Danish', 'Danish', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'DJI', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Djiboutian', 'Djiboutian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'DMA', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Dominican', 'Dominican', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'DOM', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Citizen of the Dominican Republic', 'Citizen of the Dominican Republic', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ECU', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Ecuadorean', 'Ecuadorean', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'EGY', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Egyptian', 'Egyptian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SLV', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Salvadorean', 'Salvadorean', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GNQ', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Equatorial Guinean', 'Equatorial Guinean', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ERI', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Eritrean', 'Eritrean', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'EST', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Estonian', 'Estonian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SWZ', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Swazi', 'Swazi', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ETH', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Ethiopian', 'Ethiopian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'FLK', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Falkland Islands [Malvinas]', 'Falkland Islands [Malvinas]', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'FRO', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Faroe Islands', 'Faroe Islands', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'FJI', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Fijian', 'Fijian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'FIN', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Finnish', 'Finnish', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'FRA', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'French', 'French', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GUF', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'French Guianan', 'French Guianan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PYF', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'French Polynesian', 'French Polynesian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ATF', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'French Southern Territories', 'French Southern Territories', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GAB', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Gabonese', 'Gabonese', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GMB', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Gambian', 'Gambian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GEO', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Georgian', 'Georgian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'DEU', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'German', 'German', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GHA', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Ghanaian', 'Ghanaian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GIB', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Gibraltarian', 'Gibraltarian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GRC', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Greek', 'Greek', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GRL', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Greenlandic', 'Greenlandic', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GRD', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Grenadian', 'Grenadian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GLP', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Guadeloupe', 'Guadeloupe', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GUM', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Guamanian', 'Guamanian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GTM', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Guatemalan', 'Guatemalan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GGY', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Guernsian', 'Guernsian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GIN', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Guinean', 'Guinean', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GNB', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Citizen of Guinea-Bissau', 'Citizen of Guinea-Bissau', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GUY', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Guyanese', 'Guyanese', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'HTI', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Haitian', 'Haitian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'HMD', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Heard Island and McDonald Islands', 'Heard Island and McDonald Islands', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'VAT', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Vatican citizen', 'Vatican citizen', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'HND', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Honduran', 'Honduran', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'HKG', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Hong Konger', 'Hong Konger', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'HUN', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Hungarian', 'Hungarian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ISL', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Icelandic', 'Icelandic', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'IND', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Indian', 'Indian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'IDN', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Indonesian', 'Indonesian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'IRN', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Iranian', 'Iranian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'IRQ', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Iraqi', 'Iraqi', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'IRL', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Irish', 'Irish', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'IMN', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Isle of Man', 'Isle of Man', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ISR', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Israeli', 'Israeli', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ITA', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Italian', 'Italian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'JAM', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Jamaican', 'Jamaican', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'JPN', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Japanese', 'Japanese', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'JEY', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Jersey', 'Jersey', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'JOR', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Jordanian', 'Jordanian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'KAZ', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Kazakh', 'Kazakh', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'KEN', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Kenyan', 'Kenyan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'KIR', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Citizen of Kiribati', 'Citizen of Kiribati', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PRK', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'North Korean', 'North Korean', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'KOR', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'South Korean', 'South Korean', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'KWT', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Kuwaiti', 'Kuwaiti', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'KGZ', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Kyrgyz', 'Kyrgyz', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'LAO', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Lao', 'Lao', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'LVA', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Latvian', 'Latvian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'LBN', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Lebanese', 'Lebanese', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'LSO', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Mosotho', 'Mosotho', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'LBR', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Liberian', 'Liberian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'LBY', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Libyan', 'Libyan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'LIE', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Liechtenstein citizen', 'Liechtenstein citizen', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'LTU', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Lithuanian', 'Lithuanian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'LUX', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Luxembourger', 'Luxembourger', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MAC', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Macanese', 'Macanese', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MDG', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Malagasy', 'Malagasy', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MWI', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Malawian', 'Malawian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MYS', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Malaysian', 'Malaysian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MDV', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Maldivian', 'Maldivian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MLI', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Malian', 'Malian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MLT', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Maltese', 'Maltese', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MHL', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Marshallese', 'Marshallese', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MTQ', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Martiniquais', 'Martiniquais', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MRT', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Mauritanian', 'Mauritanian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MUS', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Mauritian', 'Mauritian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MYT', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Monegasque', 'Monegasque', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MEX', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Mexican', 'Mexican', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'FSM', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Micronesian', 'Micronesian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MDA', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Moldovan', 'Moldovan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MCO', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Monacoian', 'Monacoian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MNG', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Mongolian', 'Mongolian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MNE', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Montenegrin', 'Montenegrin', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MSR', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Montserratian', 'Montserratian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MAR', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Moroccan', 'Moroccan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MOZ', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Mozambican', 'Mozambican', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MMR', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Burmese', 'Burmese', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'NAM', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Namibian', 'Namibian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'NRU', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Nauruan', 'Nauruan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'NPL', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Nepalese', 'Nepalese', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'NLD', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Dutch', 'Dutch', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'NCL', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'New Caledonia', 'New Caledonia', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'NZL', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'New Zealander', 'New Zealander', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'NIC', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Nicaraguan', 'Nicaraguan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'NER', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Nigerien', 'Nigerien', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'NGA', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Nigerian', 'Nigerian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'NIU', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Niuean', 'Niuean', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'NFK', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Norfolk Islander', 'Norfolk Islander', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MNP', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Northern Mariana Islander', 'Northern Mariana Islander', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'NOR', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Norwegian', 'Norwegian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'OMN', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Omani', 'Omani', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PAK', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Pakistani', 'Pakistani', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PLW', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Palauan', 'Palauan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PSE', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Palestinian', 'Palestinian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PAN', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Panamanian', 'Panamanian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PNG', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Papua New Guinean', 'Papua New Guinean', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PRY', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Paraguayan', 'Paraguayan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PER', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Peruvian', 'Peruvian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PHL', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Filipino', 'Filipino', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PCN', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Pitcairn Islander', 'Pitcairn Islander', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'POL', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Polish', 'Polish', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PRT', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Portuguese', 'Portuguese', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PRI', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Puerto Rican', 'Puerto Rican', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'QAT', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Qatari', 'Qatari', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MKD', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Macedonian', 'Macedonian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ROU', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Romanian', 'Romanian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'RUS', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Russian', 'Russian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'RWA', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Rwandan', 'Rwandan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'REU', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Réunion', 'Réunion', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BLM', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Saint Barthélemy', 'Saint Barthélemy', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SHN', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'St Helenian', 'St Helenian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'KNA', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Kittitian', 'Kittitian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'LCA', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'St Lucian', 'St Lucian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MAF', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Saint Martin (French part)', 'Saint Martin (French part)', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SPM', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Saint Pierre and Miquelon', 'Saint Pierre and Miquelon', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'VCT', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Saint Vincent and the Grenadines', 'Saint Vincent and the Grenadines', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'WSM', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Samoan', 'Samoan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SMR', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Sammarinese', 'Sammarinese', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'STP', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Sao Tomean', 'Sao Tomean', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SAU', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Saudi Arabian', 'Saudi Arabian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SEN', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Senegalese', 'Senegalese', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SRB', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Serbian', 'Serbian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SYC', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Citizen of Seychelles', 'Citizen of Seychelles', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SLE', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Sierra Leonean', 'Sierra Leonean', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SGP', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Singaporean', 'Singaporean', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SXM', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Sint Maarten (Dutch part)', 'Sint Maarten (Dutch part)', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SVK', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Slovak', 'Slovak', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SVN', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Slovenian', 'Slovenian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SLB', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Solomon Islander', 'Solomon Islander', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SOM', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Somali', 'Somali', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ZAF', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'South African', 'South African', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SGS', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'South Georgia and the South Sandwich Islands', 'South Georgia and the South Sandwich Islands', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SSD', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'South Sudanese', 'South Sudanese', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ESP', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Spanish', 'Spanish', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'LKA', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Sri Lankan', 'Sri Lankan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SDN', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Sudanese', 'Sudanese', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SUR', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Surinamese', 'Surinamese', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SJM', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Svalbard and Jan Mayen', 'Svalbard and Jan Mayen', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SWE', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Swedish', 'Swedish', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CHE', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Swiss', 'Swiss', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SYR', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Syrian', 'Syrian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TWN', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Taiwanese', 'Taiwanese', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TJK', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Tajik', 'Tajik', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TZA', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Tanzanian', 'Tanzanian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'THA', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Thai', 'Thai', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TLS', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'East Timorese', 'East Timorese', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TGO', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Togolese', 'Togolese', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TKL', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Tokelau', 'Tokelau', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TON', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Tongan', 'Tongan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TTO', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Trinidadian', 'Trinidadian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TUN', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Tunisian', 'Tunisian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TUR', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Turkish', 'Turkish', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TKM', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Turkmen', 'Turkmen', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TCA', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Turks and Caicos Islander', 'Turks and Caicos Islander', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TUV', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Tuvaluan', 'Tuvaluan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'UGA', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Ugandan', 'Ugandan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'UKR', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Ukrainian', 'Ukrainian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ARE', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Emirati', 'Emirati', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GBR', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'British', 'British', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'UMI', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'United States Minor Outlying Islands', 'United States Minor Outlying Islands', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'USA', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'American', 'American', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'URY', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Uruguayan', 'Uruguayan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'UZB', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Uzbek', 'Uzbek', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'VUT', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Citizen of Vanuatu', 'Citizen of Vanuatu', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'VEN', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Venezuelan', 'Venezuelan', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'VNM', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Vietnamese', 'Vietnamese', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'VGB', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Virgin Islands (British)', 'Virgin Islands (British)', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'VIR', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Virgin Islands (U.S.)', 'Virgin Islands (U.S.)', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'WLF', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Wallisian', 'Wallisian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ESH', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Western Sahara', 'Western Sahara', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'YEM', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Yemeni', 'Yemeni', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ZMB', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Zambian', 'Zambian', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ZWE', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Zimbabwean', 'Zimbabwean', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ALA', 'NTNLT', 'sysadmin', '2021-02-26 18:56:55.115', 'Åland Islands', 'Åland Islands', NULL, NULL);

--Arrival Locations
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'JIB', 'ARLOC', 'sysadmin', '2021-02-26 18:56:55.115', 'Ambouli International Airport', 'Ambouli International Airport', NULL, NULL);
	
	
--Marital Status
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'S', 'MRT', 'sysadmin', '2021-02-26 18:56:55.115', 'Single', 'Single', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'M', 'MRT', 'sysadmin', '2021-02-26 18:56:55.115', 'Married', 'Married', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'D', 'MRT', 'sysadmin', '2021-02-26 18:56:55.115', 'Divorced', 'Divorced', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'W', 'MRT', 'sysadmin', '2021-02-26 18:56:55.115', 'Widowed', 'Widowed', NULL, NULL);


--sports
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CH', 'SPR', 'sysadmin', '2022-12-16 12:45:29.16', 'Chess board', 'Chess board', 'sysadmin', '2022-12-22 18:42:22.407');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CR', 'SPR', 'sysadmin', '2022-12-16 12:45:29.16', 'Carrom board', 'Carrom board', 'sysadmin', '2022-12-22 18:42:22.407');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BT', 'SPR', 'sysadmin', '2022-12-16 12:45:29.16', 'Ball badminton', 'Ball badminton', 'sysadmin', '2022-12-22 18:42:22.407');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
 VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'FB', 'SPR', 'sysadmin', '2022-12-16 12:45:29.16', 'Foot ball', 'Foot ball', 'sysadmin', '2022-12-22 18:42:22.407');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'HB', 'SPR', 'sysadmin', '2022-12-16 12:45:29.16', 'Hand ball', 'Hand ball', 'sysadmin', '2022-12-22 18:42:22.407');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'HY', 'SPR', 'sysadmin', '2022-12-16 12:45:29.16', 'Hockey', 'Hockey', 'sysadmin', '2022-12-22 18:42:22.407');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BB', 'SPR', 'sysadmin', '2022-12-16 12:45:29.16', 'Basket ball', 'Basket ball', 'sysadmin', '2022-12-22 18:42:22.407');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'VB', 'SPR', 'sysadmin', '2022-12-16 12:45:29.16', 'Volley ball', 'Volley ball', 'sysadmin', '2022-12-22 18:42:22.407');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
 VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'KA', 'SPR', 'sysadmin', '2022-12-16 12:45:29.16', 'Kabaddi', 'Kabaddi', 'sysadmin', '2022-12-22 18:42:22.407');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TS', 'SPR', 'sysadmin', '2022-12-16 12:45:29.16', 'Table tennis', 'Table tennis', 'sysadmin', '2022-12-22 18:42:22.407');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TB', 'SPR', 'sysadmin', '2022-12-16 12:45:29.16', 'Throw ball', 'Throw ball', 'sysadmin', '2022-12-22 18:42:22.407');


---Infrastructure
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TBL', 'INF', 'sysadmin', '2022-12-16 12:41:20.737', 'Table', 'Table', 'sysadmin', '2022-12-22 18:42:22.407');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CHR', 'INF', 'sysadmin', '2022-12-16 12:41:20.737', 'Chair', 'Chair', 'sysadmin', '2022-12-22 18:42:22.407');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BLB', 'INF', 'sysadmin', '2022-12-16 12:41:20.737', 'Black board', 'Black board', 'sysadmin', '2022-12-22 18:42:22.407');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BCH', 'INF', 'sysadmin', '2022-12-16 12:41:20.737', 'Bench', 'Bench', 'sysadmin', '2022-12-22 18:42:22.407');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CLS', 'INF', 'sysadmin', '2022-12-16 12:41:20.737', 'Classroom', 'Classroom', 'sysadmin', '2022-12-22 18:42:22.407');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'LAB', 'INF', 'sysadmin', '2022-12-16 12:41:20.737', 'Laboratories', 'Laboratories', 'sysadmin', '2022-12-22 18:42:22.407');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'LIB', 'INF', 'sysadmin', '2022-12-16 12:41:20.737', 'Library', 'Library', 'sysadmin', '2022-12-22 18:42:22.407');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'FAN', 'INF', 'sysadmin', '2022-12-16 12:41:20.737', 'Fan', 'Fan', 'sysadmin', '2022-12-22 18:42:22.407');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'LHT', 'INF', 'sysadmin', '2022-12-16 12:41:20.737', 'Light', 'Light', 'sysadmin', '2022-12-22 18:42:22.407');


---Requirement Type
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'NE', 'REQTY', 'Admin', '2023-01-04 10:32:16.827', 'New', 'New', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MTN', 'REQTY', 'Admin', '2023-01-04 10:32:41.759', 'Maintenance', 'Maintenance', NULL, NULL);


--School Types
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'NUR', 'STY', 'sysadmin', '2022-07-04 20:26:40.145', 'Nursery', 'Nursery', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PRI', 'STY', 'sysadmin', '2022-07-04 20:28:00.649', 'Primary', 'Primary', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MDS', 'STY', 'sysadmin', '2022-07-04 20:28:34.68', 'Middle School', 'Middle School', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SES', 'STY', 'sysadmin', '2022-07-04 20:28:56.924', 'Secondary School', 'Secondary School', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'HSS', 'STY', 'sysadmin', '2022-07-04 20:29:15.123', 'Higher Secondary School', 'Higher Secondary School', NULL, NULL);


---Roles
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ADMIN', 'ROLE', 'sysadmin', '2021-02-26 18:56:55.115', 'Administrator', 'Administrator', 'admin', '2022-08-05 18:49:07.542');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'REVIEW', 'ROLE', 'sysadmin', '2021-02-26 18:56:55.115', 'Reviewer', 'Reviewer', 'admin', '2022-08-05 18:45:47.443');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'APPRV', 'ROLE', 'sysadmin', '2021-02-26 18:56:55.115', 'Approver', 'Approver', 'admin', '2022-08-05 18:50:46.25');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TRUSTM', 'ROLE', 'sysadmin', '2021-02-26 18:56:55.115', 'Trust Member', 'Trust Member', 'admin', '2022-08-05 18:47:27.063');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TRUSTV', 'ROLE', 'sysadmin', '2022-07-04 21:09:11.53', 'Trust Volunteer', 'Trust Volunteer', 'admin', '2022-08-05 18:48:12.837');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
 VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BENIF', 'ROLE', 'sysadmin', '2021-02-26 18:56:55.115', 'Beneficiary', 'Beneficiary', 'admin', '2022-08-05 18:51:45.346');


---school status
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'QUOARV', 'STS', 'Admin', '2022-11-24 10:37:41.967', 'Quotation Approved', 'Quotation Approved', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PARAPR', 'STS', 'Admin', '2022-12-30 13:04:18.819', 'Partially Approved', 'Partially Approved', 'ADMIN', '2023-01-02 18:18:36.991');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
 VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PARREJ', 'STS', 'Admin', '2022-12-30 14:16:39.555', 'Partially Rejected', 'Partially Rejected', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PAYINI', 'STS', 'Admin', '2023-01-04 02:30:51.08', 'Payment Initiated', 'Payment Initiated', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'APR', 'STS', 'sysadmin', '2021-02-26 18:56:55.115', 'APPROVED', 'APPROVED', 'sysadmin', '2022-07-07 18:46:57.121');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
 VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'REJ', 'STS', 'sysadmin', '2021-02-26 18:56:55.115', 'REJECTED', 'REJECTED', 'sysadmin', '2022-07-07 18:47:33.429');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PENADM', 'STS', 'sysadmin', '2022-07-04 16:32:15.253', 'Pending Admin Approval', 'Pending Admin Approval', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PENREV', 'STS', 'sysadmin', '2022-07-04 16:32:15.253', 'Pending Reviewer Approval', 'Pending Reviewer Approval', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PENAPR', 'STS', 'sysadmin', '2022-07-04 16:32:15.253', 'Pending Approver Approval', 'Pending Approver Approval', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'DEOMAL', 'STS', 'sysadmin', '2022-08-18 17:26:26.31', 'DEO Mail Sent', 'DEO Mail Sent', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'DEOAPR', 'STS', 'sysadmin', '2022-08-18 17:28:48.211', 'DEO Approved', 'DEO Approved', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ASGVOL', 'STS', 'sysadmin', '2022-08-18 20:22:56.594', 'Assigned Volunteer', 'Assigned Volunteer', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'VOLACP', 'STS', 'sysadmin', '2022-08-19 14:13:45.825', 'Volunteer Accepted', 'Volunteer Accepted', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'VOLREJ', 'STS', 'sysadmin', '2022-08-19 14:14:00.862', 'Volunteer Rejected', 'Volunteer Rejected', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'REDALL', 'STS', 'sysadmin', '2022-08-25 22:35:51.744', 'Ready For Allotment', 'Ready For Allotment', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ORDINI', 'STS', 'sysadmin', '2022-08-28 21:40:26.884', 'Work Order Initiated', 'Work Order Initiated', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PROCES', 'STS', 'Admin', '2022-08-29 15:45:42.519', 'All Invoices Processed', 'All Invoices Processed', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'INVAPR', 'STS', 'Admin', '2022-08-30 18:18:27.675', 'Pending Approver Invoice', 'Pending Approver Invoice', 'ADMIN', '2022-08-30 18:19:25.771');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
 VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'INVREV', 'STS', 'Admin', '2022-08-30 18:17:41.73', 'Pending Reviewer Invoice', 'Pending Reviewer Invoice', 'ADMIN', '2022-08-30 18:20:03.904');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
 VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'APRQUO', 'STS', 'Admin', '2022-08-30 18:15:22.502', 'Pending Approver Quotation', 'Pending Approver Quotation', 'ADMIN', '2022-08-30 18:20:31.49');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'REVQUO', 'STS', 'Admin', '2022-08-30 18:14:57.807', 'Pending Reviewer Quotation', 'Pending Reviewer Quotation', 'ADMIN', '2022-08-30 18:20:49.722');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'OPEREQ', 'STS', 'Admin', '2022-09-01 13:41:04.214', 'Open For Requirements', 'Open For Requirements', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'RECREJ', 'STS', 'Admin', '2022-09-01 13:51:10.597', 'Receipt Rejected', 'Receipt Rejected', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CMPLTD', 'STS', 'Admin', '2022-09-01 15:28:37.573', 'Completed', 'Completed', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ADDREQ', 'STS', 'Admin', '2022-09-01 15:28:37.573', 'Add requirements is in progress', 'Add requirements is in progress', NULL, NULL);

INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'APRREC', 'STS', 'Admin', '2022-09-06 17:37:27.43', 'Approved Receipt', 'Approved Receipt', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'GNRORD', 'STS', 'Admin', '2022-09-08 12:30:55.648', 'Generate Work Order', 'Generate Work Order', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'REVREQ', 'STS', 'Admin', '2022-09-19 16:41:52.36', 'Pending Reviewer Requirement', 'Pending Reviewer Requirement', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ADMREQ', 'STS', 'Admin', '2022-09-19 16:40:54.536', 'Pending Admin Requirement', 'Pending Admin Requirement', 'ADMIN', '2022-09-19 16:42:07.177');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'APRREQ', 'STS', 'Admin', '2022-09-19 16:42:41.808', 'Pending Approver Requirement', 'Pending Approver Requirement', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ADMDEO', 'STS', 'Admin', '2022-09-19 16:50:28.604', 'Pending Admin DEO', 'Pending Admin DEO', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'REVDEO', 'STS', 'Admin', '2022-09-19 16:50:48.675', 'Pending Reviewer DEO', 'Pending Reviewer DEO', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'APRDEO', 'STS', 'Admin', '2022-09-19 16:51:07.133', 'Pending Approver DEO', 'Pending Approver DEO', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
 VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ADMQUO', 'STS', 'Admin', '2022-09-19 20:29:18.034', 'Pending Admin Quotation', 'Pending Admin Quotation', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ADMINV', 'STS', 'Admin', '2022-09-20 11:21:34.188', 'Pending Admin Invoice', 'Pending Admin Invoice', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'REJQUO', 'STS', 'Admin', '2022-09-25 16:59:44.065', 'Rejected Quotation', 'Rejected Quotation', 'ADMIN', '2022-09-25 18:03:52.503');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
 VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'REJINV', 'STS', 'Admin', '2022-09-26 01:25:31.447', 'Rejected Invoice', 'Rejected Invoice', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ADMREC', 'STS', 'Admin', '2022-09-01 09:13:27.256', 'Pending Admin Receipt', 'Pending Admin Receipt', 'ADMIN', '2022-09-26 13:57:42.846');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PEN', 'STS', 'Admin', '2022-09-01 09:13:27.256', 'Pending', 'Pending', 'ADMIN', '2022-09-26 13:57:42.846');
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PARPAY', 'STS', 'Admin', '2022-09-01 09:13:27.256', 'Partially Payment Initiated', 'Partially Payment Initiated', 'ADMIN', '2022-09-26 13:57:42.846');



---Bank details
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
 VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BOB', 'BANK', 'Admin', '2022-08-29 12:12:59.138', 'Bank Of Baroda', 'Bank Of Baroda', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SBI', 'BANK', 'Admin', '2022-08-29 12:14:53.005', 'State Bank of India', 'State Bank of India', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'UBI', 'BANK', 'Admin', '2022-08-29 12:17:46.154', 'Union Bank of India', 'Union Bank of India', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'IB', 'BANK', 'Admin', '2022-08-29 12:18:49.224', 'Indian Bank', 'Indian Bank', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ICICI', 'BANK', 'Admin', '2022-08-29 12:19:20.234', 'ICICI Bank', 'ICICI Bank', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'BOI', 'BANK', 'Admin', '2022-08-29 12:20:14.289', 'Bank of India', 'Bank of India', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'AXIS', 'BANK', 'Admin', '2022-08-29 12:20:44.899', 'AXIS Bank', 'AXIS Bank', NULL, NULL);

-- Expenses status
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) VALUES 
(nextval('putholi.master_code_details_seq'), 'Y', 'EXPREV', 'STS', 'Admin', current_date, 'Pending Reviewer Expenses', 'Pending Reviewer Expenses', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) VALUES 
(nextval('putholi.master_code_details_seq'), 'Y', 'EXPAPR', 'STS', 'Admin', current_date, 'Pending Approver Expenses', 'Pending Approver Expenses', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) VALUES 
(nextval('putholi.master_code_details_seq'), 'Y', 'REJEXA', 'STS', 'Admin', current_date, 'Rejected Expenses by Approver', 'Rejected Expenses by Approver', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) VALUES 
(nextval('putholi.master_code_details_seq'), 'Y', 'REJEXR', 'STS', 'Admin', current_date, 'Rejected Expenses by Reviewer', 'Rejected Expenses by Reviewer', NULL, NULL);


--System Role Details
INSERT INTO putholi.tsys_role_mgmt(role_id, created_by, created_date, role_code, role_desc, role_desc_other, status, updated_by, updated_date)
VALUES (nextval('putholi.tsys_role_mgmt_seq'), 'sysadmin', '2021-04-13 17:41:00.000', 'ADMIN', 'Administrator', 'Administrator', 'Y', null, null);
INSERT INTO putholi.tsys_role_mgmt(role_id, created_by, created_date, role_code, role_desc, role_desc_other, status, updated_by, updated_date)
VALUES (nextval('putholi.tsys_role_mgmt_seq'), 'sysadmin', '2021-04-13 17:41:00.000', 'BENIF', 'Beneficiary', 'Beneficiary', 'Y', null, null);
INSERT INTO putholi.tsys_role_mgmt(role_id, created_by, created_date, role_code, role_desc, role_desc_other, status, updated_by, updated_date)
VALUES (nextval('putholi.tsys_role_mgmt_seq'), 'sysadmin', '2021-04-13 17:41:00.000', 'REVIEW', 'Reviewer', 'Reviewer', 'Y', null, null);
INSERT INTO putholi.tsys_role_mgmt(role_id, created_by, created_date, role_code, role_desc, role_desc_other, status, updated_by, updated_date)
VALUES (nextval('putholi.tsys_role_mgmt_seq'), 'sysadmin', '2021-04-13 17:41:00.000', 'APPRV', 'Approver', 'Approver', 'Y', null, null);
INSERT INTO putholi.tsys_role_mgmt(role_id, created_by, created_date, role_code, role_desc, role_desc_other, status, updated_by, updated_date)
VALUES (nextval('putholi.tsys_role_mgmt_seq'), 'sysadmin', '2021-04-13 17:41:00.000', 'TRUSTM', 'Trust Member', 'Trust Member', 'Y', null, null);	
INSERT INTO putholi.tsys_role_mgmt(role_id, created_by, created_date, role_code, role_desc, role_desc_other, status, updated_by, updated_date)
VALUES (nextval('putholi.tsys_role_mgmt_seq'), 'sysadmin', '2021-04-13 17:41:00.000', 'TRUSTV', 'Trust Volunteer', 'Trust Volunteer', 'Y', null, null);
INSERT INTO putholi.tsys_role_mgmt(
	role_id, created_by, created_date, role_code, role_desc, role_desc_other, status, updated_by, updated_date)
	VALUES (nextval('putholi.tsys_role_mgmt_seq'),'superadmin' , '2022-12-28 15:24:20.044', 'SUUSR', 'Super User', 'Super User', 'Y', NULL, NULL);
	
INSERT INTO putholi.tsys_role_mgmt(
	role_id, created_by, created_date, role_code, role_desc, role_desc_other, status, updated_by, updated_date)
	VALUES (nextval('putholi.tsys_role_mgmt_seq'),'superadmin' , '2022-12-28 15:24:20.044', 'SUADM', 'Super Admin', 'Super Admin', 'Y', NULL, NULL);

INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PARREC', 'STS', 'Admin', '2022-09-01 09:13:27.256', 'Partially Upload Receipt', 'Partially Upload Receipt', 'ADMIN', '2022-09-26 13:57:42.846');

--Product Config
INSERT INTO putholi.tsys_product_config (company_name, city, company_address, company_code, company_email, company_logo, company_phone, company_phone_2, cpmpany_regno, company_slogan, created_by, created_date, email_id, fax, last_modified_by, last_modified_date, postal_code, primary_contact_name, primary_contact_no, province, tax_identification_no, timezone, website)
 VALUES ('NEWRTASOLUTIONS', 'Madurai', 'Madurai', 'NE345', 'newrta@grr.la', NULL, '987654345678', '99987654567', '8765434567', 'Possibilities Infinity', 'admin', '2023-01-03 15:04:16.528', 'newrta@grr.la', '98765', 'ADMIN', '2023-01-03 15:39:06.185', 567890, 'kjhgfd', '87654567878', 'province', 'jhgfd', '1970-01-01 05:29:59.99', 'https://www.newrta.com');

INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SUUSR', 'SUUSR', 'sysadmin', '2022-12-28 15:24:20.044', 'Super User', 'Super User', NULL, NULL);
	
INSERT INTO putholi.tsys_master_code_details(
	id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SUADM', 'SUADM', 'sysadmin', '2022-12-28 15:24:20.044', 'Super Admin', 'Super Admin', NULL, NULL);
	
--Organization Type Details 
INSERT INTO putholi.tsys_master_code_details(id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CPR', 'ORGN','sysadmin', '2023-01-20 12:24:52.643', 'Corporate', 'Corporate' ,'sysadmin', '2023-01-20 13:24:52.643');
INSERT INTO putholi.tsys_master_code_details(id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'REG', 'ORGN','sysadmin', '2023-01-20 12:24:52.643', 'Registered', 'Registered' ,'sysadmin', '2023-01-20 13:24:52.643');
INSERT INTO putholi.tsys_master_code_details(id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'URG', 'ORGN','sysadmin', '2023-01-20 12:24:52.643', 'Un Registered', 'Un Registered' ,'sysadmin', '2023-01-20 13:24:52.643');
INSERT INTO putholi.tsys_master_code_details(id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ORS', 'ORGN','sysadmin', '2023-01-20 12:24:52.643', 'Others', 'Others' ,'sysadmin', '2023-01-20 13:24:52.643');

--Entity Type Details 
INSERT INTO putholi.tsys_master_code_details(id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PLC', 'ENTY','sysadmin', '2023-01-20 12:24:52.643', 'Public', 'Public' ,'sysadmin', '2023-01-20 13:24:52.643');
INSERT INTO putholi.tsys_master_code_details(id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PVT', 'ENTY','sysadmin', '2023-01-20 12:24:52.643', 'Private', 'Private' ,'sysadmin', '2023-01-20 13:24:52.643');
INSERT INTO putholi.tsys_master_code_details(id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'NGO', 'ENTY','sysadmin', '2023-01-20 12:24:52.643', 'NGO', 'NGO' ,'sysadmin', '2023-01-20 13:24:52.643');


--Payment Completion Details
INSERT INTO putholi.tsys_master_code_details(id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'FC', 'PAYM','sysadmin', '2022-12-16 12:41:20.737', 'Fully Completed', 'Fully Completed' ,'sysadmin', '2022-12-22 18:42:22.407');
INSERT INTO putholi.tsys_master_code_details(id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PC', 'PAYM','sysadmin', '2022-12-16 12:41:20.737', 'Partially Completed', 'Partially Completed' ,'sysadmin', '2022-12-22 18:42:22.407');
--Account details
INSERT INTO putholi.tsys_master_code_details(id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SA', 'ACCT','sysadmin', '2022-12-16 12:41:20.737', 'Savings', 'Savings' ,'sysadmin', '2022-12-22 18:42:22.407');
INSERT INTO putholi.tsys_master_code_details(id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CT', 'ACCT','sysadmin', '2022-12-16 12:41:20.737', 'Current', 'Current' ,'sysadmin', '2022-12-22 18:42:22.407');
INSERT INTO putholi.tsys_master_code_details(id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
--Card details
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'OP', 'CARD','sysadmin', '2022-12-16 12:41:20.737', 'Card Payment', 'Card Payment' ,'sysadmin', '2022-12-22 18:42:22.407');
INSERT INTO putholi.tsys_master_code_details(id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'IB', 'CARD','sysadmin', '2022-12-16 12:41:20.737', 'Internet Banking', 'Internet Banking' ,'sysadmin', '2022-12-22 18:42:22.407');


--Updated scripts for super user and admin (18 - 02 - 2023)
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'DELADM', 'STS', 'Admin', '2023-02-18 00:28:14.96', 'Deletion Initiated By Admin', 'Deletion Initiated By Admin', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'DELREV', 'STS', 'Admin', '2023-02-18 00:29:03.088', 'Deletion Initiated By reviewer', 'Deletion Initiated By reviewer', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) VALUES
(nextval('putholi.master_code_details_seq'), 'Y', 'DELAPR', 'STS', 'Admin', '2023-02-18 00:30:53.672', 'Deletion Initiated By Approver', 'Deletion Initiated By Approver', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) VALUES
(nextval('putholi.master_code_details_seq'), 'Y', 'DELUSR', 'STS', 'Admin', '2023-02-18 00:31:15.599', 'Deletion Intiated By Super User', 'Deletion Intiated By Super User', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'DEL', 'STS', 'Admin', '2023-02-18 00:31:36.718', 'Deleted', 'Deleted', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PENSUS', 'STS', 'Admin', '2022-09-01 09:13:27.256', 'Pending Super User Approval', 'Pending Super User Approval', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) VALUES
(nextval('putholi.master_code_details_seq'), 'Y', 'PENSUA', 'STS', 'Admin', '2023-02-18 16:49:33.86', 'Pending super Admin Approval', 'Pending super Admin Approval', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) VALUES 
(nextval('putholi.master_code_details_seq'), 'Y', 'DELSUA', 'STS', 'Admin', '2023-02-18 18:29:23.405', 'Deletion Rejection By Super Admin', 'Deletion Rejection By Super Admin', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) VALUES 
(nextval('putholi.master_code_details_seq'), 'Y', 'CHRADM', 'STS', 'Admin', '2023-02-18 18:29:23.405', 'Change Role Initiated By Admin', 'Change Role Initiated By Admin', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) VALUES 
(nextval('putholi.master_code_details_seq'), 'Y', 'CHRREV', 'STS', 'Admin', '2023-02-18 18:29:23.405', 'Change Role Initiated By Reviewer', 'Change Role Initiated By Reviewer', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) VALUES 
(nextval('putholi.master_code_details_seq'), 'Y', 'CHRAPR', 'STS', 'Admin', '2023-02-18 18:29:23.405', 'Change Role Initiated By Approver', 'Change Role Initiated By Approver', NULL, NULL);


--District
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'ARIY', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Ariyalur', 'Ariyalur', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CGAL', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Chengalpattu', 'Chengalpattu', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CHEN', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Chennai', 'Chennai', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'COIM', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Coimbatore', 'Coimbatore', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'CUDD', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Cuddalore', 'Cuddalore', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'DHRM', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Dharmapuri', 'Dharmapuri', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'DINL', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Dindigul', 'Dindigul', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'EROD', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Erode', 'Erode', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'KRIC', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Kallakurichi', 'Kallakurichi', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'KACH', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Kanchipuram', 'Kanchipuram', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'KUMA', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Kanyakumari', 'Kanyakumari', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'KRUR', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Karur', 'Karur', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'KRIS', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Krishnagiri', 'Krishnagiri', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MDRI', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Madurai', 'Madurai', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'MAYL', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Mayiladuthurai', 'Mayiladuthurai', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'NAGA', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Nagapattinam', 'Nagapattinam', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'NAMA', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Namakkal', 'Namakkal', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'NILG', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Nilgiris', 'Nilgiris', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PERA', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Perambalur', 'Perambalur', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PUDU', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Pudukkottai', 'Pudukkottai', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'RNTP', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Ramanathapuram', 'Ramanathapuram', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'RPET', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Ranipet', 'Ranipet', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SALM', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Salem', 'Salem', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'SVGA', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Sivagangai', 'Sivagangai', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'KASI', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Tenkasi', 'Tenkasi', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TENI', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Theni', 'Theni', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TVUR', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Thanjavur', 'Thanjavur', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TOOT', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Thoothukudi', 'Thoothukudi', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TRIY', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Tiruchirappalli', 'Tiruchirappalli', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'VELI', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Tirunelveli', 'Tirunelveli', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TPHR', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Tirupathur', 'Tirupathur', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TPPU', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Tiruppur', 'Tiruppur', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TVLL', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Tiruvallur', 'Tiruvallur', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TVMI', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Tiruvannamalai', 'Tiruvannamalai', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'TRUR', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Tiruvarur', 'Tiruvarur', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'VELL', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Vellore', 'Vellore', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'VPRM', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Viluppuram', 'Viluppuram', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date)
VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'VIRN', 'DIST', 'Admin', '2023-02-21 00:28:14.96', 'Virudhunagar', 'Virudhunagar', NULL, NULL);

--Application Configuration Details
INSERT INTO putholi.tpuo_application_config(config_id, config_for, config_value, created_by, created_date,description, last_modified_by, last_modified_date, module)
VALUES (nextval('putholi.tpuo_application_config_config_id_seq'), 'Adjustable_Amount', 100, 'admin', '2023-04-04 12:19:25.129', 'Adjustable_Amount', NULL, NULL, '');
INSERT INTO putholi.tpuo_application_config(config_id, config_for, config_value, created_by, created_date,description, last_modified_by, last_modified_date, module)
VALUES (nextval('putholi.tpuo_application_config_config_id_seq'), 'Bank_Processing_Fee', 0.0199, 'admin', '2023-04-04 12:19:25.129', 'Bank_Processing_Fee', NULL, NULL, '');
INSERT INTO putholi.tpuo_application_config(config_id, config_for, config_value, created_by, created_date,description, last_modified_by, last_modified_date, module)
VALUES (nextval('putholi.tpuo_application_config_config_id_seq'),'Trust_Member_Fee', 10000, 'admin', '2023-04-04 12:19:25.129', 'Trust_Member_Fee', NULL, NULL, '');

--NEW UPDATE MASTER CODE SCRIPTS 
UPDATE putholi.tsys_master_code_details
	SET description='Payment Completed' , description_other='Payment Completed'	WHERE code='PAYINI';
	
UPDATE putholi.tsys_master_code_details
	SET description='Payment Partially Completed' , description_other='Payment Partially Completed'	WHERE code='PARPAY';
	
--NEW INSERT MASTER CODE SCRIPTS 
INSERT INTO putholi.tsys_master_code_details 
	(id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PAYFAL', 'STS', 'Admin', '2023-05-17 02:30:51.08', 'Payment Failed', 'Payment Failed', NULL, NULL);

INSERT INTO putholi.tsys_master_code_details 
	(id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) 
	VALUES (nextval('putholi.master_code_details_seq'), 'Y', 'PARFAL', 'STS', 'Admin', '2023-05-17 02:30:51.08', 'Payment Partially Failed', 'Payment Partially Failed', NULL, NULL);

--NEW SELECT MASTER CODE SCRIPTS 
SELECT id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date
	FROM putholi.tsys_master_code_details where code in ('PARFAL', 'PAYFAL', 'PAYINI', 'PARPAY');

-- Category master code scripts
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) VALUES 
(nextval('putholi.master_code_details_seq'), 'Y', 'WEBE', 'CATGY', 'Admin', current_date, 'Website Maintenance Cost', 'Website Maintenance Cost', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) VALUES 
(nextval('putholi.master_code_details_seq'), 'Y', 'AFEE', 'CATGY', 'Admin', current_date, 'Audit Fees', 'Audit Fees', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) VALUES 
(nextval('putholi.master_code_details_seq'), 'Y', 'NPAY', 'CATGY', 'Admin', current_date, 'Newrta Payment', 'Newrta Payment', NULL, NULL);
INSERT INTO putholi.tsys_master_code_details (id, active, code, code_type, created_by, created_date, description, description_other, updated_by, updated_date) VALUES 
(nextval('putholi.master_code_details_seq'), 'Y', 'OTHR', 'CATGY', 'Admin', current_date, 'Others', 'Others', NULL, NULL);
