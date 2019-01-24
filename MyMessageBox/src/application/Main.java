package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FlowPane root = new FlowPane();
			
			Button redBtn = new Button();
			redBtn.setText("RED");
			root.getChildren().add(redBtn);
			FlowPane.setMargin(redBtn, new Insets(20));
			
			Button greenBtn = new Button();
			greenBtn.setText("GREEN");
			root.getChildren().add(greenBtn);
			FlowPane.setMargin(greenBtn, new Insets(20));
			
			Button blueBtn = new Button();
			blueBtn.setText("BLUE");
			root.getChildren().add(blueBtn);
			FlowPane.setMargin(blueBtn, new Insets(20));
			
			Label colorLbl = new Label();
			colorLbl.setText("Kolor");
			root.getChildren().add(colorLbl);
			FlowPane.setMargin(colorLbl, new Insets(20));
			
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Okienka by KJ");
			primaryStage.show();

			redBtn.setOnAction((ActionEvent) -> {
				
				String redBlueGreen = MyDialog.Show("Kolor zostanie zmieniony na czerwony. Czy kontynuowaæ?", "Kolor", "CriticalError", "YesNo");
				System.out.println(redBlueGreen);
				if (redBlueGreen == "Wciœniêto Tak"){
					colorLbl.setStyle("-fx-background-color: #ff0000");
				}
			
			});
			
			greenBtn.setOnAction((ActionEvent) -> {
				
				String redBlueGreen = MyDialog.Show("Uwaga nast¹pi zmiana koloru. Kolor zostanie zmieniony na zielony.", "Kolor", "Alert", "YesNoCancel");
				System.out.println(redBlueGreen);
				if (redBlueGreen == "Wciœniêto Tak"){
					colorLbl.setStyle("-fx-background-color: #00ff00");
				}
			
			});
			
			blueBtn.setOnAction((ActionEvent) -> {
				
				String redBlueGreen = MyDialog.Show("Kolor zostanie zmieniony na niebeski.", "Kolor", "Warning", "OkCancel");
				System.out.println(redBlueGreen);
				if (redBlueGreen == "Wciœniêto OK"){
					colorLbl.setStyle("-fx-background-color: #0000ff");
				}
			
			});
			
			String test = MyDialog.Show("To jest program z klas¹ okienek do wyœwietlania komunikatów.", "Okienka by KJ", "Information", "OK");
			System.out.println(test);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

class MyDialog extends Stage {

	public static String resultText;
	
	public MyDialog(String text,String title, String icon, String btns) {
		super();

		MessageBoxButton btn = MessageBoxButton.valueOf(btns);
		MessageBoxIcon img = MessageBoxIcon.valueOf(icon);

		VBox rootPane = new VBox();
		HBox paneTop = new HBox();
		HBox paneBottom = new HBox();
		paneTop.setAlignment(Pos.CENTER);
		paneBottom.setAlignment(Pos.CENTER);
		rootPane.setAlignment(Pos.CENTER);
		
		rootPane.setBorder(null);
		Image image = new Image(getClass().getResourceAsStream(img.getImg() + ".png"));
		ImageView imageView = new ImageView(image);
		paneTop.getChildren().add(imageView);
		HBox.setMargin(imageView, new Insets(10));

		Label lbl = new Label(text);
		lbl.setPrefWidth(400);
		lbl.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
		lbl.setWrapText(true);
		lbl.setTextAlignment(TextAlignment.JUSTIFY);
		paneTop.getChildren().add(lbl);
		HBox.setMargin(lbl, new Insets(10));

		for (int i = 0; i < btn.getCount(); i++) {
			Button button = new Button();
			button.setText(btn.getText(i));
			paneBottom.getChildren().add(button);
			button.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
			button.setMinWidth(100);
			HBox.setMargin(button, new Insets(20));

			/*
			 if (btnNumber.getText() == "Tak") {
				btnNumber.setDefaultButton(true);
				Image btnImage = new Image(getClass().getResourceAsStream("StatusOK_32x.png"));
				ImageView imageViewYes = new ImageView(btnImage);
				btnNumber.setGraphic(imageViewYes);
				btnNumber.setGraphicTextGap(10);
			}
			if (btnNumber.getText() == "Nie") {
				btnNumber.setCancelButton(true);
				Image btnImage = new Image(getClass().getResourceAsStream("StatusNo_32xLG.png"));
				ImageView imageViewNo = new ImageView(btnImage);
				btnNumber.setGraphic(imageViewNo);
				btnNumber.setGraphicTextGap(10);
			}
			*/

			button.setOnAction((ActionEvent) -> {
				MessageBoxResult result = MessageBoxResult.valueOf(button.getText());
				resultText = result.getResult();
				close();
			});
		}

		rootPane.getChildren().addAll(paneTop, paneBottom);

		Scene scene = new Scene(rootPane);
		setScene(scene);
		setTitle(title);
		
		scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent> () {

		        public void handle(KeyEvent t) {
		          if(t.getCode()==KeyCode.ESCAPE)
		          {
		        	  resultText = "ESC";
		        	  close();
		          }
		        }
		    });
		
	}
	
	public static String Show(String text,String title, String icon, String btns) {
		Stage myDialog = new MyDialog(text, title, icon,btns);
		myDialog.setResizable(false);
		myDialog.initModality(Modality.APPLICATION_MODAL);
		myDialog.showAndWait();
		return resultText;
	}
	
}
