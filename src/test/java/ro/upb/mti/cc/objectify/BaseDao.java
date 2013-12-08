package ro.upb.mti.cc.objectify;

import ro.upb.mti.cc.dao.ObjectifyGenericDao;

import javax.annotation.PostConstruct;

/**
 * @author Alexandru Burghelea (alexandru.george.burghelea@gmail.com)
 * @since 12/8/13 - 6:54 PM
 */
public class BaseDao extends ObjectifyGenericDao<Base> {
    @PostConstruct
    protected void init() {
        super.init(Base.class);
    }
}
