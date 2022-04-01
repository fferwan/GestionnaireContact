package ModelHibernate;

import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

public class SFGest{

	private static SessionFactory sF;
	
	public static SessionFactory getSF() {
		if(sF == null) {
			sF = new Configuration().configure("hibernate.cfg.xml")
					.addAnnotatedClass(Contact.class)
					.addAnnotatedClass(Utilisateur.class)
					.buildSessionFactory();
		}
		return sF;
	}
}