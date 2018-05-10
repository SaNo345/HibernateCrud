package pac.crud.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by sano on 4/8/18.
 */
@Entity
@Table(name = "user")
public class User {
    private int userid;
    private String name;
    private String srname;
    private String patronymic;
    private String village;
    private String address;
    private Integer pepolecount;
    private Boolean activ;
    private Date enable_start;
    private Date enable_end;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userid")
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "srname")
    public String getSrname() {
        return srname;
    }

    public void setSrname(String srname) {
        this.srname = srname;
    }

    @Column(name = "patronymic")
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Column(name = "village")
    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "pepolecount")
    public Integer getPepolecount() {
        return pepolecount;
    }

    public void setPepolecount(Integer pepolecount) {
        this.pepolecount = pepolecount;
    }

    @Column(name = "activ")
    public Boolean getActiv() {
        return activ;
    }

    public void setActiv(Boolean activ) {
        this.activ = activ;
    }
    @Column(name = "disable_start")
    public Date getEnable_start() {
        return enable_start;
    }

    public void setEnable_start(Date enable_start) {
        this.enable_start = enable_start;
    }
    @Column(name = "disable_end")
    public Date getEnable_end() {
        return enable_end;
    }

    public void setEnable_end(Date enable_end) {
        this.enable_end = enable_end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userid != user.userid) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (srname != null ? !srname.equals(user.srname) : user.srname != null) return false;
        if (patronymic != null ? !patronymic.equals(user.patronymic) : user.patronymic != null) return false;
        if (village != null ? !village.equals(user.village) : user.village != null) return false;
        if (address != null ? !address.equals(user.address) : user.address != null) return false;
        if (pepolecount != null ? !pepolecount.equals(user.pepolecount) : user.pepolecount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userid;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (srname != null ? srname.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (village != null ? village.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (pepolecount != null ? pepolecount.hashCode() : 0);
        return result;
    }

    private Set<Order> orders;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
