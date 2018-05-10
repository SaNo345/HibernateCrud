package pac.crud.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by sano on 4/8/18.
 */
@Entity
@Table(name = "company")
public class Company {
    private int companyid;
    private String name;
    private String hvhh;
    private Double areaofspace;
    private String factualAddress;



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "companyid")
    public int getCompanyid() {
        return companyid;
    }

    public void setCompanyid(int companyid) {
        this.companyid = companyid;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "hvhh")
    public String getHvhh() {
        return hvhh;
    }

    public void setHvhh(String hvhh) {
        this.hvhh = hvhh;
    }

    @Column(name = "areaofspace")
    public Double getAreaofspace() {
        return areaofspace;
    }

    public void setAreaofspace(Double areaofspace) {
        this.areaofspace = areaofspace;
    }

    @Column(name = "factualAddress")
    public String getFactualAddress() {
        return factualAddress;
    }

    public void setFactualAddress(String factualAddress) {
        this.factualAddress = factualAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (companyid != company.companyid) return false;
        if (name != null ? !name.equals(company.name) : company.name != null) return false;
        if (hvhh != null ? !hvhh.equals(company.hvhh) : company.hvhh != null) return false;
        if (areaofspace != null ? !areaofspace.equals(company.areaofspace) : company.areaofspace != null) return false;
        if (factualAddress != null ? !factualAddress.equals(company.factualAddress) : company.factualAddress != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = companyid;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (hvhh != null ? hvhh.hashCode() : 0);
        result = 31 * result + (areaofspace != null ? areaofspace.hashCode() : 0);
        result = 31 * result + (factualAddress != null ? factualAddress.hashCode() : 0);
        return result;
    }

    private Set<Order> orders;

    @OneToMany(mappedBy = "company",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
