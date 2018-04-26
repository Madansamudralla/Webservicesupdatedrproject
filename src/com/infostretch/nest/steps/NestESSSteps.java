package com.infostretch.nest.steps;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.infostretch.nest.providers.ESSEndPoints;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.CommonUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.NotYetImplementedException;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;

public class NestESSSteps {
	int index;
	JSONObject obj;
	JsonObject result;
	JsonArray param1results;
	@QAFTestStep(description = "user should get-basic-details")
	public void userShouldGetBasicDetails() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));

		ClientUtils.getWebResource(ESSEndPoints.GET_BASIC_DETAILS)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "emp_firstname");
		CommonUtils.validateParameterInJsonObject(result, "ename");

		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should get-personal-details")
	public void userShouldGetPersonalDetails() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_PERSONAL_DETAILS)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "emp_number");
		CommonUtils.validateParameterInJsonObject(result, "emp_gender");


		
	}

	@QAFTestStep(description = "user should get-base-location")
	public void userShouldGetBaseLocation() {
		ClientUtils.getWebResource(ESSEndPoints.GET_BASE_LOCATION)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		JsonArray results = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("city").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("country_code").toString(),
					Matchers.notNullValue());

		}

		Validator.verifyThat(results.size(), Matchers.greaterThan(0));
	}

	@QAFTestStep(description = "user should get idenity details")
	public void userShouldGetIdenityDetails() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_IDENTITY_DATAILS)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "emp_dri_lice_num");
		Reporter.log("Emp Number : "
				+ CommonUtils.validateParameterInJsonObject(result, "emp_number"));
	}

	@QAFTestStep(description = "user should get RR count")
	public void userShouldGetRRCount() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_RR_COUNT)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);

		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should get priviledge list")
	public void userShouldGetPriviledgeList() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_PRIVILEGES_LIST)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		JsonArray param1results = result.get("details").getAsJsonArray();
		for (index = 0; index <= param1results.size() - 1; index++) {
			Validator.verifyThat((param1results.get(index).getAsJsonObject())
					.get("sub_privileges").toString(), Matchers.notNullValue());
		}
		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "user should get employee goal hobbies")
	public void userShouldGetEmployeeGoalHobbies() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_EMPLOYEE_GOALS_HOBBIES)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		JsonObject responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		result = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("details").getAsJsonObject();
		CommonUtils.validateParameterInJsonObject(result, "emp_hobbies");
	}

	@QAFTestStep(description = "user should edit employee goals")
	public void userShouldEditEmployeeGoals() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_long_term_goals", "Learn automation on nest internal poject");

		obj.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.EDIT_EMPLOYEE_GOALS)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "response_type");

		Reporter.log("Emp Number : " + CommonUtils.getValidateResultObject(response));
	}

	@QAFTestStep(description = "user should edit employee hobbies")
	public void userShouldEditEmployeeHobbies() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.EDIT_EMPLOYEE_GOALS)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "response_type");
		Reporter.log("Emp Number : " + CommonUtils.getValidateResultObject(response));
	}

	@QAFTestStep(description = "user should get country name")
	public void userShouldGetCountryName() {
		ClientUtils.getWebResource(ESSEndPoints.GET_COUNTRY_NAME)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		JsonArray results = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("cou_code").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("cou_name").toString(),
					Matchers.notNullValue());
		}

		Validator.verifyThat(results.size(), Matchers.greaterThan(0));
	}

	@QAFTestStep(description = "user should get emergenct contact details")
	public void userShouldGetEmergenctContactDetails() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_EMEGENCY_CONTACT_DETAILS)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		JsonArray param1results = result.get("details").getAsJsonArray();
		for (index = 0; index <= param1results.size() - 1; index++) {
			Validator.verifyThat((param1results.get(index).getAsJsonObject())
					.get("eec_name").toString(), Matchers.notNullValue());
			Validator.verifyThat((param1results.get(index).getAsJsonObject())
					.get("eec_relationship").toString(), Matchers.notNullValue());
			Validator.verifyThat((param1results.get(index).getAsJsonObject())
					.get("eec_mobile_no").toString(), Matchers.notNullValue());

		}

		Reporter.log("Emp Number : " + CommonUtils.getValidateResultObject(response));
	}

	@QAFTestStep(description = "user should get family memebers")
	public void userShouldGetFamilyMemebers() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_FAMILY_MEMEBERS)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		JsonArray param1results = result.get("details").getAsJsonArray();
		for (index = 0; index <= param1results.size() - 1; index++) {
			Validator.verifyThat((param1results.get(index).getAsJsonObject())
					.get("ed_name").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(param1results.get(index).getAsJsonObject())
							.get("ed_relationship_type").toString(),
					Matchers.notNullValue());

		}
		Reporter.log("Emp Number : " + CommonUtils.getValidateResultObject(response));
	}

	@QAFTestStep(description = "user should get employee relationship details")
	public void userShouldGetEmployeeRelationshipDetails() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_EMPLOYEE_RELATIONSSHIP_DETAILS)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		JsonArray param1results = result.get("details").getAsJsonArray();
		for (index = 0; index <= param1results.size() - 1; index++) {
			Validator.verifyThat((param1results.get(index).getAsJsonObject())
					.get("ed_name").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(param1results.get(index).getAsJsonObject())
							.get("ed_relationship_type").toString(),
					Matchers.notNullValue());
		}
		Reporter.log("Emp Number : " + CommonUtils.getValidateResultObject(response));
	}

	@QAFTestStep(description = "user should get role title")
	public void userShouldGetRoleTitle() {
		ClientUtils.getWebResource(ESSEndPoints.GET_ROLE_TITLE)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		JsonArray results = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("job_title").toString(),
					Matchers.notNullValue());

		}

		Validator.verifyThat(results.size(), Matchers.greaterThan(0));
	}

	@QAFTestStep(description = "user should get employee-location-privileges")
	public void userShouldGetEmployeeLocationPrivileges() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_EMPLOYEE_LOCAION_PRIVILEDGES)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		JsonArray param1results = result.get("details").getAsJsonArray();
		for (index = 0; index <= param1results.size() - 1; index++) {
			Validator.verifyThat((param1results.get(index).getAsJsonObject())
					.get("admin_loc").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(param1results.get(index).getAsJsonObject()).get("loc_id").toString(),
					Matchers.notNullValue());
		}
		Reporter.log("Emp Number : " + CommonUtils.getValidateResultObject(response));
	}

	
	
	@QAFTestStep(description = "user should get-Include")
	public void userShouldGetInclude() {
		obj = new JSONObject();
		obj.put("token", "db453c12423112f3ffb4a2b473139ed6");
		System.out.println("#########"+obj.toString());

		ClientUtils.getWebResource(ESSEndPoints.GET_INCLUDE)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "1");
		CommonUtils.validateParameterInJsonObject(result, "2");
		CommonUtils.validateParameterInJsonObject(result, "3");
		Reporter.log("Emp Number : " + CommonUtils.getValidateResultObject(response));
}

	@QAFTestStep(description = "user should /get-Supervisors-Name")
	public void userShouldGetSupervisorsName() {
		obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.GET_SUPERVISORS_NAME)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonArray results = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("ename").toString(),
					Matchers.notNullValue());
		}
		

		Validator.verifyThat(results.size(), Matchers.greaterThan(0));
	}
	
	

	@QAFTestStep(description = "user should /get-employee-status-for-teams-list")
	public void userShouldGetEmployeeStatusForTeamsList() {
		obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.GET_EMPLOYEE_STATUS_FOR_TEAMS_LIST)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "Appointed");
		CommonUtils.validateParameterInJsonObject(result, "Initiated_Confirmation");
		CommonUtils.validateParameterInJsonObject(result, "Confirmed");
		CommonUtils.validateParameterInJsonObject(result, "Left");
		CommonUtils.validateParameterInJsonObject(result, "Resigned");
		CommonUtils.validateParameterInJsonObject(result, "Terminated");
		Reporter.log("Emp Number : " + CommonUtils.getValidateResultObject(response));
		
	
	}

	@QAFTestStep(description = "user should /get-teams-profile-list")
	public void userShouldGetTeamsProfileList() 
	{
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
		ClientUtils.getWebResource(ESSEndPoints.GET_TEAMS_PROFILE_LIST)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		JsonArray param1results = result.get("details").getAsJsonArray();
		for (index = 0; index <= param1results.size() - 1; index++) {
			Validator.verifyThat((param1results.get(index).getAsJsonObject())
					.get("empNumber").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(param1results.get(index).getAsJsonObject()).get("employeeId").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(param1results.get(index).getAsJsonObject()).get("firstName").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(param1results.get(index).getAsJsonObject()).get("lastName").toString(),
					Matchers.notNullValue());
		}
	}
	

	@QAFTestStep(description = "user should /add-new-employee")
	public void userShouldAddNewEmployee() {
	
	}

	@QAFTestStep(description = "user should /get-location-for-new-emp")
	public void userShouldGetLocationForNewEmp() {
		obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ESSEndPoints.GET_LOCATION_FOR_NEW_EMP)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		JsonArray results = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("location_id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("location_name").toString(),
					Matchers.notNullValue());

		}
		

	}

	@QAFTestStep(description = "user should /delete-Employee")
	public void userShouldDeleteEmployee() {
		obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
	JSONObject	obj1 =new JSONObject();
	
		obj1.put("emp_number",
				ConfigurationManager.getBundle().getPropertyValue("emp_id"));
	obj.put("emp_details", obj1);
	
		ClientUtils.getWebResource(ESSEndPoints.DELETE_EMPLOYEE)
				.type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		 result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "action_message");
		CommonUtils.validateParameterInJsonObject(result, "response_type");


	}


}
