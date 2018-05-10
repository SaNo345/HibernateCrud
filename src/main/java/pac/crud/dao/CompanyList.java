package pac.crud.dao;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import pac.crud.model.Company;

public class CompanyList {
	static Session sessionObj;
    public final static Logger logger = Logger.getLogger(CompanyList.class);

	
	public List<Company> getAllCompany(){
		
			List companyList= new ArrayList<Company>();
		try{
			sessionObj =ConnectionDb.buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();
			
			companyList= sessionObj.createQuery("FROM Company").list();
		} catch(Exception sqlException) {
			if(null != sessionObj.getTransaction()) {
				logger.info("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if(sessionObj != null) {
				sessionObj.close();
			}
		}
		return companyList;
			
		}

	  public void addCompany(Company company){
		  if(company ==null){
			  return;
		  }
		  try{
				sessionObj =ConnectionDb.buildSessionFactory().openSession();
				// Getting Transaction Object From Session Object
				sessionObj.beginTransaction();
				sessionObj.save(company);
				sessionObj.getTransaction().commit();
				logger.info("\nSuccessfully Add Company '" + company.getName() + "'  In The Database!\n");
	  }catch(Exception sqlException) {
			if(null != sessionObj.getTransaction()) {
				logger.info("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if(sessionObj != null) {
				sessionObj.close();
			}
		}
	  }
	  public void deleteCompany(Integer companyID){
		  if(companyID ==null){
			  return ;
		  }
		  Company company = findCompanyById(companyID);
		  try{
			  sessionObj =ConnectionDb.buildSessionFactory().openSession();
			  // Getting Transaction Object From Session Object
			  sessionObj.beginTransaction();
				if(company == null){
					logger.info("\n Delete Feild ,Not Company by Id '" + companyID + "'  In The Database!\n");
				   return;
				}
				sessionObj.delete(company);
				sessionObj.getTransaction().commit();
				logger.info("\nSuccessfully Delete Company by Id '" + companyID + "'  In The Database!\n");
	  }catch(Exception sqlException) {
			if(null != sessionObj.getTransaction()) {
				logger.info("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if(sessionObj != null) {
				sessionObj.close();
			}
		}
	  }
	  // Method do not close  session !!
	  public Company findCompanyById(Integer companyId){
		  if(companyId == null){
			  return null;
		  }
		  Company findCompany=null;
		  try{
				sessionObj =ConnectionDb.buildSessionFactory().openSession();
				// Getting Transaction Object From Session Object
				sessionObj.beginTransaction();
				findCompany	= (Company)sessionObj.get(Company.class,companyId);
				if(findCompany != null){
					logger.info("\nSuccessfully Find Company by Id '" + companyId + "'  In The Database!\n");
				}else{
					logger.info("\n Company by Id '" + companyId + "' Not Find In The Database!\n");
                   
				}
	  }catch(Exception sqlException) {
			if(null != sessionObj.getTransaction()) {
				logger.info("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		}
		  finally {
			if(sessionObj != null) {
				sessionObj.close();
			}
		}
		  return findCompany;
	  }
	  public void updateCompany(Company company){
		  try{
			  sessionObj =ConnectionDb.buildSessionFactory().openSession();
			  // Getting Transaction Object From Session Object
			  sessionObj.beginTransaction();
			  sessionObj.update(company);
			  sessionObj.getTransaction().commit();
			  logger.info("\nSuccessfully Update Company by Id '" + company.getCompanyid() + "'  In The Database!\n");

		  } catch(Exception sqlException) {
			  if(null != sessionObj.getTransaction()) {
				  logger.info("\n.......Transaction Is Being Rolled Back.......\n");
				  sessionObj.getTransaction().rollback();
			  }
			  sqlException.printStackTrace();
		  } finally {
			  if(sessionObj != null) {
				  sessionObj.close();
			  }
		  }

	  }
}
