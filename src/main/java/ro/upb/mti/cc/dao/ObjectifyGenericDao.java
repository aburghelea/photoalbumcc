package ro.upb.mti.cc.dao;

/**
 * @author Alexandru Burghelea (alexandru.george.burghelea@gmail.com)
 * @since 12/8/13 - 6:22 PM
 */

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Generic DAO over Objectify
 * To correctly use this generic dao in a class and make sure that the
 * {@see ObjectifyGenericDao.init}  method is called. If you are using Spring a solution would be
 * {@see javax.annotation.PostConstruct} or {@see org.springframework.beans.factory.InitializingBean}
 *
 * @param <T> The class type of the Domain/DTO that the repository is built for.
 */
public abstract class ObjectifyGenericDao<T> {
    @Autowired
    private ObjectifyFactory objectifyFactory;
    private Objectify ofy;
    private Class<T> clazz;

    protected void init(Class<T> clazz) {
        ofy = objectifyFactory.begin();
        this.clazz = clazz;
    }

    public Key<T> put(T entity)

    {
        return ofy.save().entity(entity).now();
    }

    public Map<Key<T>, T> putAll(Iterable<T> entities) {
        return ofy.save().entities(entities).now();
    }

    public void delete(T entity) {
        ofy.delete().entity(entity).now();
    }

    public void deleteKey(Key<T> entityKey) {
        ofy.delete().key(entityKey).now();
    }

    public void deleteAll(Iterable<T> entities) {
        ofy.delete().entities(entities);
    }

    public void deleteKeys(Iterable<Key<T>> keys) {
        ofy.delete().keys(keys).now();
    }

    public T get(Long id) throws EntityNotFoundException {
        return ofy.load().type(clazz).id(id).now();
    }

    public T get(Key<T> key) throws EntityNotFoundException {
        return ofy.load().key(key).now();
    }

    public List<T> list() {
        return ofy.load().type(clazz).list();
    }
}