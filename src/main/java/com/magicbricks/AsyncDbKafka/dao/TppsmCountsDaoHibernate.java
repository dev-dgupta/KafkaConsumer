package com.magicbricks.AsyncDbKafka.dao;


import com.magicbricks.AsyncDbKafka.domain.TppsmCounts;
import com.magicbricks.AsyncDbKafka.domain.TppsmCountsId;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * @author Divya Gupta
 */
@Repository
@Transactional
public class TppsmCountsDaoHibernate  implements TppsmCountsDao {
    private HibernateTemplate hibernateTemplate;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    public TppsmCounts findByDate(Date date, Long psmrfnum) {

        String sb = "select counts from TppsmCounts counts where counts.id.psmcpsmrfnum=? and counts.id.countdate=?";
        Object[] paramValues = new Object[2];
        paramValues[0] = psmrfnum;
        paramValues[1] = new java.sql.Date(date.getTime());

        List retval = hibernateTemplate.find(sb, paramValues);
        if (retval!=null && !retval.isEmpty()) {
            return (TppsmCounts) retval.get(0);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public boolean updateTppsmCounts(TppsmCounts tppsmCounts) {
        hibernateTemplate.update(tppsmCounts);
        return true;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public boolean saveTppsmCounts(TppsmCounts tppsmCounts) {
        //customLogFactory.info("tppsmCounts saving ");
        TppsmCountsId pk = (TppsmCountsId) hibernateTemplate.save(tppsmCounts);
        //customLogFactory.info("TppsmCounts saved-- "+pk.getPsmcpsmrfnum());
        return true;
    }

}
