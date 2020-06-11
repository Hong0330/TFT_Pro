public class MatchDto {
	private MetadataDto matadata;
    private InfoDto info;

    //생성자
    public MatchDto() {
        super();
    }

    public MatchDto(MetadataDto matadata, InfoDto info) {
        super();
        this.matadata = matadata;
        this.info = info;
    }

    //getter,setter
    public MetadataDto getMetadata() {
        return matadata;
    }

    public void setMetadata(MetadataDto matadata) {
        this.matadata = matadata;
    }

    public InfoDto getInfo() {
        return info;
    }

    public void setInfo(InfoDto info) {
        this.info = info;
    }

}
