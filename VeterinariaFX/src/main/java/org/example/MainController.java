package org.example;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class MainController {

    private final ObservableList<Cliente> clientes = FXCollections.observableArrayList();

    @FXML
    private TextField nomeClienteField, telefoneField, nomePetField, especieField, racaField;
    @FXML
    private TableView<Cliente> clientesTable;
    @FXML
    private TableColumn<Cliente, String> nomeClienteColumn, telefoneColumn, petsColumn;
    @FXML
    private Button cadastrarClienteButton, cadastrarPetButton, removerClienteButton;

    @FXML
    private void initialize() {
        // Configurar colunas da tabela
        nomeClienteColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        telefoneColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefone()));
        petsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().listarPets()));

        clientesTable.setItems(clientes);

        // Desabilitar botões que dependem de seleção
        removerClienteButton.disableProperty().bind(clientesTable.getSelectionModel().selectedItemProperty().isNull());
        cadastrarPetButton.disableProperty().bind(clientesTable.getSelectionModel().selectedItemProperty().isNull());

        // Configurar evento de duplo clique na tabela
        clientesTable.setRowFactory(tv -> {
            TableRow<Cliente> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Cliente clienteSelecionado = row.getItem();
                    abrirDetalhes(clienteSelecionado);
                }
            });
            return row;
        });
    }

    @FXML
    private void cadastrarCliente() {
        if (nomeClienteField.getText().isEmpty() || telefoneField.getText().isEmpty()) {
            showAlert("Preencha todos os campos do cliente!");
            return;
        }
        Cliente cliente = new Cliente(nomeClienteField.getText(), telefoneField.getText());
        clientes.add(cliente);
        limparCamposCliente();
    }

    @FXML
    private void cadastrarPet() {
        Cliente clienteSelecionado = clientesTable.getSelectionModel().getSelectedItem();
        if (clienteSelecionado == null || nomePetField.getText().isEmpty() || especieField.getText().isEmpty() || racaField.getText().isEmpty()) {
            showAlert("Selecione um cliente e preencha todos os campos do pet!");
            return;
        }
        Pet pet = new Pet(nomePetField.getText(), especieField.getText(), racaField.getText());
        clienteSelecionado.adicionarPet(pet);
        clientesTable.refresh();
        limparCamposPet();
    }

    @FXML
    private void removerCliente() {
        Cliente clienteSelecionado = clientesTable.getSelectionModel().getSelectedItem();
        if (clienteSelecionado != null) {
            clientes.remove(clienteSelecionado);
            clientesTable.refresh();
        }
    }

    private void abrirDetalhes(Cliente cliente) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/detalhes.fxml"));
            Parent root = loader.load();

            DetalhesController controller = loader.getController();
            controller.setCliente(cliente);

            Stage stage = new Stage();
            stage.setTitle("Detalhes do Cliente");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erro ao abrir a tela de detalhes.");
        }
    }

    private void limparCamposCliente() {
        nomeClienteField.clear();
        telefoneField.clear();
    }

    private void limparCamposPet() {
        nomePetField.clear();
        especieField.clear();
        racaField.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING, message);
        alert.show();
    }
}
