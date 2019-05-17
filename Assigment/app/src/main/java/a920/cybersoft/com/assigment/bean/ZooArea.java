package a920.cybersoft.com.assigment.bean;


import com.google.gson.annotations.SerializedName;

public class ZooArea {
    int id;

    @SerializedName("E_Name")
    String name;
    @SerializedName("E_Info")
    String info;
    @SerializedName("E_Category")
    String category;
    @SerializedName("E_Pic_URL")
    String imgUrl;
    @SerializedName("E_Memo")
    String memo;

    public ZooArea() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }


}
