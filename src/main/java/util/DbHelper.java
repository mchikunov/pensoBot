package util;

import model.Pensioner;
import model.User;
import model.Volunteer;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Statement;

public class DbHelper {
    private static final String hibernate_show_sql = "true";
    //  private static final String hibernate_hbm2ddl_auto = "create";
    private static final String hibernate_hbm2ddl_auto = "update";

    private Statement statement;


    private static SessionFactory sessionFactory;

    public DbHelper() {
        Configuration configuration = getMySqlConfiguration();
        sessionFactory = createSessionFactory(configuration);

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    @SuppressWarnings("UnusedDeclaration")
    private Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Volunteer.class);
        configuration.addAnnotatedClass(Pensioner.class);


        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/db_example");
//        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://146.120.82.46:3306/pensioner_db");
//        configuration.setProperty("hibernate.connection.verifyServerCertificate", "false");
        configuration.setProperty("hibernate.connection.requireSSL", "false");
        configuration.setProperty("hibernate.connection.useSSL", "false");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);

        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);

        return configuration;
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
