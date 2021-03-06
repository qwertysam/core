package net.qwertysam.api.language;

public enum Language
{
	ENGLISH("English", "English"), AMERICAN_ENGLISH("American English", "English"), FRENCH("French", "Francais");
	
	private String englishName;
	private String nativeName;
	
	Language(String englishName, String nativeName)
	{
		this.englishName = englishName;
		this.nativeName = nativeName;
	}
	
	public String getEnglishName()
	{
		return englishName;
	}
	
	public String getNativeName()
	{
		return nativeName;
	}
}
