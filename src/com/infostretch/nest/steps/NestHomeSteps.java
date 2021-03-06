package com.infostretch.nest.steps;
import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.infostretch.nest.bean.HomeBean;
import com.infostretch.nest.providers.EndPoints;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.CommonUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;

public class NestHomeSteps {
	JSONObject jsonObject;
	JsonObject jsonObjectResult, responseBody;
	Response response;
	JsonArray jsonArrayResult;
	int index;
	HomeBean homeBean = new HomeBean();

	@QAFTestStep(description = "user should get accessible menu list")
	public void verifyAccessibleMenuList() {
		ClientUtils.getWebResource(EndPoints.ACCESSIBLE_MENU_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();

		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);
		JsonArray param1reaults =
				jsonArrayResult.get(0).getAsJsonObject().get("manager").getAsJsonArray();
		Validator.verifyThat(
				(param1reaults.get(0).getAsJsonObject()).get("name").toString(),
				Matchers.notNullValue());
		Validator.verifyThat(
				(param1reaults.get(0).getAsJsonObject()).get("Admin").toString(),
				Matchers.notNullValue());
		Validator.verifyThat(
				param1reaults.get(0).getAsJsonObject().get("Admin").getAsJsonObject()
						.get("0").getAsJsonObject().get("url").toString(),
				Matchers.notNullValue());
	}

	@QAFTestStep(description = "user should get all leave types")
	public void userShouldGetAllLeaveTypes() {
		ClientUtils.getWebResource(EndPoints.GET_ALL_LEAVE_TYPES)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		jsonObjectResult = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("IN").getAsJsonObject();
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "PTO");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "Comp Off");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "LWP");
	}

	@QAFTestStep(description = "user should get menu urls")
	public void userShouldGetMenuUrls() {
		ClientUtils.getWebResource(EndPoints.GET_MENU_URLS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		CommonUtils.getValidatedResultArray(response);
	}

	@QAFTestStep(description = "user should get release data")
	public void userShouldGetReleaseData() {
		ClientUtils.getWebResource(EndPoints.GET_RELEASE_DATA)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("release_note_id").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject()).get("title").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject()).get("release_date").toString(),
					Matchers.notNullValue());
		}
  }
	
	@QAFTestStep(description = "user should get my leave list")
	public void userShouldGetMyLeaveList() {

		ClientUtils.getWebResource(EndPoints.GET_USER_LEAVE_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		if (responseBody.toString().contains("2270")) {
			jsonObjectResult = CommonUtils.getValidateResultObject(response);
			jsonObjectResult = responseBody.get("response").getAsJsonObject().get("results")
					.getAsJsonObject().get("2270").getAsJsonObject();
			CommonUtils.validateParameterInJsonObject(jsonObjectResult, "emp_initial");
			CommonUtils.validateParameterInJsonObject(jsonObjectResult, "date");
			CommonUtils.validateParameterInJsonObject(jsonObjectResult, "leave_request_id");
			CommonUtils.validateParameterInJsonObject(jsonObjectResult, "leave_status");
		} else {
			JsonObject result = CommonUtils.getValidateResultObject(response);
			Reporter.log(result.toString());
		}
	}

	@QAFTestStep(description = "user should get leave balances")
	public void userShouldGetLeaveBalances() {
		ClientUtils.getWebResource(EndPoints.GET_LEAVE_BALANCES)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		JsonArray param1results = jsonObjectResult.get("regular").getAsJsonArray();
		JsonArray param2results = jsonObjectResult.get("special").getAsJsonArray();

		for (index = 0; index <= param1results.size() - 1; index++) {
			Validator.verifyThat((param1results.get(index).getAsJsonObject())
					.get("leaveTypeId").toString(), Matchers.containsString("LTY"));
			Validator.verifyThat((param1results.get(index).getAsJsonObject())
					.get("leaveType").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(param1results.get(index).getAsJsonObject()).get("number").toString(),
					Matchers.notNullValue());
		}

		for (index = 0; index <= param2results.size() - 1; index++) {
			Validator.verifyThat((param2results.get(index).getAsJsonObject())
					.get("leaveTypeId").toString(), Matchers.containsString("LTY"));
			Validator.verifyThat((param2results.get(index).getAsJsonObject())
					.get("leaveType").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(param2results.get(index).getAsJsonObject()).get("number").toString(),
					Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get floating holiday list")
	public void userShouldGetFloatingHolidayList() {
		ClientUtils.getWebResource(EndPoints.GET_FLOATING_HOLIDAY_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat((jsonArrayResult.get(0).getAsJsonObject())
					.get("floating_holiday_id").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(jsonArrayResult.get(0).getAsJsonObject()).get("description").toString(),
					Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get holiday list")
	public void userShouldGetHolidayList() {
		ClientUtils.getWebResource(EndPoints.GET_HOLIDAY_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat(
					(jsonArrayResult.get(0).getAsJsonObject()).get("holiday_id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(jsonArrayResult.get(0).getAsJsonObject()).get("description").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(jsonArrayResult.get(0).getAsJsonObject()).get("date").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(jsonArrayResult.get(0).getAsJsonObject()).get("location").toString(),
					Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get training calendar list")
	public void userShouldGetTrainingCalendarList() {
		homeBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("start_date", homeBean.getStart_date());
		jsonObject.put("end_date", homeBean.getEnd_date());
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(EndPoints.GET_TRAINING_CALENDAR_LIST)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonArrayResult = jsonObjectResult.get("Friday").getAsJsonArray();

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("trn_course_id").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject()).get("title").toString(),
					Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("department_id").toString(), Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "User should get upcoming events")
	public void userShouldGetUpcomingEvents() {
		homeBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("eventlist", homeBean.getEventlist());
		jsonObject.put("order", homeBean.getOrder());
		jsonObject.put("sort", homeBean.getSort());
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(EndPoints.GET_UPCOMING_EVENTS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "main_location_name");
	}

	@QAFTestStep(description = "User should get event list")
	public void userShouldGetEventList() {
		ClientUtils.getWebResource(EndPoints.GET_EVENT_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject()).get("title").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject()).get("link").toString(),
					Matchers.notNullValue());
		}
	}
}
