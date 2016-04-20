package utilities.data;

import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * A Helper Class that essentially wraps Objectify; can be used to do CRUD ops.
 * Created by einnuj on 4/12/2016.
 */
public class ObjectifyHelper {

    /* Methods */
    public <T> void delete(T entity) {
        ofy().delete().entity(entity).now();
    }

    public <T> void save(T entity) { ofy().save().entity(entity).now(); }

    public <T> T loadById(Class<T> tClass, Long id) { return (T) ofy().load()
            .type(tClass).id(id).now(); }

    public <T> List<T> loadWithEqualsFilter(Class<T> tClass, String fieldName, Object value) {
        return ofy().load().type(tClass).filter(fieldName, value).list();
    }
}
