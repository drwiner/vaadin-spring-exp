package org.vaadin.example;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;

import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
//@ComponentScan("org.vaadin.example.repositories")
@ComponentScan("org.vaadin.example.data.repository")
@PWA(name = "Project Base for Vaadin with Spring", shortName = "Project Base", offlineResources = {"images/logo.png"})
public class Application extends SpringBootServletInitializer implements AppShellConfigurator {

    private static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
//        applicationContext = SpringApplication.run(Application.class, args);
        SpringApplication.run(Application.class, args);
//        checkBeansPresence("CompanyRepository");
    }

//    private static void checkBeansPresence(String... beans) {
//        for (String beanName : beans) {
//            System.out.println("Is " + beanName + " in ApplicationContext: " +
//                    applicationContext.containsBean(beanName));
//        }
//    }

}
