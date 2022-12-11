module com.cookos {
    //requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires lombok;
    requires org.hibernate.orm.core;
    requires org.apache.logging.log4j;
    requires mysql.connector.j;


    opens com.cookos to javafx.fxml;
    opens com.cookos.Controllers to javafx.fxml;
    opens com.cookos.Entities to org.hibernate.orm.core, javafx.fxml;
    exports com.cookos;
    exports com.cookos.Controllers;
    exports com.cookos.Entities;
}
