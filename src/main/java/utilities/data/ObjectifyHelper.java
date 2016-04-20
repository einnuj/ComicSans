package utilities.data;

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
            .type
            (tClass).id(id).now(); }
}
