package com.ncy.store.entity;

public class ShopCar extends BaseEntity{
    private Integer cid;
    private  Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShopCar shopCar)) return false;
        if (!super.equals(o)) return false;

        if (getCid() != null ? !getCid().equals(shopCar.getCid()) : shopCar.getCid() != null) return false;
        if (getUid() != null ? !getUid().equals(shopCar.getUid()) : shopCar.getUid() != null) return false;
        if (getPid() != null ? !getPid().equals(shopCar.getPid()) : shopCar.getPid() != null) return false;
        if (getPrice() != null ? !getPrice().equals(shopCar.getPrice()) : shopCar.getPrice() != null) return false;
        return getNum() != null ? getNum().equals(shopCar.getNum()) : shopCar.getNum() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getCid() != null ? getCid().hashCode() : 0);
        result = 31 * result + (getUid() != null ? getUid().hashCode() : 0);
        result = 31 * result + (getPid() != null ? getPid().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getNum() != null ? getNum().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ShopCar{" +
                "cid=" + cid +
                ", uid=" + uid +
                ", pid=" + pid +
                ", price=" + price +
                ", num=" + num +
                '}';
    }
}
