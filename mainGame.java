//Made by OneDop
package myWordle;

import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class mainGame extends Application {
	int cnt=0;
	int num=0;
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage ps) throws Exception {
		ps.setTitle("404 Wordle");
		ps.setHeight(400);
		ps.setWidth(300);
		String word[] = {"water","pizza","quest","hotel","women","graph","nodes","house","ghost","gamer","black","chemo","apple","float","vinyl","quart","opera","zebra","amman","green","mouse","minus","river","sheet","paper","panda"};
		Random rand = new Random();
		num = rand.nextInt(word.length);
		Rectangle r1 = new Rectangle(0,0,100,100);
		r1.setArcHeight(10.0d);
	    r1.setArcWidth(10.0d);
	    HBox hb[] = new HBox[5];
		VBox vb = new VBox(10);
		vb.setAlignment(Pos.BOTTOM_CENTER);
		TextField txt = new TextField();
		txt.setAlignment(Pos.CENTER);
		Text lbl = new Text("WORDLE");
		lbl.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 25));
		VBox mainvb = new VBox(10,lbl,txt,vb);
		mainvb.setAlignment(Pos.TOP_CENTER);
		txt.setMaxWidth(100);
		Tooltip tooltip = new Tooltip("Please enter exactly 5 characters");
		txt.setTooltip(tooltip);
		Text warning = new Text("just 5 letters words!!");
		warning.setFill(Color.RED);
		Scene mainscene = new Scene(mainvb);
		Button replay = new Button("REPLAY");
		txt.setOnKeyPressed( event -> {
			  if( event.getCode() == KeyCode.ENTER )
			  {
				  String str = txt.getText().toLowerCase();
				  if(str.length() == 5)
				  {
					  if(mainvb.getChildren().contains(warning))
						  mainvb.getChildren().remove(warning);
					  hb[cnt]= new HBox(3);
					  hb[cnt].setAlignment(Pos.CENTER);
					  for(int i=0;i<word[num].length();i++) {
						  
						  if(str.charAt(i) == word[num].charAt(i))
							  hb[cnt].getChildren().add(box(str.charAt(i),Color.GREEN));
						  else if(word[num].contains(str.substring(i, i+1)))
							  hb[cnt].getChildren().add(box(str.charAt(i),Color.YELLOW));
						  else
							  hb[cnt].getChildren().add(box(str.charAt(i),Color.GRAY));
						  
					  }
					  vb.getChildren().add(hb[cnt]);
					  
					  cnt++;
					  if(cnt ==5 && !word[num].equals(txt.getText().toLowerCase()))
					  {
						  Alert alt = new Alert(AlertType.INFORMATION,"Looser!! the word is: "+word[num]);
						  alt.setY(ps.getY()+ps.getHeight());
						  alt.setX(ps.getX());
						  alt.setWidth(ps.getWidth());
						  alt.show();
						  mainvb.getChildren().addLast(replay); 
						  txt.setDisable(true);
						  
					  }
					  
					  if(word[num].equals(str))
					  {
						Alert alt = new Alert(AlertType.INFORMATION,"Winner");
						alt.setY(ps.getY()+ps.getHeight());
						alt.setX(ps.getX());
						alt.setWidth(ps.getWidth());
						alt.show();
						mainvb.getChildren().addLast(replay);
						txt.setDisable(true);
					  }
					  txt.clear();
				  }
				  else {
					  if(!mainvb.getChildren().contains(warning))
						  mainvb.getChildren().add(warning);
					  txt.clear();
				  }
				  
			  }
				  });
		replay.setOnAction(e ->{
			for(int i = 0;i<cnt;i++) {
			hb[i].getChildren().removeAll();
			vb.getChildren().remove(hb[i]);
			}
			cnt = 0;
			txt.setDisable(false);
			mainvb.getChildren().remove(replay);
			num = rand.nextInt(word.length);
			});
		ps.setScene(mainscene);
		ps.show();
	}
	
	public Pane box(char c, Color cl){
		Text t = new Text(""+c);
		Rectangle R = new Rectangle();
		R.setFill(cl);
		R.setHeight(40);
		R.setWidth(40);
		R.setArcHeight(10.0d);
	    R.setArcWidth(10.0d);
	    StackPane sp = new StackPane(R,t);
		return sp;
		
	}
}
