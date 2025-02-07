export const MessagesData={
 "error": {
    "REQUIRED": {

        "Password" : "Enter password",
        "Username" : "Enter username",
        "oldPassword" : "Enter current password",
        "newPassword" : "Enter new password",
        "confirmPassword" : "Enter confirm password",
        "Email" : "Invalid email id",      
        "firstname" : "Enter first Name",      
        "lastname" : "Enter last Name",      
        "username" : "Enter user name",
        "mobile" : "Enter mobile number",
        "Title" : "Enter title ",
        "content": "Enter Content",
        "metaTitle": "Enter metaTitle",
        "metaKeyword": "Enter metaKeyword",
        "metaDescription":"Enter metaDescription" ,
        "pageId":"Enter pageId" ,
        "pageName": "Enter pageName",
        "pageUrl" :"Enter PageURL",
        "publishgroup":"Enter Publishgroup",
        "Error":"This field cannot be left blank"
    },
    "PATTERN": {
        "Age" : "Please enter proper Age",
        "Mobile" : "Phone number can not be less than 4 characters",
        "Email" : "Invalid email id",
        "Password" : "Password must contain at least one letter and one digit",
        "Username" : "Invalid Username",
        "Contactno" : "Invalid Contact Number"
    },       
    "MINLENGTH": {
        "mobile" : "Mobile number should be 10 digits",
        "Password" : "Password must be atleast 6 characters",
        "Distance" : "Distance should be minimum 1 characters",
        "PinNo" :    "Please enter minimum 6 pin number",
        "FirstName" :"FirstName should not be more than 5 chartacters",
        "LastName" : "LastName should not be more than 5 chartacters",
        "Username" : "Username must be atleast 5 characters"
    },
    "CUSTOM": {
        "ConfirmPassword" : "Confirm password does not match!",
        "Location" : "Please enter valid location",
        "Subject" : "Please select subject from available list"
    },
    
        "title": "Error page!",
        "http": {
            "400": "Bad request.",
            "403": "You are not authorized to access this page.",
            "405": "The HTTP verb you used is not supported for this URL.",
            "500": "Internal server error."
        },
        "concurrencyFailure": "Another user modified this data at the same time as you. Your changes were rejected.",
        "validation": "Validation error on the server.",
        "pendingTask": "There is a pending task on this record.",
        "NoTaskExists": "This task is already withdrawn or actioned.",
        "corporateIdNotExist": "This specified corporate does not exist",
        "UserNotAllowedTaskAction": "User not allowed to perform this action.",
        "userNotPermittedToMapOwnRole": "User not allowed to map or unmap his own role.",
        "appointMultiConsultantFailure": "Multiple consultants cannot be appointed for a business unit",
        "noActiveMember" : "No active member relationship exists for this customer",
        "couldNotBeProcessed" : "Error occurred while processing this task",
        "businessUnit":
        {
            "ShortNameExist": "This short name already exists",
            "idexists": "A new business unit cannot already have an existing ID",
            "erpcodealreadyexists" : "ERP code already exists"
        },
        "approvalPolicy":{
	      	"policyNameExist":"This approval policy already exists.",
	      	"actionsExist":"Actions are assigned to this approval policy",
            "taskPendingForEntity": "There is a Pending Task for this Entity.",
            "policyCannotBeModified" :"There are pending approval records using this policy. Modification is not permitted."
      	},
        "role":{
	        "ShortNameExist": "This role already exists",
	        "idexists":"A new role cannot already have an existing ID",
	        "orgNotExist":"This specified organisation does not exist",
	        "UsersMappedToRole":"There are active users within this role, this role cannot be deleted.",
	        "ApprovalPolicyMappedToRole":"This role is already been used in an approval policy.",
	        "SecurityPolicyMappedToRole":"This role is already been used in a security policy.",
	        "roleAlreadyExist":"This role already exists."
        },
        "userNotAllowedForThisOrgansiation": "User not allowed for this organisation action",
        "organsiationIDNotExist": "Organisation does not exist",
        "taskPendingForEntity": "Process with pending task cannot be altered/deleted.",
		"TaskActionNotValid": "This task has already been actioned by another user. Please refresh.",
		"TaskAlreadyApproved": "You cannot discard this change request since it has been already approved by another trading member",
		"TaskAlreadyApprovedByAtleastOneUser": "You cannot discard this change request since it has been already approved by another user",
		"alertConfiguration":{
			"alertConfigurationExist":"This alert configuration already exists",
			"idexists":"A new alert configuration cannot already have an existing ID"
		},
		"executeAlertNotifications":{
			"noAlertExist": "This alert configuration does not exists",
			"errorWhileExecuteAlert": "Error while executing alert"
		},
		"memberRegistration":{
			"memberExists":"Already this member name is registered in the system",
			"emailexists":"Already this Email is registered by some other client",
			"CPPIDExists":"Already this CCP Membership ID is used in the system.",
			"memberMemberNoExists":"Already this Member No is used in the system."
	},
	"branch" : {
			"branchExists" : "Already this member & branch name combination in the system",
			"sameMemberRelationshipExists": "Already this customer is having relationship with similar member"
	},
	"userProfile" : {
		"userProfileUpdatedSuccessfully" : "User Profile Details Updated successfully",
		"preferencesUpdatedSuccessfully" : "Preferences updated successfully",
		"taskPendingForEntity": "Already there is a change request waiting for the approval"
		
	},
	"applicationWideSettings" : {
		"updateConfig" : "Config Updated Successfully"
	},
	"eodProcess":{
		"errorInSendingEODProcessEmails" : "Error in sedning EOD Emails",
		"errorInGettingCurrentBusinessDate" : "Error in getting current cystem date for which EOD has to be done",
		"errorInGettingNextValidBusinessDate" : "Error in getting Next Valid Business Date"
	},
	"factorManagement":{
		"factorExists":"This short name already exists for a factor...!",
		"riskFactorCodeExists":"This risk factor code already exists for a factor...!"
	},
	"manualRun":{
		"errorInReExecutingProcess":"Error in re-executing process, Process failed...!",
		"holiday":"Manul Run cannot be processed for holidays...!",
		"errorInProcessing":"Error in processing..!",
		"manualRunInProgress":"Manual Run in Progress...!",
		"errorInUploadingFiles":"Error in uploading manually generated files...!",
		"uploadFailure":"Uploading files failed..! Please check the File Upload Exception Report for exception details...!"
	}, 
	"specialHolidays":
		{
			"specialHolidaysExists":"A Special holiday already exists for the given date",
			"idexists" :"A new Special Holiday cannot already have an ID"
		},
		 "activeuserexists": "Given PID is already associated with an another entity",
		"personalidnotfound": "Invalid personal ID",
		"invalidCredentials": "Invalid Credentials",
		"AlreadyRecordsInPendingStatus" : "Already Email change request is waiting for activation waiting for activation..",
		"InvalidOTP" : "Invalid OTP.",
		"emailAlreadyUsed": "Email ID is already used",
		"primaryEmailAlreadyUsed": "Primary email ID is already used",
		"alternateEmailAlreadyUsed": "Primary email ID is already used",
		"mobileAlreadyUsed": "Mobile Number is already used",
		"primaryMobileAlreadyUsed": "Primary mobile is already used",
		"alternateMobileAlreadyUsed": "Primary mobile is already used",
		"loginAlreadyUsed": "Given login name is already in use, please change.",
		"hasOneMemberRelationship": "Deletion not possible! The customer should have atleast one active member relationship. ",
		"hasNoMemberRelationship": "Customer has no relationship with this member",
		"hasAlreadyMemberRelationship": "Customer has already relationship with this member",
		"hasAlreadyAddPendingRequest": "Customer has already pending request to add this member",
		"hasAlreadyRemovePendingRequest": "Customer has already pending request to remove this member",
		"exceedspermissibleusers": "New user creation is not permitted, exceeds the permissible user's count for this member...!",
		"DataNotFound":"Data Not Found",
		"incorrectPassword":"Invalid Credentials",
		"DuplicateMemberName" : "Already this member name is registered in the system",
		"DuplicateCCPMembershipID" : "CCP Membership Id is already registered in the system.",
		"DuplicateMemberMemberNo" : "Already this Member No is mapped to another member in the system",
		"DuplicateLoginName" : "Given login name of user 1 is already in use, please change.",
		"DuplicateEmail" : "Given Email of user 1 is already in use, please change.",
		"DuplicateMobile" : "Given Mobile of user 1 is already in use, please change.",
		"DuplicateLoginNameUser2" : "Given login name of user 2 is already in use, please change.",
		"DuplicateEmailUser2" : "Given Email of user 2 is already in use, please change.",
		"DuplicateMobileUser2" : "Given Mobile of user 2 is already in use, please change.",
		"DuplicateBranch" : "The given branch name is already registered, please change.",
		"DuplicateIFSCCode" : "Given IFSC / Swift code is already in use, please change.",
		"ExceedsPermissibleCount" : "Permissible user's count exceeds the currently available active user's in the system",
		"MoreThanOneUser":"More than 1 record is available for the given inputs. Please specify the Aadhar number and try again",
		"IncorrectPassword": "Incorrect current password",
		"customerNotExists": "The specified Customer does not exisit",
		"PanNumberAlreadyUsed": "PAN Number is already used",
		"UserNotAllowedToTakeActionOnHisOwnProfile": "User is not allowed to take action on his own profile.",
		"UserCannotChangeHisOwnProfile": "User is not allowed to modify his own profile",
		"maxNumberOfPermissibleMemberRelationship":"Add member request cannot be made. Exceeds the number of permissible member relationship for a customer.",
		"noChangesDone":"No changes done",
		"emailSettingsExists":"Email Settings exists for this email category. Please discard...!",
		"approvalPolicyNotAvailable":"Approval Policy not available; Please discard...!",
		"UsersMappedToRole":"There are active users within this role, this role cannot be deleted.",
		"approvalPolicyMappedToRole":"This role is already been used in an approval policy.",
		"securityPolicyMappedToRole":"This role is already been used in a security policy.",
		"roleNotAvailable":"The mapped role is not available",
		"approvalPolicyCannotBeDeleted":"There are pending approval records using this policy. Modification is not permitted.",
		"actionsExistsForApprovalPolicy": "Actions are assigned to this approval policy",
		"scenarioType":{
			"pastEffectiveFrom":"Effective from should be greater than current business date",
			"effectiveFromShouldBeMonthStartDate":"Effective from should be next month start date",
			"scenarioAlreadyGenerated":"Scenario already generated",
			"shortNameExists":"Short name already exists"
		}
    }


}