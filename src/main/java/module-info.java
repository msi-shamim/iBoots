module com.incrementsinc.services.iboots {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.incrementsinc.services.iboots to javafx.fxml;
    exports com.incrementsinc.services.iboots;
}
