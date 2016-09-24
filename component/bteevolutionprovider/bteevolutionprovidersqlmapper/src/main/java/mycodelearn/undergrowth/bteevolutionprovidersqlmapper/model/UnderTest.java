package mycodelearn.undergrowth.bteevolutionprovidersqlmapper.model;

import java.math.BigDecimal;
import java.util.Date;

public class UnderTest {
    private BigDecimal id;

    private String uname;

    private String usex;

    private Short uage;

    private Date ubirthday;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getUsex() {
        return usex;
    }

    public void setUsex(String usex) {
        this.usex = usex == null ? null : usex.trim();
    }

    public Short getUage() {
        return uage;
    }

    public void setUage(Short uage) {
        this.uage = uage;
    }

    public Date getUbirthday() {
        return ubirthday;
    }

    public void setUbirthday(Date ubirthday) {
        this.ubirthday = ubirthday;
    }
}