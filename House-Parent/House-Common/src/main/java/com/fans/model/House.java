package com.fans.model;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public class House {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 房产名称
     */
    private String name;

    /**
     * 1:销售，2:出租
     */
    private Integer type;

    /**
     * 单位元
     */
    private Integer price;

    /**
     * 图片地址
     */
    private String images;

    /**
     * 面积
     */
    private Integer area;

    /**
     * 卧室数量
     */
    private Integer beds;

    /**
     * 卫生间数量
     */
    private Integer baths;

    /**
     * 评级
     */
    private Double rating;

    /**
     * 房产描述
     */
    private String remarks;

    /**
     * 属性
     */
    private String properties;

    /**
     * 户型图
     */
    private String floorPlan;

    /**
     * 标签
     */
    private String tags;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 城市名称
     */
    private Integer cityId;

    /**
     * 小区名称
     */
    private Integer communityId;

    /**
     * 房产地址
     */
    private String address;

    /**
     * 1-上架，2-下架
     */
    private Integer state;

    private Integer roundRating = 0;

    private String firstImg;

    private List<String> imageList = Lists.newArrayList();

    private List<String> floorPlanList = Lists.newArrayList();

    private List<String> featureList = Lists.newArrayList();

    private List<MultipartFile> houseFiles;

    private List<MultipartFile> floorPlanFiles;

    private String priceStr;

    private String typeStr;

    private Long userId;

    private boolean bookmarked;

    private List<Long> ids;
    //price_desc,price_asc,time_desc
    private String sort = "time_desc";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
        if (type.equals(1)) {
            this.typeStr = "For Sale";
        } else {
            this.typeStr = "For Rent";
        }
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
        this.priceStr = this.price + "万";
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getBaths() {
        return baths;
    }

    public void setBaths(Integer baths) {
        this.baths = baths;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
        this.featureList = Splitter.on(",").splitToList(properties);
    }

    public String getFloorPlan() {
        return floorPlan;
    }

    public void setFloorPlan(String floorPlan) {
        this.floorPlan = floorPlan;
        if (!Strings.isNullOrEmpty(floorPlan)) {
            this.floorPlanList = Splitter.on(",").splitToList(floorPlan);
        }

    }

    public boolean isBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        this.bookmarked = bookmarked;
    }

    public String getTags() {
        return tags;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getState() {
        return state;
    }

    public String getFirstImg() {
        return firstImg;
    }

    public void setFirstImg(String firstImg) {
        this.firstImg = firstImg;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
        if (!Strings.isNullOrEmpty(images)) {
            List<String> list = Splitter.on(",").splitToList(images);
            if (list.size() > 0) {
                this.firstImg = list.get(0);
                this.imageList = list;
            }
        }
    }

    public List<MultipartFile> getFloorPlanFiles() {
        return floorPlanFiles;
    }

    public void setFloorPlanFiles(List<MultipartFile> floorPlanFiles) {
        this.floorPlanFiles = floorPlanFiles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriceStr() {
        return priceStr;
    }

    public void setPriceStr(String priceStr) {
        this.priceStr = priceStr;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public Integer getBeds() {
        return beds;
    }

    public void setBeds(Integer beds) {
        this.beds = beds;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getFloorPlanList() {
        return floorPlanList;
    }

    public void setFloorPlanList(List<String> floorPlanList) {
        this.floorPlanList = floorPlanList;
    }

    public List<String> getFeatureList() {
        return featureList;
    }

    public void setFeatureList(List<String> featureList) {
        this.featureList = featureList;
        this.properties = Joiner.on(",").join(featureList);
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
        this.roundRating = (int) Math.round(rating);
    }

    public Integer getRoundRating() {
        return roundRating;
    }

    public void setRoundRating(Integer roundRating) {
        this.roundRating = roundRating;
    }

    public List<MultipartFile> getHouseFiles() {
        return houseFiles;
    }

    public void setHouseFiles(List<MultipartFile> houseFiles) {
        this.houseFiles = houseFiles;
    }


    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", images='" + images + '\'' +
                ", area=" + area +
                ", beds=" + beds +
                ", baths=" + baths +
                ", rating=" + rating +
                ", remarks='" + remarks + '\'' +
                ", properties='" + properties + '\'' +
                ", floorPlan='" + floorPlan + '\'' +
                ", tags='" + tags + '\'' +
                ", createTime=" + createTime +
                ", cityId=" + cityId +
                ", communityId=" + communityId +
                ", address='" + address + '\'' +
                ", state=" + state +
                ", roundRating=" + roundRating +
                ", firstImg='" + firstImg + '\'' +
                ", imageList=" + imageList +
                ", floorPlanList=" + floorPlanList +
                ", featureList=" + featureList +
                ", houseFiles=" + houseFiles +
                ", floorPlanFiles=" + floorPlanFiles +
                ", priceStr='" + priceStr + '\'' +
                ", typeStr='" + typeStr + '\'' +
                ", userId=" + userId +
                ", bookmarked=" + bookmarked +
                ", ids=" + ids +
                ", sort='" + sort + '\'' +
                '}';
    }
}