public class TraitDto {
	private String name;		//시너지 이름
    private int num_units;		//시너지 적용을받는 유닛수
    private int style; 			//시너지 단계에 따른 스타일
    private int tier_current;	//시너지 적용단계 (0은 효과 적용이 안된 상태)
    private int tier_total;		//총 단계수

    //생성자
    public TraitDto() {
        super();
    }

    public TraitDto(String name, int num_units, int tier_current, int tier_total) {
        super();
        this.name = name;
        this.num_units = num_units;
        this.tier_current = tier_current;
        this.tier_total = tier_total;
    }

    //getter,setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }
    
    public int getNum_units() {
        return num_units;
    }

    public void setNum_units(int num_units) {
        this.num_units = num_units;
    }

    public int getTier_current() {
        return tier_current;
    }

    public void setTier_current(int tier_current) {
        this.tier_current = tier_current;
    }

    public int getTier_total() {
        return tier_total;
    }

    public void setTier_total(int tier_total) {
        this.tier_total = tier_total;
    }

}
