package ro.upb.mti.cc.dao;

import ro.upb.mti.cc.domain.User;

import javax.annotation.PostConstruct;

/**
 * @author aburghelea
 * @since 1/8/14.
 */

public class UserDao extends ObjectifyGenericDao<User> {
    @PostConstruct
    protected void init() {
        super.init(User.class);
    }
}
