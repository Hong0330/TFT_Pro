public class SummonerDTO {
	//필드
    private String accountId;
    private int profileIconId;	//프로필 아이콘아이디
    private long revisionDate;
    private String name;		//닉네임
    private String id;			//아이디
    private String puuid;		// puuid
    private long summonerLevel;	//소환사레벨

    //생성자
    public SummonerDTO() {
        super();
    }

    public SummonerDTO(String accountId,int profileIconId,long revisionDate,String name,String id,String puuid,long summonerLevel) {
        super();
        this.accountId = accountId;
        this.profileIconId = profileIconId;
        this.revisionDate = revisionDate;
        this.name = name;
        this.id = id;
        this.puuid = puuid;
        this.summonerLevel = summonerLevel;
    }

    //getter,setter
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(int profileIconId) {
        this.profileIconId = profileIconId;
    }

    public long getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(long revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPuuid() {
        return puuid;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    public long getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(long summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public String toString(){
        return "name : " + name + " summonerLevel : " + summonerLevel;
    }
}
