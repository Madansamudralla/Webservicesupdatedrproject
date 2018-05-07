package com.infostretch.nest.steps;

import javax.ws.rs.core.MediaType;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.infostretch.nest.bean.ReimbursementBean;
import com.infostretch.nest.providers.ReimbursementEndPoints;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.CommonUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;

public class NestReimbursementSteps {
	int index;
	JSONObject jsonObject, jsonObject1;
	Response response;
	JsonObject jsonObjectResult;
	JsonArray jsonArrayResult;
	ReimbursementBean reimbursementBean = new ReimbursementBean();

	@QAFTestStep(description = "user should get all his expense list")
	public void userShouldGetAllHisExpenseList() {
		ClientUtils.getWebResource(ReimbursementEndPoints.GET_ALL_HIS_EXPENSE_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);
		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("reimbursement_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("emp_number").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("department_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("display_name").toString(), Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get expense details list")
	public void userShouldGetExpenseDetailsList() {
		reimbursementBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("reimbursement_id", reimbursementBean.getReimbursement_id());
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ReimbursementEndPoints.GET_EXPENSE_DETAILS_LIST)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "reimbursement_id");
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonArrayResult = jsonObjectResult.get("reimbursementotherdata").getAsJsonArray();

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("reimbursement_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("title").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("requested_date").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("payment_date").toString(), Matchers.notNullValue());
		}
  }

	@QAFTestStep(description = "user should get expense travel request")
	public void userShouldGetExpenseTravelRequest() {
		ClientUtils.getWebResource(ReimbursementEndPoints.GET_EXPENSE_TRVAVEL_REQUEST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);
	}

	@QAFTestStep(description = "user should get expense currency list")
	public void userShouldGetExpenseCurrencyList() {
		ClientUtils.getWebResource(ReimbursementEndPoints.GET_EXPENSE_CURRENCY_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);
		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("currency_type_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("base_currency").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("symbol").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("title").toString(), Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get expense user project list")
	public void userShouldGetExpenseUserProjectList() {
		ClientUtils.getWebResource(ReimbursementEndPoints.GET_EXPENSE_USER_PROJECT_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);
		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject()).get("id").toString(),
					Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get list of location category")
	public void userShouldGetListOfLocationCategory() {
		ClientUtils.getWebResource(ReimbursementEndPoints.GET_LIST_OF_LOCATION_CATEGORY)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);
		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject()).get("id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject())
							.get("category_description").toString(),
					Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("updated_date").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("location").toString(), Matchers.notNullValue());
		}

	}
	@QAFTestStep(description = "user should get netsuite expense category list")
	public void userShouldGetNetsuiteExpenseCategoryList() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils
				.getWebResource(ReimbursementEndPoints.GET_NETSUITE_EXPENSE_CATEGOTY_LIST)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonArrayResult = jsonObjectResult.get("details").getAsJsonArray();

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject())
							.get("netsuite_category_id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject())
							.get("netsuite_category_id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject())
							.get("netsuite_category_title").toString(),
					Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should edit netsuite expense category status")
	public void userShouldEditNetsuiteExpenseCategoryStatus() {
		reimbursementBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("netsuite_category_id",
				reimbursementBean.getNetsuite_category_id());
		jsonObject.put("status", reimbursementBean.getStatus());
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils
				.getWebResource(
						ReimbursementEndPoints.EDIT_NETSUITE_EXPENSE_CATEGORY_STATUS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult,
				"netsuite_category_id");
	}
}