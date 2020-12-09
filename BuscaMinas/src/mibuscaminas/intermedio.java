package mibuscaminas;
//Le librerias xdxd
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class intermedio extends JPanel implements ActionListener{
	
    //Le variables
    //Estas son las casillas que faltan por descubrir
    private int casillasFaltantes = 0;
    //Las minitas 
    private int minas = 20;
    //Le array
    //Le valores del nivel en un arraycito
    int valoresIntermedio[][] = new int[minas][minas];
    public JLabel labelI;	
    private JButton botonesIntermedio[][] = new JButton[minas][minas];
    //Es la carita, la que reinicia :3
    public JButton btnreiniciar;
    //Las imagenes del tablero uwu
    public String[] archi = {"/imagenes/gano.png", "/imagenes/perdio.png", "/imagenes/nueva.png"};
    private String archivos[] = {"/imagenes/0.PNG", "/imagenes/1.PNG", "/imagenes/2.PNG",
        "/imagenes/3.PNG", "/imagenes/4.PNG", "/imagenes/5.PNG", "/imagenes/6.PNG", 
        "/imagenes/7.PNG", "/imagenes/8.PNG", "/imagenes/9.PNG"};


    private ImageIcon[] imagenes = new ImageIcon[10];    
    public ImageIcon[] ima = new ImageIcon[3];   
    
    //Al iniciar el juego es que esta cosita se hace visible
    private boolean visibleIntermedio[][] = new boolean[minas][minas];
    
     //Abre el cosito uwu
	public intermedio() {
		this.setLayout(null);
		for(int i=0;i<3;i++){
            ima[i] = new ImageIcon(getClass().getResource(archi[i]));
        }
		nuevaPartidaIntermedio();        
		this.setSize(500, 490);
                
                //Boton de la carita
		btnreiniciar = new JButton();
		btnreiniciar.setBounds(186, 5, 30, 30);
    //Aqui es donde le asignamos al boton la imagen de la carita :3
    btnreiniciar.setIcon(ima[2]);                
        this.add(btnreiniciar);
        this.btnreiniciar.addActionListener(this);
		labelI = new JLabel();
		labelI.setBounds(15, 15, 60, 15);
		this.add(labelI);
	}
        //Reinicia la partida
	public void nuevaPartidaIntermedio(){        
        casillasFaltantes = 0;        
        ponerBotonesIntermedio();
        verIntermedio(false);
        ponerMinasIntermedio();
        contornoIntermedio();
        visualizarMinasIntermedio();
        eventosIntermedio();
    }
	
	public void ponerBotonesIntermedio(){        
        for(int i=0;i<10;i++){
            imagenes[i] = new ImageIcon(getClass().getResource(archivos[i]));
        }
        
        for(int f = 0; f<minas; f++){
        for(int c = 0; c<minas; c++){
            botonesIntermedio[f][c] = new JButton();
            botonesIntermedio[f][c].setBounds(20*f, 20*c+40,20, 20);
            botonesIntermedio[f][c].setBackground(Color.gray);
            //el evento del boton
            this.add(botonesIntermedio[f][c]);
        }
        }        
    }
	//Pone las minitas cute super destructivas
	public void ponerMinasIntermedio(){
        for(int f=0;f<minas;f++){
        for(int c=0;c<minas;c++){
            valoresIntermedio[f][c]=0;
        }
        }
           int f1, c1;
        for ( int i=0;i<2*minas;i++){
            do{
                //Esto pone las minas de mandera random
                f1=(int)(Math.random()*minas);
                c1=(int)(Math.random()*minas);
            }while(valoresIntermedio[f1][c1]!=0);
            valoresIntermedio[f1][c1]=9;
        }
    }
	
	public void contornoIntermedio(){
        for(int f=0;f<minas;f++){
            for(int c=0;c<minas;c++){
                if(valoresIntermedio[f][c]==9){
                    for(int f2=f-1;f2<=f+1;f2++){
                        for(int c2=c-1;c2<=c+1;c2++){
                            if(f2>=0 && f2<minas && c2>=0 && c2<minas && valoresIntermedio[f2][c2]!=9)
                                valoresIntermedio[f2][c2]++;
                        }
                      }
                   }
                }
            }
        }
	
	public void verIntermedio(boolean valor){
        for(int f=0;f<minas;f++){
        for(int c=0;c<minas;c++){
            visibleIntermedio[f][c]=valor;
        }
        }
    }
	
	public void pulsarBotonVasIntermedio(int f, int c){
            //Hace que cuando toques una mina ¡BUM! Pierdas, pero tambien que ganes al despejar el tablero
	        if(f>=0 && f<minas && c>=0 && c<minas && visibleIntermedio[f][c]==false){
	            if(visibleIntermedio[f][c]==false){
	                visibleIntermedio[f][c]=true;
	                if(valoresIntermedio[f][c]==9){
	                    verIntermedio(true);
	                    JOptionPane.showMessageDialog(null, ":C Perdiste");
	                    btnreiniciar.setIcon(ima[1]);}
	            else if(visibleIntermedio[f][c]==true){
	                casillasFaltantes++;
	                if (casillasFaltantes == 360){
	                    verIntermedio(true);
	                    JOptionPane.showMessageDialog(null, "YOU WIN MAN :3");
	                    btnreiniciar.setIcon(ima[0]);
	                    labelI.setText("");
	                }
                        //Esto muestra en pantalla, cuantas casillas faltan
	                labelI.setText(casillasFaltantes+"/360");
	            }
	            }
	            if(valoresIntermedio[f][c]==0){
	                pulsarBotonVasIntermedio(f, c-1);
	                pulsarBotonVasIntermedio(f, c+1);
	                pulsarBotonVasIntermedio(f-1, c);
	                pulsarBotonVasIntermedio(f+1, c);
	            }
	        }
	    }
	 
	public void pulsarBotonIntermedio(int f, int c) {
	        pulsarBotonVasIntermedio(f,c);
	        visualizarMinasIntermedio();
	    }
	 
	public void eventosIntermedio(){
	        for(int f=0;f<minas;f++){
	        for(int c=0;c<minas;c++){
	            botonesIntermedio[f][c].addActionListener(
	            new ActionListener(){
	                public void actionPerformed(ActionEvent e){
	                    for(int f=0;f<minas;f++){
	                    for(int c=0;c<minas;c++){
	                        if(e.getSource()==botonesIntermedio[f][c])
	                            pulsarBotonIntermedio(f,c);
	                    }
	                    }
	                }
	                }
	            );
	        }
	        }
	    }
	//Pone todos los numeros en diferentes lugares 
	public void visualizarMinasIntermedio(){
         for(int f=0;f<minas;f++){
         for(int c=0;c<minas;c++){
          if(visibleIntermedio[f][c]==false){
            botonesIntermedio[f][c].setText("");
          }else if(visibleIntermedio[f][c]==true){
            if(valoresIntermedio[f][c]==0){
            botonesIntermedio[f][c].setIcon(imagenes[0]);
            }else if(valoresIntermedio[f][c]==1){
            botonesIntermedio[f][c].setIcon(imagenes[1]);
            }else if(valoresIntermedio[f][c]==2){
            botonesIntermedio[f][c].setIcon(imagenes[2]);
            }else if(valoresIntermedio[f][c]==3){
            botonesIntermedio[f][c].setIcon(imagenes[3]);
            }else if(valoresIntermedio[f][c]==4){
            botonesIntermedio[f][c].setIcon(imagenes[4]);
            }else if(valoresIntermedio[f][c]==5){
            botonesIntermedio[f][c].setIcon(imagenes[5]);
            }else if(valoresIntermedio[f][c]==6){
            botonesIntermedio[f][c].setIcon(imagenes[6]);
            }else if(valoresIntermedio[f][c]==7){
            botonesIntermedio[f][c].setIcon(imagenes[7]);
            }else if(valoresIntermedio[f][c]==8){
            botonesIntermedio[f][c].setIcon(imagenes[8]);
            }else if(valoresIntermedio[f][c]==9)
            botonesIntermedio[f][c].setIcon(imagenes[9]);
           }
         }
        }
    }
	//Rehace el tablero
	public void quitarBotonesIntermedio(){
         for(int f1 = 0; f1<minas; f1++){
                for(int c1 = 0; c1<minas; c1++){
                    this.remove(botonesIntermedio[f1][c1]);
                }
    }
  }
       //Reinicia (Agarra les propiedades xd)
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnreiniciar){
			btnreiniciar.setIcon(ima[2]);
            quitarBotonesIntermedio();
            this.setVisible(false);            
            labelI.setText("");
			nuevaPartidaIntermedio();
			this.setVisible(true);
		}	
	}
}
