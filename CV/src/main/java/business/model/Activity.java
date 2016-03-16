package business.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Embeddable
public class Activity {
	public enum ActivityType {
		professional_experience, skill, training, other 
	}
	
	@Column(nullable=true)
	private Date date;
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private ActivityType type;
	@Column(nullable=false)
	@NotNull
	private String title;
	@Column(nullable=true)
	private String description;
	@Column(nullable=true)
	private String webAddress;

	/**
	 * Empty constructor
	 */
	public Activity() {}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @return the type
	 */
	public ActivityType getType() {
		return type;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @return the webAddress
	 */
	public String getWebAddress() {
		return webAddress;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(ActivityType type) {
		this.type = type;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @param webAddress the webAddress to set
	 */
	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}
}