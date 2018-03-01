import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

/**
 * Created by yuanjie.fang on 2018/2/27.
 */
public class Students implements Serializable{

    private static final long serialVersionUID = 6880159551339915429L;
    private int sid;//学号
    private String sname;//姓名
    private String gender;//性别
    private Date birthday;//生日
    private Blob picture;//图片
    private Address address;//地址对象

    public Students() {
    }


    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Blob getPicture() {
        return picture;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Students{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", picture=" + picture +
                '}';
    }
}
