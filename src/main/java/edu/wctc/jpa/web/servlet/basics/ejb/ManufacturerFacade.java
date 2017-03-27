package edu.wctc.jpa.web.servlet.basics.ejb;

import edu.wctc.jpa.web.servlet.basics.model.Manufacturer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * This class is an imlementation of the AbstractFacade, typed to handle
 * Manufacturer entities. It is also a Stateless EJB Session Bean. EJBs have
 * many sophisticated features, but for this demo it's the ability to
 * auto handle JTA transactions (the EJB container does this).
 * 
 * @author jlombardo
 */
@Stateless
public class ManufacturerFacade extends AbstractFacade<Manufacturer> {
    @PersistenceContext(unitName = "sample_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ManufacturerFacade() {
        super(Manufacturer.class);
    }
    
    public List<Manufacturer> getManufacturerByState(String state) {
//        //Write the query first:
//        String jpql = "SELECT m FROM Manufacturer m WHERE m.state = :state";
//        //Create a query object:
//        TypedQuery<Manufacturer> query = getEntityManager().createQuery(jpql, Manufacturer.class);
//        //Set your parameters:
//        query.setParameter("state", state);
//        //Return your result set
        
        //Write the query first:
        String jpql = "SELECT m FROM Manufacturer m WHERE m.state = ?1";
        //Create a query object:
        TypedQuery<Manufacturer> query = getEntityManager().createQuery(jpql, Manufacturer.class);
        //Set your parameters:
        query.setParameter(1, state);
        //Return your result set
        return query.getResultList();
    }
    
    public int deleteManufacturerById(String id){
        try{
            String jpql = "DELETE m FROM Manufacturer m WHERE m.manufacturerId = :manufacturerId";
            Query query = getEntityManager().createQuery(jpql);
            query.setParameter("manufacturerId", Integer.parseInt(id));
            return query.executeUpdate();    
        }catch(NumberFormatException ex){
            return -1;
        }
    }
    public int manufacturerToUpper(String id){
        try{
            String jpql = "UPDATE Manufacturer m set m.name = (Upper) m.name WHERE m.manufacturerId = :manufacturerId";
            Query query = getEntityManager().createQuery(jpql);
            query.setParameter("manufacturerId", Integer.parseInt(id));
            return query.executeUpdate();    
        }catch(NumberFormatException ex){
            return -1;
        }
    }
}
