package com.infostretch.nest.bean;

import com.qmetry.qaf.automation.data.BaseDataBean;
import com.qmetry.qaf.automation.util.Randomizer;
import com.qmetry.qaf.automation.util.RandomStringGenerator.RandomizerTypes;

public class ESSBean extends BaseDataBean {
	/*
	 * Description : This will create random data for edit professional experiance
	 */
	@Randomizer(type = RandomizerTypes.LETTERS_ONLY, length = 6)
	private String eexp_jobtit;
	@Randomizer(type = RandomizerTypes.LETTERS_ONLY, length = 8)
	private String eexp_employer;
	
	public String getEexp_jobtit() {
		return eexp_jobtit;
	}

	public void setEexp_jobtit(String eexp_jobtit) {
		this.eexp_jobtit = eexp_jobtit;
	}

	public String getEexp_employer() {
		return eexp_employer;
	}

	public void setEexp_employer(String eexp_employer) {
		this.eexp_employer = eexp_employer;
	}

	/*
	 * Description : This will create random data for edit relationship data
	 */
	@Randomizer(length = 3, type = RandomizerTypes.DIGITS_ONLY)
	private String rel_emp_number;
	@Randomizer(dataset = {"Father", "Mother", "Husband", "Wife", "Brother", "Sister",
			"Child", "Other"})
	private String rel_type;
	@Randomizer(length = 4, type = RandomizerTypes.DIGITS_ONLY)
	private String emp_rel_id;
	@Randomizer(length = 5, type = RandomizerTypes.DIGITS_ONLY)
	private String rel_employee_id;
	public String getRel_emp_number() {
		return rel_emp_number;
	}

	public void setRel_emp_number(String rel_emp_number) {
		this.rel_emp_number = rel_emp_number;
	}

	public String getRel_type() {
		return rel_type;
	}

	public void setRel_type(String rel_type) {
		this.rel_type = rel_type;
	}

	public String getEmp_rel_id() {
		return emp_rel_id;
	}

	public void setEmp_rel_id(String emp_rel_id) {
		this.emp_rel_id = emp_rel_id;
	}

	public String getRel_employee_id() {
		return rel_employee_id;
	}

	public void setRel_employee_id(String rel_employee_id) {
		this.rel_employee_id = rel_employee_id;
	}

	/*
	 * Description : This will create random data for edit personal details
	 */
	@Randomizer(dataset = {"A+","A-","B+","B-","O+","O-","AB+","AB-"})
	private String blood_group;
	@Randomizer(dataset = {"Male","Female"})
	private String gender;
	
	public String getBlood_group() {
		return blood_group;
	}

	public void setBlood_group(String blood_group) {
		this.blood_group = blood_group;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	/*
	 * Description : This will create random data for edit emergency contact details
	 */
	@Randomizer(length = 1, type = RandomizerTypes.DIGITS_ONLY)
	private String eec_seqno;
	@Randomizer(type = RandomizerTypes.LETTERS_ONLY, length = 6)
	private String eec_name;
	@Randomizer(dataset = {"Father", "Mother", "Husband", "Wife", "Brother", "Sister",
			"Child", "Other"})
	private String eec_relationship;
	@Randomizer(length = 10, type = RandomizerTypes.DIGITS_ONLY)
	private String eec_mobile_no;

	public String getEec_seqno() {
		return eec_seqno;
	}

	public void setEec_seqno(String eec_seqno) {
		this.eec_seqno = eec_seqno;
	}

	public String getEec_name() {
		return eec_name;
	}

	public void setEec_name(String eec_name) {
		this.eec_name = eec_name;
	}

	public String getEec_relationship() {
		return eec_relationship;
	}

	public void setEec_relationship(String eec_relationship) {
		this.eec_relationship = eec_relationship;
	}

	public String getEec_mobile_no() {
		return eec_mobile_no;
	}

	public void setEec_mobile_no(String eec_mobile_no) {
		this.eec_mobile_no = eec_mobile_no;
	}

	/*
	 * Description : This will create random data for edit family members
	 */
	@Randomizer(type = RandomizerTypes.LETTERS_ONLY, length = 6)
	private String ed_name;
	@Randomizer(dataset = {"Father", "Mother", "Husband", "Wife", "Brother", "Sister",
			"Child", "Other"})
	private String ed_relationship_type;

	public void setEd_name(String ed_name) {
		this.ed_name = ed_name;
	}

	public String getEd_name() {
		return ed_name;
	}

	public String getEd_relationship_type() {
		return ed_relationship_type;
	}

	public void setEd_relationship_type(String ed_relationship_type) {
		this.ed_relationship_type = ed_relationship_type;
	}

	/*
	 * Description : This will create random data for delete family members
	 */
	@Randomizer(length = 1, type = RandomizerTypes.DIGITS_ONLY)
	private String ed_seqno;

	public String getEd_seqno() {
		return ed_seqno;
	}

	public void setEd_seqno(String ed_seqno) {
		this.ed_seqno = ed_seqno;
	}
}
