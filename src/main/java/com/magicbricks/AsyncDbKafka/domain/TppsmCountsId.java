package com.magicbricks.AsyncDbKafka.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * @author Divya Gupta
 */
@Embeddable
public class TppsmCountsId implements Serializable {

    /**
     * Serial Id
     */
    private static final long serialVersionUID = 1L;

    private Long psmcpsmrfnum;
    private Date countdate;

    public Long getPsmcpsmrfnum() {
        return psmcpsmrfnum;
    }

    public void setPsmcpsmrfnum(Long psmcpsmrfnum) {
        this.psmcpsmrfnum = psmcpsmrfnum;
    }

    public Date getCountdate() {
        return countdate;
    }

    public void setCountdate(Date countdate) {
        this.countdate = countdate;
    }

    public TppsmCountsId(Long psmcpsmrfnum, Date countdate) {
        this.psmcpsmrfnum = psmcpsmrfnum;
        this.countdate = countdate;
    }

    public TppsmCountsId() {
    }
}
