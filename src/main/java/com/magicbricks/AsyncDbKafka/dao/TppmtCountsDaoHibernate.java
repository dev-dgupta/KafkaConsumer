package com.magicbricks.AsyncDbKafka.dao;


import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.magicbricks.AsyncDbKafka.domain.TppmtCounts;

@Repository
@Transactional
public class TppmtCountsDaoHibernate  implements TppmtCountsDao {

    private HibernateTemplate hibernateTemplate;

    @Autowired
    SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }


    public TppmtCounts findByDate(Date date, Long pmtrfnum) {

        String sb = "select counts from TppmtCounts counts where counts.id.pmtcountsrfnum=? and counts.id.countdate=?";
        Object[] paramValues = new Object[2];
        paramValues[0] = pmtrfnum;
        paramValues[1] = new java.sql.Date(date.getTime());

        List retval = hibernateTemplate.find(sb, paramValues);

        if (retval!=null && !retval.isEmpty()) {
            return (TppmtCounts) retval.get(0);
        }
        return null;
    }

    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public boolean updatePmtCounts(TppmtCounts tppmtCounts) {
        hibernateTemplate.update(tppmtCounts);
        return true;
    }

    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public boolean savePmtCounts(TppmtCounts tppmtcounts) throws DataAccessException {
        Object pkId = hibernateTemplate.save(tppmtcounts);
        return true;
    }

}
