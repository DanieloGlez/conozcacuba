package ui.controller.datamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.RelationContractHotelRoomTypeFoodPlanSeasonDto;
import dto.RelationContractServiceDailyActivityDto;
import dto.SeasonDto;
import dto.nom.DailyActivityDto;
import dto.nom.FoodPlanDto;
import dto.nom.RoomTypeDto;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.RelationContractHotelRoomFoodSeasonServices;
import service.RelationContractServiceDailyActivityServices;
import service.ServicesLocator;

import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

public class RoomTypePricesModal implements Initializable {
    private int counterTextFields = 0;
    private LinkedList<String> nameCheckComoboBoxprice;
    private VBox vbox_container;
    private JFXButton insert_button;
    private JFXTextField price_jfxtextfield;
    private int id;
    private List<RoomTypeDto> listNomenclator;
    private JFXButton button_insert;
    private List<JFXTextField> listTextField = new LinkedList<>();


    public void initializeNameTablePrices(List<RoomTypeDto> nameCheckComboBoxesItems,List<SeasonDto> seasons, List<FoodPlanDto> plans, int idContractHotel, Stage priceStage) {
        this.listNomenclator = nameCheckComboBoxesItems;
        this.id = idContractHotel;
        nameCheckComoboBoxprice = getNameNomenclatorDto();
        ListIterator listIterator = nameCheckComoboBoxprice.listIterator();
        vbox_container = new VBox();
        vbox_container.setPadding(new Insets(10,50,50,50));
        vbox_container.setSpacing(10);

        while (listIterator.hasNext()) {
            String currentName = (String) listIterator.next();
            price_jfxtextfield = new JFXTextField();
            price_jfxtextfield.setPromptText(currentName+" price");
            price_jfxtextfield.setId("price_jfxtextfield" + counterTextFields++);
            vbox_container.getChildren().add(price_jfxtextfield);
            listTextField.add(price_jfxtextfield);
        }

        button_insert= new JFXButton("Insert");
        button_insert.setOnAction(event -> {
            RelationContractHotelRoomFoodSeasonServices relationContractHotelRoomFoodSeasonServices = ServicesLocator.getRelationContractHotelRoomFoodSeasonServices();

            ListIterator<SeasonDto> listIteratorSeasons = seasons.listIterator();
            int counter = 0;

            while (listIteratorSeasons.hasNext()){
                SeasonDto currentSeasonDto = listIteratorSeasons.next();

                ListIterator<FoodPlanDto> listIteratorPlans = plans.listIterator();

                while (listIteratorPlans.hasNext()) {
                    FoodPlanDto currentFoodPlanDto = listIteratorPlans.next();

                    ListIterator<JFXTextField> listIteratorP = listTextField.listIterator();

                    while (listIteratorP.hasNext()){
                        float cost = Float.parseFloat(listIteratorP.next().getText());
                        try {
                            ServicesLocator.getRelationContractHotelRoomFoodSeasonServices().insert(
                                    new RelationContractHotelRoomTypeFoodPlanSeasonDto(
                                            idContractHotel,
                                            currentFoodPlanDto.getId(),
                                            nameCheckComboBoxesItems.get(counter++).getId(),
                                            currentSeasonDto.getId(),
                                            cost
                                    ));
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }

                    }
                }
            }

            ((Stage) vbox_container.getScene().getWindow()).close();
        });

        button_insert.setStyle(
                "-fx-text-fill: #468c82;" +
                        "-fx-border-color: #468c82;" +
                        "-fx-border-radius: 5%;"
                        + "-fx-start-margin: 20px;"

        );
        vbox_container.getChildren().add(button_insert);
        Scene pp= new Scene(vbox_container);
        priceStage.setScene(pp);
    }

    LinkedList<String> getNameNomenclatorDto() {
        LinkedList<String> resultList = new LinkedList();
        ListIterator<RoomTypeDto> listIterator = listNomenclator.listIterator();

        while (listIterator.hasNext())
            resultList.add(listIterator.next().getName());

        return resultList;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
