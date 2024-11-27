package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DetalhesController {

    private Cliente cliente;

    @FXML
    private Label nomeClienteLabel, telefoneLabel;
    @FXML
    private ListView<Pet> petsList;
    @FXML
    private TextField servicoField, dataField, horarioField;
    @FXML
    private Button agendarConsultaButton;
    @FXML
    private ListView<String> consultasList;

    private final ObservableList<String> consultas = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        consultasList.setItems(consultas);
        petsList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Pet item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNome() + " - " + item.getEspecie() + " (" + item.getRaca() + ")");
            }
        });
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        nomeClienteLabel.setText("Nome: " + cliente.getNome());
        telefoneLabel.setText("Telefone: " + cliente.getTelefone());

        petsList.getItems().setAll(cliente.getPets());
    }

    @FXML
    private void agendarConsulta() {
        Pet petSelecionado = petsList.getSelectionModel().getSelectedItem();
        if (petSelecionado == null) {
            showAlert("Selecione um pet para agendar a consulta.");
            return;
        }

        if (servicoField.getText().isEmpty() || dataField.getText().isEmpty() || horarioField.getText().isEmpty()) {
            showAlert("Preencha todos os campos para agendar a consulta.");
            return;
        }

        String consulta = "Pet: " + petSelecionado.getNome() +
                ", Serviço: " + servicoField.getText() +
                ", Data: " + dataField.getText() +
                ", Horário: " + horarioField.getText();
        consultas.add(consulta);

        servicoField.clear();
        dataField.clear();
        horarioField.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING, message);
        alert.show();
    }
}
