import java.util.ArrayList;
import java.util.List;

public class UnitDto {
	private int[] items = new int[3];
    private String character_id;	//유닛 이름
    private String name;			
    private int rarity;				//유닛의 골드(0->1골드 유닛,1->2골드유닛 )
    private int tier;				//별의 갯수

    //생성자
    public UnitDto() {
        super();
    }
    
    public UnitDto(int[] items, String character_id, String name, int rarity, int tier) {
        super();
        this.items = items;
        this.character_id = character_id;
        this.name = name;
        this.rarity = rarity;
        this.tier = tier;
    }

    //getter,setter
    public int[] getItems() {
        return items;
    }

    public void setItems(int[] items) {
        this.items = items;
    }
    

    public String getCharacter_id() {
        return character_id;
    }

    public void setCharacter_id(String character_id) {
        this.character_id = character_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

}
