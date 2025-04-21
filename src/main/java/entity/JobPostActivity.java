package entity;


import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name ="job_post_activity")
public class JobPostActivity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer jobPostId;
	
	@ManyToOne
	@JoinColumn(name ="postedById", referencedColumnName = "userId" )
	private Users postById;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name ="jobLocationId", referencedColumnName = "Id")
	private JobLocation jobLocationId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "jobCompanyId", referencedColumnName = "Id")
	private JobCompany jobCompanyId; 
	
	@Transient
	private Boolean isActive=true;
	
	@Transient
	private Boolean saved;
	
	@Length(max =10000)
	private String descriptionOfJob;
	
	private String jobType;
	
	private String salary;
	
	private String remote;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date postedDate;
	
	private String jobTitle;

	public JobPostActivity(Integer jobPostId, Users postById, JobLocation jobLocationId, JobCompany jobCompanyId,
			Boolean isActive, Boolean saved, @Length(max = 10000) String descriptionOfJob, String jobType,
			String salary, String remote, Date postedDate, String jobTitle) {
		super();
		this.jobPostId = jobPostId;
		this.postById = postById;
		this.jobLocationId = jobLocationId;
		this.jobCompanyId = jobCompanyId;
		this.isActive = isActive;
		
		this.saved = saved;
		this.descriptionOfJob = descriptionOfJob;
		this.jobType = jobType;
		this.salary = salary;
		this.remote = remote;
		this.postedDate = postedDate;
		this.jobTitle = jobTitle;
	}

	public JobPostActivity() {
		super();
	}

	public Integer getJobPostId() {
		return jobPostId;
	}

	public void setJobPostId(Integer jobPostId) {
		this.jobPostId = jobPostId;
	}

	public Users getPostById() {
		return postById;
	}

	public void setPostById(Users postById) {
		this.postById = postById;
	}

	public JobLocation getJobLocationId() {
		return jobLocationId;
	}

	public void setJobLocationId(JobLocation jobLocationId) {
		this.jobLocationId = jobLocationId;
	}

	public JobCompany getJobCompanyId() {
		return jobCompanyId;
	}

	public void setJobCompanyId(JobCompany jobCompanyId) {
		this.jobCompanyId = jobCompanyId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsSaved() {
		return saved;
	}

	public void setIsSaved(Boolean saved) {
		this.saved = saved;
	}

	public String getDescriptionOfJob() {
		return descriptionOfJob;
	}

	public void setDescriptionOfJob(String descriptionOfJob) {
		this.descriptionOfJob = descriptionOfJob;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getSalary() {
		return salary;
	}
	
	

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getRemote() {
		return remote;
	}

	public void setRemote(String remote) {
		this.remote = remote;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	@Override
	public String toString() {
		return "JobPostActivity [jobPostId=" + jobPostId + ", postById=" + postById + ", jobLocationId=" + jobLocationId
				+ ", jobCompanyId=" + jobCompanyId + ", isActive=" + isActive + ", isSaved=" + saved
				+ ", descriptionOfJob=" + descriptionOfJob + ", jobType=" + jobType + ", salaray=" + salary
				+ ", remote=" + remote + ", postedDate=" + postedDate + ", jobTitle=" + jobTitle + "]";
	}
	
	
	
}
