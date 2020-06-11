
public class CompanionDto {	//꼬마 전설이 정보
	private String content_ID;
    private int skin_ID;
    private String species;		//꼬마 전설이 이름

    //생성자
    public CompanionDto() {
        super();
    }

    public CompanionDto(String content_ID, int skin_ID, String species) {
        super();
        this.content_ID = content_ID;
        this.skin_ID = skin_ID;
        this.species = species;
    }

    //getter,setter
    public String getContent_ID() {
        return content_ID;
    }

    public void setContent_ID(String content_ID) {
        this.content_ID = content_ID;
    }

    public int getSkin_ID() {
        return skin_ID;
    }

    public void setSkin_ID(int skin_ID) {
        this.skin_ID = skin_ID;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

}
