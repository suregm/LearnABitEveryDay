package bitBeat.YunYangHighSchool;

/**
 * Created by sure GM on 2016/5/21 20:13.
 */
public class Record {
	private String name;
	private String gender;
	private String email;
	private String qq;
	private String msn;
	private String homepage;
	private String regdate;
	private String artnum;

	public Record()
	{
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getQq()
	{
		return qq;
	}

	public void setQq(String qq)
	{
		this.qq = qq;
	}

	public String getMsn()
	{
		return msn;
	}

	public void setMsn(String msn)
	{
		this.msn = msn;
	}

	public String getHomepage()
	{
		return homepage;
	}

	public void setHomepage(String homepage)
	{
		this.homepage = homepage;
	}

	public String getRegdate()
	{
		return regdate;
	}

	public void setRegdate(String regdate)
	{
		this.regdate = regdate;
	}

	public String getArtnum()
	{
		return artnum;
	}

	public void setArtnum(String artnum)
	{
		this.artnum = artnum;
	}

	public Record(String name, String gender, String email, String qq, String msn, String homepage, String regdate, String artnum)
	{
		super();
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.qq = qq;
		this.msn = msn;
		this.homepage = homepage;
		this.regdate = regdate;
		this.artnum = artnum;
	}
}
