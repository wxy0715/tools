package wxy.shiro.shiro.domain;

/**
 * @author wxxy
 */
public class Users {
	private Integer id;
	private String username;
	private String password;
	private String perm;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPerm() {
		return perm;
	}

	public void setPerm(String perm) {
		this.perm = perm;
	}

	@Override
	public String toString() {
		return "Users{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", perm='" + perm + '\'' +
				'}';
	}
}
